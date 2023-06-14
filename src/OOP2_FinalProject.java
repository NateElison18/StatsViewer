import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class OOP2_FinalProject extends Application {
    // Import files and generate arraylists
    File pitchersFile = new File("22PitcherStats.csv");
    ArrayList<Pitcher> pitchers = Utility.generatePitcherArray(pitchersFile);
    File hittersFile = new File("22HitterStats.csv");
    ArrayList<PositionPlayer> hitters = Utility.generateHitterArray(hittersFile);

    // Create center pane
    Pane centerPane = new Pane();

    public OOP2_FinalProject() throws FileNotFoundException {
    }

    public static void main(String [] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException{

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

        String [][] translateArray = Utility.generateTeamTranslateArray();

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
            try {
                centerPane.getChildren().clear();
                centerPane.getChildren().addAll(displayPitcherInfo(Utility.getPitcher(pitchers, cbPlayer.getValue())));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
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



    //TODO display infos
    public static Pane displayPitcherInfo(Pitcher pitcher) throws FileNotFoundException {
        BorderPane statsPane = new BorderPane();

        // Display header
        Label playerName = new Label(pitcher.getName());
        playerName.setFont(Font.font(30));
        String fileName = "TeamLogos/" + pitcher.getTeam() + ".png";
        FileInputStream imageInputStream = new FileInputStream(new File(fileName));
        HBox hBox = new HBox();
//        hBox.setBorder(new Border(new BorderStroke(Color.BLACK, Color.TRANSPARENT, Color.BLACK, Color.BLACK, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID,
//                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT, new javafx.geometry.Insets(0, 0, 0, 0))));
        hBox.setPrefWidth(statsPane.getPrefWidth());
        Image teamImage = new Image(imageInputStream);
        hBox.getChildren().addAll(new ImageView(teamImage), playerName);
        hBox.setAlignment(Pos.CENTER_LEFT);

        // Display stats
//        TextArea taStats = new TextArea("Summary\tW\tL\tERA\tG\tGS\tSV\tIP\tSO\tWHIP\n" +
//                "2022\t" + pitcher.getWins() + "\t" + pitcher.getLosses() + "\t" + pitcher.getEarnedRunsAllowed()
//                + "\t" + pitcher.getGamesPlayed() + "\t" + pitcher.getGamesStarted() + "\t" + pitcher.getSaves()
//                + "\t" + pitcher.getInningsPitched() + "\t" + pitcher.getStrikeOuts() + "\t" + pitcher.getWhip());
//        taStats.setEditable(false);

        ObservableList<Pitcher> pitchers = FXCollections.observableArrayList(pitcher);

        TableView statsTable = new TableView();
        statsTable.setEditable(false);
        TableColumn<Pitcher, Integer> wins = new TableColumn<>("W");
        TableColumn<Pitcher, Integer> losses = new TableColumn<>("L");
        TableColumn<Pitcher, Double> eRA = new TableColumn<>("ERA");
        TableColumn<Pitcher, Integer> games = new TableColumn<>("G");
        TableColumn<Pitcher, Integer> gamesStarted = new TableColumn<>("GS");
        TableColumn<Pitcher, Integer> saves = new TableColumn<>("SV");
        TableColumn<Pitcher, Double> iP = new TableColumn<>("IP");
        TableColumn<Pitcher, Integer> sO = new TableColumn<>("Strikeouts");
        TableColumn<Pitcher, Double> whip = new TableColumn<>("WHIP");

//        PropertyValueFactory factory= new PropertyValueFactory<>("wins");
        wins.setCellValueFactory(new PropertyValueFactory<>("wins"));
        losses.setCellValueFactory(new PropertyValueFactory<>("losses"));
        eRA.setCellValueFactory(new PropertyValueFactory<>("era"));
        games.setCellValueFactory(new PropertyValueFactory<>("gamesPlayed"));
        gamesStarted.setCellValueFactory(new PropertyValueFactory<>("gamesStarted"));
        saves.setCellValueFactory(new PropertyValueFactory<>("saves"));
        iP.setCellValueFactory(new PropertyValueFactory<>("inningsPitched"));
        sO.setCellValueFactory(new PropertyValueFactory<>("StrikeOuts"));
        whip.setCellValueFactory(new PropertyValueFactory<>("whip"));


        statsTable.getColumns().addAll(wins, losses, eRA, games, gamesStarted, saves, iP, sO, whip);
        statsTable.getItems().add(pitcher);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);


        Pane scrollPane = new Pane(statsTable);
        scrollPane.setMaxWidth(statsPane.getMaxWidth());
        scrollPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
//        Pane pane = new Pane(scrollPane);

//        pane.setMaxWidth(statsPane.getWidth());

        statsPane.setCenter(scrollPane);
        statsPane.setTop(hBox);

        return statsPane;
    }












}