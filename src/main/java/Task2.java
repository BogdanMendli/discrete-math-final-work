import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Task2 {

    private static final int[] dist = new int[SourceData.NODE_COUNT];
    private static final int[] paths = new int[SourceData.NODE_COUNT];
    private static final boolean[] used = new boolean[SourceData.NODE_COUNT];
    private static final Deque<Integer> result = new ArrayDeque<>();


    public static void main(String[] args) {
        setupArray();
        doAlgorithm(SourceData.from);
        printArray();
        printPaths();
        printResult();
    }

    private static void printResult() {
        int currentNode = SourceData.to;
        while (currentNode != 3) {
            result.push(currentNode);
            currentNode = paths[currentNode - 1];
        }
        result.push(currentNode);
        System.out.println("\tResult");
        while (!result.isEmpty()) {
            if (result.size() == 1) {
                System.out.println(result.pop());
            } else {
                System.out.print(result.pop() + " -> ");
            }
        }
    }

    private static void doAlgorithm(int currentNode) {
        int minNode = -1;
        int minWeight = Integer.MAX_VALUE;
        for (Edge node : SourceData.graph) {
            if (!used[node.second - 1] && node.first == currentNode) {
                if (dist[node.first - 1] + node.weight < dist[node.second - 1]) {
                    dist[node.second - 1] = dist[node.first - 1] + node.weight;
                    paths[node.second - 1] = currentNode;
                }
                if (dist[node.second - 1] < minWeight) {
                    minNode = node.second;
                    minWeight = dist[node.second - 1];
                }
            } else if (!used[node.first - 1] && node.second == currentNode) {
                if (dist[node.second - 1] + node.weight < dist[node.first - 1]) {
                    dist[node.first - 1] = dist[node.second - 1] + node.weight;
                    paths[node.first - 1] = currentNode;
                }
                if (dist[node.first - 1] < minWeight) {
                    minNode = node.first;
                    minWeight = dist[node.first - 1];
                }
            }
        }
        used[currentNode - 1] = true;
        if (minNode != -1) {
            doAlgorithm(minNode);
        }
    }

    private static void printArray() {
        System.out.println("\tDistance");
        for (int weight : dist) {
            System.out.print(weight + " ");
        }
        System.out.println();
        System.out.println();
    }

    private static void printPaths() {
        System.out.println("\tPaths");
        for (int weight : paths) {
            System.out.print(weight + " ");
        }
        System.out.println();
        System.out.println();
    }

    private static void setupArray() {
        Arrays.fill(dist, SourceData.INF);
        dist[SourceData.from - 1] = 0;
        Arrays.fill(paths, 0);
        Arrays.fill(used, false);
    }
}
