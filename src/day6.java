
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day6 {
    public static void populateFish() {
        try {
            File myObj = new File("input.txt");
            // File myObj = new File("input2.txt");
            Scanner myReader = new Scanner(myObj);

            List<Integer> fish = new ArrayList<>();

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] num = data.split(",");

                for(String f: num){
                    fish.add(Integer.parseInt(f));
                }
            }
            
                school sc = new school(fish);
            for(int i = 0; i<18; i++){
                sc.addDay();
            }
            System.out.println(sc.fish.size());
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void populateBigFish() {
        try {
            // File myObj = new File("input.txt");
            File myObj = new File("input2.txt");
            Scanner myReader = new Scanner(myObj);

            List<Integer> fish = new ArrayList<>();

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] num = data.split(",");

                for(String f: num){
                    fish.add(Integer.parseInt(f));
                }
            }
            
                bigSchool sc = new bigSchool(fish);
            for(int i = 0; i<256; i++){
                sc.addDay();
            }

            long count =0;
            for(long c: sc.fish){
                count += c;
            }
            System.out.println(count);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
