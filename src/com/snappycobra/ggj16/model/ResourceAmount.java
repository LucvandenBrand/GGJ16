package com.snappycobra.ggj16.model;

public class ResourceAmount {
	private String resName;
	private int amount;
	
	public ResourceAmount(String resName) {
		this.resName = resName;
		amount = 0;
	}
	
	public ResourceAmount(String resName, int amount) {
		this.resName = resName;
		this.amount = amount;
	}
	
	public void addAmount(int amount) {
		this.amount += amount;
	}
	
	public boolean useAmount(int amount) {
		if (this.amount > amount) {
			this.amount -= amount;
			return true;
		} else {
			return false;
		}
	}
	
	public int getAmount() {
		return amount;
	}
	
	public String getResName() {
		return resName;
	}
	
}
