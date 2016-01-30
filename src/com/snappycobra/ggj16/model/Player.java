package com.snappycobra.ggj16.model;

import java.util.ArrayList;
import java.util.List;

import org.dyn4j.dynamics.Body;

import com.snappycobra.motor.maps.GameObject;
import com.snappycobra.motor.maps.GameObjectGrabber;
import com.snappycobra.motor.maps.Map;

public class Player{
	private List<Unit> unitList = new ArrayList<Unit>();
	private List<Building> buildingList = new ArrayList<Building>();
	private List<ResourceAmount> resourceList = new ArrayList<ResourceAmount>();
	private Cursor cursor;
	
	public Player(int playerNumber, Map map) {
		fillBuildingList(playerNumber, map);
	}
	
	public void addResource(String resName, int amount) {
		for(ResourceAmount res : resourceList) {
			if (res.getResName().equals(resName)) {
				res.addAmount(amount);
			}
		}
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
}
