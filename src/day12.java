import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class day12 {
    static int ways = 0;
    static int count = 0;
    static String small = "";
    public static void findWays() {
        try {
            File myObj = new File("input2.txt");
            Scanner myReader = new Scanner(myObj);
            
            int temp = 0;

            HashMap<String, List<String>> map = new HashMap<>();

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                
                String[] caves = data.split("-");

                if(!map.containsKey(caves[0])){
                    map.put(caves[0], new ArrayList<>());
                }

                if(!map.containsKey(caves[1])){
                    map.put(caves[1], new ArrayList<>());
                }

                map.get(caves[0]).add(caves[1]);
                map.get(caves[1]).add(caves[0]);
            }

            HashSet<String> visited = new HashSet<>();
            
            dfs(map, visited, "start");
            System.out.println(ways);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    } 

    public static void findWaysWithOneSmallCaveTwice() {
        try {
            File myObj = new File("input2.txt");
            Scanner myReader = new Scanner(myObj);
            
            int temp = 0;

            HashMap<String, List<String>> map = new HashMap<>();

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                
                String[] caves = data.split("-");

                if(!map.containsKey(caves[0])){
                    map.put(caves[0], new ArrayList<>());
                }

                if(!map.containsKey(caves[1])){
                    map.put(caves[1], new ArrayList<>());
                }

                map.get(caves[0]).add(caves[1]);
                map.get(caves[1]).add(caves[0]);
            }

            //HashSet<String> visited = new HashSet<>();
            List<String> visited = new ArrayList<>();
            dfsWithOneSmallCaveTwice(map, visited, "start");
            System.out.println(ways);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    } 

    // private static void dfsWithOneSmallCaveTwice(HashMap<String, List<String>> map, HashSet<String> visited, String curr){
    //     if(visited.contains(curr)){
    //         return;
    //     }

    //     if(curr.equals(small) && count == 2){
    //         return;
    //     }

    //     if(curr.equals("end")){
    //         ways++;
    //         return;
    //     }

    //     if(Character.isLowerCase(curr.charAt(0))){
    //         //visited.add(curr);
            
    //         if(curr.equals("start") || (!curr.equals(small)) ){
    //             visited.add(curr);
    //         }

    //         if(curr.equals(small)){
    //             count++;
    //         }

    //         if(!small.equals("") && !curr.equals("start")){
    //             small = curr;
    //             count = 1;

                
    //         }
    //     }

    //     for(String next: map.get(curr)){
    //         dfsWithOneSmallCaveTwice(map, visited, next);
    //     }

       
    //     visited.remove(curr);
    //     if(curr.equals(small)){
    //         if(count == 2){
    //             count--;
    //         }else if(count == 1){
    //             count = 0;
    //             small = "";
    //         }
    //     }
    // }

    private static void dfsWithOneSmallCaveTwice(HashMap<String, List<String>> map, List<String> visited, String curr){
        if(curr.equals("end")){
            ways++;
            return;
        }

        if(curr.equals("start")){
            for(String s: visited){
                if(s.equals("start")){
                    return;
                }
            }

            visited.add(curr);

            
        }else if(Character.isLowerCase(curr.charAt(0))){
            String dup = "";
            boolean hasCurr = false;
            HashSet<String> set = new HashSet<>();
            for(String s: visited){
                if(set.contains(s)){
                    dup = s;
                }
                if(s.equals(curr)){
                    hasCurr = true;
                }
                set.add(s);
            }

            if(!dup.equals("") && hasCurr){
                return;
            }
            if(dup.equals(curr)){
                return;
            }

            visited.add(curr);
        }

        for(String next: map.get(curr)){
            dfsWithOneSmallCaveTwice(map, visited, next);
        }
       
        if(Character.isLowerCase(curr.charAt(0)) && !curr.equals("start")){
            visited.remove(visited.size()-1);
        }
        
    }

    private static void dfs(HashMap<String, List<String>> map, HashSet<String> visited, String curr){
        if(visited.contains(curr)){
            return;
        }

        if(curr.equals("end")){
            ways++;
            return;
        }

        if(Character.isLowerCase(curr.charAt(0))){
            visited.add(curr);
        }

        for(String next: map.get(curr)){
            dfs(map, visited, next);
        }

        visited.remove(curr);
    }
}
