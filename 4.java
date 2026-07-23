import java.util.Scanner;

public class TopologicalSorting {

    static int[][] cost = new int[10][10]; // Adjacency matrix
    static int[] indegree = new int[10];   // Stores in-degree of nodes
    static int n;                          // Number of vertices

    // Function to calculate in-degree of all vertices
    static void calculate() {

        for (int i = 0; i < n; i++) {
            indegree[i] = 0;

            for (int j = 0; j < n; j++) {
                indegree[i] += cost[j][i]; // Summing column-wise to find in-degree
            }
        }
    }

    // Function to perform Topological Sorting using Source Removal method
    static void sourceRemoval() {

        int[] removed = new int[10];

        System.out.print("Topological ordering is: ");

        for (int i = 0; i < n; i++) {

            calculate(); // Recalculate in-degrees after each removal

            int j;
            for (j = 0; j < n; j++) {
                if (removed[j] == 0 && indegree[j] == 0) {
                    // Found a source vertex
                    break;
                }
            }

            if (j == n) {
                // No source found, graph contains a cycle
                System.out.println("Graph is cyclic, so no solution.");
                return;
            }

            System.out.print(j + " ");
            removed[j] = 1;

            // Remove all outgoing edges from vertex j
            for (int k = 0; k < n; k++) {
                cost[j][k] = 0;
            }
        }
    }

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        n = s.nextInt();

        System.out.println("Enter the cost matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost[i][j] = s.nextInt();
            }
        }

        sourceRemoval();

        s.close();
    }
}
