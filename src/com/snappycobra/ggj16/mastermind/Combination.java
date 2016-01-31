package com.snappycobra.ggj16.mastermind;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.snappycobra.ggj16.model.Gear;
import com.snappycobra.ggj16.model.Oil;
import com.snappycobra.ggj16.model.Resource;
import com.snappycobra.ggj16.model.Silverfish;
import com.snappycobra.ggj16.model.Uranium;

public class Combination {
	private List<Resource> resourceList = new ArrayList<>();
	private int length;
	
	public Combination(int length) {
		this.length = length;
	}
	
	public boolean Compare(Combination solution){
		for (int i=0; i<length; i++) {
			if (!resourceList.get(i).getRealName().equals(solution.getResourceList().get(i).getRealName())){
				return false;
			}
		}
		return true;
	}
	
	public List<Integer> checkInSolution(Combination solution){
		List<Resource> testSolution = copy(solution.getResourceList());
		List<Resource> testResourceList = copy(resourceList);
		List<Integer> checkInSolution= new ArrayList<>();
		int posCol = 0;
		int col = 0;
		for(int i=0; i<length; i++){
			if(testSolution.get(i).getRealName().equals(testResourceList.get(i).getRealName())){
				testSolution.set(i, null);
				testResourceList.set(i, null);
				posCol++;
			}
		}
		checkInSolution.add(posCol);
		
		for(int i=0; i<length; i++){
			for(int j=0; j<length; j++){
				if(testSolution.get(i) != null && testResourceList.get(j) != null){
					if(testSolution.get(i).getRealName().equals(testResourceList.get(j).getRealName())){
						testSolution.set(i, null);
						col++;
						break;
					}
				}
			}
		}
		checkInSolution.add(col);
		
		return checkInSolution;
	}
	
	
	private List<Resource> copy(List<Resource> resourceList) {
		List<Resource> copy = new ArrayList<>();
		for (Resource res : resourceList) {
			copy.add(res);
		}
		return copy;
	}
	
	public void addResource(Resource res) {
		resourceList.add(res);
	}
	
	public static Combination generateSolution(int length) {
		Combination solution = new Combination(length);
		Random rand = new Random();
		for (int i=0; i<length; i++) {
			switch(rand.nextInt(4)) {
			case 0: 
				solution.addResource(new Oil());
				break;
			case 1:
				solution.addResource(new Uranium());
				break;
			case 2:
				solution.addResource(new Silverfish());
				break;
			case 3: 
				solution.addResource(new Gear());
				break;
			}
		}
		return solution;
	}
	
	public List<Resource> getResourceList() {
		return resourceList;
	}

	public boolean isFull() {
		return resourceList.size() == length;
	}
	
	public String toString() {
		String result = "";
		for (Resource res : resourceList) {
			result = result+", "+res.getRealName();
		}
		return result;
	}
}

