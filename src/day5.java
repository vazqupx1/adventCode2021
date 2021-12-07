import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class day5 {
    public static void findPoints() {
        try {
            File myObj = new File("input2.txt");
            Scanner myReader = new Scanner(myObj);

            List<int[]> lines = new ArrayList<>();
            
            int maxRow = -1;
            int maxCol = -1;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                String[] row = data.split("->");
                int[] line = new int[4];
                String[] row1 = row[0].split(",");
                String[] row2 = row[1].split(",");
                line[0] = Integer.parseInt(row1[0].trim());
                line[1] = Integer.parseInt(row1[1].trim());
                line[2] = Integer.parseInt(row2[0].trim());
                line[3] = Integer.parseInt(row2[1].trim());

                if(line[0] == line[2]){
                    // same col, diff row
                    int max = Math.max(line[1], line[3]);
                    if(maxRow < max){
                        maxRow = max;
                    }
                    if(line[0] > maxCol){
                        maxCol = line[0];
                    }
                lines.add(line);

                }

                if(line[1] == line[3]){
                    //same row, diff col
                    int max = Math.max(line[0], line[2]);
                    if(maxCol < max){
                        maxCol = max;
                    }
                    if(line[1] > maxRow){
                        maxRow = line[1];
                    }
                lines.add(line);

                }
            }

            canvas c = new canvas(maxCol, maxRow);
            for (int[] l : lines) {
                c.addLine(l);
            }

            int count = 0;
            for (int i = 0; i <= c.maxRow; i++) {
                for (int j = 0; j <= c.maxCol; j++) {
                    if (c.points[i][j] > 1) {
                        count++;
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

    public static void findPointsIncludeDiagnal() {
        try {
            File myObj = new File("input2.txt");
            Scanner myReader = new Scanner(myObj);

            List<int[]> lines = new ArrayList<>();
            
            int maxRow = -1;
            int maxCol = -1;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                String[] row = data.split("->");
                int[] line = new int[4];
                String[] row1 = row[0].split(",");
                String[] row2 = row[1].split(",");
                line[0] = Integer.parseInt(row1[0].trim());
                line[1] = Integer.parseInt(row1[1].trim());
                line[2] = Integer.parseInt(row2[0].trim());
                line[3] = Integer.parseInt(row2[1].trim());

                int currCol = Math.max(line[0], line[2]);
                int currRow = Math.max(line[1], line[3]);
                maxCol = Math.max(maxCol, currCol);
                maxRow = Math.max(maxRow, currRow);
                lines.add(line);

            }

            canvas c = new canvas(maxCol, maxRow);
            for (int[] l : lines) {
                c.addLineIncludeDiagnol(l);
            }

            int count = 0;
            for (int i = 0; i <= c.maxRow; i++) {
                for (int j = 0; j <= c.maxCol; j++) {
                    if (c.points[i][j] > 1) {
                        count++;
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
}
