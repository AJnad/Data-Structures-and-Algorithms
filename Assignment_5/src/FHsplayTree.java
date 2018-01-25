/*
	AJAY NADHAVAJHALA
	CS 1C - Summer 2017 
	Professor David Harden
	Assignment 5 - Top-Down Splay Trees Using Inheritance
 	This program is based off of FHsearch_tree in which 
 	it will add methods in where we can modify and display
 	the tree to our own liking. 
 */

import cs_1c.*;

public class FHsplayTree<E extends Comparable<? super E>> extends FHsearch_tree<E> {

	@Override
	// inserts a value into the tree
	public boolean insert(E x) {
		if (mRoot == null) {
			mRoot = new FHs_treeNode<E>(x, null, null);
			return true;
		} else
			mRoot = splay(mRoot, x);

		int compareResult = x.compareTo(mRoot.data);
		if (compareResult < 0) {
			mRoot = new FHs_treeNode<E>(x, mRoot.lftChild, mRoot);
			return true;
		} else if (compareResult > 0) {
			mRoot = new FHs_treeNode<E>(x, mRoot, mRoot.rtChild);
			return true;
		} else {
			return false;
		}
	}

	@Override
	// remove a value from the tree
	public boolean remove(E x) {
		FHs_treeNode<E> newRoot;

		if (mRoot == null)
			return false;
		else
			mRoot = splay(mRoot, x);

		if (mRoot.data.compareTo(x) != 0)
			return false;

		if (mRoot.lftChild == null) {
			newRoot = mRoot.rtChild;
		} else {
			newRoot = mRoot.lftChild;
			mRoot = splay(newRoot, x);
			newRoot.rtChild = mRoot.rtChild;
		}

		mRoot = newRoot;
		return true;
	}

	// returns (doesn't really show) the m_root data,
	// or null if there is nothing in the tree
	public E showRoot() {
		if (this.mRoot == null)
			return null;
		else
			return this.mRoot.data;
	}

	// method is analyzed in depth where the last node that
	// was accessed becomes the new root
	protected FHs_treeNode<E> splay(FHs_treeNode<E> root, E x) {
		FHs_treeNode<E> leftTree = null;
		FHs_treeNode<E> rightTree = null;
		FHs_treeNode<E> rightTreeMin = null;
		FHs_treeNode<E> leftTreeMax = null;
		int compareResult = 0;

		while (root != null) {
			compareResult = x.compareTo(root.data);
			if (compareResult < 0) {
				if (root.lftChild == null)
					break;

				if (x.compareTo(root.lftChild.data) < 0) {
					root = rotateWithLeftChild(root);

					if (root.lftChild == null)
						break;
				}

				if (rightTree == null) {
					rightTree = root;
					rightTreeMin = root;
				} else {
					rightTreeMin.lftChild = root;
					rightTreeMin = root;
				}

				root = root.lftChild;
			} else if (compareResult > 0) {
				if (root.rtChild == null)
					break;

				if (x.compareTo(root.rtChild.data) > 0) {
					root = rotateWithRightChild(root);

					if (root.rtChild == null)
						break;
				}

				if (leftTree == null) {
					leftTree = root;
					leftTreeMax = root;
				} else {
					leftTreeMax.rtChild = root;
					leftTreeMax = root;
				}
				root = root.rtChild;
			} else {
				break;
			}
		}

		if (leftTree != null) {
			leftTreeMax.rtChild = root.lftChild;
			root.lftChild = leftTree;
		}

		if (rightTree != null) {
			rightTreeMin.lftChild = root.rtChild;
			root.rtChild = rightTree;
		}

		return root;
	}

	// both of the rotate child methods are based off of the same methods
	// in FHavlTree.java
	protected FHs_treeNode<E> rotateWithLeftChild(FHs_treeNode<E> k2) {
		FHs_treeNode<E> k1 = k2.lftChild;
		k2.lftChild = k1.rtChild;
		k1.rtChild = k2;
		return k1;
	}

	protected FHs_treeNode<E> rotateWithRightChild(FHs_treeNode<E> k2) {
		FHs_treeNode<E> k1 = k2.rtChild;
		k2.rtChild = k1.lftChild;
		k1.lftChild = k2;
		return k1;
	}

	// private equivalent of the public method, find()
	@Override
	protected FHs_treeNode<E> find(FHs_treeNode<E> root, E x) {
		mRoot = splay(mRoot, x);
		if (mRoot == null)
			return null;
		else if (mRoot.data.compareTo(x) != 0) {
			return null;
		}
		return mRoot;
	}
}

/***	OUTPUT 1

Initial size: 0
New size: 0

Traversal
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30
 31 32 
 oops 
splay -1 --> root: 1 height: 16
 oops 
splay 0 --> root: 1 height: 16
splay 1 --> root: 1 height: 16
splay 2 --> root: 2 height: 9
splay 3 --> root: 3 height: 6
splay 4 --> root: 4 height: 6
splay 5 --> root: 5 height: 5
splay 6 --> root: 6 height: 6
splay 7 --> root: 7 height: 6
splay 8 --> root: 8 height: 7
splay 9 --> root: 9 height: 8

***/