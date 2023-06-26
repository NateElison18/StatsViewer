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
        
        Pitcher camiloDoval = new Pitcher("Camilo Doval", 24, "SFG", "NL", 6, 6, 0.5, 2.53, 68, 0, 51, 0, 0, 27, 67.2, 54, 27, 19, 4, 30, 2, 80, 3, 0, 4, 286, 158, 2.98, 1.241, 7.2, 0.5, 4, 10.6, 2.7);
        Pitcher kevinGausman = new Pitcher("Kevin Gausman", 31, "TOR", "AL", 12, 10, 0.545, 3.35, 31, 31, 0, 0, 0, 0, 174.2, 188, 72, 65, 15, 28, 0, 205, 1, 2, 2, 725, 114, 2.38, 1.237, 9.7, 0.8, 1.4, 10.6, 7.3);
        
        
        for (int i = 0; i < 5; i++) {
        	System.out.println(hittingLeaders[i][0] + " " + hittingLeaders[i][1]);
        }
//        for (int i = (leaders.length - 1); i > (leaders.length - 6); i--) {
//        	System.out.println(leaders[i][0] + " " + leaders[i][1]);
//        }
    }
}
