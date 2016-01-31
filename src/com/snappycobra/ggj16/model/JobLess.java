package com.snappycobra.ggj16.model;

import com.snappycobra.motor.graphics.Frame;
import com.snappycobra.motor.graphics.Sprite;

public class JobLess extends Job {
	
	public JobLess() {
		sprite = new Sprite(Frame.framesFromTileset("data/images/Creatures/player00_sleep_white.png", 7, 256, 256));
	}
	@Override
	public void update() {
	}

}
