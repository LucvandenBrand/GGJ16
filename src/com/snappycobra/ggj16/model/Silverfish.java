package com.snappycobra.ggj16.model;

import com.snappycobra.motor.graphics.Frame;
import com.snappycobra.motor.graphics.Sprite;

public class Silverfish extends Resource {
	
	String sound = "data/sounds/fx/silver.wav";
	
	public Silverfish() {
		this.sprite = new Sprite(new Frame("data/images/Resources/resource-silver.png", 10));
	}
	
	public static String getResName() {
		return "Silverfish";
	}
	
	public String getSound(){
		return sound;
	}
}
