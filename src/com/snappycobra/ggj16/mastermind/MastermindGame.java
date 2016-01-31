package com.snappycobra.ggj16.mastermind;

import java.util.ArrayList;
import java.util.List;

import com.snappycobra.ggj16.model.Resource;

public class MastermindGame {
	private int length;
	Combination solution;
	Combination currentTry;
	List<OldCombination> oldTries;
	
	
	public MastermindGame(int length) {
		this.length = length;
		solution = Combination.generateSolution(length);
		System.out.println(solution);
		currentTry = new Combination(length);
		oldTries = new ArrayList<OldCombination>();
	}
	
	public boolean addResource(Resource res) {
		currentTry.addResource(res);
		System.out.println("someone is playing!");
		if (currentTry.isFull()) {
			System.out.println("the try is full");
			if (currentTry.Compare(solution)) {
				System.out.println("WINNNEER");
				return true;
			} else {
				List<Integer> result = currentTry.checkInSolution(solution);
				oldTries.add(new OldCombination(currentTry,result.get(0),result.get(1)));
				currentTry = new Combination(length);
			}
		}
		return false;
	}
	
	public List<Resource> getCurrentTry() {
		return currentTry.getResourceList();
	}
	
	public List<OldCombination> getOldTries() {
		return this.oldTries;
	}
}