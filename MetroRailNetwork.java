import java.util.*;

class Edge implements Comparable<Edge> {
    int source, destination, weight;

    Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

class DisjointSet {
    int[] parent, rank;

    DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);

        return parent[x];
    }

    void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY)
            return;

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }
}

public class MetroRailNetwork {

    public static void main(String[] args) {

        String[] stations = {
                "Miyapur",
                "Ameerpet",
                "HitechCity",
                "Secunderabad",
                "LBNagar"
        };

        ArrayList<Edge> edges = new ArrayList<>();

        edges.add(new Edge(0, 1, 6));
        edges.add(new Edge(0, 2, 3));
        edges.add(new Edge(1, 2, 2));
        edges.add(new Edge(1, 3, 5));
        edges.add(new Edge(2, 3, 3));
        edges.add(new Edge(2, 4, 4));
        edges.add(new Edge(3, 4, 2));

        Collections.sort(edges);

        DisjointSet ds = new DisjointSet(stations.length);

        int totalCost = 0;

        System.out.println("===== Metro Rail Network Design =====");
        System.out.println("\nSelected Metro Tracks (MST):\n");

        for (Edge edge : edges) {

            int rootSource = ds.find(edge.source);
            int rootDestination = ds.find(edge.destination);

            if (rootSource != rootDestination) {

                ds.union(rootSource, rootDestination);

                System.out.println(
                        stations[edge.source] + " --> "
                                + stations[edge.destination]
                                + " : Cost = " + edge.weight);

                totalCost += edge.weight;
            }
        }

        System.out.println("\nMinimum Construction Cost = " + totalCost);
    }
}