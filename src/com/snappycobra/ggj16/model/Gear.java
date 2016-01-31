package com.snappycobra.ggj16.model;

import com.snappycobra.motor.graphics.Frame;
import com.snappycobra.motor.graphics.Sprite;

public class Gear extends Resource {

	String sound = "data/sounds/fx/gears.wav";
	
	public Gear() {
		name = "Gear";
		this.sprite = new Sprite(new Frame("data/images/Resources/resource-gears.png", 10));
	}
	
	public static String getResName() {
		return "Gear";
	}
	
	public String getSound(){
		return sound;
	}
}
