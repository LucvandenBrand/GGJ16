package com.snappycobra.ggj16.model;

import com.snappycobra.motor.graphics.Frame;
import com.snappycobra.motor.graphics.Sprite;

public abstract class Job {
	protected Sprite sprite;
	protected Sprite workSprite;
	protected Unit owner;
	
	public abstract void update();
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public Sprite SelectGoodSprite(String imageUrl, Unit own) {
		System.out.println(imageUrl);
		return new Sprite(Frame.framesFromTileset(imageUrl+own.getOwner().playerNumber+".png", 17, 256, 256));
	}
	/*public Sprite SelectGoodWorkSprite(String imageUrl) {
		owner.getBase();
		return new Sprite(Frame.framesFromTileset(imageUrl+owner.getOwner().playerNumber+".png", 17, 256, 256));
	}*/
}
