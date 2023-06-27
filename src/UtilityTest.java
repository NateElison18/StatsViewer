import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class UtilityTest {

	@Test
	void testIdentifyPlayerType() {
		Pitcher nullPitcher = null;
		PositionPlayer nullHitter = null;
		Pitcher pitcher = new Pitcher();
		PositionPlayer hitter = new PositionPlayer();

		assertEquals(Utility.identifyPlayerType(pitcher, nullHitter), 1);
		assertEquals(Utility.identifyPlayerType(pitcher, hitter), 2);
		assertEquals(Utility.identifyPlayerType(nullPitcher, hitter), 3);
	}

	@Test
	void testGenerateTeamTranslateArray() {
		String [][] testTranslateArray =
			  		  {{"Arizona Diamondbacks", "ARI"},
                      {"Atlanta Braves", "ATL"},
                      {"Baltimore Orioles", "BAL"},
                      {"Boston Red Sox", "BOS"},
                      {"Chicago White Sox", "CHW"},
                      {"Chicago Cubs", "CHC"},
                      {"Cincinnati Reds", "CIN"},
                      {"Cleveland Guardians", "CLE"},
                      {"Colorado Rockies", "COL"},
                      {"Detroit Tigers", "DET"},
                      {"Houston Astros", "HOU"},
                      {"Kansas City Royals", "KCR"},
                      {"Los Angeles Angels", "LAA"},
                      {"Los Angeles Dodgers", "LAD"},
                      {"Miami Marlins", "MIA"},
                      {"Milwaukee Brewers", "MIL"},
                      {"Minnesota Twins", "MIN"},
                      {"New York Mets", "NYM"},
                      {"New York Yankees", "NYY"},
                      {"Oakland Athletics", "OAK"},
                      {"Philadelphia Phillies", "PHI"},
                      {"Pittsburgh Pirates", "PIT"},
                      {"San Diego Padres", "SDP"},
                      {"San Francisco Giants", "SFG"},
                      {"Seattle Mariners", "SEA"},
                      {"St. Louis Cardinals", "STL"},
                      {"Tampa Bay Rays", "TBR"},
                      {"Texas Rangers", "TEX"},
                      {"Toronto Blue Jays", "TOR"},
                      {"Washington Nationals", "WSN"},
              };
		for (int i = 0; i < testTranslateArray.length; i++) {
			for (int j = 0; j < 2; j++) {
				assertEquals(Utility.generateTeamTranslateArray()[i][j], testTranslateArray[i][j]);
			}
		}
	}

	@Test
	void testGetPitchingStatLeaders() {
        Pitcher camiloDoval = new Pitcher  ("Camilo Doval", 24, "SFG", "NL", 6,   6,   0.5, 2.53, 68,  0, 51, 0, 0, 27,  67.2,  54, 27, 19,  4, 30, 2,  80, 3, 0, 4, 286, 158, 2.98, 1.241, 7.2, 0.5,   4, 10.6, 2.7);
        Pitcher kevinGausman = new Pitcher("Kevin Gausman", 31, "TOR", "AL", 12, 10, 0.545, 3.35, 31, 31,  0, 0, 0,  0, 174.2, 188, 72, 65, 15, 28, 0, 205, 1, 2, 2, 725, 114, 2.38, 1.237, 9.7, 0.8, 1.4, 10.6, 7.3);

        ArrayList<Pitcher> pitchers = new ArrayList<Pitcher>();
        pitchers.add(camiloDoval);
        pitchers.add(kevinGausman);


        String[][] ageTestList	= {
        	{"Kevin Gausman", "31"},
        	{"Camilo Doval", "24"}
        };

        String[][] ageTestList2 = Utility.getPitchingStatLeaders(pitchers, "Age", "MLB", null, 10);

		for (int i = 0; i < ageTestList2.length; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.print(ageTestList[i][j]);
				System.out.print(" ");
				System.out.print(ageTestList2[i][j]);
				System.out.println();
			}
		}
		System.out.print(ageTestList2.length);


		for (int i = 0; i < ageTestList.length; i++) {
			for (int j = 0; j < 2; j++) {
				assertEquals(Utility.getPitchingStatLeaders(pitchers, "Age", "MLB", null, 0)[i][j], ageTestList[i][j]);
			}
		}
        String[][] savesTestList = {
            	{"Camilo Doval", "27"},
            	{"Kevin Gausman", "0"}

        };

		for (int i = 0; i < savesTestList.length; i++) {
			for (int j = 0; j < 2; j++) {
				assertEquals(Utility.getPitchingStatLeaders(pitchers, "Saves", "MLB", null, 20)[i][j], savesTestList[i][j]);
			}
		}
        String[][] nlGSTestList = {
            	{"Camilo Doval", "0"}
        };
		for (int i = 0; i < nlGSTestList.length; i++) {
			for (int j = 0; j < 2; j++) {
				assertEquals(Utility.getPitchingStatLeaders(pitchers, "Games Started", "NL", null, 0)[i][j], nlGSTestList[i][j]);
			}
		}
		String[][] teamEraTestList = {
            	{"Kevin Gausman", "3.35"}
		};
		for (int i = 0; i < teamEraTestList.length; i++) {
			for (int j = 0; j < 2; j++) {
				assertEquals(Utility.getPitchingStatLeaders(pitchers, "ERA", "MLB", "Toronto Blue Jays", 0)[i][j], teamEraTestList[i][j]);
			}
		}


	}

	@Test
	void testReverseLeaders() {
		String [][] toBeReversed = {
				{"1", "2"},
				{"3", "4"},
				{"5", "6"}
		};
		String [][] reversed = {
				{"5", "6"},
				{"3", "4"},
				{"1", "2"},
		};

		for (int i = 0; i < reversed.length; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.print(Utility.reverseLeaders(toBeReversed)[i][j]);
				System.out.print(" ");
				System.out.println(reversed[i][j]);
				assertEquals(Utility.reverseLeaders(toBeReversed)[i][j], reversed[i][j]);
			}
		}
	}

	@Test
	void testGetPitcher() {
		 Pitcher camiloDoval = new Pitcher  ("Camilo Doval", 24, "SFG", "NL", 6,   6,   0.5, 2.53, 68,  0, 51, 0, 0, 27,  67.2,  54, 27, 19,  4, 30, 2,  80, 3, 0, 4, 286, 158, 2.98, 1.241, 7.2, 0.5,   4, 10.6, 2.7);
	     Pitcher kevinGausman = new Pitcher("Kevin Gausman", 31, "TOR", "AL", 12, 10, 0.545, 3.35, 31, 31,  0, 0, 0,  0, 174.2, 188, 72, 65, 15, 28, 0, 205, 1, 2, 2, 725, 114, 2.38, 1.237, 9.7, 0.8, 1.4, 10.6, 7.3);

	      ArrayList<Pitcher> pitchers = new ArrayList<Pitcher>();
	      pitchers.add(camiloDoval);
	      pitchers.add(kevinGausman);

	      Pitcher checkCamilo = Utility.getPitcher(pitchers, "Camilo Doval");
	      Pitcher checkKevin = Utility.getPitcher(pitchers, "Kevin Gausman");
	      assertEquals(checkCamilo.getName(), camiloDoval.getName());
	      assertEquals(checkKevin.getName(), kevinGausman.getName());
	      assertEquals(checkCamilo.getSaves(), camiloDoval.getSaves());
	      assertEquals(checkKevin.getWins(), kevinGausman.getWins());
	}

	@Test
	void testGethittingStatLeaders() {
		PositionPlayer player1 = new PositionPlayer("Test Player1", 25, "SFG", "NL", 162, 300, 260, 50, 79, 60, 2, 40, 100, 20, 4, 20, 60, 0.333, 0.400, 0.694, 1.094, 179, 300, 15, 56, 20, 15, 9);
		PositionPlayer player2 = new PositionPlayer("Test Player2", 40, "NYY", "AL", 100, 120, 100, 10, 10, 10, 20, 4, 12, 14, 40, 10, 60, 0.122, 0.200, 0.333, 0.533, 60, 100, 90, 0, 0, 1, 5);

		ArrayList<PositionPlayer> hitters = new ArrayList();
		hitters.add(player1);
		hitters.add(player2);

		String[][] ageLeaders = {
				{"Test Player2", "40"},
				{"Test Player1", "25"}
		};
		String[][] hitsLeaders = {
				{"Test Player1", "79"},
				{"Test Player2", "10"}
		};
		String[][] homerLeadersNL = {
				{"Test Player1", "40"}
		};
		String[][] ibbLeadersNYY = {
				{"Test Player2", "5"}
		};

		String[][] ageLeadersTest = Utility.getHittingStatLeaders(hitters, "Age", "MLB", null, 0);
		for (int i = 0; i < ageLeaders.length; i++) {
			for (int j = 0; j < 2; j++) {
				assertEquals(ageLeaders[i][j], ageLeadersTest[i][j]);
			}
		}
		String[][] hitsLeadersTest = Utility.getHittingStatLeaders(hitters, "Hits", "MLB", null, 0);
		for (int i = 0; i < hitsLeaders.length; i++) {
			for (int j = 0; j < 2; j++) {
				assertEquals(hitsLeaders[i][j], hitsLeadersTest[i][j]);
			}
		}
		String[][] homerLeadersTest = Utility.getHittingStatLeaders(hitters, "Home Runs", "NL", null, 0);
		for (int i = 0; i < homerLeadersNL.length; i++) {
			for (int j = 0; j < 2; j++) {
				assertEquals(homerLeadersNL[i][j], homerLeadersTest[i][j]);
			}
		}
		String[][] ibbLeadersTest = Utility.getHittingStatLeaders(hitters, "Intentional Walks", "MLB", "New York Yankees", 0);
		for (int i = 0; i < ibbLeadersNYY.length; i++) {
			for (int j = 0; j < 2; j++) {
				assertEquals(ibbLeadersNYY[i][j], ibbLeadersTest[i][j]);
			}
		}
	}

	@Test
	void testPositionPlayer() {
		PositionPlayer player1 = new PositionPlayer("Test Player1", 25, "SFG", "NL", 162, 300, 260, 50, 79, 60, 2, 40, 100, 20, 4, 20, 60, 0.333, 0.400, 0.694, 1.094, 179, 300, 15, 56, 20, 15, 9);
		PositionPlayer player2 = new PositionPlayer("Test Player2", 25, "NYY", "AL", 100, 120, 100, 10, 10, 10, 20, 4, 12, 14, 40, 10, 60, 0.122, 0.200, 0.333, 0.533, 60, 100, 90, 0, 0, 1, 5);

		ArrayList<PositionPlayer> hitters = new ArrayList();
		hitters.add(player1);
		hitters.add(player2);

		PositionPlayer testPlayer1 = Utility.getPositionPlayer(hitters, "Test Player1");
		PositionPlayer testPlayer2 = Utility.getPositionPlayer(hitters, "Test Player2");

		assertEquals(player1.getName(), testPlayer1.getName());
		assertEquals(player2.getName(), testPlayer2.getName());
		assertEquals(player1.getBattingAverage(), testPlayer1.getBattingAverage());
		assertEquals(player2.getSacrificeFlies(), testPlayer2.getSacrificeFlies());

	}



}
