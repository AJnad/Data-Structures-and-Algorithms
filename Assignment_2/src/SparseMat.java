/*
	AJAY NADHAVAJHALA
	CS 1C - Summer 2017 
	Professor David Harden
	Assignment 2 - Sparse Matrices
 	This program takes a 100000 x 100000 matrix 
 	of objects and creates a sparse matrix. The
 	majority of the objects in the matrix will 
 	have a value of zero but, some of them will
 	contain a value. Sparse Matrices are a way
 	of representing a matrix. In this program,
 	we are making use of linked lists to showcase
 	them. 
 */
import java.util.Iterator;
import java.util.ListIterator;

import cs_1c.*;

public class SparseMat<E> implements Cloneable {

	/**
	 * inner node class used to store column index and data
	 */
	protected class MatNode implements Cloneable {
		public int col;
		public E data;

		// we need a default constructor for lists
		MatNode() {
			col = 0;
			data = null;
		}

		MatNode(int cl, E dt) {
			col = cl;
			data = dt;
		}

		// accessor method to get column
		public int getCol() {
			return col;
		}

		// accessor method to get data
		public E getData() {
			return data;
		}

		public Object clone() throws CloneNotSupportedException {
			// shallow copy
			MatNode newObject = (MatNode) super.clone();
			return (Object) newObject;
		}
	};

	// class member data
	protected int rowSize, colSize;
	protected E defaultVal;
	protected FHarrayList<FHlinkedList<MatNode>> rows;

	// constructor that establishes a size as well as a
	// default value for all elements
	SparseMat(int numRows, int numCols, E defaultVal) {
		if (numRows >= 1 || numCols >= 1) {
			this.rowSize = numRows;
			this.colSize = numCols;
			this.defaultVal = defaultVal;
			allocateEmptyMatrix();
		}
		// setting default values
		else {
			this.rowSize = 10;
			this.colSize = 10;
			this.defaultVal = defaultVal;
			allocateEmptyMatrix();
		}
	}

	// accessor that returns the object stored in row
	// r and column c
	public E get(int r, int c) {
		if (r < 0 || r > rowSize - 1 || c < 0 || c > colSize - 1)
			throw new IndexOutOfBoundsException();
		ListIterator<MatNode> val = rows.get(r).listIterator();
		MatNode node;
		while (val.hasNext()) {
			node = val.next();
			if (c == node.getCol()) {
				return node.getData();
			}
		}
		return this.defaultVal;
	}

	// mutator that places x in row r and column c
	// removes existing node if x is the default val
	public boolean set(int r, int c, E x) {
		if (r < 0 || r > rowSize - 1 || c < 0 || c > colSize - 1)
			return false;

		FHlinkedList<MatNode> rowList = rows.get(r);
		Iterator<MatNode> tempVal = rowList.iterator();
		MatNode tempNode;
		MatNode set = null;

		while (tempVal.hasNext()) {
			tempNode = (MatNode) tempVal.next();

			if (tempNode.col == c)
				set = tempNode;
		}

		if (set != null) {
			// removing existing node since x equals
			// default val
			if (x.equals(defaultVal)) {
				rows.get(r).remove(set);
			} else
				set.data = x;
		} else {
			if (!x.equals(defaultVal)) {
				rows.get(r).add(new MatNode(c, x));
			}
		}
		return true;
	}

	// allocates all the memory to a blank matrix
	// by clearing all the rows
	private void allocateEmptyMatrix() {
		rows = new FHarrayList<FHlinkedList<MatNode>>();

		// loop through each rows
		for (int i = 0; i < rowSize; i++) {
			rows.add(new FHlinkedList<MatNode>());
		}
	}

	// clears all the rows
	// sets all values to the defaultVal
	public void clear() {
		allocateEmptyMatrix();
	}

	// displays the square sub-matrix
	public void showSubSquare(int start, int size) {
		System.out.println();
		for (int row = start; row < start + size; row++) {
			for (int col = start; col < start + size; col++)
				System.out.format("%4.1f  ", get(row, col));
			System.out.println();
		}
	}

	// deep copies each node to a new Sparse matrix
	public Object clone() throws CloneNotSupportedException {
		FHlinkedList<MatNode> row;
		Iterator<MatNode> nodeIterator;
		SparseMat<E> newMat = new SparseMat<E>(rowSize, colSize, defaultVal);

		for (int i = 0; i < rows.size(); i++) {
			row = rows.get(i);
			nodeIterator = row.iterator();

			while (nodeIterator.hasNext()) {
				newMat.rows.get(i).add((MatNode) nodeIterator.next().clone());
			}
		}
		return newMat;
	}
}
/**	Output **

 0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0  
 0.0   1.0   0.0   0.0  -10.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0  
 0.0   0.0   2.0   0.0  -20.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0  
 0.0   0.0   0.0   3.0  -30.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0  
 0.0  10.0  20.0  30.0  -40.0  50.0  60.0  70.0  80.0  90.0   0.0   0.0  
 0.0   0.0   0.0   0.0  -50.0   5.0   0.0   0.0   0.0   0.0   0.0   0.0  
 0.0   0.0   0.0   0.0  -60.0   0.0   6.0   0.0   0.0   0.0   0.0   0.0  
 0.0   0.0   0.0   0.0  -70.0   0.0   0.0   7.0   0.0   0.0   0.0   0.0  
 0.0   0.0   0.0   0.0  -80.0   0.0   0.0   0.0   8.0   0.0   0.0   0.0  
 0.0   0.0   0.0   0.0  -90.0   0.0   0.0   0.0   0.0   9.0   0.0   0.0  
 0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0  
 0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0  


 1.0   0.0   0.0   0.0  -10.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0  
 0.0   1.0   0.0   0.0  -10.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0  
 0.0   0.0   1.0   0.0  -10.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0  
 0.0   0.0   0.0   1.0  -10.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0  
10.0  10.0  10.0  10.0  -10.0  10.0  10.0  10.0  10.0  10.0   0.0   0.0  
 0.0   0.0   0.0   0.0  -10.0   1.0   0.0   0.0   0.0   0.0   0.0   0.0  
 0.0   0.0   0.0   0.0  -10.0   0.0   1.0   0.0   0.0   0.0   0.0   0.0  
 0.0   0.0   0.0   0.0  -10.0   0.0   0.0   1.0   0.0   0.0   0.0   0.0  
 0.0   0.0   0.0   0.0  -10.0   0.0   0.0   0.0   1.0   0.0   0.0   0.0  
 0.0   0.0   0.0   0.0  -10.0   0.0   0.0   0.0   0.0   1.0   0.0   0.0  
 0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0  
 0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0  


 0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0  
 0.0   1.0   0.0   0.0  -10.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0  
 0.0   0.0   2.0   0.0  -20.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0  
 0.0   0.0   0.0   3.0  -30.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0  
 0.0  10.0  20.0  30.0  -40.0  50.0  60.0  70.0  80.0  90.0   0.0   0.0  
 0.0   0.0   0.0   0.0  -50.0   5.0   0.0   0.0   0.0   0.0   0.0   0.0  
 0.0   0.0   0.0   0.0  -60.0   0.0   6.0   0.0   0.0   0.0   0.0   0.0  
 0.0   0.0   0.0   0.0  -70.0   0.0   0.0   7.0   0.0   0.0   0.0   0.0  
 0.0   0.0   0.0   0.0  -80.0   0.0   0.0   0.0   8.0   0.0   0.0   0.0  
 0.0   0.0   0.0   0.0  -90.0   0.0   0.0   0.0   0.0   9.0   0.0   0.0  
 0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0  
 0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 
  
*/