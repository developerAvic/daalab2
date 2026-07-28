// Online Java Compiler
// Use this editor to write, compile and run your Java code online
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
