package com.snappycobra.ggj16.model;

public class GetNewJob extends Job {
	Job nextJob;
	
	public GetNewJob(Unit owner, Job nextJob, Job lastJob) {
		sprite = lastJob.getSprite();
		this.nextJob = nextJob;
		this.owner = owner;
	}
	
	@Override
	public void update() {
		owner.getBase();
		if(owner.moveTo(owner.getBase().getBody())) {
			owner.setJob(nextJob);
		}
	}
}
