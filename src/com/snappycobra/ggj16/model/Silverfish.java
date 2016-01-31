package com.snappycobra.ggj16.model;

import com.snappycobra.motor.graphics.Frame;
import com.snappycobra.motor.graphics.Sprite;

public class Silverfish extends Resource {
	
	String sound = "data/sounds/fx/silver.wav";
	String walkSprite = "data/images/Creatures/player_walk_blue_";
	String workSprite = "data/images/Creatures/player_work_blue_";
	
	public Silverfish() {
		name = "Silverfish";
		this.sprite = new Sprite(new Frame("data/images/Resources/resource-silver.png", 10));
	}
	
	public static String getResName() {
		return "Silverfish";
	}
	
	public String getSound(){
		return sound;
	}
	
	public String getWalkSprite(){
		return walkSprite;
	}
	
	public String getWorkSprite(){
		return walkSprite;
	}
}
