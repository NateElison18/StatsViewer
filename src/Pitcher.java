/**
 * <h1>Pitcher</h1>
 * The Pitcher class is a subclass of Player, and it is how pitcher stats are stored and referenced in StatsViewer.
 *
 * <p>Last updated 6/20/23</p>
 *
 * @author Nate Elison
 */
public class Pitcher extends Player {
    int wins;
    int losses;
    double winLossPercentage;
    double era;
    int gamesStarted;
    int gamesFinished;
    int completeGames;
    int shutouts;
    int saves;
    double inningsPitched;
    int hitsAllowed;
    int earnedRunsAllowed;
    int runsAllowed;
    int homersAllowed;
    int walks;
    int intentionalWalk;
    int strikeOuts;
    int hbp;
    int balks;
    int wildPitches;
    int battersFaced;
    int eraPlus;
    double fip;
    double whip;
    double h9;
    double hr9;
    double bb9;
    double so9;
    double soPerW;

    Pitcher(String name, int age, String team, String league, int wins, int losses, double winLossPercentage,
            double era, int gamesPlayed, int gamesStarted, int gamesFinished, int completeGames, int shutouts, int saves, double inningsPitched,
            int hitsAllowed, int runsAllowed, int earnedRunsAllowed, int homersAllowed, int walks, int intentionalWalk, int strikeOuts,
            int hbp, int balks, int wildPitches, int battersFaced, int eraPlus, double fip, double whip, double h9, double hr9,
            double bb9, double so9, double soPerW) {
        this.name = name;
        this.age = age;
        this.team = team;
        this.league = league;
        this.gamesPlayed = gamesPlayed;
        this.wins = wins;
        this.losses = losses;
        this.winLossPercentage = winLossPercentage;
        this.era = era;
        this.gamesStarted = gamesStarted;
        this.gamesFinished = gamesFinished;
        this.completeGames = completeGames;
        this.shutouts = shutouts;
        this.saves = saves;
        this.inningsPitched = inningsPitched;
        this.hitsAllowed = hitsAllowed;
        this.earnedRunsAllowed = earnedRunsAllowed;
        this.runsAllowed = runsAllowed;
        this.homersAllowed = homersAllowed;
        this.walks = walks;
        this.intentionalWalk = intentionalWalk;
        this.strikeOuts = strikeOuts;
        this.hbp = hbp;
        this.balks = balks;
        this.wildPitches = wildPitches;
        this.battersFaced = battersFaced;
        this.eraPlus = eraPlus;
        this.fip = fip;
        this.whip = whip;
        this.h9 = h9;
        this.hr9 = hr9;
        this.bb9 = bb9;
        this.so9 = so9;
        this.soPerW = soPerW;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public double getWinLossPercentage() {
        return winLossPercentage;
    }

    public double getEra() {
        return era;
    }

    public int getGamesStarted() {
        return gamesStarted;
    }

    public int getGamesFinished() {
        return gamesFinished;
    }

    public int getCompleteGames() {
        return completeGames;
    }

    public int getShutouts() {
        return shutouts;
    }

    public int getSaves() {
        return saves;
    }

    public double getInningsPitched() {
        return inningsPitched;
    }

    public int getHitsAllowed() {
        return hitsAllowed;
    }

    public int getEarnedRunsAllowed() {
        return earnedRunsAllowed;
    }

    public int getRunsAllowed() {
        return runsAllowed;
    }

    public int getHomersAllowed() {
        return homersAllowed;
    }

    public int getWalks() {
        return walks;
    }

    public int getIntentionalWalk() {
        return intentionalWalk;
    }

    public int getStrikeOuts() {
        return strikeOuts;
    }

    public int getHbp() {
        return hbp;
    }

    public int getBalks() {
        return balks;
    }

    public int getWildPitches() {
        return wildPitches;
    }

    public int getBattersFaced() {
        return battersFaced;
    }

    public int getEraPlus() {
        return eraPlus;
    }

    public double getFip() {
        return fip;
    }

    public double getWhip() {
        return whip;
    }

    public double getH9() {
        return h9;
    }

    public double getHr9() {
        return hr9;
    }

    public double getBb9() {
        return bb9;
    }

    public double getSo9() {
        return so9;
    }

    public double getSoPerW() {
        return soPerW;
    }

}