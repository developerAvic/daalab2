import java.util.Scanner;

public class GreedyKnapsack {

    static int n;
    static float m, p[], w[];

    static void greedy() {

        float max, u = m, profit = 0;
        int k = 0, i;

        System.out.println("Items included are:");

        for (i = 0; i < n; i++) {

            max = 0;

            // Choose the item with the highest profit-to-weight ratio
            for (int j = 0; j < n; j++) {
                if ((p[j] / w[j]) > max) {
                    k = j;
                    max = p[j] / w[j];
                }
            }

            // kth element has the highest profit-to-weight ratio
            if (w[k] > u) {

                System.out.println((k + 1) + " item selected");
                System.out.println("Fraction selected is " + (u / w[k]));

                profit = profit + (p[k] * u / w[k]);
                break;

            } else {

                System.out.println((k + 1) + " item is selected");
                System.out.println("Fraction is 1");

                u = u - w[k];
                profit = profit + p[k];

                // This item should not be selected in the next iteration
                p[k] = 0;
            }
        }

        System.out.println("Knapsack profit = " + profit);
    }

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        System.out.println("Enter the no. of items");
        n = s.nextInt();

        p = new float[n];
        w = new float[n];

        System.out.println("Enter the weights of n items");
        for (int i = 0; i < n; i++) {
            w[i] = s.nextFloat();
        }

        System.out.println("Enter the profits of n items");
        for (int i = 0; i < n; i++) {
            p[i] = s.nextFloat();
        }

        System.out.println("Enter the capacity of Knapsack");
        m = s.nextFloat();

        greedy();

        s.close();
    }
}
