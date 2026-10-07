import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    static List<List<Edge>> graph;
    static boolean[] visited;
    static PriorityQueue<Edge> queue;
    static int result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        // init
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        visited = new boolean[n + 1];

        queue = new PriorityQueue<>();

        result = 0;

        // make graph
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph.get(u).add(new Edge(u, v, w));
            graph.get(v).add(new Edge(v, u, w));
        }

        // start vertex
        queue.add(new Edge(1, 1, 0));

        // prim algo
        while (!queue.isEmpty()) {
            Edge cur = queue.poll();

            if (visited[cur.to]) {
                continue;
            }

            result += cur.weight;
            visited[cur.to] = true;

            for (Edge next : graph.get(cur.to)) {
                if (!visited[next.to]) {
                    queue.add(next);
                }
            }

        }

        // print result
        System.out.print(result);
    }
}
