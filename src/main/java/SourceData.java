import java.util.Objects;

public class SourceData {

    public static final Edge[] graph = new Edge[] {
            new Edge(1, 2, 33),
            new Edge(1, 3, 22),
            new Edge(1, 8, 19),
            new Edge(2, 4, 13),
            new Edge(2, 7, 32),
            new Edge(3, 7, 21),
            new Edge(3, 8, 11),
            new Edge(4, 6, 4),
            new Edge(4, 8, 12),
            new Edge(5, 6, 31),
            new Edge(5, 7, 27),
            new Edge(6, 7, 28),
            new Edge(6, 8, 6),
            new Edge(7, 8, 7),
    };

    public static final int INF = 1000;
    public static final int NODE_COUNT = getNodeCount();
    public static final int from = 3;
    public static final int to = 5;

    private SourceData() {}

    private static int getNodeCount() {
        int count = -1;
        for (Edge edge: graph) {
            if (edge.first > count) {
                count = edge.first;
            }
            if (edge.second > count) {
                count = edge.second;
            }
        }
        return count;
    }
}

class Edge {
    final int first;
    final int second;
    final int weight;

    Edge(int first, int second, int weight) {
        this.first = first;
        this.second = second;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return (first == edge.first && second == edge.second
                || first == edge.second && second == edge.first) &&
                weight == edge.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, weight);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "first=" + first +
                ", second=" + second +
                ", weight=" + weight +
                '}';
    }
}
