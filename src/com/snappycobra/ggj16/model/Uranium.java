package com.snappycobra.ggj16.model;

import com.snappycobra.motor.graphics.Frame;
import com.snappycobra.motor.graphics.Sprite;

public class Uranium extends Resource {
	
	public Uranium() {
		name = "Uranium";
		this.sprite = new Sprite(new Frame("data/images/Resources/resource-uranium.png", 10));
	}
	
	public static String getResName() {
		return "Uranium";
	}
}
