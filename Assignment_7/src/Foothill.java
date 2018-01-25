
/*
	AJAY NADHAVAJHALA
	CS 1C - Summer 2017 
	Professor David Harden
	Assignment 7 - Analyzing Shellsort's Gaps
 	This program tests four different gap sequences-
 	implied, explicit, Sedgewick, and Knuth. It 
 	counts how many seconds it takes for each 
 	sequence and displays it on the screen based 
 	off of the array size.
 */

import cs_1c.*;

import java.util.*;
import java.text.*;

public class Foothill {

	// generic
	public static <E extends Comparable<? super E>> void shellSortX(E[] a, int[] gaps) {
		int gap = 1;
		int i, x, pos, arraySize;
		E tmp;

		arraySize = a.length;
		int swaps = 0;
		for (i = gaps.length - 1; i >= 0; i--) {
			gap = gaps[i];
			for (pos = gap; pos < arraySize; pos++) {
				tmp = a[pos];
				for (x = pos; x >= gap && tmp.compareTo(a[x - gap]) < 0; x -= gap) {
					a[x] = a[x - gap];
					swaps++;
				}
				a[x] = tmp;
			}
		}
	}

	// main method that counts how long each sequence takes
	public static void main(String[] args) throws Exception {
		final int ARRAY_SIZE = 200000;
		int randomInt;
		long startTime, stopTime;
		NumberFormat tidy = NumberFormat.getInstance(Locale.US);
		tidy.setMaximumFractionDigits(4);

		Integer[] arrayOfInts1 = new Integer[ARRAY_SIZE];
		Integer[] arrayOfInts2 = new Integer[ARRAY_SIZE];
		Integer[] arrayOfInts3 = new Integer[ARRAY_SIZE];
		Integer[] arrayOfInts4 = new Integer[ARRAY_SIZE];

		int[] gapArray = { 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072,
				262144, 524288, 1048576 };

		int[] sedgewickArray = new int[30];
		int[] knuthArray = new int[15];

		// sedgewick
		int size = sedgewickArray.length;
		if (size > 1) {
			for (int i = 1; i < size; i++) {
				sedgewickArray[i] = (int) (Math.pow(4, i) + 3 * Math.pow(2, i - 1) + 1);
			}
		}

		// knuth
		int i = 1;
		for (int k = 0; k < knuthArray.length; k++) {
			knuthArray[k] = i;
			i = 3 * i + 1;
		}

		// fills the arrays with random elements
		for (int k = 0; k < ARRAY_SIZE; k++) {
			randomInt = (int) (Math.random() * ARRAY_SIZE);
			arrayOfInts1[k] = randomInt;
			arrayOfInts2[k] = randomInt;
			arrayOfInts3[k] = randomInt;
			arrayOfInts4[k] = randomInt;

		}

		// implied gap
		System.out.println("Array Size: " + ARRAY_SIZE);
		startTime = System.nanoTime();
		FHsort.shellSort1(arrayOfInts1);
		stopTime = System.nanoTime();
		System.out.println("Shell's Implied Gap Sequence: " + tidy.format((stopTime - startTime) / 1e9) + " seconds.");

		// explicit gap
		startTime = System.nanoTime();
		shellSortX(arrayOfInts2, gapArray);
		stopTime = System.nanoTime();
		System.out.println("Shell's Explicit Gap Sequence: " + tidy.format((stopTime - startTime) / 1e9) + " seconds.");

		// sedgewick
		startTime = System.nanoTime();
		shellSortX(arrayOfInts3, sedgewickArray);
		stopTime = System.nanoTime();
		System.out.println("Sedgewick's Gap Sequence: " + tidy.format((stopTime - startTime) / 1e9) + " seconds.");

		// knuth
		startTime = System.nanoTime();
		shellSortX(arrayOfInts4, knuthArray);
		stopTime = System.nanoTime();
		System.out.println("Knuth's Gap Sequence: " + tidy.format((stopTime - startTime) / 1e9) + " seconds.");
	}
}

/*****	OUTPUTS

Array Size: 10000
Shell's Implied Gap Sequence: 0.0099 seconds.
Shell's Explicit Gap Sequence: 0.016 seconds.
Sedgewick's Gap Sequence: 0.0038 seconds.
Knuth's Gap Sequence: 0.0045 seconds.


Array Size: 20000
Shell's Implied Gap Sequence: 0.0167 seconds.
Shell's Explicit Gap Sequence: 0.0384 seconds.
Sedgewick's Gap Sequence: 0.0049 seconds.
Knuth's Gap Sequence: 0.0072 seconds.


Array Size: 90000
Shell's Implied Gap Sequence: 0.1163 seconds.
Shell's Explicit Gap Sequence: 0.1259 seconds.
Sedgewick's Gap Sequence: 0.0406 seconds.
Knuth's Gap Sequence: 0.0521 seconds.


Array Size: 115000
Shell's Implied Gap Sequence: 0.117 seconds.
Shell's Explicit Gap Sequence: 0.178 seconds.
Sedgewick's Gap Sequence: 0.0683 seconds.
Knuth's Gap Sequence: 0.0803 seconds.


Array Size: 150000
Shell's Implied Gap Sequence: 0.1529 seconds.
Shell's Explicit Gap Sequence: 0.2417 seconds.
Sedgewick's Gap Sequence: 0.0962 seconds.
Knuth's Gap Sequence: 0.1173 seconds.


Array Size: 200000
Shell's Implied Gap Sequence: 0.2232 seconds.
Shell's Explicit Gap Sequence: 0.4574 seconds.
Sedgewick's Gap Sequence: 0.1363 seconds.
Knuth's Gap Sequence: 0.153 seconds.

------------------------------------------------------------------------------			
SEQUENCE |	100000	 |	20000	|	90000	|	115000	|	150000	|	200000	
-------- |	---------|--------------------------------------------------------
Implied	 |	0.0099 	 |	0.0167	|	0.1163  |	0.117	|	0.1529	|	0.2232
Explicit |	0.016 	 |	0.0384	|	0.1259 	|	0.178	|	0.2417	|	0.4574
Sedgewick|	0.0038	 |	0.0049	|	0.0406	|	0.0683	|	0.0962	|	0.1363
Knuth	 |	0.0045	 |	0.0072	|	0.0521	|	0.0803	|	0.1173	|	0.153
------------------------------------------------------------------------------

1)  Why does Shell's gap sequence implied by shellSort1() give a different timing 
	result than the explicit array described above and passed to shellSortX()?
	- The implied sequence divides the array size by two and keeps doing so until
	  it reaches one. Therefore, it gives results that are more relevant to our 
	  current array size. Whereas in explicit, it starts at one and doubles 
	  each time when it iterates. As a result, there is different timing between 
	  the two. 
	  
2) 	 Which is faster and why?
	 - Based off the data, the implied sequence is faster because it does not need
	 to go through the extra effort that the explicit function does by dividing 
	 and not multiplying. Out of all four sequences, Sedgewick was still the fastest.
*/