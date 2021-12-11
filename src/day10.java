import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class day10 {
    public static void findCorrupted() {
        try {
            File myObj = new File("input2.txt");
            Scanner myReader = new Scanner(myObj);


            int score = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                Stack<Character> stack = new Stack<>();

                for(char c : data.toCharArray()){
                    if(c == '(' || c == '<' || c == '{' || c == '['){
                        stack.push(c);
                    }else{
                        char prev = stack.pop();
                        if(c == ')'){
                            if(prev != '('){
                                score += calcSocre(c);
                                break;
                            }
                        }else if(c == '}'){
                            if(prev != '{'){
                                score += calcSocre(c);
                                break;
                            }
                        }else if(c == ']'){
                            if(prev != '['){
                                score += calcSocre(c);
                                break;
                            }
                        }else{
                            if(prev != '<'){
                                score += calcSocre(c);
                                break;
                            }
                        }
                    }

                }
            }
            
            System.out.println(score);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static int calcSocre(int c){
        if(c == ')'){
            return 3;
        }
        if(c == ']'){
            return 57;
        }
        if(c == '}'){
            return 1197;
        }
        if(c == '>'){
            return 25137;
        }
        return 0;
    }

    public static void fixImcomplete() {
        try {
            File myObj = new File("input2.txt");
            Scanner myReader = new Scanner(myObj);


            List<Long> scores = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                Stack<Character> stack = new Stack<>();

                boolean cor = false;
                for(char c : data.toCharArray()){
                    if(c == '(' || c == '<' || c == '{' || c == '['){
                        stack.push(c);
                    }else{
                        char prev = stack.pop();
                        if(c == ')'){
                            if(prev != '('){
                                cor = true;
                                break;
                            }
                        }else if(c == '}'){
                            if(prev != '{'){
                                cor = true;
                                break;
                            }
                        }else if(c == ']'){
                            if(prev != '['){
                                cor = true;
                                break;
                            }
                        }else{
                            if(prev != '<'){
                                cor = true;
                                break;
                            }
                        }
                    }

                }
                if(cor){
                    continue;
                }
                List<Character> list = new ArrayList<>();
                while(stack.size()>0){
                   list.add(stack.pop()); 
                }

                long total = 0;
                for(char c : list){
                    total *= 5;
                    if(c == '('){
                        total += 1;
                    }else if( c == '['){
                        total += 2;
                    }else if(c == '{'){
                        total += 3;
                    }else{
                        total += 4;
                    }
                }
                scores.add(total);
            }
            
            scores.sort((o1, o2) -> o2.compareTo(o1));
            System.out.println(scores.get(scores.size()/2));
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
