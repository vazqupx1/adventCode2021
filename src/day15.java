import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class day15 {
    static Long min = Long.MAX_VALUE;
    public static void findPath() {
        try {
            File myObj = new File("input2.txt");
            Scanner myReader = new Scanner(myObj);
            
            int[][] cave = null;
            int rows = 0;
            int cols = 0;

            List<String> list = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                list.add(data);
                cols = data.length();
                rows++;
            }

            cave = new int[rows][cols];

            for(int i = 0; i< rows; i++){
                String line = list.get(i);
                for(int j = 0; j< cols; j++){
                    cave[i][j] = line.charAt(j) - '0';
                }
            }


            int[][] dp = new int[rows][cols];
            for(int i = 0; i< rows; i++){
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }

            PriorityQueue<int[]> pq = new PriorityQueue<>( (a,b)-> a[0] - b[0]);
            pq.add(new int[]{0,0,0}); // path, r,c 
            while(pq.size()> 0){
                int[] top = pq.poll();
                int path = top[0];
                int r = top[1];
                int c = top[2];
                if(path > dp[r][c]){
                    continue;
                }

                if(r == rows-1 && c == cols - 1){
                    System.out.println(path);
                    return;
                }

                addToPQ(pq, r+1, c, cave, dp, path);
                addToPQ(pq, r-1, c, cave, dp, path);
                addToPQ(pq, r, c+1, cave, dp, path);
                addToPQ(pq, r, c-1, cave, dp, path);

            }
            // dp[0][0] = 0;
            // for(int j = 1; j< cols; j++){
            //     dp[0][j] = dp[0][j-1] + cave[0][j];
            // }
            // for(int i = 1; i< rows; i++){
            //     dp[i][0] = dp[i-1][0] + cave[i][0];
            // }
            // for(int i = 1; i< rows; i++){
            //     for(int j = 1; j< cols; j++){
            //         dp[i][j] = cave[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
            //     }
            // }
            
            System.out.println(dp[rows-1][cols-1]);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    } 

    private static void addToPQ(PriorityQueue<int[]> pq, int i, int j, int[][] cave, int[][] dp, int path){
        int m = cave.length *5;
        int n = cave[0].length *5;

        if(i< 0 || j < 0 || i == m || j == n ){
            return;
        }

        int newPath = path + getCor(cave, i, j);
        if(newPath < dp[i][j]){
            dp[i][j] = newPath;
            pq.add(new int[] {newPath, i, j });
        }
    }

    public static void findPathBigCave() {
        try {
            File myObj = new File("input2.txt");
            Scanner myReader = new Scanner(myObj);
            
            int[][] cave = null;
            int rows = 0;
            int cols = 0;

            List<String> list = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                list.add(data);
                cols = data.length();
                rows++;
            }

            cave = new int[rows][cols];

            for(int i = 0; i< rows; i++){
                String line = list.get(i);
                for(int j = 0; j< cols; j++){
                    cave[i][j] = line.charAt(j) - '0';
                }
            }


           
            rows *= 5;
            cols *= 5;
            int[][] dp = new int[rows][cols];
            for(int i = 0; i< rows; i++){
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }

            PriorityQueue<int[]> pq = new PriorityQueue<>( (a,b)-> a[0] - b[0]);
            pq.add(new int[]{0,0,0}); // path, r,c 
            while(pq.size()> 0){
                int[] top = pq.poll();
                int path = top[0];
                int r = top[1];
                int c = top[2];
                if(path > dp[r][c]){
                    continue;
                }

                if(r == rows-1 && c == cols - 1){
                    System.out.println(path);
                    return;
                }

                addToPQ(pq, r+1, c, cave, dp, path);
                addToPQ(pq, r-1, c, cave, dp, path);
                addToPQ(pq, r, c+1, cave, dp, path);
                addToPQ(pq, r, c-1, cave, dp, path);

            }
            // dp[0][0] = 0;
            // for(int j = 1; j< cols; j++){
            //     dp[0][j] = dp[0][j-1] + cave[0][j];
            // }
            // for(int i = 1; i< rows; i++){
            //     dp[i][0] = dp[i-1][0] + cave[i][0];
            // }
            // for(int i = 1; i< rows; i++){
            //     for(int j = 1; j< cols; j++){
            //         dp[i][j] = cave[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
            //     }
            // }
            
            System.out.println(dp[rows-1][cols-1]);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    } 

    private static int getCor(int[][] cave, int i, int j){
        int m = cave.length;
        int n = cave[0].length;

        int k = (i / m) + (j/m) ;
        int originI = i%m;
        int originJ = j%m;
        int val1 = (cave[originI][originJ] +k)% 9;
        if(val1 == 0){
            val1 = 9;
        }
        return val1;
    }

    // private static void findMinPath(int[][] cave, boolean[][] visited, int i, int j, long sum){
    //     int m = cave.length;
    //     int n = cave[0].length;

    //     if(i< 0 || j < 0 || i == m || j == n || visited[i][j]){
    //         return;
    //     }

    //     sum+= cave[i][j];
    //     if(i == m-1 && j == n-1){
    //         min = Math.min(sum, min);
    //         return;
    //     }

    //     visited[i][j] = true;
    //     findMinPath(cave, visited, i+1, j, sum);
    //    // findMinPath(cave, visited, i-1, j, sum);
    //     findMinPath(cave, visited, i, j+1, sum);
    //   //  findMinPath(cave, visited, i, j-1, sum);
    //     visited[i][j] = false;
    // }
}
