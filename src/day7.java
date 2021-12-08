import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day7 {
    public static void findMinMoves() {
        try {
            // File myObj = new File("input.txt");
            File myObj = new File("input2.txt");
            Scanner myReader = new Scanner(myObj);

            int[] nums = null;

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] num = data.split(",");

                int n = num.length;
                nums = new int[n];
                for(int i = 0; i< n; i++){
                    nums[i] = Integer.parseInt(num[i]);
                }
            }
            
            Arrays.sort(nums);
            int me = nums[nums.length/2];
            
            int count = 0;
            for(int crab: nums){
                count += Math.abs(crab - me);
            }

            System.out.println(count);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void findMinMovesWithIncresingCosts() {
        try {
            // File myObj = new File("input.txt");
            File myObj = new File("input2.txt");
            Scanner myReader = new Scanner(myObj);

            int[] nums = null;

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] num = data.split(",");

                int n = num.length;
                nums = new int[n];
                for(int i = 0; i< n; i++){
                    nums[i] = Integer.parseInt(num[i]);
                }
            }
            
            int len = nums.length;
            Arrays.sort(nums);
            int[] costs = new int[nums[len-1]+1];
            
            for(int crab: nums){
                int cost = 0;
                int step = 0;
                for(int i = crab; i>=0; i--){
                    costs[i]+= cost;
                    step++;
                    cost += step;
                }
                
                cost = 0;
                step = 0;
                for(int i = crab; i<=nums[len-1]; i++){
                    costs[i] += cost;
                    step++;
                    cost += step;
                }
            }

            int index = 0;
            for(int i = 0; i< costs.length; i++){
                if(costs[index] > costs[i]){
                    index = i;
                }
            }

            System.out.println(costs[index]);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
