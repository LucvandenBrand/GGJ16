package com.snappycobra.ggj16.model;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.AABB;

import com.snappycobra.motor.maps.GameObject;

public class Cursor {
	private double position;
	private boolean movingLeft, movingRight;
	private static final double SPEED=0.5;
	private double mapWidth;
	private Player owner;
	private WorldMap worldMap;
	private Unit selectedUnit;
	
	public Cursor(double mapWidth, Player player, WorldMap worldMap) {
		this.owner = player;
		this.worldMap = worldMap;
		this.mapWidth = mapWidth;
		this.movingLeft=false;
		this.movingRight=false;
		position = mapWidth/2;
	}
	
	public void click() {
		GameObject clicked = select();
		if (clicked != null) {
			doClicked(clicked);
		}
	}
	
	public void doClicked(GameObject go) {
		if (go instanceof Unit) {
			selectedUnit = (Unit) go;
		}
		else if (go instanceof ResourcePoint) {
			if (selectedUnit != null) {
				//Job newJob = new Gatherer(selectedUnit, (ResourcePoint) go);
				//selectedUnit.switchJob(newJob);
				System.out.println("give new job");
				System.out.println(go);
				//selectedUnit.setJob(new TestJob((ResourcePoint) go, selectedUnit));
				selectedUnit.setJob(new Gatherer(selectedUnit, (ResourcePoint) go ));
				selectedUnit = null;
			}
		} else if (go instanceof Shrine) {
			if (selectedUnit != null) {
				Shrine shrine = (Shrine) go;
				shrine.choseOffer(owner, selectedUnit);
			}
		}
	}
	
	public void update() {
		if (movingRight) {
			this.moveRight();
		} else if (movingLeft) {
			this.moveLeft();
		}
	}
	
	public void moveRight() {
		moveCursor(SPEED);
	}

	public GameObject select() {
		if (selectedUnit != null) {
			for(ResourcePoint rp : worldMap.getResourcePointList()) {
				if (inResSelection(rp.getBody())) {
					System.out.println("Resource Selected");
					return rp;
				}
			}
		}
		if (selectedUnit != null) {
			if (inShrineSelection(worldMap.getShrine().getBody())) {
				System.out.println("Shrine Selected");
				return worldMap.getShrine();
			}
		}
		for(Unit unit : owner.getUnitList()) {
			if (inUnitSelection(unit.getBody())) {
				System.out.println("UNIT Selected");
				return unit;
			}
		}
		if (selectedUnit == null) {
			if (inBaseSelection(owner.getBuildingList().get(0).getBody())) {
				System.out.println("Base Selected");
				return owner.getBuildingList().get(0);
			}
		}
		return null;
	}
	
	private boolean inResSelection(Body body) {
		AABB aabb = body.createAABB();
		double width = aabb.getWidth();
		double minX = body.getWorldCenter().x-width/2+7;
		double maxX = minX+4;
		return position > minX && position < maxX;
	}
	
	private boolean inShrineSelection(Body body) {
		AABB aabb = body.createAABB();
		double width = aabb.getWidth();
		double minX = body.getWorldCenter().x-width/2+8;
		double maxX = minX+7;
		return position > minX && position < maxX;
	}
	
	private boolean inUnitSelection(Body body) {
		AABB aabb = body.createAABB();
		double width = aabb.getWidth();
		double minX = body.getWorldCenter().x-width/2+11;
		double maxX = minX+4;
		return position > minX && position < maxX;
	}
	
	private boolean inBaseSelection(Body body) {
		AABB aabb = body.createAABB();
		double width = aabb.getWidth();
		double minX = body.getWorldCenter().x-width/2+9;
		double maxX = minX+14;
		return position > minX && position < maxX;
	}

	
	public void moveLeft() {
		moveCursor(-SPEED);
	}
	
	public void moveCursor(double speed) {
		if (position < 0) {
			position +=mapWidth;
		}
		position = (position+speed)%mapWidth;
	}

	public double getPosition() {
		return position;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}
	
	public WorldMap getWorldMap() {
		return this.worldMap;
	}
	
}
