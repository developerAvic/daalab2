import java.util.Scanner;

public class DPKnapsack {

    static int n, m;
    static int[] p, w;

    static void knapsackDP() {

        int[][] v = new int[n + 1][m + 1];

        // Construct DP table
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {

                if (i == 0 || j == 0)
                    v[i][j] = 0;

                else if (j < w[i])   // Weight exceeds capacity
                    v[i][j] = v[i - 1][j];

                else
                    v[i][j] = max(
                            v[i - 1][j],
                            p[i] + v[i - 1][j - w[i]]
                    );
            }
        }

        // Display DP table
        System.out.println("DP Table:");

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++)
                System.out.print(v[i][j] + " ");

            System.out.println();
        }

        // Track back the optimal solution
        System.out.println("OPTIMAL PROFIT IS: " + v[n][m]);

        System.out.print(
            "Products selected for shelf that yields maximum profit are: "
        );

        while (n != 0) {
            if (v[n][m] != v[n - 1][m]) {
                System.out.print(n + " ");
                m = m - w[n];
            }
            n--;
        }
    }

    static int max(int a, int b) {
        return (a > b ? a : b);
    }

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        System.out.println("Enter the no. of products");
        n = s.nextInt();

        p = new int[n + 1];
        w = new int[n + 1];

        System.out.println("Enter the weights of n products");
        for (int i = 1; i <= n; i++)
            w[i] = s.nextInt();

        System.out.println("Enter the profits of n products");
        for (int i = 1; i <= n; i++)
            p[i] = s.nextInt();

        System.out.println("Enter the capacity of shelf (Knapsack)");
        m = s.nextInt();

        knapsackDP();

        s.close();
    }
}
