package com.snappycobra.ggj16.model;

import java.util.ArrayList;
import java.util.List;

public class MenuItem {
	List<ResourceAmount> costList;
	String text;
	
	public MenuItem() {
		costList = new ArrayList<ResourceAmount>();
	}
	
	static MenuItem unitCreate(Player player) {
		MenuItem menuItem = new MenuItem();
		menuItem.addText("Unit");
		menuItem.addRA(new ResourceAmount("Gears",3));
		menuItem.addRA(new ResourceAmount("Oil",2));
		return menuItem;
	}
	
	public void CreateUnitAction(Player player) {
		if (this.checkCost(player.getResourceAmountList())){
			this.removeCost(player.getResourceAmountList());
			player.addUnit(new Unit(player, (Base) player.getBuildingList().get(0)));
		}
	}
	
	public void addText(String text) {
		this.text = text;
	}
	
	public void addRA(ResourceAmount ra){
		costList.add(ra);
	}
	
	public boolean checkCost(List<ResourceAmount> got) {
		for (ResourceAmount rac : costList) {
			for (ResourceAmount rag : got) {
				if (rac.getResName().equals(rag.getResName())) {
					if (rac.getAmount()>rag.getAmount()) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public void removeCost(List<ResourceAmount> got) {
		for (ResourceAmount rac : costList) {
			for (ResourceAmount rag : got) {
				if (rac.getResName().equals(rag.getResName())) {
					rag.useAmount(rac.getAmount());
				}
			}
		}
	}
}
