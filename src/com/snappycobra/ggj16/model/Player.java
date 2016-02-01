package com.snappycobra.ggj16.model;

import java.util.ArrayList;
import java.util.List;

import org.dyn4j.dynamics.Body;

import com.snappycobra.ggj16.mastermind.Mastermind;
import com.snappycobra.motor.maps.GameObject;
import com.snappycobra.motor.maps.GameObjectGrabber;
import com.snappycobra.motor.maps.Map;

public class Player{
	private List<Unit> unitList = new ArrayList<Unit>();
	private List<Building> buildingList = new ArrayList<Building>();
	private List<ResourceAmount> resourceAmountList = new ArrayList<ResourceAmount>();
	private Cursor cursor;
	private Mastermind mastermind;
	int playerNumber;
	
	public Player(int playerNumber, Map map) {
		fillBuildingList(playerNumber, map);
		this.playerNumber = playerNumber;
		mastermind = new Mastermind();
		resourceAmountList.add(new ResourceAmount("Gear",5));
		resourceAmountList.add(new ResourceAmount("Oil",5));
		resourceAmountList.add(new ResourceAmount("Silver",5));
		resourceAmountList.add(new ResourceAmount("Uranium",5));
	}
	
	public void addResource(String resName, int amount) {
		for(ResourceAmount res : resourceAmountList) {
			if (res.getResName().equals(resName)) {
				res.addAmount(amount);
			}
		}
	}
	
	public boolean removeResource(String resName, int amount) {
		for(ResourceAmount res : resourceAmountList) {
			if (res.getResName().equals(resName)) {
				return res.useAmount(amount);
			}
		}
		return false;
	}
	
	public void fillBuildingList(int playerNumber, Map map) {
		for(Building building : new GameObjectGrabber<Building>().getObjects(map, Base.class)) {
			if (building.getPropertySet().getProperty("Player").equals(Integer.toString(playerNumber))) {
				buildingList.add(building);
			}
		}
	}
	
	public void update() {
		for(Unit unit : unitList) {
			unit.update();
		}
		cursor.update();
	}

	public void addCursor(Cursor cursor) {
		this.cursor = cursor;
	}
	
	public List<Unit> getUnitList() {
		return unitList;
	}

	public List<Building> getBuildingList() {
		return buildingList;
	}

	public Cursor getCursor() {
		return cursor;
	}
	
	public void addUnit(Unit unit) {
		unitList.add(unit);
	}
	
	public void playMastermind(Resource res) {
		if (mastermind.addResource(res)) {
			System.out.println("Je bent de ownende supah winner.");
		}
	}
	
	public List<ResourceAmount> getResourceAmountList() {
		return resourceAmountList;
	}

	public Mastermind getMastermind() {
		return this.mastermind;
	}
}
