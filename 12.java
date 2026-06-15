// ### Sum of Subsets Algorithm

// **1. Mark Current Element as Selected**

// * Set `x[k] = 1`.

// **2. Check if Adding `w[k]` to Current Sum Matches Target**

// * If `s + w[k] == d`, then:

//   * A valid subset is found.
//   * Increment and print the subset count.
//   * Print all elements `i` where `x[i] == 1` (i.e., the selected elements and their categories).

// **3. Left Subtree (Include Current Element)**

// * If:

//   * `k + 1` is a valid index, **and**
//   * `s + w[k] + w[k+1] <= d`
// * Then:

//   ```text
//   sum_of_subsets(s + w[k], k + 1, rem - w[k])
//   ```
// * This explores the case where the current item is **included** in the subset.

// **4. Right Subtree (Exclude Current Element)**

// * If:

//   * `k + 1` is a valid index, **and**
//   * `s + rem - w[k] >= d`, **and**
//   * `s + w[k+1] <= d`
// * Then:

//   * Set `x[k] = 0` (exclude the current element).
//   * Recur:

//     ```text
//     sum_of_subsets(s, k + 1, rem - w[k])
//     ```
// * This explores the case where the current item is **excluded** from the subset.






// Program to implement Sum of Subsets

import java.util.Scanner;

public class SumofSubsets {

    static int x[], w[], count, d;
    static String[] categories;

    static void sum_of_subsets(int s, int k, int rem) {

        x[k] = 1;

        if (s + w[k] == d) {   // if subset found

            System.out.println("Subset/Event Planning = " + ++count);

            for (int i = 0; i <= k; i++) {
                if (x[i] == 1)
                    System.out.println(categories[i] + " : " + w[i]);
            }

            System.out.println();

        } else if (s + w[k] + w[k + 1] <= d) {   // left tree evaluation

            sum_of_subsets(s + w[k], k + 1, rem - w[k]);
        }

        // right tree evaluation
        if ((s + rem - w[k] >= d) && (s + w[k + 1] <= d)) {

            x[k] = 0;
            sum_of_subsets(s, k + 1, rem - w[k]);
        }
    }

    public static void main(String[] args) {

        int sum = 0, n;
        Scanner s = new Scanner(System.in);

        System.out.println("Enter no of categories");
        n = s.nextInt();

        w = new int[n];
        x = new int[n];
        categories = new String[n];

        s.nextLine();

        System.out.println(
                "Enter the category names followed by their fixed budgets (in increasing order of budget):");

        for (int i = 0; i < n; i++) {

            System.out.print("Category " + (i + 1) + " name: ");
            categories[i] = s.nextLine();

            System.out.print("Category " + (i + 1) + " budget: ");
            w[i] = Integer.parseInt(s.nextLine());

            // Use nextLine to avoid newline issues
            sum += w[i];
        }

        System.out.println("Enter the budget to host the event");
        d = s.nextInt();

        if ((sum < d) || (w[0] > d))
            System.out.println("No subset possible - Unable to host the events");
        else
            sum_of_subsets(0, 0, sum);

        s.close();
    }
}
