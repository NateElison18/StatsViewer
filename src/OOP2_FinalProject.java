import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
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
    public  Pane displayPitcherInfo(Pitcher pitcher) throws FileNotFoundException {
        BorderPane statsPane = new BorderPane();

        // Display header
        Label playerName = new Label(pitcher.getName());
        playerName.setFont(Font.font(30));
        Label filler = new Label("");
        filler.setFont(Font.font(30));
        String fileName = "TeamLogos/" + pitcher.getTeam() + ".png";
        FileInputStream imageInputStream = new FileInputStream(new File(fileName));
        HBox hBox = new HBox();

//        hBox.setMinWidth(statsPane.getPrefWidth());
        Image teamImage = new Image(imageInputStream);
        hBox.getChildren().addAll(new ImageView(teamImage), playerName, filler);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setStyle("-fx-border-color: black");
//        hBox.setMinWidth(centerPane.getWidth());

        // Display stats
//        TextArea taStats = new TextArea("Summary\tW\tL\tERA\tG\tGS\tSV\tIP\tSO\tWHIP\n" +
//                "2022\t" + pitcher.getWins() + "\t" + pitcher.getLosses() + "\t" + pitcher.getEarnedRunsAllowed()
//                + "\t" + pitcher.getGamesPlayed() + "\t" + pitcher.getGamesStarted() + "\t" + pitcher.getSaves()
//                + "\t" + pitcher.getInningsPitched() + "\t" + pitcher.getStrikeOuts() + "\t" + pitcher.getWhip());
//        taStats.setEditable(false);


//        TableView statsTable = new TableView();
//        statsTable.setEditable(false);
//        TableColumn<Pitcher, Integer> wins = new TableColumn<>("W");
//        TableColumn<Pitcher, Integer> losses = new TableColumn<>("L");
//        TableColumn<Pitcher, Double> eRA = new TableColumn<>("ERA");
//        TableColumn<Pitcher, Integer> games = new TableColumn<>("G");
//        TableColumn<Pitcher, Integer> gamesStarted = new TableColumn<>("GS");
//        TableColumn<Pitcher, Integer> saves = new TableColumn<>("SV");
//        TableColumn<Pitcher, Double> iP = new TableColumn<>("IP");
//        TableColumn<Pitcher, Integer> sO = new TableColumn<>("Strikeouts");
//        TableColumn<Pitcher, Double> whip = new TableColumn<>("WHIP");
//
////        PropertyValueFactory factory= new PropertyValueFactory<>("wins");
//        wins.setCellValueFactory(new PropertyValueFactory<>("wins"));
//        losses.setCellValueFactory(new PropertyValueFactory<>("losses"));
//        eRA.setCellValueFactory(new PropertyValueFactory<>("era"));
//        games.setCellValueFactory(new PropertyValueFactory<>("gamesPlayed"));
//        gamesStarted.setCellValueFactory(new PropertyValueFactory<>("gamesStarted"));
//        saves.setCellValueFactory(new PropertyValueFactory<>("saves"));
//        iP.setCellValueFactory(new PropertyValueFactory<>("inningsPitched"));
//        sO.setCellValueFactory(new PropertyValueFactory<>("StrikeOuts"));
//        whip.setCellValueFactory(new PropertyValueFactory<>("whip"));
//
//
//        statsTable.getColumns().addAll(wins, losses, eRA, games, gamesStarted, saves, iP, sO, whip);
//        statsTable.getItems().add(pitcher);

        // Build Center Pane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);

        VBox summaryBox = new VBox();
        VBox winBox = new VBox();
        VBox lossBox = new VBox();
        VBox eraBox = new VBox();
        VBox gameBox = new VBox();
        VBox gamesStartedBox = new VBox();
        VBox savesBox = new VBox();
        VBox iPBox = new VBox();
        VBox soBox = new VBox();
        VBox whipBox = new VBox();

        summaryBox.getChildren().addAll(new Label("Summary"), new Label("2022"));
        winBox.getChildren().addAll(new Label("W"), new Label(String.valueOf(pitcher.getWins())));
        lossBox.getChildren().addAll(new Label("L"), new Label(String.valueOf(pitcher.getLosses())));
        eraBox.getChildren().addAll(new Label("ERA"), new Label(String.valueOf(pitcher.getEra())));
        gameBox.getChildren().addAll(new Label("G"), new Label(String.valueOf(pitcher.getGamesPlayed())));
        gamesStartedBox.getChildren().addAll(new Label("GS"), new Label(String.valueOf(pitcher.getGamesStarted())));
        savesBox.getChildren().addAll(new Label("SV"), new Label(String.valueOf(pitcher.getSaves())));
        iPBox.getChildren().addAll(new Label("IP"), new Label(String.valueOf(pitcher.getInningsPitched())));
        soBox.getChildren().addAll(new Label("SO"), new Label(String.valueOf(pitcher.getStrikeOuts())));
        whipBox.getChildren().addAll(new Label("WHIP"), new Label(String.valueOf(pitcher.getWhip())));

        summaryBox.setAlignment(Pos.CENTER);
        winBox.setAlignment(Pos.CENTER);
        lossBox.setAlignment(Pos.CENTER);
        eraBox.setAlignment(Pos.CENTER);
        gameBox.setAlignment(Pos.CENTER);
        gamesStartedBox.setAlignment(Pos.CENTER);
        savesBox.setAlignment(Pos.CENTER);
        iPBox.setAlignment(Pos.CENTER);
        soBox.setAlignment(Pos.CENTER);
        whipBox.setAlignment(Pos.CENTER);

        gridPane.add(summaryBox, 0, 0);
        gridPane.add(winBox, 1, 0);
        gridPane.add(lossBox, 2, 0);
        gridPane.add(eraBox, 3, 0);
        gridPane.add(gameBox, 4, 0);
        gridPane.add(gamesStartedBox, 5, 0);
        gridPane.add(savesBox, 6, 0);
        gridPane.add(iPBox, 7, 0);
        gridPane.add(soBox, 8, 0);
        gridPane.add(whipBox, 9, 0);

        gridPane.setHgap(20);
        gridPane.setTranslateY(30);





//        gridPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        // Build Bottom pane
        HBox defaultCheckBoxs = new HBox();
        CheckBox chkWin = new CheckBox("W");
        CheckBox chkLoss = new CheckBox("L");
        CheckBox chkERA = new CheckBox("ERA");
        CheckBox chkGames = new CheckBox("G");
        CheckBox chkGamesStarted = new CheckBox("GS");
        CheckBox chkSaves = new CheckBox("SV");
        CheckBox chkIp = new CheckBox("IP");
        CheckBox chkSo = new CheckBox("SO");
        CheckBox chkWhip = new CheckBox("WHIP");

        chkWin.setSelected(true);
        chkLoss.setSelected(true);
        chkERA.setSelected(true);
        chkGames.setSelected(true);
        chkGamesStarted.setSelected(true);
        chkSaves.setSelected(true);
        chkIp.setSelected(true);
        chkSo.setSelected(true);
        chkWhip.setSelected(true);

        chkWin.setFont(Font.font(10));
        chkLoss.setFont(Font.font(10));
        chkERA.setFont(Font.font(10));
        chkGames.setFont(Font.font(10));
        chkGamesStarted.setFont(Font.font(10));
        chkSaves.setFont(Font.font(10));
        chkIp.setFont(Font.font(10));
        chkSo.setFont(Font.font(10));
        chkWhip.setFont(Font.font(10));

        defaultCheckBoxs.getChildren().addAll(chkWin,chkLoss, chkERA, chkGames, chkGamesStarted, chkSaves, chkIp, chkSo, chkWhip);
        defaultCheckBoxs.setSpacing(15);
        defaultCheckBoxs.setAlignment(Pos.CENTER);
        VBox checkBoxs = new VBox();
        checkBoxs.getChildren().addAll(defaultCheckBoxs);
//        checkBoxs.setStyle("-fx-border-color: black");
        checkBoxs.setTranslateY(-5);


        statsPane.setTop(hBox);
        statsPane.setCenter(gridPane);
        statsPane.setBottom(checkBoxs);

        statsPane.setMinHeight(centerPane.getHeight());
        statsPane.setMinWidth(centerPane.getWidth());

        return statsPane;
    }












}