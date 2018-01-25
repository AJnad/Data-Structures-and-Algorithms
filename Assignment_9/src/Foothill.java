
/*
	AJAY NADHAVAJHALA
	CS 1C - Summer 2017 
	Professor David Harden
	Assignment 9 - The Maximum Flow Problem
 	This program will implement a maximum flow graph
 	algorithm that is based off of the generic class,
 	FHflowGraph. Using various methods, it will modify
 	the inputs of the graph and it will then display
 	the outputs of the flow graph and residual graph. 
 */

import java.util.*;

import cs_1c.*;

public class Foothill {
	// ------- main --------------
	public static void main(String[] args) throws Exception {
		double finalFlow;

		// TABLE 1
		System.out.println("---------------------------------------");
		System.out.println("FLOW 1");

		// build graph
		FHflowGraph<String> myG = new FHflowGraph<String>();

		myG.addEdge("s", "a", 3);
		myG.addEdge("s", "b", 2);
		myG.addEdge("a", "b", 1);
		myG.addEdge("a", "c", 3);
		myG.addEdge("a", "d", 4);
		myG.addEdge("b", "d", 2);
		myG.addEdge("c", "t", 2);
		myG.addEdge("d", "t", 3);

		// show the original flow graph
		myG.showResAdjTable();
		myG.showFlowAdjTable();

		myG.setStartVert("s");
		myG.setEndVert("t");
		finalFlow = myG.findMaxFlow();

		System.out.println("Final flow: " + finalFlow);

		myG.showResAdjTable();
		myG.showFlowAdjTable();

		// TABLE 2
		System.out.println("---------------------------------------");
		System.out.println("FLOW 2");

		myG.clear();
		myG.addEdge("s", "a", 4);
		myG.addEdge("s", "b", 2);
		myG.addEdge("a", "b", 1);
		myG.addEdge("a", "c", 2);
		myG.addEdge("a", "d", 4);
		myG.addEdge("b", "d", 2);
		myG.addEdge("c", "t", 3);
		myG.addEdge("d", "t", 3);

		// show the original flow graph
		myG.showResAdjTable();
		myG.showFlowAdjTable();

		myG.setStartVert("s");
		myG.setEndVert("t");
		finalFlow = myG.findMaxFlow();

		System.out.println("Final flow: " + finalFlow);

		myG.showResAdjTable();
		myG.showFlowAdjTable();

		// TABLE 3
		System.out.println("---------------------------------------");
		System.out.println("FLOW 3");

		myG.clear();
		myG.addEdge("s", "a", 1);
		myG.addEdge("s", "d", 4);
		myG.addEdge("s", "g", 6);
		myG.addEdge("a", "e", 2);
		myG.addEdge("a", "b", 2);
		myG.addEdge("b", "c", 2);
		myG.addEdge("e", "t", 4);
		myG.addEdge("a", "e", 3);
		myG.addEdge("d", "a", 3);
		myG.addEdge("e", "c", 2);
		myG.addEdge("e", "f", 3);

		// show the original flow graph
		myG.showResAdjTable();
		myG.showFlowAdjTable();

		myG.setStartVert("s");
		myG.setEndVert("t");
		finalFlow = myG.findMaxFlow();

		System.out.println("Final flow: " + finalFlow);

		myG.showResAdjTable();
		myG.showFlowAdjTable();
	}
}

// this class is based off of the given file, FHvertex
class FHflowVertex<E> {
	public static Stack<Integer> keyStack = new Stack<Integer>();
	public static final int KEY_ON_DATA = 0, KEY_ON_DIST = 1;
	public static int keyType = KEY_ON_DATA;
	public static final double INFINITY = Double.MAX_VALUE;

	// added hash sets
	public HashSet<Pair<FHflowVertex<E>, Double>> flowAdjList = new HashSet<Pair<FHflowVertex<E>, Double>>();
	public HashSet<Pair<FHflowVertex<E>, Double>> resAdjList = new HashSet<Pair<FHflowVertex<E>, Double>>();
	public E data;
	public double dist;
	public FHflowVertex<E> nextInPath;

	public FHflowVertex(E x) {
		this.data = x;
		this.dist = INFINITY;
		nextInPath = null;
	}

	public FHflowVertex() {
		this(null);
	}

	public void addToFlowAdjList(FHflowVertex<E> neighbor, double cost) {
		this.flowAdjList.add(new Pair<FHflowVertex<E>, Double>(neighbor, cost));
	}

	public void addToResAdjList(FHflowVertex<E> neighbor, double cost) {
		this.resAdjList.add(new Pair<FHflowVertex<E>, Double>(neighbor, cost));
	}

	@Override
	public boolean equals(Object rhs) {
		@SuppressWarnings("unchecked")
		FHflowVertex<E> other = (FHflowVertex<E>) rhs;
		switch (keyType) {
		case KEY_ON_DIST:
			return (this.dist == other.dist);
		case KEY_ON_DATA:
			return (this.data.equals(other.data));
		default:
			return (this.data.equals(other.data));
		}
	}

	@Override
	public int hashCode() {
		switch (keyType) {
		case KEY_ON_DIST:
			Double d = this.dist;
			return (d.hashCode());
		case KEY_ON_DATA:
			return (this.data.hashCode());
		default:
			return (this.data.hashCode());
		}
	}

	public void showFlowAdjList() {
		Iterator<Pair<FHflowVertex<E>, Double>> itr;
		Pair<FHflowVertex<E>, Double> pair;

		System.out.print("Adj Flow List for " + this.data + ": ");
		for (itr = flowAdjList.iterator(); itr.hasNext();) {
			pair = itr.next();
			System.out.print(pair.first.data + "(" + String.format("%3.1f", pair.second) + ") ");
		}
		System.out.println();
	}

	public void showResAdjList() {
		Iterator<Pair<FHflowVertex<E>, Double>> itr;
		Pair<FHflowVertex<E>, Double> pair;

		System.out.print("Adj Res List for " + this.data + ": ");
		for (itr = resAdjList.iterator(); itr.hasNext();) {
			pair = itr.next();
			System.out.print(pair.first.data + "(" + String.format("%3.1f", pair.second) + ") ");
		}
		System.out.println();
	}

	public static boolean setKeyType(int whichType) {
		switch (whichType) {
		case KEY_ON_DATA:
		case KEY_ON_DIST:
			keyType = whichType;
			return true;
		default:
			return false;
		}
	}

	public static void pushKeyType() {
		keyStack.push(keyType);
	}

	public static void popKeyType() {
		keyType = keyStack.pop();
	};
}

class FHflowGraph<E> {
	// added data members
	protected HashSet<FHflowVertex<E>> vertexSet;
	protected FHflowVertex<E> startVert, endVert;

	public FHflowGraph() {
		this.vertexSet = new HashSet<FHflowVertex<E>>();

	}

	// adds vertices
	public void addEdge(E source, E dest, double cost) {
		FHflowVertex<E> src, dst;
		src = addToVertexSet(source);
		dst = addToVertexSet(dest);
		src.addToResAdjList(dst, cost);
		dst.addToResAdjList(src, 0);
		// all the costs = 0
		src.addToFlowAdjList(dst, 0);
	}

	public void addEdge(E source, E dest, int cost) {
		addEdge(source, dest, (double) cost);
	}

	public boolean setStartVert(E x) {
		this.startVert = getVertexWithThisData(x);
		if (startVert == null)
			return false;
		return true;
	}

	public boolean setEndVert(E x) {
		this.endVert = getVertexWithThisData(x);
		if (endVert == null)
			return false;
		return true;
	}

	public FHflowVertex<E> addToVertexSet(E x) {
		FHflowVertex<E> retVal, vtx;
		boolean successfulInsertion;
		Iterator<FHflowVertex<E>> itr;

		FHflowVertex.pushKeyType();
		FHflowVertex.setKeyType(FHflowVertex.KEY_ON_DATA);

		retVal = new FHflowVertex<E>(x);
		successfulInsertion = this.vertexSet.add(retVal);

		FHflowVertex.popKeyType();

		if (successfulInsertion)
			return retVal;
		;

		for (itr = this.vertexSet.iterator(); itr.hasNext();) {
			vtx = itr.next();
			if (vtx.equals(retVal))
				return vtx;
		}

		return null;
	}

	public void showFlowAdjTable() {
		Iterator<FHflowVertex<E>> itr;

		System.out.println("---------------------------------------");
		for (itr = this.vertexSet.iterator(); itr.hasNext();)
			(itr.next()).showFlowAdjList();
		System.out.println();
	}

	public void showResAdjTable() {
		Iterator<FHflowVertex<E>> itr;

		System.out.println("---------------------------------------");
		for (itr = this.vertexSet.iterator(); itr.hasNext();)
			(itr.next()).showResAdjList();
		System.out.println();
	}

	public void clear() {
		this.vertexSet.clear();
		this.startVert = null;
		this.endVert = null;
	}

	protected FHflowVertex<E> getVertexWithThisData(E x) {
		FHflowVertex<E> searchVert, vtx;
		Iterator<FHflowVertex<E>> itr;

		FHflowVertex.pushKeyType();
		FHflowVertex.setKeyType(FHflowVertex.KEY_ON_DATA);

		searchVert = new FHflowVertex<E>(x);

		for (itr = this.vertexSet.iterator(); itr.hasNext();) {
			vtx = itr.next();
			if (vtx.equals(searchVert)) {
				FHflowVertex.popKeyType();
				return vtx;
			}
		}

		FHflowVertex.popKeyType();
		return null;
	}

	// main public algorithm which returns the max flow found
	public double findMaxFlow() {
		while (establishNextFlowPath()) {
			adjustPathByCost(getLimitingFlowOnResPath());
		}
		Iterator<Pair<FHflowVertex<E>, Double>> itrE;
		double maxFlow = 0;
		for (itrE = startVert.flowAdjList.iterator(); itrE.hasNext();) {
			maxFlow += itrE.next().second;
		}
		return -1.0 * maxFlow;
	}

	// based on the dijkstra() method
	// adjust vertex costs in each of the two graphs.
	protected boolean establishNextFlowPath() {
		double cost;
		FHflowVertex<E> vtx, i, j;

		Pair<FHflowVertex<E>, Double> e;
		Iterator<FHflowVertex<E>> itr;
		Iterator<Pair<FHflowVertex<E>, Double>> itrE;

		Deque<FHflowVertex<E>> dqv = new LinkedList<FHflowVertex<E>>();

		for (itr = this.vertexSet.iterator(); itr.hasNext();) {
			vtx = itr.next();
			vtx.dist = FHflowVertex.INFINITY;
			vtx.nextInPath = null;
		}
		this.startVert.dist = 0;
		dqv.addLast(this.startVert);
		while (dqv.isEmpty() == false) {
			i = dqv.removeFirst();
			for (itrE = i.resAdjList.iterator(); itrE.hasNext();) {
				e = itrE.next();
				j = e.first;
				cost = e.second;

				if (cost <= 0)
					continue;
				if (i.dist + cost < j.dist) {
					j.dist = i.dist + cost;
					j.nextInPath = i;
					if (j == endVert)
						return true;
					dqv.addLast(j);
				}
			}
		}
		return false;
	}

	// helper for findMaxFlow() to find the limiting flow
	// of the residual path just found
	protected double getLimitingFlowOnResPath() {
		FHflowVertex<E> vtx;
		double lim, cost;

		if (startVert == null || endVert == null)
			return 0;

		lim = FHflowVertex.INFINITY;
		for (vtx = this.endVert; vtx != this.startVert; vtx = vtx.nextInPath) {
			if (vtx.nextInPath == null)
				return 0;
			cost = getCostOfResEdge(vtx.nextInPath, vtx);
			if (cost < lim)
				lim = cost;
		}
		return lim;
	}

	// returns true if there is no error in the arguments
	protected boolean adjustPathByCost(double cost) {
		FHflowVertex<E> vtx = this.endVert;

		while (vtx != this.startVert) {
			if (vtx.nextInPath == null)
				return false;
			if (!addCostToFlowEdge(vtx.nextInPath, vtx, cost))
				return false;
			if (!addCostToResEdge(vtx, vtx.nextInPath, cost))
				return false;
			if (!addCostToResEdge(vtx.nextInPath, vtx, -cost))
				return false;
			vtx = vtx.nextInPath;
		}
		return true;
	}

	// examines src's residual adjacency list to find dst
	// and then return the cost of the edg
	protected double getCostOfResEdge(FHflowVertex<E> src, FHflowVertex<E> dst) {
		Iterator<Pair<FHflowVertex<E>, Double>> itr;
		Pair<FHflowVertex<E>, Double> p;

		if (src == null || dst == null)
			return 0;

		for (itr = src.resAdjList.iterator(); itr.hasNext();) {
			p = itr.next();

			if (p.first == dst)
				return p.second;
		}
		return 0;
	}

	// helper to adjustPathByCost()
	// examines src's flow adjacency list to find dst and then adds cost to that
	// edge
	protected boolean addCostToFlowEdge(FHflowVertex<E> src, FHflowVertex<E> dst, double cost) {
		Iterator<Pair<FHflowVertex<E>, Double>> itr;
		Pair<FHflowVertex<E>, Double> ePair;
		boolean tester = false;
		boolean checkIter = false;
		for (itr = dst.flowAdjList.iterator(); itr.hasNext();) {
			ePair = itr.next();
			if (ePair.first.equals(src)) {
				ePair.second += cost;
				tester = true;
			}
		}
		if (tester)
			return true;
		for (itr = src.flowAdjList.iterator(); itr.hasNext();) {
			ePair = itr.next();
			if (ePair.first.equals(dst)) {
				ePair.second -= cost;
				checkIter = true;
			}
		}
		if (checkIter)
			return true;
		else
			return false;
	}

	// examines src's residual adjacency list to find dst and then add cost to
	// that edge
	protected boolean addCostToResEdge(FHflowVertex<E> src, FHflowVertex<E> dst, double cost) {
		Iterator<Pair<FHflowVertex<E>, Double>> itr;
		Pair<FHflowVertex<E>, Double> p;
		if (src == null || dst == null)
			return false;
		for (itr = src.resAdjList.iterator(); itr.hasNext();) {
			p = itr.next();

			if (p.first == dst) {
				p.second += cost;
				return true;
			}
		}
		return false;
	}

}

/*** OUTPUT ***
---------------------------------------
FLOW 1
---------------------------------------
Adj Res List for a: b(1.0) s(0.0) c(3.0) d(4.0) 
Adj Res List for b: a(0.0) s(0.0) d(2.0) 
Adj Res List for s: a(3.0) b(2.0) 
Adj Res List for c: a(0.0) t(2.0) 
Adj Res List for d: a(0.0) b(0.0) t(3.0) 
Adj Res List for t: c(0.0) d(0.0) 

---------------------------------------
Adj Flow List for a: b(0.0) c(0.0) d(0.0) 
Adj Flow List for b: d(0.0) 
Adj Flow List for s: a(0.0) b(0.0) 
Adj Flow List for c: t(0.0) 
Adj Flow List for d: t(0.0) 
Adj Flow List for t: 

Final flow: 5.0
---------------------------------------
Adj Res List for a: b(1.0) s(3.0) c(1.0) d(3.0) 
Adj Res List for b: a(0.0) s(2.0) d(0.0) 
Adj Res List for s: a(0.0) b(0.0) 
Adj Res List for c: a(2.0) t(0.0) 
Adj Res List for d: a(1.0) b(2.0) t(0.0) 
Adj Res List for t: c(2.0) d(3.0) 

---------------------------------------
Adj Flow List for a: b(0.0) c(-2.0) d(-1.0) 
Adj Flow List for b: d(-2.0) 
Adj Flow List for s: a(-3.0) b(-2.0) 
Adj Flow List for c: t(-2.0) 
Adj Flow List for d: t(-3.0) 
Adj Flow List for t: 

---------------------------------------
FLOW 2
---------------------------------------
Adj Res List for a: b(1.0) s(0.0) c(2.0) d(4.0) 
Adj Res List for b: a(0.0) s(0.0) d(2.0) 
Adj Res List for s: a(4.0) b(2.0) 
Adj Res List for c: a(0.0) t(3.0) 
Adj Res List for d: a(0.0) b(0.0) t(3.0) 
Adj Res List for t: c(0.0) d(0.0) 

---------------------------------------
Adj Flow List for a: b(0.0) c(0.0) d(0.0) 
Adj Flow List for b: d(0.0) 
Adj Flow List for s: a(0.0) b(0.0) 
Adj Flow List for c: t(0.0) 
Adj Flow List for d: t(0.0) 
Adj Flow List for t: 

Final flow: 5.0
---------------------------------------
Adj Res List for a: b(1.0) s(3.0) c(0.0) d(3.0) 
Adj Res List for b: a(0.0) s(2.0) d(0.0) 
Adj Res List for s: a(1.0) b(0.0) 
Adj Res List for c: a(2.0) t(1.0) 
Adj Res List for d: a(1.0) b(2.0) t(0.0) 
Adj Res List for t: c(2.0) d(3.0) 

---------------------------------------
Adj Flow List for a: b(0.0) c(-2.0) d(-1.0) 
Adj Flow List for b: d(-2.0) 
Adj Flow List for s: a(-3.0) b(-2.0) 
Adj Flow List for c: t(-2.0) 
Adj Flow List for d: t(-3.0) 
Adj Flow List for t: 

---------------------------------------
FLOW 3
---------------------------------------
Adj Res List for a: b(2.0) s(0.0) d(0.0) e(2.0) 
Adj Res List for b: a(0.0) c(2.0) 
Adj Res List for s: a(1.0) d(4.0) g(6.0) 
Adj Res List for c: b(0.0) e(0.0) 
Adj Res List for d: a(3.0) s(0.0) 
Adj Res List for t: e(0.0) 
Adj Res List for e: a(0.0) c(2.0) t(4.0) f(3.0) 
Adj Res List for f: e(0.0) 
Adj Res List for g: s(0.0) 

---------------------------------------
Adj Flow List for a: b(0.0) e(0.0) 
Adj Flow List for b: c(0.0) 
Adj Flow List for s: a(0.0) d(0.0) g(0.0) 
Adj Flow List for c: 
Adj Flow List for d: a(0.0) 
Adj Flow List for t: 
Adj Flow List for e: c(0.0) t(0.0) f(0.0) 
Adj Flow List for f: 
Adj Flow List for g: 

Final flow: 2.0
---------------------------------------
Adj Res List for a: b(2.0) s(1.0) d(1.0) e(0.0) 
Adj Res List for b: a(0.0) c(2.0) 
Adj Res List for s: a(0.0) d(3.0) g(6.0) 
Adj Res List for c: b(0.0) e(0.0) 
Adj Res List for d: a(2.0) s(1.0) 
Adj Res List for t: e(2.0) 
Adj Res List for e: a(2.0) c(2.0) t(2.0) f(3.0) 
Adj Res List for f: e(0.0) 
Adj Res List for g: s(0.0) 

---------------------------------------
Adj Flow List for a: b(0.0) e(-2.0) 
Adj Flow List for b: c(0.0) 
Adj Flow List for s: a(-1.0) d(-1.0) g(0.0) 
Adj Flow List for c: 
Adj Flow List for d: a(-1.0) 
Adj Flow List for t: 
Adj Flow List for e: c(0.0) t(-2.0) f(0.0) 
Adj Flow List for f: 
Adj Flow List for g: 

*/
