import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class day13 {
    public static void countPoints() {
        try {
            File myObj = new File("input2.txt");
            Scanner myReader = new Scanner(myObj);
            
            int temp = 0;

            boolean[][] paper = null;
            List<int[]> points = new ArrayList<>();
            int m = 0;
            int n = 0;
            List<Character> dirs = new ArrayList<>();
            List<Integer> lines = new ArrayList<>();

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data.length() == 0){
                    continue;
                }
                if(Character.isAlphabetic( data.charAt(0)) ){
                    //is line
                    String[] parts = data.split("=");
                    dirs.add(parts[0].charAt(parts[0].length()-1));
                    lines.add(Integer.parseInt(parts[1]));
                }else{
                    // is point
                    String[] parts = data.split(",");
                    int col = Integer.parseInt(parts[0]);
                    int row =  Integer.parseInt(parts[1]);
                    int[] point = new int[2];
                    point[0] = col;
                    point[1] = row;
                    if(col> n){
                        n = col;
                    }
                    if(row > m){
                        m = row;
                    }
                    points.add(point);
                } 
            }

            m++;
            n++;
            paper = new boolean[m][n];
            for(int[] p: points){
                paper[p[1]][p[0]] = true;
            }

            int currM = m;
            int currN = n;

            Character dir = dirs.get(0);
            int line = lines.get(0);
            if(dir == 'y'){
                for(int i = line+1; i< currM; i++){
                    for(int j = 0; j< currN; j++){
                        paper[line - (i-line) ][j] |= paper[i][j];
                    }
                }

                currM = line+1;
            }else{
                for(int i = 0; i< currM; i++){
                    for(int j = line+1; j< currN; j++){
                        paper[i ][line - (j-line)] |= paper[i][j];
                    }
                }

                currN = line+1;
            }

            for(int i = 0; i< currM; i++){
                for(int j = 0; j< currN; j++){
                    if(paper[i][j]){
                        temp++;
                    }
                }
            }
            
            System.out.println(temp);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    } 

    public static void printPaper() {
        try {
            File myObj = new File("input2.txt");
            Scanner myReader = new Scanner(myObj);
            
            int temp = 0;

            boolean[][] paper = null;
            List<int[]> points = new ArrayList<>();
            int m = 0;
            int n = 0;
            List<Character> dirs = new ArrayList<>();
            List<Integer> lines = new ArrayList<>();

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data.length() == 0){
                    continue;
                }
                if(Character.isAlphabetic( data.charAt(0)) ){
                    //is line
                    String[] parts = data.split("=");
                    dirs.add(parts[0].charAt(parts[0].length()-1));
                    lines.add(Integer.parseInt(parts[1]));
                }else{
                    // is point
                    String[] parts = data.split(",");
                    int col = Integer.parseInt(parts[0]);
                    int row =  Integer.parseInt(parts[1]);
                    int[] point = new int[2];
                    point[0] = col;
                    point[1] = row;
                    if(col> n){
                        n = col;
                    }
                    if(row > m){
                        m = row;
                    }
                    points.add(point);
                } 
            }

            m++;
            n++;
            paper = new boolean[m][n];
            for(int[] p: points){
                paper[p[1]][p[0]] = true;
            }

            int currM = m;
            int currN = n;

            for(int k = 0; k< dirs.size(); k++){
                Character dir = dirs.get(k);
                int line = lines.get(k);
                if(dir == 'y'){
                    for(int i = line+1; i< currM; i++){
                        for(int j = 0; j< currN; j++){
                            if(line - (i-line) < 0){
                                continue;
                            }
                            paper[line - (i-line) ][j] |= paper[i][j];
                        }
                    }
    
                    currM = line+1;
                }else{
                    for(int i = 0; i< currM; i++){
                        for(int j = line+1; j< currN; j++){
                            if(line - (j-line) < 0){
                                continue;
                            }
                            paper[i ][line - (j-line)] |= paper[i][j];
                        }
                    }
    
                    currN = line+1;
                }
            }

            for(int i = 0; i< currM; i++){
                StringBuilder sb = new StringBuilder();

                for(int j = 0; j< currN; j++){
                    sb.append(paper[i][j]? '#': '.');
                }

                System.out.println(sb.toString());
            }
            
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    } 
}
