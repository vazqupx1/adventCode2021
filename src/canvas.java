import java.util.ArrayList;
import java.util.List;

public class canvas {
    int maxRow;
    int maxCol;
    int[][] points;

    public canvas(int maxCol, int maxRow){
        this.maxCol = maxCol;
        this.maxRow = maxRow;
        this.points = new int[maxRow+1][maxCol+1];
        
    }

    public void addLine(int[] line){
        if(line[0] == line[2]){ // same col, diff row
            int max = Math.max(line[1], line[3]);

            for(int i = Math.min(line[1], line[3]); i <= max; i++){
                points[i][line[0]]++;
            }
        }else if(line[1] == line[3]) { // same row, diff col
            int max = Math.max(line[0], line[2]);

            for(int j = Math.min(line[0], line[2]); j <= max; j++){
                points[line[1]][j]++;
            }
        }
    }

    public void addLineIncludeDiagnol(int[] line){
        if(line[0] == line[2]){ // same col, diff row
            int max = Math.max(line[1], line[3]);

            for(int i = Math.min(line[1], line[3]); i <= max; i++){
                points[i][line[0]]++;
            }
        }else if(line[1] == line[3]) { // same row, diff col
            int max = Math.max(line[0], line[2]);

            for(int j = Math.min(line[0], line[2]); j <= max; j++){
                points[line[1]][j]++;
            }
        }else if(line[0] - line[2] == line[1] - line[3]){
            int minRow = Math.min(line[1], line[3]);
            int maxRow = Math.max(line[1], line[3]);
            int start = 0;
            if(minRow == line[1]){
                start = line[0];
            }else{
                start = line[2];
            }
            for(int i = minRow; i<= maxRow; i++){
                points[i][start]++;
                start++;
            }
        }else{
            int minRow = Math.min(line[1], line[3]);
            int maxRow = Math.max(line[1], line[3]);
            int start = 0;
            if(minRow == line[1]){
                start = line[0];
            }else{
                start = line[2];
            }
            for(int i = minRow; i<= maxRow; i++){
                points[i][start]++;
                start--;
            }
        }

    }
}
