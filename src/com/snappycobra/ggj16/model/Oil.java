package com.snappycobra.ggj16.model;

import org.dyn4j.dynamics.Body;

import com.snappycobra.motor.graphics.Frame;
import com.snappycobra.motor.graphics.Sprite;

public class Oil extends Resource {
	
	String sound = "data/sounds/fx/oil.wav";
	
	public Oil() {
		this.sprite = new Sprite(new Frame("data/images/Resources/resource-oil.png", 10));
	}
	
	public static String getResName() {
		return "Oil";
	}
	
	public String getSound(){
		return sound;
	}
}
