import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class day8 {
    public static void findSpecialDigits() {
        try {
            File myObj = new File("input2.txt");
            Scanner myReader = new Scanner(myObj);

            int count = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split("\\|");

                String[] output = parts[1].trim().split(" ");

                for(String o: output){
                    int len = o.length();
                    if(len == 2 || len == 4 || len == 3 || len == 7){
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

    public static void sumOfOutput() {
        try {
            File myObj = new File("input2.txt");
            Scanner myReader = new Scanner(myObj);

            int sum = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split("\\|");

                String[] input = parts[0].trim().split(" ");
                char[] chars = new char[7];
                String s1 = "";
                StringBuilder s235 = new StringBuilder();
                StringBuilder s069 = new StringBuilder();
                String s4 = "";
                String s7 = "";
                String s8 = "";

                for(String s : input){
                    int len = s.length();
                    if(len == 2){
                        s1 = s;
                    }else if(len == 5){
                        s235.append(s);
                    }else if(len == 6){
                        s069.append(s);
                    }else if(len == 4){
                        s4 = s;
                    }else if(len == 3){
                        s7 = s;
                    }else{
                        s8 = s;
                    }
                }

                // 7 -1 = a
                HashSet<Character> set = new HashSet<>();
                for(char c : s7.toCharArray()){
                    set.add(c);
                }
                for(char c: s1.toCharArray()){
                    set.remove(c);
                }
                chars[0] =  set.toArray(new Character[0])[0];

                // 4 -1 = b d
                set = new HashSet<>();
                for(char c : s4.toCharArray()){
                    set.add(c);
                }
                for(char c: s1.toCharArray()){
                    set.remove(c);
                }
                Character[] bd = set.toArray(new Character[0]);

                // 8 - 7- 4 = eg
                set = new HashSet<>();
                for(char c : s8.toCharArray()){
                    set.add(c);
                }
                for(char c: s7.toCharArray()){
                    set.remove(c);
                }
                for(char c: s4.toCharArray()){
                    set.remove(c);
                }
                Character[] eg = set.toArray(new Character[0]);

                // 3*8 - 235 = bbfcee
                List<Character> bbfcee = new ArrayList<>();
                for(char c : s8.toCharArray()){
                    bbfcee.add(c);
                    bbfcee.add(c);
                    bbfcee.add(c);
                }
                for(char c: s235.toString().toCharArray()){
                    bbfcee.remove((Character) c);
                }
                set = new HashSet<>();
                List<Character> be = new ArrayList<>();
                for(char c: bbfcee){
                    if(set.contains(c)){
                        be.add(c);
                        set.remove(c);
                    }
                    else{
                        set.add(c);
                    }
                }
                //set has c, f
                for(char c: be){
                    if(c == eg[0] || c == eg[1]){
                        chars[4] = c;
                    }else{
                        chars[1] = c;
                    }
                }

                if(bd[0] == chars[1]){
                    chars[3] = bd[1];
                }else{
                    chars[3] = bd[0];
                }

                if(eg[0] == chars[4]){
                    chars[6] = eg[1];
                }else{
                    chars[6] = eg[0];
                }

                // 3*8 - 069 = cde
                List<Character> cde = new ArrayList<>();
                for(char c : s8.toCharArray()){
                    cde.add(c);
                    cde.add(c);
                    cde.add(c);
                }
                for(char c: s069.toString().toCharArray()){
                    cde.remove((Character) c);
                }
                cde.remove((Character) chars[3]);
                cde.remove( (Character) chars[4]);
                chars[2] = cde.get(0);

                String[] output = parts[1].trim().split(" ");

                int curr = 0;
                for(String o: output){
                    curr *= 10;
                    int len = o.length();
                    if(len == 2){
                        curr += 1;
                    }else if(len == 3){
                        curr += 7;
                    }else if(len == 4){
                        curr += 4;
                    }else if(len == 7){
                        curr += 8;
                    }else if(len == 5){
                        if(o.indexOf(chars[1]) != -1){
                            curr += 5;
                        }else if(o.indexOf(chars[4]) != -1){
                            curr += 2;
                        }else{
                            curr += 3;
                        }
                    }else{
                        if(o.indexOf(chars[3]) == -1){
                            curr += 0;
                        }else if(o.indexOf(chars[4]) == -1){
                            curr += 9;
                        }else{
                            curr += 6;
                        }
                    }
                }
                sum += curr;
            }
            
            System.out.println(sum);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
