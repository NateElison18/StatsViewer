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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
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
    public void start(Stage primaryStage){

        // Format center pane
        centerPane.setPrefWidth(500);
        centerPane.setPrefHeight(500);
        centerPane.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        centerPane.getChildren().addAll(displayPitcherInfo(pitchers.get(5)));

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
            displayPitcherInfo(Utility.getPitcher(pitchers, cbPlayer.getValue()));
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
    public static Pane displayPitcherInfo(Pitcher pitcher){
        BorderPane statsPane = new BorderPane();
        Label playerName = new Label(pitcher.getName());
//       Image image = new Image("http://www.capsinfo.com/images/MLB_Team_Logos/Arizona_Diamondbacks.png");
        Image image = new Image("https://github.com/NateElison18/StatsViewer/blob/main/TeamLogos/SanFrancisco_Giants.png");

        statsPane.getChildren().addAll(new ImageView(image));

        return statsPane;
    }











}