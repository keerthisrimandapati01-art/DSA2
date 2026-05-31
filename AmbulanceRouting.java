import java.util.*;

class Node implements Comparable<Node> {
    int vertex;
    int distance;

    Node(int vertex, int distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    public int compareTo(Node other) {
        return this.distance - other.distance;
    }
}

public class AmbulanceRouting {

    static void dijkstra(List<List<Node>> graph, int source, String[] locations) {

        int V = graph.size();
        int[] dist = new int[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {

            Node current = pq.poll();
            int u = current.vertex;

            for (Node neighbor : graph.get(u)) {

                int v = neighbor.vertex;
                int weight = neighbor.distance;

                if (dist[u] + weight < dist[v]) {

                    dist[v] = dist[u] + weight;
                    pq.add(new Node(v, dist[v]));
                }
            }
        }

        System.out.println("===== Emergency Ambulance Routing System =====\n");

        System.out.println("Shortest Distance from Ambulance Station:\n");

        for (int i = 0; i < V; i++) {
            System.out.println(locations[i] + " = " + dist[i] + " km");
        }
    }

    public static void main(String[] args) {

        String[] locations = {
                "Ambulance Station",
                "Hospital A",
                "Hospital B",
                "Hospital C",
                "Hospital D"
        };

        int V = locations.length;

        List<List<Node>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++)
            graph.add(new ArrayList<>());

        graph.get(0).add(new Node(1, 4));
        graph.get(0).add(new Node(2, 7));

        graph.get(1).add(new Node(0, 4));
        graph.get(1).add(new Node(2, 1));
        graph.get(1).add(new Node(3, 5));

        graph.get(2).add(new Node(0, 7));
        graph.get(2).add(new Node(1, 1));
        graph.get(2).add(new Node(4, 3));

        graph.get(3).add(new Node(1, 5));
        graph.get(3).add(new Node(4, 2));

        graph.get(4).add(new Node(2, 3));
        graph.get(4).add(new Node(3, 2));

        dijkstra(graph, 0, locations);
    }
}