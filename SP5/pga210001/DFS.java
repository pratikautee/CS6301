/** Starter code for SP5
 *  @author rbk
 */

// change to your netid
package pga210001;

import idsa.Graph.Vertex;
import pga210001.DFS.DFSVertex.STATUS;
import idsa.Graph;
import idsa.Graph.Edge;
import idsa.Graph.GraphAlgorithm;
import idsa.Graph.Factory;
import idsa.Graph.Timer;

import java.io.File;
import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;

public class DFS extends GraphAlgorithm<DFS.DFSVertex> {
    public static class DFSVertex implements Factory {
        int cno;

        enum STATUS {
            NEW, ACTIVE, FINISHED
        };

        STATUS status;
        boolean marked;

        public DFSVertex(Vertex u) {
            this.marked = false;
            this.status = STATUS.NEW;
        }

        public DFSVertex make(Vertex u) {
            return new DFSVertex(u);
        }
    }
    
    private static List<Vertex> topologicalOrder = new LinkedList<>();

    public DFS(Graph g) {
        super(g, new DFSVertex(null));
    }
    
    public static boolean isDAGAll(Graph g) {
        DFS d = new DFS(g);
        for (Vertex v : g.getVertexArray()) {
            d.get(v).status = STATUS.NEW;
        }
        for (Vertex v : g.getVertexArray()) {
            if (d.get(v).status == STATUS.NEW) {
                if (!isDAG(g, d, v)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private static boolean isDAG(Graph g, DFS d, Vertex u) {
        d.get(u).status = STATUS.ACTIVE;
        for (Edge e : g.outEdges(u)) {
            Vertex v = e.toVertex();
            if (d.get(v).status == STATUS.ACTIVE) {
                return false;
            } else if (d.get(v).status == STATUS.NEW) {
                if (!isDAG(g, d, v)) {
                    return false;
                }
            }
        }
        d.get(u).status = STATUS.FINISHED;
        return true;
    }

    public static DFS depthFirstSearch(Graph g) {
        DFS d = new DFS(g);
        for (Vertex u: g){
            if (!d.get(u).marked) {
                dfs(g, d, u);
            }
        }
	return d;
    }

    private static void dfs(Graph g, DFS d, Vertex u){
        d.get(u).marked = true;
        for (Edge e : g.outEdges(u)) {
            Vertex v = e.toVertex();
            if (!d.get(v).marked) {
                dfs(g, d, v);
            }
            
        }
        topologicalOrder.add(0,u);
    }

    // Member function to find topological order
    public List<Vertex> topologicalOrder1() {
        depthFirstSearch(g);
	return topologicalOrder;
    }

    // Find the number of connected components of the graph g by running dfs.
    // Enter the component number of each vertex u in u.cno.
    // Note that the graph g is available as a class field via GraphAlgorithm.
    public int connectedComponents() {
	return 0;
    }

    // After running the connected components algorithm, the component no of each vertex can be queried.
    public int cno(Vertex u) {
	return get(u).cno;
    }
    
    // Find topological oder of a DAG using DFS. Returns null if g is not a DAG.
    public static List<Vertex> topologicalOrder1(Graph g) {
	DFS d = new DFS(g);
	return d.topologicalOrder1();
    }

    // Find topological oder of a DAG using the second algorithm. Returns null if g is not a DAG.
    public static List<Vertex> topologicalOrder2(Graph g) {
	return null;
    }

    public static void main(String[] args) throws Exception {
        // String string = "7 8   1 2 2   1 3 3   2 4 5   3 4 4   4 5 1   5 1 7   6 7 1   7 6 1 0";
        // String string = "3 3   1 2 0   3 1 0   3 2 0";
        String string = "6 6    6 1 0   6 3 0   3 4 0   4 2 0   5 1 0   5 2 0";
        // String string = "3 2    2 3 0   3 1 0";
        Scanner in;
	// If there is a command line argument, use it as file from which
	// input is read, otherwise use input from string.
	in = args.length > 0 ? new Scanner(new File(args[0])) : new Scanner(string);
	
	// Read graph from input
    Graph g = Graph.readGraph(in, true);
    g.printGraph(false);
	
    
	// print the vertices in topological order
    // write the code
    if (isDAGAll(g)) {
        System.out.println("The topological ordering for the graph is : ");
        for (Vertex v : topologicalOrder1(g)) {
            System.out.print(v.getName());
            System.out.print("  ");
        }
    }
    else {
        System.out.println("Graph is not a DAG. Topological ordering not possible");
    }
	
    }
}