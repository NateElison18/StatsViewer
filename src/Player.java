/**
 * <h1>Player</h1>
 * Player is the superclass of the Pitcher and PositionPlayer classes. It is used to get the characteristics common between hitters and pitchers.
 *
 * <p>Last updated 6/26/23</p>
 *
 * @author Nate Elison
 */

public class Player {
    String name;
    int age;
    String team;
    String league;
    int gamesPlayed;

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }

    public String getTeam() {
        return team;
    }

    public String getLeague() {
        return league;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }
}