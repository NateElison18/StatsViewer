import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class testing {
    public static void main(String[] args) throws FileNotFoundException {
        File pitchersFile = new File("22PitcherStats.csv");
        File hittersFile = new File("22HitterStats.csv");
        ArrayList<Pitcher> pitchers = Utility.generatePitcherArray(pitchersFile);
        ArrayList<Pitcher> cheese = new ArrayList();
        ArrayList<PositionPlayer> hitters = Utility.generateHitterArray(hittersFile);
        String [][] hittingLeaders = Utility.getHittingStatLeaders(hitters, "HomeRuns", "MLB", null, 0);
        String [][] pitchingLeaders = Utility.getPitchingStatLeaders(pitchers, "ERA", "MLB", null, 200);
        pitchingLeaders = Utility.reverseLeaders(pitchingLeaders);
        hittingLeaders = Utility.reverseLeaders(hittingLeaders);
        
        for (int i = 0; i < 5; i++) {
        	System.out.println(hittingLeaders[i][0] + " " + hittingLeaders[i][1]);
        }
//        for (int i = (leaders.length - 1); i > (leaders.length - 6); i--) {
//        	System.out.println(leaders[i][0] + " " + leaders[i][1]);
//        }
    }
}
