import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;


public class day3 {

    public static void findGammaEp() {
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);

            String data = myReader.nextLine();
            int n = data.length();
            int[] count1 = new int[n];

            int count = 1;
            for(int i = 0; i< n; i++){
                if(data.charAt(i) == '1'){
                    count1[i]++;
                }
            }
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                for(int i = 0; i< n; i++){
                    if(data.charAt(i) == '1'){
                        count1[i]++;
                    }
                }
                count++;
            }
            
            int gamma = 0;
            int ep = 0;
            for(int i = 0; i< n; i++){
                if(count1[i] > (count-count1[i])){
                    gamma |= (1 << (n-i-1) );
                }else{
                    ep |= (1 << (n-i-1) );
                }
            }
            System.out.println(gamma * ep);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // public static void findOxAndCo2() {
    //     try {
    //         File myObj = new File("input.txt");
    //         Scanner myReader = new Scanner(myObj);

    //         String data = myReader.nextLine();
    //         int n = data.length();
    //         int[] count1 = new int[n];
    //         List<String> res = new ArrayList<>();

    //         int count = 1;
    //         for(int i = 0; i< n; i++){
    //             if(data.charAt(i) == '1'){
    //                 count1[i]++;
    //             }
    //         }
    //         while (myReader.hasNextLine()) {
    //             data = myReader.nextLine();
    //             for(int i = 0; i< n; i++){
    //                 if(data.charAt(i) == '1'){
    //                     count1[i]++;
    //                 }
    //             }
    //             count++;
    //             res.add(data);
    //         }
            
    //         Queue<String> ox = new LinkedList<>();
    //         Queue<String> co2 = new LinkedList<>();
            
    //         for(String curr : res){
    //             if(count1[0] >= (count-count1[0])){
    //                 if(curr.charAt(0) == '1'){
    //                     ox.add(curr);
    //                 }else{
    //                     co2.add(curr);
    //                 }
    //             }else{
    //                 if(curr.charAt(0) == '0'){
    //                     ox.add(curr);
    //                 }else{
    //                     co2.add(curr);
    //                 }
    //             }
    //         }

    //         int k = 1;
            
    //         while(k < n){
    //             if(ox.size() == 1){
    //                 break;
    //             }
    //             int size = ox.size();
    //             for(int j = 0; j< size; j++){
    //                 String curr = ox.poll();
    //                 if(count1[k] >= (count-count1[k])){
    //                     if(curr.charAt(k) == '1'){
    //                         ox.add(curr);
    //                     }
    //                 }else{
    //                     if(curr.charAt(k) == '0'){
    //                         ox.add(curr);
    //                     }
    //                 }
    //             }
    //             k++;
    //         }
            
    //         k = 1;
    //         while(k < n){
    //             if(co2.size() == 1){
    //                 break;
    //             }
    //             int size = co2.size();
    //             for(int j = 0; j< size; j++){
    //                 String curr = co2.poll();
    //                 if(count1[k] >= (count-count1[k])){
    //                     if(curr.charAt(k) == '0'){
    //                         co2.add(curr);
    //                     }
    //                 }else{
    //                     if(curr.charAt(k) == '1'){
    //                         co2.add(curr);
    //                     }
    //                 }
    //             }
    //             k++;
    //         }

    //         String oxS = ox.poll();
    //         String coS = co2.poll();

    //         int oxN = 0;
    //         int coN = 0;

    //         for(int i = 0; i< n; i++){
    //             if(oxS.charAt(i) == '1'){
    //                 oxN |= (1<< (n-i-1));
    //             }
    //             if(coS.charAt(i) == '1'){
    //                 coN |= (1 << (n-i-1));
    //             }
    //         }

    //         System.out.println(oxN * coN);
    //         myReader.close();
    //     } catch (FileNotFoundException e) {
    //         System.out.println("An error occurred.");
    //         e.printStackTrace();
    //     }
    // }

    public static void findOxAndCo2() {
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);

            String data = "";
            // int n = data.length();
            // int[] count1 = new int[n];

            // int count = 1;
            // for(int i = 0; i< n; i++){
            //     if(data.charAt(i) == '1'){
            //         count1[i]++;
            //     }
            // }

            List<String> list = new ArrayList<String>();

            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                list.add(data);
            }

            List<String> oxs = filter(0, list, true);
            List<String> cos = filter(0, list, false);

            int k = 1;
            while(oxs.size() > 1){
                oxs = filter(k, oxs, true);
                k++;
            }

            k = 1;
            while(cos.size()>1){
                cos = filter(k, cos, false);
                k++;
                
            }

                     String oxS = oxs.get(0);
            String coS = cos.get(0);

            int oxN = 0;
            int coN = 0;

            int n = coS.length();
            for(int i = 0; i< n; i++){
                if(oxS.charAt(i) == '1'){
                    oxN |= (1<< (n-i-1));
                }
                if(coS.charAt(i) == '1'){
                    coN |= (1 << (n-i-1));
                }
            }

            System.out.println(oxN * coN);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static List<String> filter(int i, List<String> list, boolean mostCommon){
        List<String> res = new ArrayList<>();
        int count1 = 0;
        for(String curr : list){
            if(curr.charAt(i) == '1'){
                count1++;
            }
        }

        boolean commonIs1 = count1>= list.size() - count1;
        for(String curr : list){
            if(mostCommon){
                if(commonIs1 && curr.charAt(i) == '1'){
                    res.add(curr);
                }
                if(!commonIs1 && curr.charAt(i) == '0'){
                    res.add(curr);
                }
            }else{
                if(commonIs1 && curr.charAt(i) == '0'){
                    res.add((curr));
                    System.out.println(curr);
                }
                if(!commonIs1 && curr.charAt(i) == '1'){
                    res.add(curr);
                    System.out.println(curr);
                }
            }
        }
        return res;
    }
}
