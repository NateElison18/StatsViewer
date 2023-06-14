import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class testing {
    public static void main(String[] args) throws FileNotFoundException {
        File pitchersFile = new File("22PitcherStats.csv");
        ArrayList<Pitcher> pitchers = Utility.generatePitcherArray(pitchersFile);
        System.out.println(pitchers.get(1).getEra());
    }
}
