package com.snappycobra.ggj16.model;

import com.snappycobra.motor.graphics.Frame;
import com.snappycobra.motor.graphics.Sprite;

public class TestJob extends Job {
	ResourcePoint rp;

	public TestJob(ResourcePoint rp, Unit owner) {
		this.rp = rp;
		this.sprite = new Sprite(new Frame("data/images/Workers/Worker_Blue.png", 10));
		this.owner = owner;
	}
	
	public void update() {
		owner.moveTo(rp.getBody());
	}

}
