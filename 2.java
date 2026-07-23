import java.util.Random;
import java.util.Scanner;

public class QuickSort {

    static int count = 0;

    // Partitions a subarray by Hoare's algorithm, using the first element as a pivot
    // Input: Subarray A[l..r]
    // Output: Partition index
    static int partition(int a[], int l, int r) {

        int pivot = a[l], temp, i = l, j = r + 1;

        do {
            // Traverse i from left to right
            do {
                i++;
                count++;
            } while (i < r && a[i] <= pivot);

            // Traverse j from right to left
            do {
                j--;
                count++;
            } while (j > l && a[j] >= pivot);

            // Swap a[i] and a[j]
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;

        } while (i < j);

        // Undo the last swap
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;

        // Swap pivot with a[j]
        temp = a[l];
        a[l] = a[j];
        a[j] = temp;

        return j;
    }

    // Quicksort algorithm
    static void quicksort(int a[], int l, int r) {

        if (l < r) {
            int s = partition(a, l, r);

            quicksort(a, l, s - 1);
            quicksort(a, s + 1, r);
        }
    }

    public static void main(String args[]) {

        Scanner s = new Scanner(System.in);
        Random r = new Random();

        System.out.print("Enter the no. of elements: ");
        int n = s.nextInt();

        int a[] = new int[n];

        // Generate random numbers
        System.out.println("Input numbers:");
        for (int i = 0; i < n; i++) {
            a[i] = r.nextInt(10000);
            System.out.print(a[i] + " ");
        }

        // Sort the array
        quicksort(a, 0, n - 1);

        // Display sorted array
        System.out.println("\n\nSorted numbers are:");
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }

        // Display complexity information
        System.out.println("\n\nBest Case: " + (int) (n * Math.log(n) / Math.log(2)));
        System.out.println("No. of basic operations: " + count);
        System.out.println("Worst Case: " + (n * n));

        s.close();
    }
}
