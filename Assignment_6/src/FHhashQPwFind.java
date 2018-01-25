/*
	AJAY NADHAVAJHALA
	CS 1C - Summer 2017 
	Professor David Harden
	Assignment 6 - Quadratic Probing with a find()
 	This program is based off of FHhashQP that was 
 	presented in the modules. It contains methods
 	that will allow the user to find and modify 
 	the objects from catalog-short4.txt
 */

import cs_1c.*;
import java.util.*;

public class FHhashQPwFind<KeyType, E extends Comparable<KeyType>> extends FHhashQP<E> {

	// finds the data by the key
	public E find(KeyType key) {
		int pos = findPosKey(key);
		if (mArray[pos].state != ACTIVE)
			throw new NoSuchElementException();

		return mArray[pos].data;
	}

	// hash the key
	protected int myHashKey(KeyType key) {
		int hashVal;

		hashVal = key.hashCode() % mTableSize;
		if (hashVal < 0)
			hashVal += mTableSize;

		return hashVal;
	}

	// finds the position of an object that is
	// equivalent to the key
	protected int findPosKey(KeyType key) {
		int kthOddNum = 1;
		int index = myHashKey(key);

		while (mArray[index].state != EMPTY && mArray[index].data.compareTo(key) != 0) {
			index += kthOddNum;
			kthOddNum += 2;
			if (index >= mTableSize)
				index -= mTableSize;
		}
		return index;
	}

	// outputs the object
	public void display() {
		for (int i = 0; i < mArray.length; i++) {
			if (mArray[i].data != null) {
				System.out.println("Array[ " + i + " ] " + mArray[i].data);
			}
		}
	}
}