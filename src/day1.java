import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class day1 {
    public static void findIncrease() {
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);

            int count = 0;
            int prev = Integer.MAX_VALUE;

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                int curr = Integer.parseInt(data);
                if (curr > prev) {
                    count++;
                }
                prev = curr;
            }
            System.out.println(count);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void findIncreaseOfGroup3() {
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);

            int count = 0;
            Queue<Integer> q = new LinkedList<>();
            int prevSum = 0;
            boolean first = true;

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                int curr = Integer.parseInt(data);
                
                int currSum = prevSum;

                if(q.size()==3){
                    currSum -= q.poll();
                }

                currSum += curr;
                q.add(curr);

                if(q.size() ==3 && prevSum < currSum){
                    if(first){
                        first = false;
                    }else{
                        count++;
                    }
                }

                prevSum = currSum;
            }
            System.out.println(count);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
