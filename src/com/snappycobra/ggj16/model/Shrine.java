package com.snappycobra.ggj16.model;

import java.util.ArrayList;
import java.util.List;

import org.dyn4j.dynamics.Body;

import com.snappycobra.motor.maps.GameObject;

public class Shrine extends GameObject {
	private SacrificeInterface si = null;
	
	public Shrine(String name, Body body) {
		super(name, body);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
	public void choseOffer(Player player, Unit unit) {
		List<String> possibleResourceList = new ArrayList<>();
		for (ResourceAmount ra : player.getResourceAmountList()) {
			if (ra.getAmount()>0) {
				possibleResourceList.add(ra.getResName());
			}
		}
		si = new SacrificeInterface(possibleResourceList, this, unit);
	}
	
	public boolean existSI() {
		return si != null;
	}
	
	public SacrificeInterface getSacrificeInterface() {
		return si;
	}
	
	public void removeSI() {
		si = null;
	}
}
