import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {

    @org.junit.jupiter.api.Test
    void generatePitcherArray() throws FileNotFoundException {
        File file = new File("PitchersTest.csv" );
        ArrayList<Pitcher> pitchersTestList = new ArrayList<Pitcher>();
        Pitcher camiloDoval = new Pitcher("Camilo Doval", 24, "SFG", "NL", 6, 6, 0.5, 2.53, 68, 0, 51, 0, 0, 27, 67.2, 54, 27, 19, 4, 30, 2, 80, 3, 0, 4, 286, 158, 2.98, 1.241, 7.2, 0.5, 4, 10.6, 2.7);
        Pitcher kevinGausman = new Pitcher("Kevin Gausman", 31, "TOR", "AL", 12, 10, 0.545, 3.35, 31, 31, 0, 0, 0, 0, 174.2, 188, 72, 65, 15, 28, 0, 205, 1, 2, 2, 725, 114, 2.38, 1.237, 9.7, 0.8, 1.4, 10.6, 7.3);
        pitchersTestList.add(camiloDoval);
        pitchersTestList.add(kevinGausman);

        ArrayList<Pitcher> pitchers = Utility.generatePitcherArray(file);

        assertEquals(pitchers, pitchersTestList);
        //assertEquals(pitchers.get(1), pitchersTestList.get(1));

    }

    @org.junit.jupiter.api.Test
    void generateHitterArray() {
    }

    @org.junit.jupiter.api.Test
    void identifyPlayerType() {
    }

    @org.junit.jupiter.api.Test
    void generateTeamTranslateArray() {
    }

    @org.junit.jupiter.api.Test
    void getPitchingStatLeaders() {
    }

    @org.junit.jupiter.api.Test
    void reverseLeaders() {
    }

    @org.junit.jupiter.api.Test
    void getHittingStatLeaders() {
    }

    @org.junit.jupiter.api.Test
    void getPitcher() {
    }

    @org.junit.jupiter.api.Test
    void getPositionPlayer() {
    }
}