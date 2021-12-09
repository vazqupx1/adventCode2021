import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class day9 {
    public static void findLowPoints() {
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);

            int rows = 1;
            int cols = myReader.nextLine().length();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                rows++;
            }

            int[][] map = new int[rows][cols];

            int i = 0;
            myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                for(int j = 0; j< cols; j++){
                    map[i][j] = data.charAt(j) - '0';
                }
                i++;
            }

            int sum = 0;
            for(i = 0; i< rows; i++){
                for(int j = 0; j< cols; j++){
                    if(isLow(map, i, j)){
                        sum += 1+ map[i][j];
                    }
                }
            }
            
            System.out.println(sum);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void findBasins() {
        try {
            File myObj = new File("input2.txt");
            Scanner myReader = new Scanner(myObj);

            int rows = 1;
            int cols = myReader.nextLine().length();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                rows++;
            }

            int[][] map = new int[rows][cols];

            int i = 0;
            myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                for(int j = 0; j< cols; j++){
                    map[i][j] = data.charAt(j) - '0';
                }
                i++;
            }

            boolean[][] visited = new boolean[rows][cols]; 
            List<Integer> basinSizes = new ArrayList<>();
            for(i = 0; i< rows; i++){
                for(int j = 0; j< cols; j++){
                    if(isLow(map, i, j)){
                        int sum = getBasin(map, i, j, visited, -1);
                        basinSizes.add(sum);
                    }
                }
            }

            basinSizes.sort((a,b) -> b-a);

            int res = basinSizes.get(0) * basinSizes.get(1) * basinSizes.get(2);
            
            System.out.println(res);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static int getBasin(int[][] map, int i, int j, boolean[][] visited, int prev){
        int m = map.length;
        int n = map[0].length;

        if(i == m || j == n || i< 0 || j < 0 || visited[i][j] || map[i][j] == 9){
            return 0;
        }

        int curr = map[i][j];
        if(prev != -1 && curr <= prev){
            return 0;
        }

        int sum = 1;

        visited[i][j] = true;
        
        sum += getBasin(map, i+1, j, visited, curr);
        sum += getBasin(map, i-1, j, visited, curr);
        sum += getBasin(map, i, j+1, visited, curr);
        sum += getBasin(map, i, j-1, visited, curr);

        return sum;
    }

    private static boolean isLow(int[][] map, int i, int j){
        int m = map.length;
        int n = map[0].length;

        int curr = map[i][j];
        if(i> 0 && map[i-1][j] <= curr){
            return false;
        }
        if(j> 0 && map[i][j-1] <= curr){
            return false;
        }
        if(i < m-1 && map[i+1][j] <= curr ){
            return false;
        }
        if(j < n-1 && map[i][j+1] <= curr){
            return false;
        }
        return true;
    }
}
