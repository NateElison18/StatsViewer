public class PositionPlayer extends Player {
    int plateAppearances;
    int atBats;
    int runs;
    int hits;
    int doubles;
    int triples;
    int homeRuns;
    int rbis;
    int stolenBases;
    int caughtStealing;
    int walks;
    int strikeOuts;
    double battingAverage;
    double onBasePercentage;
    double slugging;
    double onBasePlusSlugging;
    double opsPlus;
    int totalBases;
    int groundIntoDoublePlay;
    int hitByPitch;
    int sacrificeHits;
    int sacrificeFlies;
    int intentionalWalks;

    PositionPlayer(String name, int age, String team, String league, int gamesPlayed, int plateAppearances, int atBats,
                   int runs, int hits, int doubles, int triples, int homeRuns, int rbis, int stolenBases, int caughtStealing,
                   int walks, int strikeOuts, double battingAverage, double onBasePercentage, double slugging, double onBasePlusSlugging,
                   double opsPlus, int totalBases, int groundIntoDoublePlay, int hitByPitch, int sacrificeHits, int sacrificeFlies,
                   int intentionalWalks) {
        this.name = name;
        this.age = age;
        this.team = team;
        this.league = league;
        this.gamesPlayed = gamesPlayed;
        this.plateAppearances = plateAppearances;
        this.atBats = atBats;
        this.runs = runs;
        this.hits = hits;
        this.doubles = doubles;
        this.triples = triples;
        this.homeRuns = homeRuns;
        this.rbis = rbis;
        this.stolenBases = stolenBases;
        this.caughtStealing = caughtStealing;
        this.walks = walks;
        this.strikeOuts = strikeOuts;
        this.battingAverage = battingAverage;
        this.onBasePercentage = onBasePercentage;
        this.slugging = slugging;
        this.onBasePlusSlugging = onBasePlusSlugging;
        this.opsPlus = opsPlus;
        this.totalBases = totalBases;
        this.groundIntoDoublePlay = groundIntoDoublePlay;
        this.hitByPitch = hitByPitch;
        this.sacrificeHits = sacrificeHits;
        this.sacrificeFlies = sacrificeFlies;
        this.intentionalWalks = intentionalWalks;



    }




    public int getPlateAppearances() {
        return plateAppearances;
    }

    public int getAtBats() {
        return atBats;
    }

    public int getRuns() {
        return runs;
    }

    public int getHits() {
        return hits;
    }

    public int getDoubles() {
        return doubles;
    }

    public int getTriples() {
        return triples;
    }

    public int getHomeRuns() {
        return homeRuns;
    }

    public int getRBIs() {
        return rbis;
    }

    public int getStolenBases() {
        return stolenBases;
    }

    public int getCaughtStealing() {
        return caughtStealing;
    }

    public int getWalks() {
        return walks;
    }

    public int getStrikeOuts() {
        return strikeOuts;
    }

    public double getBattingAverage() {
        return battingAverage;
    }

    public double getOnBasePercentage() {
        return onBasePercentage;
    }

    public double getSlugging() {
        return slugging;
    }

    public double getOnBasePlusSlugging() {
        return onBasePlusSlugging;
    }

    public double getOpsPlus() {
        return opsPlus;
    }

    public int getTotalBases() {
        return totalBases;
    }

    public int getGroundIntoDoublePlay() {
        return groundIntoDoublePlay;
    }

    public int getHitByPitch() {
        return hitByPitch;
    }

    public int getSacrificeHits() {
        return sacrificeHits;
    }

    public int getSacrificeFlies() {
        return sacrificeFlies;
    }

    public int getIntentionalWalks() {
        return intentionalWalks;
    }
}
