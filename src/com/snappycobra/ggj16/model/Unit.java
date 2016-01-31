package com.snappycobra.ggj16.model;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Vector2;

import com.snappycobra.motor.maps.GameObject;

public class Unit extends GameObject{
	private Job job;
	private double walkSpeed;
	private Base base;
	private Player owner;
	private boolean switching;
	
	public Unit(String name, Body body, Player owner, Base base) {
		super(name, body);
		this.owner = owner;
		this.base = base;
		walkSpeed = 1;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
	public void createBody() {
		Body unitBody = new Body();
		unitBody.shift(new Vector2(0,18));
		unitBody.addFixture(new Rectangle(10,3));
	}
	
	public boolean moveTo(Body destination) {
		double ownX = this.getBody().getWorldCenter().x;
		double desX = destination.getWorldCenter().x;
		System.out.println("current x:"+ ownX+" destination x:" + desX);
		double direction = desX-ownX;
		if (Math.abs(direction)<=walkSpeed) {
			this.getBody().translate(direction,0);
			return true;
		}
		if (direction > 0) {
			this.getBody().translate(walkSpeed,0);
		} else {
			this.getBody().translate(-walkSpeed,0);
		}
		return false;
	}
	
	public void update(){
		if (job != null) {
			job.update();
		}
	}
	
	public void removeJob() {
		job = new JobLess();
	}
	
	public Job getJob() {
		return job;
	}
	
	public Base getBase() {
		return base;
	}
	
	public Player getOwner() {
		return owner;
	}
	
	public void switchJob(Job job) {
		this.job = new GetNewJob(job, this.job);
	}

	public void setJob(Job job) {
		this.job = job;
	}
	
}
