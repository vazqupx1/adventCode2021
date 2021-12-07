import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.border.Border;

public class day4 {
    public static void findBingo() {
        try {
            var myObj = new File("input2.txt");
            Scanner myReader = new Scanner(myObj);

            List<bingo> list = new ArrayList<>();

            String[] nums = null;

            StringBuilder numsBuilder = new StringBuilder();

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                if (data.length() == 0 || data.charAt(0) == '\n') {
                    break;
                }

                numsBuilder.append(data);
            }

            String numsS = numsBuilder.toString();
            nums = numsS.split(",");

            String[][] board = new String[5][5];
            int r = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                if (data.length() == 0 || data.charAt(0) == '\n') {
                    list.add(new bingo(board));

                    board = new String[5][5];
                    r = 0;
                    continue;

                }

                String[] row = data.split(" ");
                int col = 0;
                for (String boardNumber : row) {
                    if (boardNumber.length() == 0 || boardNumber == "" || !Character.isDigit(boardNumber.charAt(0))) {
                        continue;
                    }
                    board[r][col] = boardNumber;
                    col++;
                }
                r++;

            }
            list.add(new bingo(board));
            boolean[] won = new boolean[list.size()];

            for (String num : nums) {
                for (int bi = 0; bi < list.size(); bi++) {
                    bingo bb = list.get(bi);
                    if(won[bi]){
                        continue;
                    }
                    if (bb.addNum(num)) {
                        won[bi] = true;
                        int sum = 0;
                        int rowSum = 0;
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 5; j++) {
                                int curr = Integer.parseInt(bb.board[i][j]);

                                if (!bb.visited[i][j]) {
                                    sum += curr;

                                }
                                // if(bb.counts[i] == 5){
                                // rowSum += curr;
                                // }

                                // if(bb.counts[5+j] == 5){
                                // rowSum += curr;

                                // }

                                // if(i == j){
                                // if(bb.counts[10] == 5){
                                // rowSum += curr;

                                // }

                                // }

                                // if (i+j == 4){
                                // if(bb.counts[11] ==5){
                                // rowSum += curr;
                                // }
                                // }
                            }
                        }
                        System.out.println(sum * Integer.parseInt(num));

                        // return;
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
