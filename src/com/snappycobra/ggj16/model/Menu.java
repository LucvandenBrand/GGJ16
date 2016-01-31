package com.snappycobra.ggj16.model;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	List<MenuItem> itemList = new ArrayList<>();
	int selected = 0;
	
	static Menu baseMenu(Player player) {
		Menu menu = new Menu();
		menu.addItem(MenuItem.unitCreate(player));
		return menu;
	}
	
	public void addItem(MenuItem mi) {
		itemList.add(mi);
	}
	
	public void up() {
		selected = (selected+1)%itemList.size();
	}
	
	public void down() {
		selected = selected-1;
		if (selected < 0) {
			selected = selected+itemList.size();
		}
	}
}
