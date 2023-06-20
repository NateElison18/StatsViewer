import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Utility {

    public static ArrayList<Pitcher> generatePitcherArray(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file);
        input.useDelimiter(",");
        input.nextLine(); //skip column headings
        ArrayList<Pitcher> pitchersList = new ArrayList<Pitcher>();

        while (input.hasNext()) {
            String name = input.next();
            name = name.replaceAll("\u00A0"," "); // Remove and replace weird space w real space
            int age = input.nextInt();
            String team = input.next();
            String league = input.next();
            int wins = input.nextInt();
            int losses = input.nextInt();
            double winLosPercentage = input.nextDouble();
            double era = input.nextDouble();
            int gamesPlayed = input.nextInt();
            int gamesStarted = input.nextInt();
            int gamesFinished = input.nextInt();
            int completeGames = input.nextInt();
            int shutouts = input.nextInt();
            int saves = input.nextInt();
            double inningsPitched = input.nextDouble();
            int hitsAllowed = input.nextInt();
            int runsAllowed = input.nextInt();
            int earnedRunsAllowed = input.nextInt();
            int homersAllowed = input.nextInt();
            int walks = input.nextInt();
            int intentionalWalks = input.nextInt();
            int strikeouts = input.nextInt();
            int hbp = input.nextInt();
            int balks = input.nextInt();
            int wildPitches = input.nextInt();
            int battersFaced = input.nextInt();
            int eraPlus = input.nextInt();
            double fip = input.nextDouble();
            double whip = input.nextDouble();
            double h9 = input.nextDouble();
            double hr9 = input.nextDouble();
            double bb9 = input.nextDouble();
            double so9 = input.nextDouble();
            String soPerWStr = input.nextLine(); // save stat with coma.
            double soPerW = Double.parseDouble(soPerWStr.substring(1)); // remove coma, save as a double

            Pitcher pitcher = new Pitcher(name, age, team, league, wins, losses, winLosPercentage, era, gamesPlayed, gamesStarted, gamesFinished,
                    completeGames, shutouts, saves, inningsPitched, hitsAllowed, runsAllowed, earnedRunsAllowed, homersAllowed, walks, intentionalWalks,
                    strikeouts, hbp, balks, wildPitches, battersFaced, eraPlus, fip, whip, h9, hr9, bb9, so9, soPerW);
            pitchersList.add(pitcher);
        }
        return pitchersList;
    }

    public static ArrayList<PositionPlayer> generateHitterArray(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file);
        input.useDelimiter(",");
        input.nextLine(); //skip column headings
        ArrayList<PositionPlayer> hittersList = new ArrayList<PositionPlayer>();

        while (input.hasNext()) {
            String name = input.next();
            name = name.replaceAll("\u00A0"," "); // Remove and replace weird space w real space
            int age = input.nextInt();
            String team = input.next();
            String league = input.next();
            int gamesPlayed = input.nextInt();
            int plateApps = input.nextInt();
            int atBats = input.nextInt();
            int runs = input.nextInt();
            int hits = input.nextInt();
            int doubles = input.nextInt();
            int triples = input.nextInt();
            int homers = input.nextInt();
            int rbi = input.nextInt();
            int stolenBases = input.nextInt();
            int caughtStealing = input.nextInt();
            int walks = input.nextInt();
            int strikeOuts = input.nextInt();
            double battingAverage = input.nextDouble();
            double onBasePercentage = input.nextDouble();
            double slugging = input.nextDouble();
            double onBasePlusSlugging = input.nextDouble();
            int opsPlus = input.nextInt();
            int totalBases = input.nextInt();
            int groundIntoDoublePlays = input.nextInt();
            int hitByPitches = input.nextInt();
            int sacrificeHits = input.nextInt();
            int sacrificeFlies = input.nextInt();
            String stringIBBs = input.nextLine();
            int intentionalWalks = Integer.parseInt(stringIBBs.substring(1));

            PositionPlayer hitter = new PositionPlayer(name, age, team, league, gamesPlayed, plateApps, atBats, runs,
                    hits, doubles, triples,  homers, rbi, stolenBases, caughtStealing, walks, strikeOuts, battingAverage,
                    onBasePercentage, slugging, onBasePlusSlugging, opsPlus, totalBases, groundIntoDoublePlays, hitByPitches,
                    sacrificeHits, sacrificeFlies, intentionalWalks);
            hittersList.add(hitter);
        }
        return hittersList;
    }

    public static int identifyPlayerType(Pitcher pitcher, PositionPlayer hitter) {
    	int playerType = 0;
    	 if (pitcher != null && hitter == null){
             playerType = 1;
         }
         else if (pitcher != null && hitter != null) {
        	 playerType = 2;
         }
         else if (pitcher == null && hitter != null) {
             playerType = 3;
         }
    	
    	return playerType;
    }

    public static String[][] generateTeamTranslateArray(){
        // Future updates change the translate array to a hash map.
//        HashMap<String, String> translationMap = new HashMap<String, String>();
//        translationMap.put("ARI", "Arizona DiamondBacks");
//        translationMap.get("ARI");
        String [][] translateArray =
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
        return translateArray;
    }

    public static String[][] getPitchingStatLeaders(ArrayList<Pitcher> pitchers, String stat, String league, String team, double minIP) {
    	ArrayList<Pitcher> leaders = new ArrayList();
    	for (int i = 0; i < pitchers.size(); i++) {
    		if (pitchers.get(i).getInningsPitched() > minIP) {
    			leaders.add(pitchers.get(i));
    		}
    	}
    	if (league != "MLB") {
    		ArrayList<Pitcher> tempLeaders = new ArrayList();
    		for (int i = 0; i < leaders.size(); i++) {
    			if (leaders.get(i).getLeague().equals(league)) {
    				tempLeaders.add(leaders.get(i));
    			}
    		}
    		leaders = tempLeaders;
    	}
    	if (team != null) {
    		String[][] translateArray = generateTeamTranslateArray();
    		String teamAbreviation = "";
    		for (int i = 0; i < translateArray.length; i++) {
    			if (team.equals(translateArray[i][0])) {
    				teamAbreviation = translateArray[i][1];
    			}
    		}
    		ArrayList<Pitcher> tempLeaders = new ArrayList();
    		for (int i = 0; i < leaders.size(); i++) {
    			if (leaders.get(i).getTeam().equals(teamAbreviation)) {
    				tempLeaders.add(leaders.get(i));
    			}
    		}
    		leaders = tempLeaders;
    	}
    	String[][] leadersTrimmed = new String[leaders.size()][2];
    	
    	if (stat.equals("Age")) {
    		leaders.sort((a, b) -> a.getAge() - b.getAge());
    		for (int i = 0; i < leaders.size(); i++) {
    			leadersTrimmed[i][0] = leaders.get(i).getName();
    			leadersTrimmed[i][1] = String.valueOf(leaders.get(i).getAge());    			
    		}
    	}
    	else if (stat.equals("Games Played")) {
    		leaders.sort((a, b) -> a.getGamesPlayed() - b.getGamesPlayed());
    		for (int i = 0; i < leaders.size(); i++) {
    			leadersTrimmed[i][0] = leaders.get(i).getName();
    			leadersTrimmed[i][1] = String.valueOf(leaders.get(i).getGamesPlayed());    			
    		}
    	}
    	else if (stat.equals("Wins")) {
    		leaders.sort((a, b) -> a.getWins() - b.getWins()); 
    		for (int i = 0; i < leaders.size(); i++) {
    			leadersTrimmed[i][0] = leaders.get(i).getName();
    			leadersTrimmed[i][1] = String.valueOf(leaders.get(i).getWins());    			
    		}
    	}
    	else if (stat.equals("Losses")) {
    		leaders.sort((a, b) -> a.getLosses() - b.getLosses()); 
    		for (int i = 0; i < leaders.size(); i++) {
    			leadersTrimmed[i][0] = leaders.get(i).getName();
    			leadersTrimmed[i][1] = String.valueOf(leaders.get(i).getLosses());    			
    		}
    	}
    	else if (stat.equals("W/L Percentage")) {
    		leaders.sort((a, b) -> ((int) (100 * a.getWinLossPercentage())) - (int) (100 * b.getWinLossPercentage())); 
    		for (int i = 0; i < leaders.size(); i++) {
    			leadersTrimmed[i][0] = leaders.get(i).getName();
    			leadersTrimmed[i][1] = String.valueOf(leaders.get(i).getWinLossPercentage());    			
    		}
    	}
    	else if (stat.equals("ERA")) {
    		leaders.sort((a, b) -> ((int) (100 * a.getEra())) - (int) (100 * b.getEra()));
    		for (int i = 0; i < leaders.size(); i++) {
    			leadersTrimmed[i][0] = leaders.get(i).getName();
    			leadersTrimmed[i][1] = String.valueOf(leaders.get(i).getEra());    			
    		}
    	}
    	else if (stat.equals("Games Started")) {
    		leaders.sort((a, b) -> a.getGamesStarted() - b.getGamesStarted()); 
    		for (int i = 0; i < leaders.size(); i++) {
    			leadersTrimmed[i][0] = leaders.get(i).getName();
    			leadersTrimmed[i][1] = String.valueOf(leaders.get(i).getGamesStarted());    			
    		}
    	}
    	else if (stat.equals("Games Finished")) {
    		leaders.sort((a, b) -> a.getGamesFinished() - b.getGamesFinished()); 
    		for (int i = 0; i < leaders.size(); i++) {
    			leadersTrimmed[i][0] = leaders.get(i).getName();
    			leadersTrimmed[i][1] = String.valueOf(leaders.get(i).getGamesFinished());    			
    		}
    	}
    	else if (stat.equals("Complete Games")) {
    		leaders.sort((a, b) -> a.getCompleteGames() - b.getCompleteGames()); 
    		for (int i = 0; i < leaders.size(); i++) {
    			leadersTrimmed[i][0] = leaders.get(i).getName();
    			leadersTrimmed[i][1] = String.valueOf(leaders.get(i).getCompleteGames());    			
    		}
    	}
    	else if (stat.equals("Shut Outs")) {
    		leaders.sort((a, b) -> a.getShutouts() - b.getShutouts()); 
    		for (int i = 0; i < leaders.size(); i++) {
    			leadersTrimmed[i][0] = leaders.get(i).getName();
    			leadersTrimmed[i][1] = String.valueOf(leaders.get(i).getShutouts());    			
    		}
    	}
    	else if (stat.equals("Saves")) {
    		leaders.sort((a, b) -> a.getSaves() - b.getSaves()); 
    		for (int i = 0; i < leaders.size(); i++) {
    			leadersTrimmed[i][0] = leaders.get(i).getName();
    			leadersTrimmed[i][1] = String.valueOf(leaders.get(i).getSaves());    			
    		}
    	}
    	else if (stat.equals("Innings Pitched")) {
    		leaders.sort((a, b) -> ((int) (100 * a.getInningsPitched())) - (int) (100 * b.getInningsPitched()));
    		for (int i = 0; i < leaders.size(); i++) {
    			leadersTrimmed[i][0] = leaders.get(i).getName();
    			leadersTrimmed[i][1] = String.valueOf(leaders.get(i).getInningsPitched());    			
    		}
    	}
    	else if (stat.equals("Hits Allowed")) {
    		leaders.sort((a, b) -> a.getHitsAllowed() - b.getHitsAllowed()); 
    		for (int i = 0; i < leaders.size(); i++) {
    			leadersTrimmed[i][0] = leaders.get(i).getName();
    			leadersTrimmed[i][1] = String.valueOf(leaders.get(i).getHitsAllowed());    			
    		}
    	}
    	else if (stat.equals("Earned Runs")) {
    		leaders.sort((a, b) -> a.getEarnedRunsAllowed() - b.getEarnedRunsAllowed()); 
    		for (int i = 0; i < leaders.size(); i++) {
    			leadersTrimmed[i][0] = leaders.get(i).getName();
    			leadersTrimmed[i][1] = String.valueOf(leaders.get(i).getEarnedRunsAllowed());    			
    		}
    	}
    	else if (stat.equals("Runs")) {
    		leaders.sort((a, b) -> a.getRunsAllowed() - b.getRunsAllowed()); 
    		for (int i = 0; i < leaders.size(); i++) {
    			leadersTrimmed[i][0] = leaders.get(i).getName();
    			leadersTrimmed[i][1] = String.valueOf(leaders.get(i).getRunsAllowed());    			
    		}
    	}
    	else if (stat.equals("Home Runs")) {
    		leaders.sort((a, b) -> a.getHomersAllowed() - b.getHomersAllowed()); 
    		for (int i = 0; i < leaders.size(); i++) {
    			leadersTrimmed[i][0] = leaders.get(i).getName();
    			leadersTrimmed[i][1] = String.valueOf(leaders.get(i).getHomersAllowed());    			
    		}
    	}
    	else if (stat.equals("Walks")) 	{
    		leaders.sort((a, b) -> a.getWalks() - b.getWalks()); 
    		for (int i = 0; i < leaders.size(); i++) {
    			leadersTrimmed[i][0] = leaders.get(i).getName();
    			leadersTrimmed[i][1] = String.valueOf(leaders.get(i).getWalks());    			
    		}
    	}
    	else if (stat.equals("Intentional Walks")) {
    		leaders.sort((a, b) -> a.getIntentionalWalk() - b.getIntentionalWalk()); 
    		for (int i = 0; i < leaders.size(); i++) {
    			leadersTrimmed[i][0] = leaders.get(i).getName();
    			leadersTrimmed[i][1] = String.valueOf(leaders.get(i).getIntentionalWalk());    			
    		}
    	}
    	else if (stat.equals("Strikeouts"))	{
    		leaders.sort((a, b) -> a.getStrikeOuts() - b.getStrikeOuts()); 
    		for (int i = 0; i < leaders.size(); i++) {
    			leadersTrimmed[i][0] = leaders.get(i).getName();
    			leadersTrimmed[i][1] = String.valueOf(leaders.get(i).getStrikeOuts());    			
    		}
    	}
    	else if (stat.equals("Hit Batters")) {
    		leaders.sort((a, b) -> a.getHbp() - b.getHbp()); 
    		for (int i = 0; i < leaders.size(); i++) {
    			leadersTrimmed[i][0] = leaders.get(i).getName();
    			leadersTrimmed[i][1] = String.valueOf(leaders.get(i).getHbp());    			
    		}
    	}
    	else if (stat.equals("Balks")) {
    		leaders.sort((a, b) -> a.getBalks() - b.getBalks()); 
    		for (int i = 0; i < leaders.size(); i++) {
    			leadersTrimmed[i][0] = leaders.get(i).getName();
    			leadersTrimmed[i][1] = String.valueOf(leaders.get(i).getBalks());    			
    		}
    	}
    	else if (stat.equals("Wild Pitches")) {
    		leaders.sort((a, b) -> a.getWildPitches() - b.getWildPitches()); 
    		for (int i = 0; i < leaders.size(); i++) {
    			leadersTrimmed[i][0] = leaders.get(i).getName();
    			leadersTrimmed[i][1] = String.valueOf(leaders.get(i).getWildPitches());    			
    		}
    	}
    	else if (stat.equals("Batters Faced")) {
    		leaders.sort((a, b) -> a.getBattersFaced() - b.getBattersFaced()); 
    		for (int i = 0; i < leaders.size(); i++) {
    			leadersTrimmed[i][0] = leaders.get(i).getName();
    			leadersTrimmed[i][1] = String.valueOf(leaders.get(i).getBattersFaced());    			
    		}
    	}
    	else if (stat.equals("ERA+")) {
    		leaders.sort((a, b) -> a.getEraPlus() - b.getEraPlus()); 
    		for (int i = 0; i < leaders.size(); i++) {
    			leadersTrimmed[i][0] = leaders.get(i).getName();
    			leadersTrimmed[i][1] = String.valueOf(leaders.get(i).getEraPlus());    			
    		}
    	}
    	else if (stat.equals("FIP")) {
    		leaders.sort((a, b) -> ((int) (100 * a.getFip())) - (int) (100 * b.getFip()));
    		for (int i = 0; i < leaders.size(); i++) {
    			leadersTrimmed[i][0] = leaders.get(i).getName();
    			leadersTrimmed[i][1] = String.valueOf(leaders.get(i).getFip());    			
    		}
    	}
    	else if (stat.equals("WHIP")) {
    		leaders.sort((a, b) -> ((int) (100 * a.getWhip())) - (int) (100 * b.getWhip()));
    		for (int i = 0; i < leaders.size(); i++) {
    			leadersTrimmed[i][0] = leaders.get(i).getName();
    			leadersTrimmed[i][1] = String.valueOf(leaders.get(i).getWhip());    			
    		}
    	}
    	else if (stat.equals("Hits per 9")) {
    		leaders.sort((a, b) -> ((int) (100 * a.getH9())) - (int) (100 * b.getH9())); 
    		for (int i = 0; i < leaders.size(); i++) {
    			leadersTrimmed[i][0] = leaders.get(i).getName();
    			leadersTrimmed[i][1] = String.valueOf(leaders.get(i).getH9());    			
    		}
    	}
    	else if (stat.equals("Home Runs per 9")) {
    		leaders.sort((a, b) -> ((int) (100 * a.getHr9())) - (int) (100 * b.getHr9()));
    		for (int i = 0; i < leaders.size(); i++) {
    			leadersTrimmed[i][0] = leaders.get(i).getName();
    			leadersTrimmed[i][1] = String.valueOf(leaders.get(i).getHr9());    			
    		}
    	}
    	else if (stat.equals("Walks per 9")) {
    		leaders.sort((a, b) -> ((int) (100 * a.getBb9())) - (int) (100 * b.getBb9()));
    		for (int i = 0; i < leaders.size(); i++) {
    			leadersTrimmed[i][0] = leaders.get(i).getName();
    			leadersTrimmed[i][1] = String.valueOf(leaders.get(i).getBb9());    			
    		}
    	}
    	else if (stat.equals("Strikeouts per 9")) {
    		leaders.sort((a, b) -> ((int) (100 * a.getSo9())) - (int) (100 * b.getSo9())); 
    		for (int i = 0; i < leaders.size(); i++) {
    			leadersTrimmed[i][0] = leaders.get(i).getName();
    			leadersTrimmed[i][1] = String.valueOf(leaders.get(i).getSo9());    			
    		}
    	}
    	else {
    		leaders.sort((a, b) -> ((int) (100 * a.getSoPerW())) - (int) (100 * b.getSoPerW()));
    		for (int i = 0; i < leaders.size(); i++) {
    			leadersTrimmed[i][0] = leaders.get(i).getName();
    			leadersTrimmed[i][1] = String.valueOf(leaders.get(i).getSoPerW());    			
    		}
    	}
    	
    	return leadersTrimmed;
    }
    
    public static ArrayList<PositionPlayer> getHittingStatLeaders(ArrayList<PositionPlayer> hitters, String stat, String league, String team, int minPA) {
    	ArrayList<PositionPlayer> leaders = new ArrayList();
    	for (int i = 0; i < hitters.size(); i++) {
    		if (hitters.get(i).getPlateAppearances() > minPA) {
    			leaders.add(hitters.get(i));
    		}
    	}
    	if (league != "MLB") {
    		ArrayList<PositionPlayer> tempLeaders = new ArrayList();
    		for (int i = 0; i < leaders.size(); i++) {
    			if (leaders.get(i).getLeague().equals(league)) {
    				tempLeaders.add(leaders.get(i));
    			}
    		}
    		leaders = tempLeaders;
    	}
    	if (team != null) {
    		String[][] translateArray = generateTeamTranslateArray();
    		String teamAbreviation = "";
    		for (int i = 0; i < translateArray.length; i++) {
    			if (team.equals(translateArray[i][0])) {
    				teamAbreviation = translateArray[i][1];
    			}
    		}
    		ArrayList<PositionPlayer> tempLeaders = new ArrayList();
    		for (int i = 0; i < leaders.size(); i++) {
    			if (leaders.get(i).getTeam().equals(teamAbreviation)) {
    				tempLeaders.add(leaders.get(i));
    			}
    		}
    		leaders = tempLeaders;
    		
        	if 		(stat.equals("Age")) 			   			leaders.sort((a, b) -> a.getAge() - b.getAge());
        	else if (stat.equals("Games Played")) 	   			leaders.sort((a, b) -> a.getGamesPlayed() - b.getGamesPlayed());
        	else if (stat.equals("Plate Appearances")) 	  		leaders.sort((a, b) -> a.getPlateAppearances() - b.getPlateAppearances());
        	else if (stat.equals("At Bats")) 	   				leaders.sort((a, b) -> a.getAtBats() - b.getAtBats());
        	else if (stat.equals("Runs")) 	   					leaders.sort((a, b) -> a.getRuns() - b.getRuns());
        	else if (stat.equals("Hits")) 	   					leaders.sort((a, b) -> a.getHits() - b.getHits());
        	else if (stat.equals("Doubles")) 	   				leaders.sort((a, b) -> a.getDoubles() - b.getDoubles());
        	else if (stat.equals("Triples")) 	   				leaders.sort((a, b) -> a.getTriples() - b.getTriples());
        	else if (stat.equals("HomeRuns")) 	   				leaders.sort((a, b) -> a.getHomeRuns() - b.getHomeRuns());
        	else if (stat.equals("RBIs")) 	   					leaders.sort((a, b) -> a.getRBIs() - b.getRBIs());
        	else if (stat.equals("Stolen Bases")) 	   			leaders.sort((a, b) -> a.getStolenBases() - b.getStolenBases());
        	else if (stat.equals("Caught Stealing")) 	   		leaders.sort((a, b) -> a.getCaughtStealing() - b.getCaughtStealing());
        	else if (stat.equals("Walks")) 	   					leaders.sort((a, b) -> a.getWalks() - b.getWalks());
        	else if (stat.equals("Strikeouts")) 	   			leaders.sort((a, b) -> a.getStrikeOuts() - b.getStrikeOuts());
        	else if (stat.equals("Batting Average")) 	   		leaders.sort((a, b) -> ((int) (100 * a.getBattingAverage())) - (int) (100 * b.getBattingAverage()));
        	else if (stat.equals("OBP")) 	   					leaders.sort((a, b) -> ((int) (100 * a.getOnBasePercentage())) - (int) (100 * b.getOnBasePercentage()));
        	else if (stat.equals("Slugging")) 	   				leaders.sort((a, b) -> ((int) (100 * a.getSlugging())) - (int) (100 * b.getSlugging()));
        	else if (stat.equals("OPS")) 	  					leaders.sort((a, b) -> ((int) (100 * a.getOnBasePlusSlugging())) - (int) (100 * b.getOnBasePlusSlugging()));
        	else if (stat.equals("OPS+")) 	   					leaders.sort((a, b) -> ((int) (100 * a.getOpsPlus())) - (int) (100 * b.getOpsPlus()));
        	else if (stat.equals("Total Bases")) 	   			leaders.sort((a, b) -> a.getTotalBases() - b.getTotalBases());
        	else if (stat.equals("Ground Into Double Plays"))	leaders.sort((a, b) -> a.getGroundIntoDoublePlay() - b.getGroundIntoDoublePlay());
        	else if (stat.equals("Hit by Pitch")) 	   			leaders.sort((a, b) -> a.getHitByPitch() - b.getHitByPitch());
        	else if (stat.equals("Sacrifice Hits")) 	   		leaders.sort((a, b) -> a.getSacrificeHits() - b.getSacrificeHits());
        	else if (stat.equals("Sacrifice Flies")) 	   		leaders.sort((a, b) -> a.getSacrificeFlies() - b.getSacrificeFlies());
        	else if (stat.equals("Intentional Walks")) 	   		leaders.sort((a, b) -> a.getIntentionalWalks() - b.getIntentionalWalks());

    		
    	}
    	return leaders;
    }
    
    public static Pitcher getPitcher(ArrayList<Pitcher> list, String name) {
        for (int i = 0; i < list.size(); i++) {
            String nameToCheck = list.get(i).getName();
            if (name.equalsIgnoreCase(nameToCheck)) return list.get(i);
        }
        return null;
    }

    // TODO check to see if this is needed. 
    public static String[][] teamImages() {
        String [][] translateArray =
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
        return translateArray;
    }

    public static PositionPlayer getPositionPlayer(ArrayList<PositionPlayer> list, String name) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equalsIgnoreCase(name)) return list.get(i);
        }
        return null;
    }

    public static ArrayList<PositionPlayer> getTeamsPositionPlayers(ArrayList<PositionPlayer> fullList, String team) {
        ArrayList<PositionPlayer> teamPlayerList = new ArrayList<PositionPlayer>();
        for (int i = 0; i < fullList.size(); i++) {
            if (fullList.get(i).getTeam().equalsIgnoreCase(team)){
                teamPlayerList.add(fullList.get(i));
            }
        }
        return teamPlayerList;
    }

    public static ArrayList<Pitcher> getTeamsPitchers(ArrayList<Pitcher> fullList, String team) {
        ArrayList<Pitcher> teamPitcherList = new ArrayList<Pitcher>();
        for (int i = 0; i < fullList.size(); i++) {
            if (fullList.get(i).getTeam().equalsIgnoreCase(team)){
                teamPitcherList.add(fullList.get(i));
            }
        }
        return teamPitcherList;
    }



}
