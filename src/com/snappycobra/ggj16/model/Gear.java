package com.snappycobra.ggj16.model;

import com.snappycobra.motor.graphics.Frame;
import com.snappycobra.motor.graphics.Sprite;

public class Gear extends Resource {

	public Gear() {
		this.sprite = new Sprite(new Frame("data/images/Resources/resource-gears.png", 10));
	}
	
	public static String getResName() {
		return "Gear";
	}
}
