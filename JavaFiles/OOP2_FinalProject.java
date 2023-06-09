import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class OOP2_FinalProject extends Application {
    public OOP2_FinalProject() throws FileNotFoundException {
    }

    public static void main(String [] args) {
        launch(args);
    }
    // Import files and generate arraylists
    File pitchersFile = new File("22PitcherStats.csv");
    ArrayList<Pitcher> pitchers = generatePitcherArray(pitchersFile);
    File hittersFile = new File("22HitterStats.csv");
    ArrayList<PositionPlayer> hitters = generateHitterArray(hittersFile);

    // Create center pane
    Pane centerPane = new Pane();


    @Override
    public void start(Stage primaryStage){

        // Format center pane
        centerPane.setPrefWidth(500);
        centerPane.setPrefHeight(500);
        centerPane.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        // Left pane
        VBox vBox = new VBox();
        Label playerSearchLabel = new Label("View Player Stats");
        playerSearchLabel.setFont(Font.font(20));
        Label yearLabel = new Label("Year:");
        ComboBox<Integer> cbYear = new ComboBox<Integer>();
        cbYear.getItems().addAll(2022);
        Label teamLabel = new Label("Team:");
        ComboBox<String> cbTeam = new ComboBox<String>();
        cbTeam.getItems().addAll("Arizona Diamondbacks", "Atlanta Braves", "Baltimore Orioles", "Boston Red Sox",
                "Chicago White Sox", "Chicago Cubs", "Cincinnati Reds", "Cleveland Guardians", "Colorado Rockies",
                "Detroit Tigers", "Houston Astros", "Kansas City Royals", "Los Angeles Angels", "Los Angeles Dodgers",
                "Miami Marlins", "Milwaukee Brewers", "Minnesota Twins", "New York Mets", "New York Yankees", "Oakland Athletics",
                "Philadelphia Phillies", "Pittsburgh Pirates", "San Diego Padres", "San Francisco Giants", "Seattle Mariners",
                "St. Louis Cardinals", "Tampa Bay Rays", "Texas Rangers", "Toronto Blue Jays", "Washington Nationals");

        String [][] translateArray = generateTeamTranslateArray();

        Label playerLabel = new Label("Player");
        ComboBox<String> cbPlayer = new ComboBox<String>();

        // Team combo box action
        cbTeam.setOnAction(e -> {
                cbPlayer.getItems().clear();
                int index = 0;
                for (int i = 0; i < translateArray.length; i++){
                    if(cbTeam.getValue().equalsIgnoreCase(translateArray[i][0]))
                        index = i;
                }
                for (int j = 0; j < pitchers.size(); j++) {
                    if (translateArray[index][1].equals(pitchers.get(j).getTeam())){
                        cbPlayer.getItems().add(pitchers.get(j).getName());
                    }
                }
        });

        // Player combo box action
        cbPlayer.setOnAction(e -> {
            displayPitcherInfo(cbPlayer.getValue(), pitchers);
        });



        vBox.setSpacing(5);
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.getChildren().addAll(playerSearchLabel, yearLabel, cbYear, teamLabel, cbTeam, playerLabel, cbPlayer);

        // Top pane
        HBox hBoxTop = new HBox();
        Label title = new Label("Welcome to a Simple Stats Viewer!" );
        title.setPrefSize(500, 50);
        title.setFont(Font.font(30));
        title.setAlignment(Pos.CENTER);
        TextField playerSearch = new TextField("Player search");
        hBoxTop.getChildren().addAll(title);
        hBoxTop.setAlignment(Pos.CENTER);

        // Bottom pane
        HBox hBoxBottom = new HBox();
        Label byLine = new Label("By Nate Elison     ");
        byLine.setFont(Font.font(10));
        hBoxBottom.getChildren().addAll(byLine);
        hBoxBottom.setAlignment(Pos.CENTER_RIGHT);

        BorderPane borderPane = new BorderPane();
        borderPane.setRight(playerSearch);
        borderPane.setTop(hBoxTop);
        borderPane.setLeft(vBox);
        borderPane.setCenter(centerPane);
        borderPane.setBottom(hBoxBottom);

        Scene scene = new Scene(borderPane);
        primaryStage.setTitle("A Simple Stats Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static String[][] generateTeamTranslateArray(){
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

    //TODO display infos
    public static void displayPitcherInfo(String name, ArrayList<Pitcher> pitchers){

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


    public static PositionPlayer getPositionPlayer(ArrayList<PositionPlayer> list, String name) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equalsIgnoreCase(name)) return list.get(i);
        }
        System.out.println("Player not Found");
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



    public static Pitcher getPitcher(ArrayList<Pitcher> list, String name) {
        for (int i = 0; i < list.size(); i++) {
            String nameToCheck = list.get(i).getName();
            if (name.equalsIgnoreCase(nameToCheck)) return list.get(i);
        }
        System.out.println("Player not Found");
        return null;
    }

}
