import java.util.*;

class Quick {

    static int count = 0;

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        Random r = new Random();

        System.out.print("Enter n: ");
        int n = s.nextInt();

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = r.nextInt(10000);
        }

        quicksort(a, 0, n - 1);

        System.out.println("\n\nSorted Array:");

        for (int i = 0; i < 10; i++) {
            System.out.print(a[i] + " ");
        }

        System.out.println("\n\nBasic Operations: " + count);
    }

    static int partition(int[] a, int low, int high) {

        int pivot = a[low];
        int temp;
        int i = low;
        int j = high + 1;

        do {

            do {
                i++;
                count++;
            } while (i < high && a[i] <= pivot);

            do {
                j--;
                count++;
            } while (j > low && a[j] >= pivot);

            if (i < j) {
                temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }

        } while (i < j);

        temp = a[low];
        a[low] = a[j];
        a[j] = temp;

        return j;
    }

    static void quicksort(int[] a, int low, int high) {

        if (low < high) {

            int s = partition(a, low, high);

            quicksort(a, low, s - 1);
            quicksort(a, s + 1, high);
        }
    }
}
