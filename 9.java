// ALGORITHM Floyd(W[1..n, 1..n])

// // Implements Floyd's algorithm for the all-pairs shortest-paths problem

// // Input:
// //    The weight matrix W of a graph with no negative-length cycle

// // Output:
// //    The distance matrix of the shortest paths' lengths

// D ← W          // Not necessary if W can be overwritten

// for k ← 1 to n do
//     for i ← 1 to n do
//         for j ← 1 to n do
//             D[i, j] ← min(D[i, j], D[i, k] + D[k, j])

// return D



import java.util.Scanner;

public class Floyd {

    static void floyd(int D[][], int n) {

        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    D[i][j] = min(D[i][j], D[i][k] + D[k][j]);
    }

    static int min(int a, int b) {
        return (a < b ? a : b);
    }

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        int n;

        System.out.println("Enter no. of Stations");
        n = s.nextInt();

        int[][] cost = new int[n][n];

        // Enter the cost matrix
        // 0 for principal diagonal
        // 999 for no direct edge

        System.out.println("Enter the travel time between subway lines");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                cost[i][j] = s.nextInt();

                if (cost[i][j] == 0 && i != j)
                    cost[i][j] = 999;
            }
        }

        floyd(cost, n);

        System.out.println("Shortest Travel Time between all stations");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(cost[i][j] + " ");

            System.out.println();
        }

        s.close();
    }
}
