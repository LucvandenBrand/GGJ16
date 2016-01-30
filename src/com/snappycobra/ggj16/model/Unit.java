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
	
	public Unit(String name, Body body, Player owner) {
		super(name, body);
		this.owner = owner;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
	public void createBody() {
		Body unitBody = new Body();
		unitBody.shift(new Vector2(0,18));
		unitBody.addFixture(new Rectangle(3,3));
	}
	
	public boolean moveTo(Body destination) {
		double ownX = this.getBody().getWorldCenter().x;
		double desX = destination.getWorldCenter().x;
		double direction = ownX-desX;
		if (Math.abs(direction) <= walkSpeed) {
			ownX = desX;
			return true;
		}else if (direction > 0) {
			ownX += walkSpeed;
		}else if (direction < 0) {
			ownX -= walkSpeed;
		}
		this.getBody().getWorldCenter().x = ownX;
		return false;
	}
	
	public void update(){
		if (job != null) {
			job.update();
		}
	}
	
	public void removeJob() {
		job = null;
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
