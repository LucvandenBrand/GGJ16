package com.snappycobra.ggj16.model;

import com.snappycobra.motor.graphics.Frame;
import com.snappycobra.motor.graphics.Sprite;

public class Uranium extends Resource {
	
	String sound = "data/sounds/fx/uranium.wav";
	String walkSprite = "data/images/Creatures/player_walk_green_";
	String workSprite = "data/images/Creatures/player_fly_green_";
	
	public Uranium() {
		name = "Uranium";
		this.sprite = new Sprite(new Frame("data/images/Resources/resource-uranium.png", 10));
	}
	
	public static String getResName() {
		return "Uranium";
	}
	
	public String getSound(){
		return sound;
	}
	
	public String getWalkSprite(){
		return walkSprite;
	}
	
	public String getWorkSprite(){
		return workSprite;
	}
}
