package com.snappycobra.ggj16.model;

import java.util.ArrayList;
import java.util.List;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Vector2;

import com.snappycobra.ggj16.Music.MusicPlayer;
import com.snappycobra.motor.maps.GameObject;
import com.snappycobra.motor.maps.Map;
import com.snappycobra.motor.maps.MapFactory;
import com.snappycobra.motor.model.Game;

public class GameModel extends Game {
	private WorldMap worldMap;
	private List<Player> playerList = new ArrayList<Player>();
	private MusicPlayer musicPlayer;
	private boolean showIntro;

	public GameModel(int amountPlayers) {
		MapFactory factory = new MapFactory();
		Map map = factory.loadMap("data/maps/test4.tmx");
		this.setMap(map);
		this.showIntro = true;
		
		worldMap = new WorldMap(map.getGameObjectsWithProp("resource"), (Shrine) map.getGameObjectsWithProp("shrine").get(0));
		addPlayers(amountPlayers, map, worldMap);
		Body unitBody = new Body();
		unitBody.shift(new Vector2(100,27));
		/*unitBody.addFixture(new Rectangle(5,1));*/
		Unit unit = new Unit("harry",unitBody,playerList.get(1), (Base) playerList.get(1).getBuildingList().get(0));
		unit.createBody();
		unit.setJob(new JobLess());
		playerList.get(1).addUnit(unit);
		unitBody = new Body();
		Unit unit2 = new Unit("harry",unitBody,playerList.get(0), (Base) playerList.get(0).getBuildingList().get(0));
		unit2.createBody();
		unitBody.shift(new Vector2(108,27));
		unit2.setJob(new JobLess());
		playerList.get(0).addUnit(unit2);
		/*unit = new Unit("harry",unitBody,playerList.get(0));
		unit.addJob(new JobLess());
		playerList.get(0).addUnit(unit);
		unit = new Unit("harry",unitBody,playerList.get(0));
		unit.addJob(new JobLess());
		playerList.get(0).addUnit(unit);
		unit = new Unit("harry",unitBody,playerList.get(1));
		unit.addJob(new JobLess());
		playerList.get(1).addUnit(unit);*/
		musicPlayer = new MusicPlayer();
		musicPlayer.start();
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
	
	public void setShowIntro(boolean show) {
		this.showIntro = show;
	}
	
	public boolean isShowIntro() {
		return this.showIntro;
	}
	
}
