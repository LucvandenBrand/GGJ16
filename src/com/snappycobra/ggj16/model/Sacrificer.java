package com.snappycobra.ggj16.model;

import com.snappycobra.motor.graphics.Frame;
import com.snappycobra.motor.graphics.Sprite;

public class Sacrificer extends Job {
	private Shrine shrine;
	private int state;
	private Unit owner;
	private Resource resource;
	
	public Sacrificer(Unit owner, Shrine shrine, Resource res) {
		this.sprite = new Sprite(new Frame("data/images/Workers/Worker_Red.png", 10));
		this.owner = owner;
		this.shrine = shrine;
		this.state = 1;
		this.resource = res;
	}
	
	@Override
	public void update() {
		switch(state){
		case 1:
			if(owner.moveTo(shrine.getBody())) {
				Offer();
				state = 2;
			}
			
			break;
		case 2:
			if(owner.moveTo(owner.getBase().getBody())) {
				owner.removeJob();
			}
		}
	}
	
	public void Offer() {
		System.out.println("play Resource" + resource.getRealName());
		owner.getOwner().playMastermind(resource);
	}

}
