public class bingo {
    int[] counts = new int[10];
    String[][] board = new String[5][5];
    boolean[][] visited = new boolean[5][5];

    public bingo(String[][] board){
        this.board = board;
    }

    public boolean addNum(String n){
        boolean found = false;
        for(int i = 0; i< 5;  i++){
            for(int j =0; j< 5; j++){
                
                if(board[i][j].equals(n)){
                    visited[i][j] = true;

                    counts[i]++;
                    if(counts[i] == 5){
                        found = true;
                        break;
                    }

                    counts[5+j]++;
                    if(counts[5+j] == 5){
                        found = true;
                        break;
                    }

                    // if(i == j){
                    //     counts[10]++;
                    //     if(counts[10] == 5){
                    //         found = true;
                    //         break;
                    //     }

                    // }

                    // if (i+j == 4){
                    //     counts[11]++;
                    //     if(counts[11] ==5){
                    //         found = true;
                    //         break;
                    //     }
                    // }  
                }

            }
        }
        return found;
    }
}
