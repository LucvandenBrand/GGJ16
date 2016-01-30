package com.snappycobra.ggj16.model;

import java.util.ArrayList;
import java.util.List;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Vector2;

import com.snappycobra.motor.maps.GameObject;
import com.snappycobra.motor.maps.Map;
import com.snappycobra.motor.maps.MapFactory;
import com.snappycobra.motor.model.Game;

public class GameModel extends Game {
	private WorldMap worldMap;
	private List<Player> playerList = new ArrayList<Player>();

	public GameModel(int amountPlayers) {
		MapFactory factory = new MapFactory();
		Map map = factory.loadMap("data/maps/test.tmx");
		this.setMap(map);
		
		worldMap = new WorldMap(map.getGameObjectsWithProp("resource"));
		addPlayers(amountPlayers, map, worldMap);
		
		Body unitBody = new Body();
		unitBody.shift(new Vector2(3,7));
		unitBody.addFixture(new Rectangle(1,1));
		Unit unit = new Unit("harry",unitBody,playerList.get(0));
		unit.addJob(new JobLess());
		playerList.get(0).addUnit(unit);
		/*unit = new Unit("harry",unitBody,players.get(0));
		unit.addJob(new JobLess());
		players.get(0).addUnit(unit);
		unit = new Unit("harry",unitBody,players.get(0));
		unit.addJob(new JobLess());
		players.get(0).addUnit(unit);
		unit = new Unit("harry",unitBody,players.get(1));
		unit.addJob(new JobLess());
		players.get(1).addUnit(unit);*/
	}
	
	private void addPlayers(int amountPlayers, Map map, WorldMap worldMap) {
		for (int i=0; i<amountPlayers; i++) {
			Player player = new Player(i,map);
			player.addCursor(new Cursor(map.getWidth(),player, worldMap));
			playerList.add(player);
		}
	}
	
	@Override
	public void tick() {
		for(Player player : playerList) {
			player.update();
		}
	}

	public List<Player> getPlayerList() {
		return playerList;
	}	
	
}
