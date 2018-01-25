/*
	AJAY NADHAVAJHALA
	CS 1C - Summer 2017 
	Professor David Harden
	Assignment 4 - Lazy Deletion in Binary Search Trees
 	This program is based on the given program, FHsearch_tree
 	in which we will have methods and functions that will
 	allow us to modify the trees to our own liking. 
 */

// file FHlazySearchTree.java

import cs_1c.*;
import java.util.*;

public class FHlazySearchTree<E extends Comparable<? super E>> implements Cloneable {
	protected int mSize;
	protected int mSizeHard; // tracks the number of "hard" nodes
	protected FHlazySTNode<E> mRoot;

	public FHlazySearchTree() {
		clear();
	}

	public boolean empty() {
		return (mSize == 0);
	}

	public int size() {
		return mSize;
	}

	public int sizeHard() {
		return mSizeHard;
	}

	public void clear() {
		mSize = 0;
		mRoot = null;
	}

	public int showHeight() {
		return findHeight(mRoot, -1);
	}

	public E findMin() {
		if (mRoot == null)
			throw new NoSuchElementException();

		FHlazySTNode<E> temp = findMin2(mRoot);

		if (temp == null)
			throw new NoSuchElementException();

		return temp.data;
	}

	// finds the max value in the tree
	public E findMax() {
		if (mRoot == null)
			throw new NoSuchElementException();

		FHlazySTNode<E> temp = findMax2(mRoot);

		// throw exception when nothing is found
		if (temp == null)
			throw new NoSuchElementException();

		return temp.data;
	}

	// searches the given value and returns it
	public E find(E x) {
		FHlazySTNode<E> resultNode;
		resultNode = find(mRoot, x);
		if (resultNode == null)
			throw new NoSuchElementException();
		return resultNode.data;
	}

	// checks if the value is in the tree
	public boolean contains(E x) {
		return find(mRoot, x) != null;
	}

	// inserts a value to the tree
	public boolean insert(E x) {
		int oldSize = mSize;
		mRoot = insert(mRoot, x);
		return (mSize != oldSize);
	}

	// removes the value given in the parameter from the
	// tree
	public boolean remove(E x) {
		int pastSize = mSize;
		remove(mRoot, x);
		return (mSize != pastSize);
	}

	public <F extends Traverser<? super E>> void traverse(F func) {
		traverse(func, mRoot);
	}

	// hard copies the current tree
	@Override
	public Object clone() throws CloneNotSupportedException {
		FHlazySearchTree<E> newObject = (FHlazySearchTree<E>) super.clone();
		newObject.clear();

		newObject.mRoot = cloneSubtree(mRoot);
		newObject.mSize = mSize;
		newObject.mSizeHard = mSizeHard;
		return newObject;
	}

	// hard deletes the nodes
	public void collectGarbage() {
		collectGarbage(mRoot);
	}

	// finds the node with the min value
	protected FHlazySTNode<E> findMin(FHlazySTNode<E> root) {
		if (root == null)
			return null;
		if (root.lftChild == null)
			return root;
		return findMin(root.lftChild);
	}

	// finds the node with the min value but differs because it
	// does not consider the soft deleted nodes
	protected FHlazySTNode<E> findMin2(FHlazySTNode<E> root) {
		if (root == null)
			return null;

		FHlazySTNode<E> temp = findMin2(root.lftChild);

		if (temp != null)
			return temp;

		if (!root.deleted)
			return root;

		return findMin2(root.rtChild);
	}

	// finds the node with the max value but does not include the
	// soft deleted nodes
	protected FHlazySTNode<E> findMax2(FHlazySTNode<E> root) {
		if (root == null)
			return null;

		FHlazySTNode<E> temp = findMax2(root.rtChild);

		if (temp != null)
			return temp;

		if (!root.deleted)
			return root;

		return findMax2(root.lftChild);
	}

	// inserts the value into the tree
	protected FHlazySTNode<E> insert(FHlazySTNode<E> root, E x) {
		int compareResult; // avoid multiple calls to compareTo()

		// creates a new node if the first one is null
		if (root == null) {
			mSize++;
			mSizeHard++;
			return new FHlazySTNode<E>(x, null, null);
		}

		compareResult = x.compareTo(root.data);
		if (compareResult < 0)
			root.lftChild = insert(root.lftChild, x);
		else if (compareResult > 0)
			root.rtChild = insert(root.rtChild, x);
		else if (root.deleted) {
			root.deleted = false;
			mSize++;
		}
		return root;
	}

	// soft deletes a node
	protected void remove(FHlazySTNode<E> root, E x) {
		int compareResult;

		if (root == null)
			return;

		compareResult = x.compareTo(root.data);
		if (compareResult < 0)
			remove(root.lftChild, x);
		else if (compareResult > 0)
			remove(root.rtChild, x);
		else if (!root.deleted) {
			root.deleted = true;
			this.mSize--;
		}
		return;
	}

	// performs a hard deletion of the nodes
	// this is implemented in collectGarbage()
	protected FHlazySTNode<E> removeHard(FHlazySTNode<E> root, E x) {
		int compareResult; // avoid multiple calls to compareTo()

		if (root == null)
			return null;

		compareResult = x.compareTo(root.data);
		if (compareResult < 0)
			root.lftChild = removeHard(root.lftChild, x);
		else if (compareResult > 0)
			root.rtChild = removeHard(root.rtChild, x);
		else if (root.lftChild != null && root.rtChild != null) {
			FHlazySTNode<E> temp = findMin(root.rtChild);
			root.data = temp.data;
			root.deleted = temp.deleted;
			root.rtChild = removeHard(root.rtChild, root.data);
		} else {
			root = (root.lftChild != null) ? root.lftChild : root.rtChild;
			mSizeHard--;
		}
		return root;
	}

	protected <F extends Traverser<? super E>> void traverse(F func, FHlazySTNode<E> treeNode) {
		if (treeNode == null)
			return;

		traverse(func, treeNode.lftChild);

		if (!treeNode.deleted)
			func.visit(treeNode.data);

		traverse(func, treeNode.rtChild);
	}

	// finds the value given in the parameter in the tree
	protected FHlazySTNode<E> find(FHlazySTNode<E> root, E x) {
		int compareResult; // avoids multiple calls to compareTo()

		if (root == null)
			return null;

		compareResult = x.compareTo(root.data);
		if (compareResult < 0)
			return find(root.lftChild, x);
		if (compareResult > 0)
			return find(root.rtChild, x);
		return root; // found
	}

	// clones the node in the parameters and all of its child
	// nodes
	protected FHlazySTNode<E> cloneSubtree(FHlazySTNode<E> root) {
		FHlazySTNode<E> newNode;

		if (root == null)
			return null;

		newNode = new FHlazySTNode<E>(root.data, cloneSubtree(root.lftChild), cloneSubtree(root.rtChild));
		newNode.deleted = root.deleted;
		return newNode;
	}

	protected int findHeight(FHlazySTNode<E> treeNode, int height) {
		int leftHeight, rightHeight;
		if (treeNode == null)
			return height;
		height++;
		leftHeight = findHeight(treeNode.lftChild, height);
		rightHeight = findHeight(treeNode.rtChild, height);
		return (leftHeight > rightHeight) ? leftHeight : rightHeight;
	}

	// does a hard removal of deleted nodes
	protected FHlazySTNode<E> collectGarbage(FHlazySTNode<E> node) {
		if (node == null)
			return null;

		if (node.deleted) {
			node = removeHard(node, node.data);
			collectGarbage(node);
		} else {
			collectGarbage(node.rtChild);
			collectGarbage(node.lftChild);
		}
		return node;
	}
}

class FHlazySTNode<E extends Comparable<? super E>> {
	public boolean deleted;
	public FHlazySTNode<E> lftChild, rtChild;
	public E data;
	public FHlazySTNode<E> myRoot;

	// constructor for given parameters
	public FHlazySTNode(E d, FHlazySTNode<E> lft, FHlazySTNode<E> rt) {
		deleted = false;
		lftChild = lft;
		rtChild = rt;
		data = d;
	}

	public FHlazySTNode() {
		this(null, null, null);
	}

	public int getHeight() {
		return 0;
	}

	boolean setHeight(int height) {
		return true;
	}
}