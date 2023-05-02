// Starter code for SP9
// Project by pga210001, axk190189

package pga210001;

import idsa.Graph;
import idsa.Graph.Vertex;
import idsa.Graph.Edge;
import idsa.Graph.GraphAlgorithm;
import idsa.Graph.Factory;
import idsa.Graph.Timer;

import pga210001.BinaryHeap.Index;
import pga210001.BinaryHeap.IndexedHeap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.io.FileNotFoundException;
import java.io.File;

public class MST extends GraphAlgorithm<MST.MSTVertex> {
	String algorithm;
	public long wmst;
	List<Edge> mst;
	Edge[] safeEdges = new Edge[g.size()];

	MST(Graph g) {
		super(g, new MSTVertex((Vertex) null));
	}

	public static class MSTVertex implements Index, Comparable<MSTVertex>, Factory {
		boolean marked;
		int component;
		int dist;
		Vertex self;
		int PrimVertex;

		MSTVertex(Vertex u) {
			this.marked = false;
			this.dist = Integer.MAX_VALUE;
			this.self = u;
			this.PrimVertex = 0;
		}

		MSTVertex(MSTVertex u) {
			// for prim2
			this.marked = u.marked;
			this.dist = u.dist;
			this.self = u.self;
			this.PrimVertex = u.PrimVertex;
		}

		public MSTVertex make(Vertex u) {
			return new MSTVertex(u);
		}

		public void putIndex(int index) {
			this.PrimVertex = index;
		}

		public int getIndex() {
			return this.PrimVertex;
		}

		public int compareTo(MSTVertex other) {
			if (other == null || this.dist > other.dist) {
				return 1;
			} else if (this.dist < other.dist) {
				return -1;
			} else {
				return 0;
			}
		}
	}

	public long kruskal() {
		algorithm = "Kruskal";
		Edge[] edgeArray = g.getEdgeArray();
		mst = new LinkedList<>();
		wmst = 0;
		return wmst;
	}

	public long boruvka() {
		algorithm = "Boruvka";
		wmst = Boruvka();
		return wmst;
	}

	public int Boruvka() {
		int minWeight = 0;
		Graph F = new Graph(g.size());
		int count = countAndLabel(F);
		while (count > 1) {
			addAllSafeEdges(g.getEdgeArray(), F, count);
			count = countAndLabel(F);
		}
		for (Edge e : F.getEdgeArray()) {
			minWeight += e.getWeight();
		}
		return minWeight;
	}

	private int countAndLabel(Graph F) {
		int count = 0;
		for (Vertex u : F) {
			get(u).marked = false;
		}
		for (Vertex u : F) {
			if (!get(u).marked) {
				countAndLabelHelper(u, F, count++);
			}
		}
		return count;
	}

	private void countAndLabelHelper(Vertex u, Graph F, int count) {
		get(u).marked = true;
		get(u).component = count;
		for (Edge e : F.outEdges(u)) {
			Vertex v = e.otherEnd(u);
			if (!get(v).marked) {
				countAndLabelHelper(v, F, count);
			}
		}
	}

	private void addAllSafeEdges(Edge[] E, Graph F, int count) {
		for (int i = 0; i < count; i++) {
			safeEdges[i] = null;
		}
		for (Edge e : E) {
			Vertex u = e.fromVertex();
			Vertex v = e.otherEnd(u);
			int u_comp = get(u).component;
			int v_comp = get(v).component;
			if (u_comp != v_comp) {
				if (safeEdges[u_comp] == null || e.compareTo(safeEdges[u_comp]) < 0) {
					safeEdges[u_comp] = e;
				}
				if (safeEdges[v_comp] == null || e.compareTo(safeEdges[v_comp]) < 0) {
					safeEdges[v_comp] = e;
				}
			}
		}
		Set<Edge> uniqEdges = new HashSet<>();
		for (int i = 0; i < count; i++) {
			if (safeEdges[i] != null) {
				uniqEdges.add(safeEdges[i]);
			}
		}
		for (Edge e : uniqEdges) {
			if (e != null) {
				F.addEdge(e.fromVertex(), e.toVertex(), e.getWeight(), e.getName());
			}
		}
	}

	public long prim2(Vertex s) {
		algorithm = "indexed heaps";
		mst = new LinkedList<>();
		wmst = 0;
		IndexedHeap<MSTVertex> q = new IndexedHeap<>(g.size());
		for (Vertex u : g) {
			get(u).marked = false;
			get(u).dist = Integer.MAX_VALUE;
			get(u).self = u;
		}
		get(s).dist = 0;
		get(s).marked = true;
		for (Vertex u : g) {
			q.add(get(u));
		}
		while (!q.isEmpty()) {
			MSTVertex u = q.remove();
			u.marked = true;
			wmst += u.dist;
			for (Edge e : g.incident(u.self)) {
				Vertex v = e.otherEnd(u.self);
				if (!get(v).marked && e.getWeight() < get(v).dist) {
					get(v).dist = e.getWeight();
					q.decreaseKey(get(v));
				}
			}
		}
		return wmst;
	}

	public long prim1(Vertex s) {
		algorithm = "PriorityQueue<Edge>";
		mst = new LinkedList<>();
		wmst = 0;
		PriorityQueue<Edge> q = new PriorityQueue<>();
		return wmst;
	}

	public static MST mst(Graph g, Vertex s, int choice) {
		MST m = new MST(g);
		switch (choice) {
			case 0:
				m.boruvka();
				break;
			case 1:
				m.prim1(s);
				break;
			case 2:
				m.prim2(s);
				break;
			case 3:
				m.kruskal();
				break;
			default:

				break;
		}
		return m;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in;
		int choice = 2; // prim2
		if (args.length == 0 || args[0].equals("-")) {
			in = new Scanner(System.in);
		} else {
			File inputFile = new File(args[0]);
			in = new Scanner(inputFile);
		}

		if (args.length > 1) {
			choice = Integer.parseInt(args[1]);
		}

		Graph g = Graph.readGraph(in);
		Vertex s = g.getVertex(1);

		Timer timer = new Timer();
		MST m = mst(g, s, choice);
		System.out.println(m.algorithm + "\n" + m.wmst);
		System.out.println(timer.end());
	}
}
