package com.snappycobra.ggj16.model;

import java.util.List;

public class SacrificeInterface {
	List<String> possibleResourceList;
	int selected;
	Shrine shrine;
	Unit unit;
	
	public SacrificeInterface(List<String> possibleResourceList, Shrine shrine, Unit unit) {
		selected = 0;
		this.possibleResourceList = possibleResourceList;
		this.shrine = shrine;
		this.unit = unit;
	}
	
	public String getSelected() {
		String selectedRes = null;
		if (possibleResourceList.size() > 0) {
			selectedRes = possibleResourceList.get(selected);
		}
		return selectedRes;
	}
	
	public void confirm() {
		String resName = possibleResourceList.get(selected);
		if (unit.getOwner().removeResource(resName, 1))
		unit.switchJob(new Sacrificer(unit, shrine, Resource.getResource(resName)));
		dismiss();
	}
	
	public void dismiss() {
		shrine.removeSI();
	}
	
	public void up() {
		selected = (selected+1)%possibleResourceList.size();
	}
	
	public void down() {
		selected = selected-1;
		if (selected < 0) {
			selected = selected+possibleResourceList.size();
		}
	}
}
