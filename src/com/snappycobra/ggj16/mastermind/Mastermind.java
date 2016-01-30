package com.snappycobra.ggj16.mastermind;

import com.snappycobra.ggj16.model.Resource;

public class Mastermind {
	private int length;
	private MastermindGame mastermindGame; 
	
	public Mastermind() {
		length = 1;
	}
	
	public boolean addResource(Resource res) {
		if (mastermindGame.addResource(res)) {
			if (length == 5) {
				return true;
			}
			length++;
			mastermindGame = new MastermindGame(length);
		}
		return false;
	}
	
	public int getLength(int length){
		return length;
	}
}
