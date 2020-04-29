import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task3 {

    private static List<Edge> nodes = new ArrayList<>(SourceData.graph.length);
    private static List<Edge> ostov = new ArrayList<>(SourceData.NODE_COUNT - 1);
    private static List<Integer> nodesInOstov = new ArrayList<>(SourceData.NODE_COUNT);

    public static void main(String[] args) {
        setupData();
        doAlgorithm();
        printResult();
    }

    private static void setupData() {
        nodes.addAll(Arrays.asList(SourceData.graph));
        nodesInOstov.add(SourceData.from);
    }

    private static void printResult() {
        ostov.forEach(System.out::println);
    }

    private static void doAlgorithm() {
        while (nodesInOstov.size() != SourceData.NODE_COUNT) {
            Edge minEdge = null;
            int minNode = -1;
            for (Edge node : nodes) {
                if (nodesInOstov.contains(node.first) && !nodesInOstov.contains(node.second) && (minEdge == null || node.weight < minEdge.weight)) {
                    minEdge = node;
                    minNode = node.second;
                } else if (nodesInOstov.contains(node.second)
                        && !nodesInOstov.contains(node.first)
                        && (minEdge == null || node.weight < minEdge.weight)){
                    minEdge = node;
                    minNode = node.first;
                }
            }
            nodesInOstov.add(minNode);
            ostov.add(minEdge);
            nodes.remove(minEdge);
        }
    }
}
