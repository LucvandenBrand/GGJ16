package com.snappycobra.ggj16.model;

import org.dyn4j.dynamics.Body;

import com.snappycobra.motor.graphics.Frame;
import com.snappycobra.motor.graphics.Sprite;

public class Oil extends Resource {
	
	String sound = "data/sounds/fx/oil.wav";
	String walkSprite = "data/images/creatures/player_walk_yellow_";
	String workSprite = "data/images/creatures/player_work_yellow_";
	
	public Oil() {
		name = "Oil";
		this.sprite = new Sprite(new Frame("data/images/Resources/resource-oil.png", 10));
	}
	
	public static String getResName() {
		return "Oil";
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
