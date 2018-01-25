/*
	AJAY NADHAVAJHALA
	CS 1C - Summer 2017 
	Professor David Harden
	Assignment 4 - Lazy Deletion in Binary Search Trees
 	This program will test the functions in FHlazySearchTree
 	and will display the outputs onto the console. It will
 	test the program by using its various methods to 
 	modify the trees and its nodes. 
 */

import java.util.*;
import cs_1c.*;

class PrintObject<E> implements Traverser<E> {
	public void visit(E x) {
		System.out.print(x + " ");
	}
};

// ------------------------------------------------------
public class Foothill {
	// ------- main --------------
	public static void main(String[] args) throws Exception {
		int k;
		FHlazySearchTree<Integer> searchTree = new FHlazySearchTree<Integer>();
		PrintObject<Integer> intPrinter = new PrintObject<Integer>();

	
		// test insertion
		System.out.println("\ninitial size: " + searchTree.size());
		searchTree.insert(50);
		searchTree.insert(20);
		searchTree.insert(30);
		searchTree.insert(70);
		searchTree.insert(10);
		searchTree.insert(60);

		System.out.println("After populating -- traversal and sizes: ");
		searchTree.traverse(intPrinter);
		System.out.println("\ntree 1 size: " + searchTree.size() + "  Hard size: " + searchTree.sizeHard());

		System.out.println("Collecting garbage on new tree - should be " + "no garbage.");
		searchTree.collectGarbage();
		System.out.println("tree 1 size: " + searchTree.size() + "  Hard size: " + searchTree.sizeHard());

		System.out.print("test min and max on a hard-filled tree\n");
		testMin(searchTree);
		testMax(searchTree);

		// test assignment operator
		FHlazySearchTree<Integer> searchTree2 = (FHlazySearchTree<Integer>) searchTree.clone();

		System.out.println("\n\nAttempting 1 removal: ");
		if (searchTree.remove(20))
			System.out.println("removed " + 20);
		System.out.println("tree 1 size: " + searchTree.size() + "  Hard size: " + searchTree.sizeHard());

		System.out.println("Collecting Garbage - should clean 1 item. ");
		searchTree.collectGarbage();
		System.out.println("tree 1 size: " + searchTree.size() + "  Hard size: " + searchTree.sizeHard());

		System.out.println("Collecting Garbage again - no change expected. ");
		searchTree.collectGarbage();
		System.out.println("tree 1 size: " + searchTree.size() + "  Hard size: " + searchTree.sizeHard());

		// test soft insertion and deletion:

		System.out.println("Adding 'hard' 22 - should see new sizes. ");
		searchTree.insert(22);
		searchTree.traverse(intPrinter);
		System.out.println("\ntree 1 size: " + searchTree.size() + "  Hard size: " + searchTree.sizeHard());

		System.out.println("\nAfter soft removal. ");
		searchTree.remove(22);
		searchTree.traverse(intPrinter);
		System.out.println("\ntree 1 size: " + searchTree.size() + "  Hard size: " + searchTree.sizeHard());

		System.out.println("Repeating soft removal. Should see no change. ");
		searchTree.remove(22);
		searchTree.traverse(intPrinter);
		System.out.println("\ntree 1 size: " + searchTree.size() + "  Hard size: " + searchTree.sizeHard());

		System.out.println("Soft insertion. Hard size should not change. ");
		searchTree.insert(22);
		searchTree.traverse(intPrinter);
		System.out.println("\ntree 1 size: " + searchTree.size() + "  Hard size: " + searchTree.sizeHard());

		System.out.println("\n\nAttempting 100 removals: ");
		for (k = 100; k > 0; k--) {
			if (searchTree.remove(k)) {
				System.out.println("removed " + k);
			}
		}
		
		System.out.println("test on a hard-empty tree");
		searchTree.traverse(intPrinter);
		testMin(searchTree);
		testMax(searchTree);

		// tests the min the max
		System.out.println("\ntest min and max on a soft-empty tree");
		testMin(searchTree);
		testMax(searchTree);

		searchTree.collectGarbage();
		System.out.println("\nsearch_tree now:");
		searchTree.traverse(intPrinter);
		System.out.println("\ntree 1 size: " + searchTree.size() + "  Hard size: " + searchTree.sizeHard());

		searchTree2.insert(500);
		searchTree2.insert(200);
		searchTree2.insert(300);
		searchTree2.insert(700);
		searchTree2.insert(100);
		searchTree2.insert(600);
		System.out.println("\nsearchTree2:\n");
		searchTree2.traverse(intPrinter);
		System.out.println("\ntree 2 size: " + searchTree2.size() + "  Hard size: " + searchTree2.sizeHard());

		testMin(searchTree2);
		testMax(searchTree2);

		System.out.println("\nRemove 10 from the tree 2:");
		searchTree2.remove(10);
		testMin(searchTree2);
		testMax(searchTree2);

		System.out.println("\nRemove 700 from the tree 2:");
		searchTree2.remove(700);
		testMin(searchTree2);
		testMax(searchTree2);

		System.out.println("\nRemove 200 from the tree 2:");
		searchTree2.remove(200);
		testMin(searchTree2);
		testMax(searchTree2);
	}

	// finds the min
	private static void testMin(FHlazySearchTree<Integer> tr) {
		try {
			System.out.printf("Min in tree: %d ", tr.findMin());
		} catch (NoSuchElementException e) {
			System.out.println("Error! Cannot find the minimum!");
		}
	}

	// finds the max
	private static void testMax(FHlazySearchTree<Integer> tr) {
		try {
			System.out.printf("\nMax in tree: %d ", tr.findMax());
		} catch (NoSuchElementException e) {
			System.out.println("Error! Cannot find the maximum!");
		}
	}
}

/*** OUTPUT 1

initial size: 0
After populating -- traversal and sizes: 
10 20 30 50 60 70 
tree 1 size: 6  Hard size: 6
Collecting garbage on new tree - should be no garbage.
tree 1 size: 6  Hard size: 6
test min and max on a hard-filled tree
Min in tree: 10 
Max in tree: 70 

Attempting 1 removal: 
removed 20
tree 1 size: 5  Hard size: 6
Collecting Garbage - should clean 1 item. 
tree 1 size: 5  Hard size: 5
Collecting Garbage again - no change expected. 
tree 1 size: 5  Hard size: 5
Adding 'hard' 22 - should see new sizes. 
10 22 30 50 60 70 
tree 1 size: 6  Hard size: 6

After soft removal. 
10 30 50 60 70 
tree 1 size: 5  Hard size: 6
Repeating soft removal. Should see no change. 
10 30 50 60 70 
tree 1 size: 5  Hard size: 6
Soft insertion. Hard size should not change. 
10 22 30 50 60 70 
tree 1 size: 6  Hard size: 6


Attempting 100 removals: 
removed 70
removed 60
removed 50
removed 30
removed 22
removed 10
test on a hard-empty tree
Error! Cannot find the minimum!
Error! Cannot find the maximum!

test min and max on a soft-empty tree
Error! Cannot find the minimum!
Error! Cannot find the maximum!

search_tree now:

tree 1 size: 0  Hard size: 0

searchTree2:

10 20 30 50 60 70 100 200 300 500 600 700 
tree 2 size: 12  Hard size: 12
Min in tree: 10 
Max in tree: 700 
Remove 10 from the tree 2:
Min in tree: 20 
Max in tree: 700 
Remove 700 from the tree 2:
Min in tree: 20 
Max in tree: 600 
Remove 200 from the tree 2:
Min in tree: 20 
Max in tree: 600 

**/