import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class OOP2_FinalProject {
    public static void main(String [] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("22PitcherStats.csv"));
        input.useDelimiter(",");
        input.nextLine(); //skip column headings
        ArrayList<Pitcher> pitchersList = new ArrayList<Pitcher>();


        while (input.hasNext()) {
            String name = input.next();
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

        String typedCamiloDoval = "Camilo Doval";
        String listCamiloDoval = pitchersList.get(205).getName();
        String choppedCamilo = listCamiloDoval;
        System.out.println(typedCamiloDoval);
        System.out.println(choppedCamilo);
        System.out.println(typedCamiloDoval.equalsIgnoreCase(listCamiloDoval));
        System.out.println(choppedCamilo.compareTo(typedCamiloDoval));

        Pitcher camiloDoval =  getPitcher(pitchersList, listCamiloDoval);
        System.out.println(camiloDoval.getName() + " had " + camiloDoval.getSaves() + " saves in 2022");
    }

    public static PositionPlayer getPositionPlayer(ArrayList<PositionPlayer> list, String name) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(name)) return list.get(i);
        }
        System.out.println("Player not Found");
        return null;
    }

    public static Pitcher getPitcher(ArrayList<Pitcher> list, String name) {
        for (int i = 0; i < list.size(); i++) {
            String nameToCheck = list.get(i).getName();
            if (name.equalsIgnoreCase(nameToCheck)) return list.get(i);
        }
        System.out.println("Player not Found");
        return null;
    }

}
