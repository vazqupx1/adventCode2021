import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class template {
    public static void tempName() {
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            
            int temp = 0;

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                

            }
            
            System.out.println(temp);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    } 
}
