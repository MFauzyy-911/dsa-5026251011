package lw05.prelab;

import java.io.InputStream;
import java.util.*;

public class Main {
    static int size;
    static String[] names;
    static int[][] graph;
    static List<Integer> dfsOrder = new ArrayList<>();

    static void addEdge(int u, int v, int w) {
        graph[u][v] = w;
        graph[v][u] = w;
    }

    static void printPath(List<Integer> path) {
        for (int i = 0; i < path.size(); i++) {
            System.out.print(names[path.get(i)]);
            if (i < path.size() - 1) System.out.print(" -> ");
        }
        System.out.println();
    }

    static List<Integer> bfs(int start) {
        List<Integer> order = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            order.add(current);

            for (int next = 0; next < size; next++) {
                if (graph[current][next] != 0 && !visited.contains(next)) {
                    visited.add(next);
                    queue.add(next);
                }
            }
        }
        return order;
    }

    static void dfs(int current, Set<Integer> visited) {
        visited.add(current);
        dfsOrder.add(current);

        for (int next = 0; next < size; next++) {
            if (graph[current][next] != 0 && !visited.contains(next)) {
                dfs(next, visited);
            }
        }
    }

    static int findTheNextNode(int[] dist, boolean[] visited) {
        int best = -1;
        for (int i = 0; i < size; i++) {
            if (!visited[i] && dist[i] != Integer.MAX_VALUE
                    && (best == -1 || dist[i] < dist[best])) {
                best = i;
            }
        }
        return best;
    }

    static void route(int[] prev, int dest) {
        List<Integer> path = new ArrayList<>();
        for (int at = dest; at != -1; at = prev[at]) {
            path.add(0, at);
        }
        printPath(path);
    }

    static void dijkstraAlg(int start, int dest) {
        int[] dist = new int[size];
        int[] prev = new int[size];
        boolean[] visited = new boolean[size];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[start] = 0;

        for (int step = 0; step < size; step++) {
            int u = findTheNextNode(dist, visited);
            if (u == -1) break;
            visited[u] = true;

            for (int v = 0; v < size; v++) {
                if (graph[u][v] != 0 && !visited[v] && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    prev[v] = u;
                }
            }
        }

        System.out.println("=== Shortest Time from " + names[start] + " ===");
        for (int i = 0; i < size; i++) {
            System.out.println(names[i] + " : " + dist[i] + " min");
        }

        System.out.println("=== Shortest Route from " + names[start] + " to " + names[dest] + " ===");
        route(prev, dest);
        System.out.println("Total time: " + dist[dest] + " min");
    }

    public static void main(String[] args) {
        InputStream in = Main.class.getResourceAsStream("campus.txt");
        if (in == null) {
            System.out.println("campus.txt tidak ditemukan");
            return;
        }
        Scanner sc = new Scanner(in);

        size = sc.nextInt();
        names = new String[size];
        for (int i = 0; i < size; i++) {
            names[i] = sc.next();
        }
        graph = new int[size][size];

        int edges = sc.nextInt();
        for (int i = 0; i < edges; i++) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            int minutes = sc.nextInt();
            addEdge(v1, v2, minutes);
        }

        int start = sc.nextInt();
        int dest = sc.nextInt();
        sc.close();

        System.out.println("=== BFS from " + names[start] + " ===");
        printPath(bfs(start));

        System.out.println("=== DFS from " + names[start] + " ===");
        dfs(start, new HashSet<>());
        printPath(dfsOrder);

        dijkstraAlg(start, dest);
    }
}