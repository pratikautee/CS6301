// Project by pga210001, axk190189

import java.io.FileWriter;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class TestCaseGenerator {

    public static void main(String[] args) {
        int numNodes = 10_000;
        int numEdges = 1_000_000;

        if (args.length == 2) {
            numNodes = Integer.parseInt(args[0]);
            numEdges = Integer.parseInt(args[1]);
        }
        try {
            String fileName = "mst_" + numNodes + "_" + numEdges + ".txt";
            FileWriter fw = new FileWriter(fileName);
            fw.write(numNodes + " " + numEdges + "\n");

            Set<String> inputSet = new HashSet<>();

            Random rng = new Random();

            while (inputSet.size() < numEdges) {
                int v1 = rng.nextInt(numNodes - 1) + 1;
                int v2 = rng.nextInt(numNodes - 1) + 1;
                int wt = rng.nextInt(1000 - 1) + 1;
                if (v1 != v2 && !inputSet.contains(v1 + " " + v2) && !inputSet.contains(v2 + " " + v1)) {
                    String e = v1 + " " + v2 + " " + wt;
                    inputSet.add(e);
                    fw.write(e + "\n");
                }
            }
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
