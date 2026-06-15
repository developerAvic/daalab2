// Algorithm GreedyKnapsack(m, n)

// // p[1:n] and w[1:n] contain the profits and weights
// // respectively of the n objects ordered such that
// // p[i]/w[i] ≥ p[i+1]/w[i+1]
// // m is the knapsack size
// // x[1:n] is the solution vector

// for i ← 1 to n do
//     x[i] ← 0.0          // Initialize solution vector

// U ← m                   // Remaining capacity

// for i ← 1 to n do
//     if w[i] > U then
//         break

//     x[i] ← 1.0          // Take entire object
//     U ← U − w[i]

// if i ≤ n then
//     x[i] ← U / w[i]     // Take fraction of object





import java.util.Scanner;

public class Knapsack1 {

    static int n;
    static float m, p[], w[];

    static void greedy() {

        float max, u = m, profit = 0;
        int k = 0, i;

        System.out.println("Items included are:");

        for (i = 0; i < n; i++) {

            max = 0;

            // Choose the item with highest profit/weight ratio
            for (int j = 0; j < n; j++) {

                if ((p[j] / w[j]) > max) {
                    k = j;
                    max = p[j] / w[j];
                }
            }

            // kth item has highest profit/weight ratio
            if (w[k] > u) {

                System.out.println((k + 1) + " item selected");
                System.out.println("Fraction selected is " + (u / w[k]));

                profit = profit + (p[k] * u / w[k]);
                break;
            }
            else {

                System.out.println((k + 1) + " item selected");
                System.out.println("Fraction is 1");

                u = u - w[k];
                profit = profit + p[k];

                // Prevent selecting the same item again
                p[k] = 0;
            }
        }

        System.out.println("Knapsack Profit = " + profit);
    }

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        System.out.println("Enter the no. of items");
        n = s.nextInt();

        p = new float[n];
        w = new float[n];

        System.out.println("Enter the weights of n items");
        for (int i = 0; i < n; i++)
            w[i] = s.nextFloat();

        System.out.println("Enter the profits of n items");
        for (int i = 0; i < n; i++)
            p[i] = s.nextFloat();

        System.out.println("Enter the capacity of Knapsack");
        m = s.nextFloat();

        greedy();

        s.close();
    }
}
