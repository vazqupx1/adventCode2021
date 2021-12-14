import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class day14 {
    public static void doTemplates() {
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            
            StringBuilder sb = new StringBuilder();

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                sb.append(data);
                if(data.length() == 0){
                    break;
                }
            }

            HashMap<String, Character> map = new HashMap<>();

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split(" -> ");
                map.put(parts[0], parts[1].charAt(0));
            }

            for(int i = 0; i< 40; i++){
                //10 times;

                StringBuilder next = new StringBuilder();

                int len = sb.length();
                for(int j = 0; j< len-1; j++){
                    next.append(sb.charAt(j));
                    

                    String curr = sb.substring(j, j+2);
                    if(map.containsKey(curr)){
                        next.append(map.get(curr));
                    }
                }
                next.append(sb.charAt(len-1));
                sb = next;
            }

            long[] counts = new long[26];
            long min = Integer.MAX_VALUE;
            long max = 0;
            for(char c : sb.toString().toCharArray()){
                counts[c-'A']++;
                
            }

            for(int i = 0; i< 26; i++){
                if(counts[i] == 0){
                    continue;
                }
                min = Math.min(min, counts[i]);
                max = Math.max(max, counts[i]);
            }
            
            System.out.println(max-min);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    } 

    public static void doBigTemplates() {
        try {
            File myObj = new File("input2.txt");
            Scanner myReader = new Scanner(myObj);
            
            StringBuilder sb = new StringBuilder();

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                sb.append(data);
                if(data.length() == 0){
                    break;
                }
            }

            HashMap<String, Character> map = new HashMap<>();

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split(" -> ");
                map.put(parts[0], parts[1].charAt(0));
            }

            long[] counts = new long[26];
            //Queue<String> q = new LinkedList<String>();
            HashMap<String, Long> occurence = new HashMap<>();
            int len = sb.length();
            
            for(int j = 0; j< len-1; j++){
                String curr = sb.substring(j, j+2);
                if(map.containsKey(curr)){
                    //countNext(counts, map, curr, 1);
                    //q.add(curr);
                    occurence.put(curr,  occurence.getOrDefault(curr, (long) 0) + (long) 1);
                }
                counts[sb.charAt(j) - 'A']++;
            }
            counts[sb.charAt(len-1) - 'A']++;

            for(int i = 0; i< 40; i++){
//                int size = q.size();
                HashMap<String, Long> occurenceNext = new HashMap<>();
                for(String curr: occurence.keySet()){
                    String next1 = curr.charAt(0) + "" + map.get(curr);
                    String next2 =  "" + map.get(curr) + curr.charAt(1);

                    counts[map.get(curr) -'A']+= occurence.get(curr); 
                    if(map.containsKey(next1))
                    occurenceNext.put(next1,  occurenceNext.getOrDefault(next1, (long) 0) + occurence.get(curr));
                    if(map.containsKey(next2))
                    occurenceNext.put(next2,  occurenceNext.getOrDefault(next2, (long) 0) + occurence.get(curr));
                }       
                occurence = occurenceNext; 
            }
            
            long min = Long.MAX_VALUE;
            long max = 0;
           

            for(int i = 0; i< 26; i++){
                if(counts[i] == 0){
                    continue;
                }
                min = Math.min(min, counts[i]);
                max = Math.max(max, counts[i]);
            }
            
            System.out.println(max-min);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    } 

    private static void countNext(long[] counts, HashMap<String, Character> map, String curr, int times){
        if(times == 41){
            return;
        }
        if(!map.containsKey(curr)){
            return;
        }
        String next1 = curr.charAt(0) + "" + map.get(curr);
        String next2 =  "" + map.get(curr) + curr.charAt(1);

        counts[map.get(curr) -'A']++;
        countNext(counts, map, next1, times+1);
        countNext(counts, map, next2, times+1);
    } 

    private static void addNext(long[] counts, HashMap<String, Character> map, String curr, Queue<String> q){
        if(!map.containsKey(curr)){
            return;
        }
        String next1 = curr.charAt(0) + "" + map.get(curr);
        String next2 =  "" + map.get(curr) + curr.charAt(1);

        counts[map.get(curr) -'A']++;
        if(map.containsKey(next1))
        q.add(next1);
        if(map.containsKey(next2))
        q.add(next2);
    } 
}
