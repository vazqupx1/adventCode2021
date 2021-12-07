import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class day2 {
    public static void findRange() {
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);

            int product = 0;
            int depth = 0;
            int forward = 0;

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String num = data.split(" ")[1];
                int n = Integer.parseInt(num);

                if(data.indexOf("forward ") != -1){
                    forward += n;
                } else if(data.indexOf("down ") != -1){
                    depth += n;
                }else{
                    depth -= n;
                }
            }
            
            product = depth*forward;
            System.out.println(product);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void findRangeWithAim() {
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);

            int product = 0;
            int depth = 0;
            int forward = 0;
            int aim = 0;

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String num = data.split(" ")[1];
                int n = Integer.parseInt(num);

                if(data.indexOf("forward ") != -1){
                    forward += n;
                    depth += aim*n;
                } else if(data.indexOf("down ") != -1){
                    aim += n;
                }else{
                    aim -= n;
                }
            }
            
            product = depth*forward;
            System.out.println(product);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
