import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Label;


import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;


public class OOP2_FinalProject extends Application {
    public static void main(String [] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        Label title = new Label("Welcome to a Simple Stats Viewer!" );
        title.setPrefSize(500, 50);
        title.setFont(Font.font(30));
        TextField searchField = new TextField("Search");
        searchField.setPrefHeight(20);

        BorderPane borderPane = new BorderPane();
        Pane centerPane = new Pane();
        centerPane.setPrefWidth(700);
        centerPane.setPrefHeight(700);
        borderPane.setCenter(centerPane);
        HBox hBox = new HBox();

        VBox vBox = new VBox();
        Label playerSearch = new Label("View player stats");
        Label year = new Label("Year");
        ComboBox<Integer> cbYear = new ComboBox<Integer>();
        cbYear.getItems().addAll(2022);

        hBox.centerShapeProperty();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(title, searchField);
        borderPane.setTop(hBox);
        Scene scene = new Scene(borderPane);

        primaryStage.setTitle("A Simple Stats Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();
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
