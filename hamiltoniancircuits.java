import java.util.Scanner;

public class HamiltonianCkts {

    static int[][] a;
    static int n;
    static boolean found = false;

    static void printPath(int path[]) {
        for (int i = 0; i < n; i++) {
            System.out.print(path[i] + "->");
        }
        System.out.println(path[0]);
    }

    static void hamiltonian(int pos, int path[], boolean visited[]) {

        // All vertices visited
        if (pos == n) {

            // Check if last vertex connects back to start
            if (a[path[n - 1]][path[0]] == 1) {
                found = true;
                printPath(path);
            }
            return;
        }

        // Try every vertex except source
        for (int v = 1; v < n; v++) {

            if (!visited[v] && a[path[pos - 1]][v] == 1) {

                path[pos] = v;
                visited[v] = true;

                hamiltonian(pos + 1, path, visited);

                // Backtrack
                visited[v] = false;
            }
        }
    }

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        System.out.println("Enter the number of vertices:");
        n = s.nextInt();

        a = new int[n][n];

        System.out.println("Enter the adjacency matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = s.nextInt();
            }
        }

        int[] path = new int[n];
        boolean[] visited = new boolean[n];

        path[0] = 0;      // Start from vertex 0
        visited[0] = true;

        System.out.println(
            "Routes for delivery vehicle / Hamiltonian Circuits:"
        );

        hamiltonian(1, path, visited);

        if (!found) {
            System.out.println("No Hamiltonian Circuit Exists");
        }

        s.close();
    }
}
