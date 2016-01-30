package com.snappycobra.ggj16.model;

import com.snappycobra.motor.graphics.Frame;
import com.snappycobra.motor.graphics.Sprite;

public class Silverfish extends Resource {
	
	public Silverfish() {
		this.sprite = new Sprite(new Frame("data/images/resource_silver.png", 10));
	}
	
	public static String getResName() {
		return "Silverfish";
	}
}
