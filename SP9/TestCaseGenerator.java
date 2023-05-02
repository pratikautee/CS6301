// Project by pga210001, axk190189

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class TestCaseGenerator {

    public static void main(String[] args) {
        long numNodes = 10_000;
        long numEdges = 1_000_000;

        if (args.length == 2) {
            numNodes = Long.valueOf(args[0]);
            numEdges = Long.valueOf(args[1]);
        }

        Set<String> inputSet = new HashSet<>();

        Random rng = new Random();

        while (inputSet.size() < numEdges) {
            Long v1 = rng.nextLong(1, numNodes);
            Long v2 = rng.nextLong(1, numNodes);
            int wt = rng.nextInt(1, 1000);
            if (v1 != v2 && !inputSet.contains(v1 + " " + v2) && !inputSet.contains(v2 + " " + v1)) {
                inputSet.add(v1 + " " + v2 + " " + wt);
            }
        }
        try {
            String fileName = "mst_" + numNodes + "_" + numEdges + ".txt";
            FileWriter fw = new FileWriter(fileName);
            fw.write(numNodes + " " + numEdges + "\n");
            for (String edge : inputSet) {
                fw.write(edge + "\n");
            }
            fw.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
