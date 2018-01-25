/* 
	AJAY NADHAVAJHALA
	CS 1C - Summer 2017 
	Professor David Harden
 	Assignment 1 - The Subset-Sum Problem Part A
 	This program solves the Subset-sum problem in which
 	it will output the sum of the data to equal the target
 	value. There will be circumstances in which the data 
 	cannot equal the target, therefore, it will get as close
 	as possible to the target value. It will utilize an ArrayList 
 	in which it will go through every data in the list to 
 	see if it can be used to equal the target value. 
 */

import java.util.*;

public class Foothill {
	// ------- main --------------
	public static void main(String[] args) throws Exception {
		int target = 110;
		ArrayList<Integer> dataSet = new ArrayList<Integer>();
		ArrayList<Sublist> choices = new ArrayList<Sublist>();
		int k, j, numSets, max, kBest, masterSum;
		boolean foundPerfect;
		

		dataSet.add(2);
		dataSet.add(12);
		dataSet.add(22);
		dataSet.add(5);
		dataSet.add(15);
		dataSet.add(25);
		dataSet.add(9);
		dataSet.add(19);
		dataSet.add(29);

		// code supplied by student

		// displays target value 
		System.out.println("Target time: " + target);

		kBest = 0;
		max = 0;
		masterSum = 0;
		choices.add(new Sublist(dataSet));
		numSets = dataSet.size();

		// calculates the sum of all the ints in dataSet
		for (k = 0; k < numSets; k++) {
			masterSum += dataSet.get(k);
		}

		// if the target is greater or equal to the masterSum, it will display
		// all the values in the dataSet
		if (target >= masterSum) {

				System.out.println("Sublist ----------------------------- ");
			System.out.println("   sum: " + masterSum);
			for (k = 0; k < numSets; k++) {
				System.out.println("   array[" + k + "]" + " = " + dataSet.get(k));
			}
			foundPerfect = false;
		} else
			foundPerfect = true;

		// if the target is less than the masterSum, it will get add all the 
		// possible values in the dataSet to equal the target value 
		if (foundPerfect) {
			for (k = 0; k < dataSet.size(); k++) {
				numSets = choices.size();
				for (j = 0; j < numSets; j++) {
					masterSum = choices.get(j).getSum() + dataSet.get(k);
					if (masterSum <= target) {
						choices.add(choices.get(j).addItem(k));
						if (masterSum > max) {
							kBest = choices.size() - 1;
							max = masterSum;
						}
					}
				}
			}
			choices.get(kBest).showSublist();
		}
	}
}

class Sublist implements Cloneable {
	private int sum = 0;
	private ArrayList<Integer> originalObjects;
	private ArrayList<Integer> indices;

	// constructor creates an empty Sublist (no indices)
	public Sublist(ArrayList<Integer> orig) {
		sum = 0;
		originalObjects = orig;
		indices = new ArrayList<Integer>();
	}

	int getSum() {
		return sum;
	}

	// I have done the clone() for you, since you will need clone() inside
	// addItem().
	public Object clone() throws CloneNotSupportedException {
		// shallow copy
		Sublist newObject = (Sublist) super.clone();
		// deep copy
		newObject.indices = (ArrayList<Integer>) indices.clone();

		return newObject;
	}

	Sublist addItem(int indexOfItemToAdd) throws CloneNotSupportedException {
		Sublist newSublist = (Sublist)clone();
		newSublist.indices.add(indexOfItemToAdd);
		newSublist.sum += originalObjects.get(indexOfItemToAdd);
		
		return newSublist;
	}

	// displays the sublist in relation to what the target value is 
	void showSublist() {
		System.out.print("Sublist ----------------------------- ");
		System.out.println("\n   sum: " + this.sum);
		for (int x = 0; x < indices.size(); x++)
			System.out.println("   array[" + indices.get(x) + "] = " + originalObjects.get(indices.get(x)));
	}
};


/**** Output 1 ****
 
Target time: 72
Sublist ----------------------------- 
   sum: 72
   array[0] = 2
   array[2] = 22
   array[3] = 5
   array[4] = 15
   array[6] = 9
   array[7] = 19
   

**** Output 2 ****
 
Target time: 26
Sublist ----------------------------- 
   sum: 26
   array[1] = 12
   array[3] = 5
   array[6] = 9
 
**** Output 3 ****

Target time: 176
Sublist ----------------------------- 
   sum: 138
   array[0] = 2
   array[1] = 12
   array[2] = 22
   array[3] = 5
   array[4] = 15
   array[5] = 25
   array[6] = 9
   array[7] = 19
   array[8] = 29


**** Output 4 ****
 
Target time: 13
Sublist ----------------------------- 
   sum: 12
   array[1] = 12

*/