import java.util.*;

public class GraphExample {

    private static final char[] WAREHOUSES = {'A', 'B', 'C', 'D', 'E'};
    private static final int N = WAREHOUSES.length;

    private final int[][] adjMatrix = new int[N][N];

    private final List<List<Integer>> adjList = new ArrayList<>(N);

    public GraphExample() {
        for (int i = 0; i < N; i++) adjList.add(new ArrayList<>());
        addEdge('A', 'B');
        addEdge('A', 'C');
        addEdge('B', 'D');
        addEdge('C', 'D');
        addEdge('D', 'E');
        addEdge('C', 'E');
        addEdge('E', 'A');
    }

    private void addEdge(char u, char v) {
        int from = indexOf(u);
        int to   = indexOf(v);
        adjMatrix[from][to] = 1;
        adjList.get(from).add(to);
    }

    public List<Character> bfs(char start) {
        boolean[] visited = new boolean[N];
        Queue<Integer> q  = new ArrayDeque<>();
        List<Character> order = new ArrayList<>();

        q.add(indexOf(start));
        visited[indexOf(start)] = true;

        while (!q.isEmpty()) {
            int node = q.poll();
            order.add(WAREHOUSES[node]);

            for (int neigh : adjList.get(node)) {
                if (!visited[neigh]) {
                    visited[neigh] = true;
                    q.add(neigh);
                }
            }
        }
        return order;
    }

    public List<Character> dfs(char start) {
        boolean[] visited = new boolean[N];
        List<Character> order = new ArrayList<>();
        dfsUtil(indexOf(start), visited, order);
        return order;
    }

    private void dfsUtil(int node, boolean[] visited, List<Character> order) {
        visited[node] = true;
        order.add(WAREHOUSES[node]);

        for (int neigh : adjList.get(node)) {
            if (!visited[neigh]) dfsUtil(neigh, visited, order);
        }
    }

    public void printAdjacencyMatrix() {
        System.out.println("Adjacency Matrix:");
        System.out.print("    ");
        for (char c : WAREHOUSES) System.out.print(c + " ");
        System.out.println();
        for (int i = 0; i < N; i++) {
            System.out.print(WAREHOUSES[i] + " [ ");
            for (int j = 0; j < N; j++) System.out.print(adjMatrix[i][j] + " ");
            System.out.println("]");
        }
    }

    private int indexOf(char warehouse) {
        return warehouse - 'A';
    }

    public static void main(String[] args) {
        GraphExample g = new GraphExample();

        g.printAdjacencyMatrix();
        System.out.println();

        List<Character> bfsOrder = g.bfs('A');
        System.out.println("BFS dari A: " + bfsOrder);

        List<Character> dfsOrder = g.dfs('A');
        System.out.println("DFS dari A: " + dfsOrder);
    }
}
