import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class testing {
    public static void main(String[] args) throws FileNotFoundException {
        File pitchersFile = new File("22PitcherStats.csv");
        ArrayList<Pitcher> pitchers = Utility.generatePitcherArray(pitchersFile);
        ArrayList<Pitcher> cheese = new ArrayList();
        String [][] leaders = Utility.getPitchingStatLeaders(pitchers, "Strikeouts", "MLB", null, 100);
        
        
        for (int i = (leaders.length - 1); i > (leaders.length - 6); i--) {
        	System.out.println(leaders[i][0] + " " + leaders[i][1]);
        }
    }
}
