//Project by axk190189, pga210001
package axk190189;
import idsa.Graph;
import idsa.BFSOO;
import java.util.*;
import idsa.Graph.Vertex;
import idsa.Graph.Edge;

public class BFSCycle {
    public static final int INFINITY = Integer.MAX_VALUE;

    public static List<Vertex> oddCycle(Graph g) {
        for (Vertex src : g.getVertexArray()) {
            BFSOO b = BFSOO.breadthFirstSearch(g, g.getVertex(src));
            for (Edge e : g.getEdgeArray()) {
                Vertex v = e.toVertex();
                Vertex u = e.fromVertex();
                int uDistance = b.getDistance(u);
                int vDistance = b.getDistance(v);
                if (uDistance != INFINITY && uDistance == vDistance) {
                    StringBuilder res = new StringBuilder();
                    List<Vertex> result = new ArrayList<>();
                    while (u != null) {
                        result.add(0, u);
                        res.insert(0, u.getName() + " ");
                        u = b.getParent(u);
                    }

                    while (v != null) {
                        result.add(v);
                        res.append(v.getName() + " ");
                        v = b.getParent(v);
                    }
                    return result;
                }

            }

        }
        return null;

    }
    public static void main(String[]args)
    {
        String input = "6 7    1 2 1   1 4 1    1 6 1   2 3 1   3 6 1   4 5 1   5 6 1"; // no cycle
        // String input1 = "10 11   1 2 1   1 4 1   2 3 1   3 4 1   5 6 1   5 7 1   5 9 1   6 8 1   7 8 1   8 10 1  9 10 1"; //cycle 5 6 8 10 9 5
        String input2 = "8 8    1 2 0   1 3 0   3 4 0   4 5 0   5 6 0   6 7 0   7 3 0   3 8 0"; //cycle 1 3 4 5 6 7 3 1
        String testcase1 = "6 7     1 2 1   1 4 1   1 6 1   2 3 1   3 6 1   4 5 1   5 6 1"; //no cycle
        String testcase2 = "8 9     1 2 1   1 3 1   1 4 1   1 6 1   2 3 1   3 6 1   4 5 1   5 6 1   7 8 1"; //cycle 1 2 3 1
        String testcase3 = "10 11   1 2 1   1 4 1   2 3 1   3 4 1   5 6 1   5 7 1   5 9 1   6 8 1   7 8 1   8 10 1  9 10 1"; //cycle 5 6 8 10 9 5
        Scanner in = new Scanner(testcase3);
        Graph g = Graph.readGraph(in);
        g.printGraph(true);
        List<Vertex> oddCycle = oddCycle(g);
        if(oddCycle == null)
         System.out.println("THERE IS NO ODD CYCLE");
        else
          {
              System.out.println("Odd length cycle is :");
              for(Vertex v : oddCycle)
              {
                  System.out.print(v.getName()+" ");
              }
          } 

       
    }
}
