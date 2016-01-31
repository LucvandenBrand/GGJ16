package com.snappycobra.ggj16.model;

import com.snappycobra.motor.graphics.Frame;
import com.snappycobra.motor.graphics.Sprite;

public class Gear extends Resource {

	String sound = "data/sounds/fx/gears.wav";
	String walkSprite = "data/images/Creatures/player_walk_red_";
	String workSprite = "data/images/Creatures/player_work_red_";
	
	public Gear() {
		name = "Gear";
		//this.sprite = new Sprite(new Frame("data/images/Resources/resource-gears.png", 10));
		this.sprite = new Sprite(Frame.framesFromTileset("data/images/Resources/gears_spritesheet.png", 10, 144, 144));
	}
	
	public static String getResName() {
		return "Gear";
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
