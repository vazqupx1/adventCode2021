import java.util.List;

public class bigSchool {
    long[] fish;

    public bigSchool(List<Integer> list){
        fish = new long[9];
        for(int f : list){
            fish[f]++;
        }
    }

    public void addDay(){
        long temp8 = fish[0];
        long temp6 = fish[0]+ fish[7];
        fish[0] = fish[1];
        fish[1] = fish[2];
        fish[2] = fish[3];
        fish[3] = fish[4];
        fish[4] = fish[5];
        fish[5] = fish[6];
        fish[6] = temp6;
        fish[7] = fish[8];
        fish[8] = temp8; 
    }
}
