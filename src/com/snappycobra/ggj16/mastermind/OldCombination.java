package com.snappycobra.ggj16.mastermind;

import java.util.ArrayList;
import java.util.List;

import com.snappycobra.ggj16.model.Resource;

public class OldCombination {
	private List<Resource> resourceList;
	private int poscol;
	private int col;
	
	public OldCombination(Combination com, int poscol, int col) {
		resourceList = com.getResourceList();
		this.poscol = poscol;
		this.col = col;
	}
}
