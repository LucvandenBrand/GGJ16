package com.snappycobra.ggj16.model;

public class GetNewJob extends Job {
	Job nextJob;
	
	public GetNewJob(Job nextJob, Job lastJob) {
		sprite = lastJob.getSprite();
		this.nextJob = nextJob;
	}
	
	@Override
	public void update() {
		if(owner.moveTo(owner.getBase().getBody())) {
			owner.setJob(nextJob);
		}
	}
}
