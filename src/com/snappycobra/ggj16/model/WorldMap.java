package com.snappycobra.ggj16.model;

import java.util.ArrayList;
import java.util.List;

import com.snappycobra.motor.maps.GameObject;


public class WorldMap {
	private List<ResourcePoint> resourcePointList;
	private Shrine shrine;
	
	public WorldMap(List<GameObject> resources, Shrine shrine) {
		this.resourcePointList = new ArrayList<ResourcePoint>();
		for (GameObject res : resources) {
			resourcePointList.add((ResourcePoint) res);
		}
		this.shrine = shrine;
	}
	
	public List<ResourcePoint> getResourcePointList() {
		return resourcePointList;
	}
	
	public Shrine getShrine() {
		return shrine;
	}
}
