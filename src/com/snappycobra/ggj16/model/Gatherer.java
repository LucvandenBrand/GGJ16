package com.snappycobra.ggj16.model;

import com.snappycobra.motor.graphics.Frame;
import com.snappycobra.motor.graphics.Sprite;

public class Gatherer extends Job {
	private ResourcePoint gatherAt;
	private double yield;
	private int maxYield;
	private int state=3;
	private Unit owner;
	private Sprite spriteWalk;
	private Sprite spriteWork;
	
	public Gatherer(Unit owner, ResourcePoint gatherAt) {
		this.owner = owner;
		this.gatherAt = gatherAt;
		spriteWalk = SelectGoodSprite(gatherAt.getResource().getWalkSprite(), owner);
		spriteWork = SelectGoodSprite(gatherAt.getResource().getWorkSprite(), owner);
		sprite = spriteWalk;
		yield = 0;
		maxYield = 1;
	}
	
	public void update() {
		switch(state){
		case 1:
			if(owner.moveTo(gatherAt.getBody())) {
				state = 2;
				sprite = spriteWork;
			}
			break;
		case 2:
			
			if(yield>=maxYield){
				state=3;
				sprite = spriteWalk;
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
		yield = yield+0.1;
	}
	
	public void storeResources() {
		System.out.println("Is a:"+gatherAt.getResource().getRealName());
		owner.getOwner().addResource(gatherAt.getResource().getRealName(), 1);
		yield = 0;
	}
}
