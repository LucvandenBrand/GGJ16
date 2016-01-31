package com.snappycobra.ggj16.model;

import org.dyn4j.dynamics.Body;

import com.snappycobra.motor.graphics.Frame;
import com.snappycobra.motor.graphics.Sprite;

public class Oil extends Resource {
	
	public Oil() {
		this.sprite = new Sprite(new Frame("data/images/Resources/resource-oil.png", 10));
	}
	
	public static String getResName() {
		return "Oil";
	}
}
