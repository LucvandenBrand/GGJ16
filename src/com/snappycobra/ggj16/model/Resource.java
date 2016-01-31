package com.snappycobra.ggj16.model;

import org.dyn4j.dynamics.Body;

import com.snappycobra.motor.graphics.Sprite;
import com.snappycobra.motor.maps.GameObject;

public abstract class Resource{
	protected Sprite sprite;
	protected String name;
	
	public static Resource getResource(String resName) {
		switch (resName) {
		case "Oil":
			return new Oil();
			
		case "Gear":
			return new Gear();
			
		case "Uranium":
			return new Uranium();
			
		case "Silver":
			return new Silverfish();
		default:
			System.out.println("Not recognized:"+resName);
			break;
		}
		return null;
	}
	
	public static String getResName(){
		return "MAG NIET";
	}
	

	public abstract String getSound();

	public String getRealName(){
		return name;
	}
	
	public Sprite getSprite() {
		return this.sprite;
	}
}
