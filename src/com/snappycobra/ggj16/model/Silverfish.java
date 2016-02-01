package com.snappycobra.ggj16.model;

import com.snappycobra.motor.graphics.Frame;
import com.snappycobra.motor.graphics.Sprite;

public class Silverfish extends Resource {
	
	String sound = "data/sounds/fx/silver.wav";
	String walkSprite = "data/images/Creatures/player_walk_blue_";
	String workSprite = "data/images/Creatures/player_work_blue_";
	
	public Silverfish() {
		name = "Silverfish";
		this.sprite = new Sprite(Frame.framesFromTileset("data/images/Resources/zilver_spritesheet.png", 10, 144, 144));
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
		return workSprite;
	}
}
