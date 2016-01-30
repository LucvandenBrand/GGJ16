package com.snappycobra.ggj16.model;

import com.snappycobra.motor.graphics.Frame;
import com.snappycobra.motor.graphics.Sprite;

public class Gatherer extends Job {
	private ResourcePoint gatherAt;
	private int yield;
	private int maxYield;
	private int state=3;
	private Unit owner;
	
	public Gatherer(Unit owner, ResourcePoint gatherAt) {
		this.owner = owner;
		this.gatherAt = gatherAt;
		this.sprite = new Sprite(new Frame("data/images/Workers/Worker_Blue.png", 10));
		yield = 0;
		maxYield = 1;
	}
	
	public void update() {
		switch(state){
		case 1:
			if(owner.moveTo(gatherAt.getBody())) {
				state = 2;
			}
			break;
		case 2:
			if(yield==maxYield){
				state=3;
			} else {
				Work();
			}
			break;
		case 3:
			if(owner.moveTo(owner.getBase().getBody())) {
				state=1;
				storeResources();
			}
			
		}
	}
	
	public void Work(){
		System.out.println("working");
		yield++;
	}
	
	public void storeResources() {
		owner.getOwner().addResource(gatherAt.getResource().getResName(), yield);
	}
}
