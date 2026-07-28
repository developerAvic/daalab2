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
