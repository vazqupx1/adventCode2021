import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day11 {
    static int count = 0;
    public static void flash() {
        try {
            File myObj = new File("input2.txt");
            Scanner myReader = new Scanner(myObj);

            int[][] os = new int[10][10];

            int i = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                

                for(int j = 0; j< 10; j++){
                    os[i][j] = data.charAt(j) - '0';
                }
                i++;
            }

            for(int k = 0; k< 100; k++){
                boolean[][] flashed = new boolean[10][10];
                for(i = 0; i< 10; i++){
                    for(int j = 0; j< 10; j++){
                        if(flashed[i][j]){
                            continue;
                        }

                        increase(os, flashed, i, j);
                    }
                }
            }
            
            System.out.println(count);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    } 

    public static void calculateAllFlash() {
        try {
            File myObj = new File("input2.txt");
            Scanner myReader = new Scanner(myObj);

            int[][] os = new int[10][10];

            int i = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                

                for(int j = 0; j< 10; j++){
                    os[i][j] = data.charAt(j) - '0';
                }
                i++;
            }

            for(int k = 0; k< 100000; k++){
                count = 0;
                boolean[][] flashed = new boolean[10][10];
                for(i = 0; i< 10; i++){
                    for(int j = 0; j< 10; j++){
                        if(flashed[i][j]){
                            continue;
                        }

                        increase(os, flashed, i, j);
                    }
                }
                if(count == 100){
                    System.out.println(k+1);
                    break;
                }
            }
            
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    } 

    private static void increase(int[][] os, boolean[][] flashed, int i, int j){
        if(i < 0 || j< 0 || i==10 || j == 10 || flashed[i][j]){
            return;
        }
        os[i][j]++;

        if(os[i][j]> 9){
            flashed[i][j] = true;
            count++;
            increase(os, flashed, i+1, j);
            increase(os, flashed, i-1, j);
            increase(os, flashed, i+1, j+1);
            increase(os, flashed, i+1, j-1);
            increase(os, flashed, i-1, j+1);
            increase(os, flashed, i-1, j-1);
            increase(os, flashed, i, j+1);
            increase(os, flashed, i, j-1);
            os[i][j] = 0;
        }
    }
}
