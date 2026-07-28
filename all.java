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


----------------------------------------------------------------------------------------------


import java.util.Random;
import java.util.Scanner;

public class SeqSort {

    static int searchcount = 0;
    static int sortcount = 0;

    // Sequential Search
    public static int sequentialSearch(int[] a, int key) {
        int n = a.length;

        for (int i = 0; i < n; i++) {
            searchcount++;

            if (a[i] == key) {
                return i;
            }
        }

        return -1;
    }

    // Selection Sort
    public static void selectionSort(int[] a) {
        int n = a.length;

        for (int i = 0; i < n - 1; i++) {
            int min = i;

            for (int j = i + 1; j < n; j++) {
                sortcount++;

                if (a[j] < a[min]) {
                    min = j;
                }
            }

            int temp = a[min];
            a[min] = a[i];
            a[i] = temp;
        }
    }

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        Random r = new Random();

        System.out.print("Enter the number of elements (n > 5000): ");
        int n = s.nextInt();

        int[] a = new int[n];

        // Generate random numbers
        for (int i = 0; i < n; i++) {
            a[i] = r.nextInt(10000); // Random numbers between 0 and 9999
        }

        // Display a random element from the array
        int rnd = r.nextInt(a.length);
        System.out.println("Random number is: " + a[rnd]);

        // Search
        System.out.print("Enter the key to search: ");
        int key = s.nextInt();

        int index = sequentialSearch(a, key);

        if (index != -1) {
            System.out.println("Key found at index: " + index);
        } else {
            System.out.println("Key not found");
        }

        System.out.println("Number of basic operations for searching is: " + searchcount);

        // Sort
        selectionSort(a);

        System.out.println("First 5 sorted numbers are:");
        for (int i = 0; i < 5; i++) {
            System.out.println(a[i]);
        }

        System.out.println("Number of basic operations for sorting is: " + sortcount);

        s.close();
    }
}


-----------------------------------------------------------------------------------------------


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


------------------------------------------------------------------------------------------------


import java.util.*;
class Main {
    public static void main(String[] args) {
         Scanner s = new Scanner(System.in);

        System.out.println("Enter the no. of Cities");
        int n = s.nextInt();

        int[][] c = new int[n][n];

        System.out.println("Enter the cost of laying pipelines between the two cities");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                c[i][j] = s.nextInt();

                if (c[i][j] == 0) {
                    c[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        System.out.print("Enter the source city: ");
        int source = s.nextInt();

        prim(c, n, source);

    }
    
    static void prim(int[][]cost, int n, int source){
        int[]visited = new int[n];
        int a=0, b=0, ne=0, mincost=0;
        visited[source] = 1;
        while(ne<n-1){
            int min = Integer.MAX_VALUE;
            for(int i = 0; i<n; i++){
                if(visited[i] == 1){
                    for(int j = 0 ; j<n; j++){
                        if(cost[i][j] < min && visited[j] == 0){
                            min = cost[i][j];
                            a=i;
                            b=j;
                        }
                    }
                }
            }
            System.out.println(a + " to " + b + " cost = " + min);
            mincost += min;
            visited[b] = 1;
            ne++;
        }
        System.out.println("min cost is: " +mincost);
    }
}


---------------------------------------------------------------------------------------------


import java.util.Random;
import java.util.Scanner;

public class MergeSort {

    public static int count = 0;

    // Merges two sorted arrays into one sorted array
    static void merge(int b[], int c[], int a[]) {

        int i = 0, j = 0, k = 0;
        int p = b.length;
        int q = c.length;

        while ((i < p) && (j < q)) {
            count++;

            if (b[i] <= c[j]) {
                a[k++] = b[i++];
            } else {
                a[k++] = c[j++];
            }
        }

        // Copy the remaining elements
        while (i < p) {
            a[k++] = b[i++];
        }

        while (j < q) {
            a[k++] = c[j++];
        }
    }

    // Recursive Merge Sort
    static void mergesort(int a[]) {

        int n = a.length;

        if (n > 1) {
            int p = (int) Math.floor(n / 2);
            int q = (int) Math.ceil(n / 2);

            int b[] = new int[p];
            int c[] = new int[q];

            System.arraycopy(a, 0, b, 0, p);
            System.arraycopy(a, p, c, 0, q);

            mergesort(b);
            mergesort(c);

            merge(b, c, a);
        }
    }

    public static void main(String[] args) {

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

        // Perform Merge Sort
        mergesort(a);

        // Display sorted array
        System.out.println("\n\nSorted numbers are:");
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }

        System.out.println("\n\nNo. of basic operations are " + count);

        s.close();
    }
}


--------------------------------------------------------------------------------------------

  
  
import java.util.*;
class Main {
    public static void main(String[] args) {
         Scanner s = new Scanner(System.in);

        System.out.println("Enter the no. of vertices");
        int n = s.nextInt();

        int[][] c = new int[n][n];

        System.out.println("Enter the cost");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                c[i][j] = s.nextInt();

                if (c[i][j] == 0) {
                    c[i][j] = Integer.MAX_VALUE;
                }
            }
        }


        kruskal(c, n);

    }
    
    static void kruskal(int[][]cost, int n){
        int[] parent = new int[n];
        for(int i = 0 ; i < n ; i++){
            parent[i] = -1;
        }
        int mincost = 0;
        int ne = 0;
        while(ne < n-1){
            int min = Integer.MAX_VALUE;
            int a=0,b=0,v=0,u=0;
            for(int i = 0 ; i < n ; i ++){
                for(int j = 0; j < n; j++){
                    if(cost[i][j] < min){
                        min = cost[i][j];
                        a= u=i;
                        b=v=j;
                    }
                }
            }
            while(parent[u] != -1){
                u = parent[u];
            }
            while(parent[v] != -1){
                v = parent[v];
            }
            if(u != v){
                System.out.println( a + " to " + b + " cost " + min);
                mincost += min;
                if(u < v){
                    parent[v] = u;
                } else {
                    parent[u] = v;
                }
                ne++;
            }
            cost[a][b] = Integer.MAX_VALUE;
        }
        System.out.println("mincost: " + mincost);
    }
}



----------------------------------------------------------------------------------------------


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


----------------------------------------------------------------------------------------------


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


------------------------------------------------------------------------------------------------


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


----------------------------------------------------------------------------------------------


import java.util.Scanner;

public class Dijkstra {

    static int min(int m, int n) {
        return (m < n) ? m : n;
    }

    static void dijkstra(int cost[][], int source, int n) {

        int[] tvertex = new int[n];
        int[] dist = new int[n];

        int min, u = 0, v, i, j;

        // Copy source row to distance array
        System.arraycopy(cost[source], 0, dist, 0, n);

        // Source vertex initialization
        tvertex[source] = 1;
        dist[source] = 0;

        for (i = 0; i < n - 1; i++) {

            // Find nearest unvisited vertex
            min = Integer.MAX_VALUE;

            for (j = 0; j < n; j++) {
                if (tvertex[j] == 0 && dist[j] < min) {
                    min = dist[j];
                    u = j;
                }
            }

            tvertex[u] = 1;

            // Update shortest paths
            for (v = 0; v < n; v++) {

                if (tvertex[v] == 0 &&
                    cost[u][v] != Integer.MAX_VALUE) {

                    dist[v] = min(
                            dist[v],
                            dist[u] + cost[u][v]
                    );
                }
            }
        }

        System.out.println("The shortest travel time");

        for (v = 0; v < n; v++) {
            System.out.println(
                "from " + source +
                " city to " + v +
                " city is " + dist[v]
            );
        }
    }

    public static void main(String[] args) {

        int source, n;

        Scanner s = new Scanner(System.in);

        System.out.println("Enter the no. of cities");
        n = s.nextInt();

        int[][] cost = new int[n][n];

        System.out.println(
            "Enter the travel time in hours between every two cities"
        );

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                cost[i][j] = s.nextInt();

                if (cost[i][j] == 0)
                    cost[i][j] = Integer.MAX_VALUE;
            }
        }

        System.out.println("Enter the source city");
        source = s.nextInt();

        dijkstra(cost, source, n);

        s.close();
    }
}
