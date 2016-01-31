package com.snappycobra.ggj16.model;

import org.dyn4j.dynamics.Body;

import com.snappycobra.motor.graphics.Frame;
import com.snappycobra.motor.graphics.Sprite;

public class Oil extends Resource {
	
	String sound = "data/sounds/fx/oil.wav";
	String walkSprite = "data/images/Creatures/player_walk_yellow_";
	String workSprite = "data/images/Creatures/player_work_yellow_";
	
	public Oil() {
		name = "Oil";
		//this.sprite = new Sprite(new Frame("data/images/Resources/resource-oil.png", 10));
		this.sprite = new Sprite(Frame.framesFromTileset("data/images/Resources/oil_spritesheet.png", 10, 144, 144));
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
