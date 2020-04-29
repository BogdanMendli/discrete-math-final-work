import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Task1 {

    private static final int[][] matrix = new int[SourceData.NODE_COUNT][SourceData.NODE_COUNT];
    private static final int[][] paths = new int[SourceData.NODE_COUNT][SourceData.NODE_COUNT];
    private static final Deque<Integer> result = new ArrayDeque<>();

    public static void main(String[] args) {
        setupMatrix();
        printMatrix();
        doAlgorithm();
        printMatrix();
        printPaths();
        printResult(SourceData.from, SourceData.to);
    }

    private static void printResult(int from, int to) {
        int currentNode = to - 1;
        while (currentNode != from - 1) {
            result.push(currentNode + 1);
            currentNode = paths[from - 1][currentNode];
        }
        result.push(from);
        System.out.println("\tResult");
        while (!result.isEmpty()) {
            if (result.size() == 1) {
                System.out.println(result.pop());
            } else {
                System.out.print(result.pop() + " -> ");
            }
        }
    }

    private static void printMatrix() {
        System.out.println("\tMatrix");
        for (int[] array : matrix) {
            for (int path : array) {
                System.out.print(path + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void printPaths() {
        System.out.println("\tPaths");
        for (int[] array : paths) {
            for (int path : array) {
                System.out.print(path + 1 + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void doAlgorithm() {
        for (int k = 0; k < matrix.length; k++) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    final int newPath = matrix[i][k] + matrix[k][j];
                    if (matrix[i][k] < SourceData.INF && matrix[k][j] < SourceData.INF) {
                        if (newPath < matrix[i][j]) {
                            matrix[i][j] = newPath;
                            paths[i][j] = paths[k][j];
                        }
                    }
                }
            }
        }
    }

    private static void setupMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            Arrays.fill(matrix[i], SourceData.INF);
            Arrays.fill(paths[i], -1);
            matrix[i][i] = 0;
            paths[i][i] = i;
        }
        for (Edge edge : SourceData.graph) {
            matrix[edge.first - 1][edge.second - 1] = matrix[edge.second - 1][edge.first - 1] = edge.weight;
            paths[edge.second - 1][edge.first - 1] = edge.second - 1;
            paths[edge.first - 1][edge.second - 1] = edge.first - 1;
        }
    }
}
