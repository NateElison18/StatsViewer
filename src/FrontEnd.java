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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class FrontEnd extends Application {
    // Import files and generate arraylists
    File pitchersFile = new File("22PitcherStats.csv");
    ArrayList<Pitcher> pitchers = Utility.generatePitcherArray(pitchersFile);
    File hittersFile = new File("22HitterStats.csv");
    ArrayList<PositionPlayer> hitters = Utility.generateHitterArray(hittersFile);
    String [][] translateArray = Utility.generateTeamTranslateArray();

    // Create center pane
    Pane centerPane = new Pane();
    int prefWidth = 600;
    int prefHeight = 500;

    public FrontEnd() throws FileNotFoundException {
    }

    public static void main(String [] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException{

        // Format center pane
        centerPane.setPrefWidth(prefWidth);
        centerPane.setPrefHeight(prefWidth);
        centerPane.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        // Top pane
        HBox hBoxTop = new HBox();
        Label title = new Label("Welcome to a Simple Stats Viewer!" );
        title.setPrefSize(prefWidth, 50);
        title.setFont(Font.font(30));
        title.setAlignment(Pos.CENTER);
        hBoxTop.getChildren().addAll(title);
        hBoxTop.setAlignment(Pos.CENTER);
        
        // Right Pane
        TextField playerSearchTF = new TextField("Player search");
        BorderPane rightPane = new BorderPane();
        rightPane.setPrefHeight(centerPane.getHeight());
        
        VBox statLeaders = new VBox();
        
        ComboBox yearsCb = new ComboBox();
        yearsCb.getItems().addAll("2022");
        VBox yearsVBox = new VBox();
        yearsVBox.getChildren().addAll(new Label("Year:"), yearsCb);
        
        statLeaders.setAlignment(Pos.CENTER_LEFT);
        statLeaders.setTranslateX(5);
        statLeaders.setSpacing(7);
        RadioButton pitchingStats = new RadioButton("Pitching Stats");
        RadioButton hittingStats = new RadioButton("Hitting Stats");
        ToggleGroup statsTypeGroup = new ToggleGroup();
        VBox statTypeVBox = new VBox();
        statTypeVBox.getChildren().addAll(pitchingStats, hittingStats);
        
        pitchingStats.setToggleGroup(statsTypeGroup);
        hittingStats.setToggleGroup(statsTypeGroup);
        
        Label statLeadersLabel = new Label("Stat Leaders");
        statLeadersLabel.setFont(Font.font(20));
        Label stats = new Label("Stats:");
        ComboBox statsCb = new ComboBox();
        statsCb.setMaxWidth(150);
        VBox statsCbVBox = new VBox();
        statsCbVBox.getChildren().addAll(stats, statsCb);
        
        Label filters = new Label("Filters:");
        RadioButton rbMlb = new RadioButton("MLB");
        RadioButton rbNL = new RadioButton("NL");
        RadioButton rbAL = new RadioButton("AL");
        rbMlb.setSelected(true);
        
        ToggleGroup leagueGroup = new ToggleGroup();
        rbMlb.setToggleGroup(leagueGroup);
        rbNL.setToggleGroup(leagueGroup);
        rbAL.setToggleGroup(leagueGroup);
        
        VBox filtersVbox = new VBox();
        ComboBox filtersTeam = new ComboBox();
        Label team = new Label("Team:");
        filtersVbox.getChildren().addAll(filters, rbMlb, rbNL, rbAL, team, filtersTeam);
        for (int i = 0; i < translateArray.length; i++) {
    		filtersTeam.getItems().add(translateArray[i][0]);
    	}
        Button go = new Button("Go!");
        TextField paMinTF = new TextField("Minimum PA");
    	TextField ipMinTF = new TextField("Minimum IP");
        
        
        statLeaders.getChildren().addAll(statLeadersLabel, yearsVBox, statTypeVBox, statsCbVBox, filtersVbox, go);
        rightPane.setCenter(statLeaders);
        rightPane.setTop(playerSearchTF);

        // Right pane actions
        playerSearchTF.setOnAction(e -> {
            try {
                playerSearchAction(playerSearchTF.getText(), pitchers, hitters);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        pitchingStats.setOnAction(e -> {
        	statsCb.getItems().clear();
        	statsCb.getItems().addAll("Age", "Games Played", "Wins", "Losses", "W/L Percentage", "ERA", "Games Started", "Games Finished", "Complete Games", "Shut Outs", 
        			"Saves", "Innings Pitched", "Hits Allowed", "Earned Runs", "Runs", "Home Runs", "Walks", "Intentional Walks", "Strikeouts", "Hit Batters", 
        			"Balks", "Wild Pitches", "Batters Faced", "ERA+", "FIP", "WHIP", "Hits per 9", "Home Runs per 9", "Walks per 9", "Strikeouts per 9", "Strikeouts per Win");
        	HBox min = new HBox();
        	ipMinTF.setMaxWidth(100);
        	min.getChildren().add(ipMinTF);
        	min.setAlignment(Pos.BOTTOM_CENTER);
        	rightPane.setBottom(min);        	
        });
        hittingStats.setOnAction(e -> {
        	statsCb.getItems().clear();
        	statsCb.getItems().addAll("Age", "Games Played", "Plate Appearances", "At Bats", "Runs", 
        			"Hits", "Doubles", "Triples", "Home Runs", "RBIs", "Stolen Bases",
        			"Caught Stealing", "Walks", "Strikeouts", "Batting Average", "OBP", 
        			"Slugging", "OPS", "OPS+", "Total Bases", "GIDP", "Hit by Pitch", 
        			"Sacrifice Hits", "Sacrifice Flies", "Intentional Walks");
        	HBox min = new HBox();
        	paMinTF.setMaxWidth(100);
        	min.getChildren().add(paMinTF);
        	min.setAlignment(Pos.BOTTOM_CENTER);
        	rightPane.setBottom(min);
        	
        	
        });
        rbMlb.setOnAction(e -> {
        	filtersTeam.getItems().clear();
        	for (int i = 0; i < translateArray.length; i++) {
        		filtersTeam.getItems().add(translateArray[i][0]);
        	}
        });
        rbNL.setOnAction(e -> {
        	filtersTeam.getItems().clear();
        	filtersTeam.getItems().addAll("Arizona Diamondbacks", "Atlanta Braves", "Chicago Cubs", "Cincinnati Reds", "Colorado Rockies", "Los Angeles Dodgers", "Miami Marlins",
        			"Milwaukee Brewers", "New York Mets", "Philadelphia Phillies", "Pittsburgh Pirates", "San Diego Padres", "San Francisco Giants", "St. Louis Cardinals", "Washington Nationals");
        });
        rbAL.setOnAction(e -> {
        	filtersTeam.getItems().clear();
        	filtersTeam.getItems().addAll("Baltimore Orioles", "Boston Red Sox", "Chicago White Sox", "Cleveland Guardians", "Detroit Tigers", "Houston Astros", "Kansas City Royals", 
        			"Los Angeles Angels", "Minnesota Twins", "New York Yankees", "Oakland Athletics", "Seattle Mariners", "Tampa Bay Rays", "Texas Rangers", "Toronto Blue Jays");
        });
        
        go.setOnAction(e -> {
        centerPane.getChildren().clear();
        String stat = String.valueOf(statsCb.getValue());
        RadioButton selectedLeague = (RadioButton) leagueGroup.getSelectedToggle();
        String league = selectedLeague.getText();
        String selectedTeam = (String) filtersTeam.getValue();
        String filterTeam = selectedTeam;

//        if (filtersTeam.isArmed()) {
//        	filterTeam = String.valueOf(filtersTeam.getItems());
//        }
//        
        String.valueOf(filtersTeam.getValue());
        double minIp;
        try {
        	minIp = Double.parseDouble(ipMinTF.getText());
        }
        catch (NumberFormatException e2) {
        	minIp = 0;
        }
        int minPa;
        try {
        	minPa = Integer.parseInt(paMinTF.getText());
        }
        catch (NumberFormatException e3) {
        	minPa = 0;
        }
         
        if (pitchingStats.isSelected()) {
           String[][] leaders = Utility.getPitchingStatLeaders(pitchers, stat, league, filterTeam, minIp);
           try {
				centerPane.getChildren().add(displayStatsLeader(leaders, stat, league, filterTeam));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            
        }
        else {
        	String[][] leaders = Utility.getHittingStatLeaders(hitters, stat, league, filterTeam, minPa);
        	try {
				centerPane.getChildren().add(displayStatsLeader(leaders, stat, league, filterTeam));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        }
        
        
            
        	
        });

        // Left pane
        VBox vBox = new VBox();
        Label playerSearchLabel = new Label("View Player Stats");
        playerSearchLabel.setFont(Font.font(20));
        Label yearLabel = new Label("Year:");
        ComboBox<Integer> cbYear = new ComboBox<Integer>();
        cbYear.getItems().addAll(2022);
        Label teamLabel = new Label("Team:");
        ComboBox<String> cbTeam = new ComboBox<String>();
        for (int i = 0; i < translateArray.length; i++) {
    		cbTeam.getItems().add(translateArray[i][0]);
    	}
      
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
                if (translateArray[index][1].equals(hitters.get(j).getTeam())) {
                	cbPlayer.getItems().add(hitters.get(j).getName());
                }
            }
            // TODO Sort array alphabetically, if possible 
        });

        // Player combo box action
        cbPlayer.setOnAction(e -> {
            try {
                centerPane.getChildren().clear();
                Pitcher pitcher = Utility.getPitcher(pitchers, cbPlayer.getValue());
                PositionPlayer hitter = Utility.getPositionPlayer(hitters, cbPlayer.getValue());
                int playerType = Utility.identifyPlayerType(pitcher, hitter);
                
                if (playerType == 1)  	  	centerPane.getChildren().add(displayPitcherInfo(pitcher));
                else if (playerType == 2) 	centerPane.getChildren().add(displayTwoWayPlayerInfo(pitcher, hitter));
                else 					  	centerPane.getChildren().add(displayHitterInfo(hitter));        
            } 
            catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        vBox.setSpacing(5);
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.getChildren().addAll(playerSearchLabel, yearLabel, cbYear, teamLabel, cbTeam, playerLabel, cbPlayer);

        // Bottom pane
        HBox hBoxBottom = new HBox();
        Label byLine = new Label("By Nate Elison");
        byLine.setTranslateX(-10);
        byLine.setFont(Font.font(10));
        hBoxBottom.getChildren().addAll(byLine);
        hBoxBottom.setAlignment(Pos.CENTER_RIGHT);

        BorderPane borderPane = new BorderPane();
        borderPane.setRight(rightPane);
        borderPane.setTop(hBoxTop);
        borderPane.setLeft(vBox);
        borderPane.setCenter(centerPane);
        borderPane.setBottom(hBoxBottom);

        Scene scene = new Scene(borderPane);
        primaryStage.setTitle("A Simple Stats Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public Pane displayStatsLeader(String[][] leaders, String targetStat, String league, String team) throws FileNotFoundException {
    	BorderPane statPane = new BorderPane();
    	
    	Label targetStatLabel = new Label(league + " " + targetStat + " Leaders");
    	String fileName = "TeamLogos/" + league + ".png";
    	FileInputStream imageInputStream = new FileInputStream(new File(fileName));
    	Image headerImage = new Image(imageInputStream);
      	ImageView imageView = new ImageView(headerImage);
      	targetStatLabel.setFont(Font.font(30));
  	
    	if (team == null && (league.equals("NL") || league.equals("AL"))) {
    		imageView.setFitHeight(200);
			imageView.setFitWidth(200);
    		
    	}
    	else if (team == null && league.equals("MLB")) {
     		imageView.setFitHeight(120);
    		imageView.setFitWidth(200);   		
    	}
    	else {
    		String[][] translateArray = Utility.generateTeamTranslateArray();
    		int index = 0;
    		for (int i = 0; i < translateArray.length; i++) {
    			if (team.equalsIgnoreCase(translateArray[i][0])) {
    				index = i;
    				
    			}
    		}
    		team = translateArray[index][1];
    		System.out.println(team);                 
    		
    		fileName = "TeamLogos/" + team + ".png";
    		targetStatLabel.setText(targetStat + " Leaders");
    		imageInputStream = new FileInputStream(new File(fileName));
    		headerImage = new Image(imageInputStream);
    		imageView = new ImageView(headerImage);
    	}
    	
    	
    
        HBox header = new HBox();
        header.setMinWidth(centerPane.getWidth());

        header.getChildren().addAll(imageView, targetStatLabel);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setStyle("-fx-border-color: black");
    	
    	
    	int displayAmount = 10;
    	// Ensure display amount is less than the filtered players. 
    	if (leaders.length < 10) {
    		displayAmount = leaders.length;
    	}
    	GridPane gridPane = new GridPane();
    	gridPane.setHgap(10);
    	gridPane.setVgap(5);
    	
    	for (int i = 0; i < displayAmount; i++)	{
    		Label number = new Label((i + 1) + ".");
    		number.setFont(Font.font(15));
    		Label name = new Label(leaders[i][0]);
    		name.setFont(Font.font(15));
    		Label stat = new Label(leaders[i][1]);
    		stat.setFont(Font.font(15));
    		
    		gridPane.add(number, 0, i);
    		gridPane.add(name, 1, i);
    		gridPane.add(stat, 2, i);
    		System.out.println("Adding " + i);
    	}
    	gridPane.setAlignment(Pos.CENTER);
    	gridPane.setTranslateY(20);
    	
    	CheckBox reverse = new CheckBox("Reverse");
    	reverse.setIndeterminate(false);
    	StackPane bottomPane = new StackPane();
    	bottomPane.setMinHeight(200);
    	bottomPane.getChildren().add(reverse);
    	bottomPane.setAlignment(Pos.CENTER_RIGHT);
    	
    	reverse.setOnAction(e ->{
    		gridPane.getChildren().clear();
    		String[][] reversedLeaders = Utility.reverseLeaders(leaders);
    		for (int i = 0; i < 10; i++)	{
        		Label number = new Label((i + 1) + ".");
        		number.setFont(Font.font(15));
        		Label name = new Label(reversedLeaders[i][0]);
        		name.setFont(Font.font(15));
        		Label stat = new Label(reversedLeaders[i][1]);
        		stat.setFont(Font.font(15));
        		
        		gridPane.add(number, 0, i);
        		gridPane.add(name, 1, i);
        		gridPane.add(stat, 2, i);
        	}
    		
    		
    		
    	});
    	
    	statPane.setBottom(bottomPane);
    	statPane.setTop(header);
    	statPane.setCenter(gridPane);
    	return statPane;
    }

    public Pane displayHitterInfo(PositionPlayer player) throws FileNotFoundException {
        BorderPane statsPane = new BorderPane();

        // Build header
        HBox header = buildHeader(player);

        // Build Center Pane
        GridPane gridPane = buildHitterStatsPane(player);

        statsPane.setTop(header);
        statsPane.setCenter(gridPane);
        return statsPane;
    }


    public Pane displayPitcherInfo(Pitcher pitcher) throws FileNotFoundException {
        BorderPane statsPane = new BorderPane();

        // Build header
        HBox hBox = buildHeader(pitcher);

        // Build Center Pane
        GridPane gridPane = buildPitcherStatsPane(pitcher);

        statsPane.setTop(hBox);
        statsPane.setCenter(gridPane);

        statsPane.setMinHeight(centerPane.getHeight());
        statsPane.setMinWidth(centerPane.getWidth());

        return statsPane;
    }

    public  Pane displayTwoWayPlayerInfo(Pitcher pitcher, PositionPlayer positionPlayer) throws FileNotFoundException {
        BorderPane statPane = new BorderPane();
        HBox header = buildHeader(positionPlayer);

        GridPane pitcherStats = buildPitcherStatsPane(pitcher);
        GridPane hitterStats = buildHitterStatsPane(positionPlayer);

        Label pitchingStatsLabel = new Label("Pitching Stats");
        Label hittingStatsLabel = new Label("Hitting Stats");

        VBox bothStatLines = new VBox();
        bothStatLines.getChildren().addAll(pitchingStatsLabel, pitcherStats, hittingStatsLabel, hitterStats);

        statPane.setTop(header);
        statPane.setCenter(bothStatLines);

        return statPane;
    }

//

    public HBox buildHeader(Player player) throws FileNotFoundException {
        Label playerName = new Label(player.getName());
        playerName.setFont(Font.font(30));
        Label filler = new Label("");
        filler.setFont(Font.font(30));
        String fileName = "TeamLogos/" + player.getTeam() + ".png";
        FileInputStream imageInputStream = new FileInputStream(new File(fileName));
        HBox hBox = new HBox();
        hBox.setMinWidth(centerPane.getWidth());

        Image teamImage = new Image(imageInputStream);
        hBox.getChildren().addAll(new ImageView(teamImage), playerName, filler);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setStyle("-fx-border-color: black");

        return hBox;
    }

    public GridPane buildPitcherStatsPane(Pitcher pitcher) {
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
        return gridPane;
    }

    public GridPane buildHitterStatsPane(PositionPlayer player) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);

        VBox summaryBox = new VBox();
        VBox abBox = new VBox();
        VBox hBox = new VBox();
        VBox hrBox = new VBox();
        VBox baBox = new VBox();
        VBox rBox = new VBox();
        VBox rbiBox = new VBox();
        VBox sbBox = new VBox();
        VBox obpBox = new VBox();
        VBox slgBox = new VBox();
        VBox opsBox = new VBox();
        VBox opsPlusBox = new VBox();

        summaryBox.getChildren().addAll(new Label("Summary"), new Label("2022"));
        abBox.getChildren().addAll(new Label("AB"), new Label(String.valueOf(player.getAtBats())));
        hBox.getChildren().addAll(new Label("H"), new Label(String.valueOf(player.getHits())));
        hrBox.getChildren().addAll(new Label("HR"), new Label(String.valueOf(player.getHomeRuns())));
        baBox.getChildren().addAll(new Label("BA"), new Label(String.valueOf(player.getBattingAverage())));
        rBox.getChildren().addAll(new Label("R"), new Label(String.valueOf(player.getRuns())));
        rbiBox.getChildren().addAll(new Label("RBI"), new Label(String.valueOf(player.getRBIs())));
        sbBox.getChildren().addAll(new Label("SB"), new Label(String.valueOf(player.getStolenBases())));
        obpBox.getChildren().addAll(new Label("OBP"), new Label(String.valueOf(player.getOnBasePercentage())));
        slgBox.getChildren().addAll(new Label("SLG"), new Label(String.valueOf(player.getSlugging())));
        opsBox.getChildren().addAll(new Label("OPS"), new Label(String.valueOf(player.getOnBasePlusSlugging())));
        opsPlusBox.getChildren().addAll(new Label("OPS+"), new Label(String.valueOf(player.getOpsPlus())));

        summaryBox.setAlignment(Pos.CENTER);
        abBox.setAlignment(Pos.CENTER);
        hBox.setAlignment(Pos.CENTER);
        hrBox.setAlignment(Pos.CENTER);
        baBox.setAlignment(Pos.CENTER);
        rBox.setAlignment(Pos.CENTER);
        rbiBox.setAlignment(Pos.CENTER);
        sbBox.setAlignment(Pos.CENTER);
        obpBox.setAlignment(Pos.CENTER);
        slgBox.setAlignment(Pos.CENTER);
        opsBox.setAlignment(Pos.CENTER);
        opsPlusBox.setAlignment(Pos.CENTER);

        gridPane.add(summaryBox, 0, 0);
        gridPane.add(abBox, 1, 0);
        gridPane.add(hBox, 2, 0);
        gridPane.add(hrBox, 3, 0);
        gridPane.add(baBox, 4, 0);
        gridPane.add(rBox, 5, 0);
        gridPane.add(rbiBox, 6, 0);
        gridPane.add(sbBox, 7, 0);
        gridPane.add(obpBox, 8, 0);
        gridPane.add(slgBox, 9, 0);
        gridPane.add(opsBox, 10, 0);
        gridPane.add(opsPlusBox, 11, 0);

        gridPane.setHgap(20);
        gridPane.setTranslateY(30);
        return gridPane;
    }


    public void playerSearchAction(String name, ArrayList<Pitcher> pitchers, ArrayList<PositionPlayer> hitters) throws FileNotFoundException {
        centerPane.getChildren().clear();
        Pitcher pitcher = Utility.getPitcher(pitchers, name);
        PositionPlayer hitter = Utility.getPositionPlayer(hitters, name);
        int playerType = Utility.identifyPlayerType(pitcher, hitter);
        if (playerType == 1) 	  centerPane.getChildren().add(displayPitcherInfo(pitcher));
        else if (playerType == 2) centerPane.getChildren().add(displayTwoWayPlayerInfo(pitcher, hitter));
        else if (playerType == 3) centerPane.getChildren().add(displayHitterInfo(hitter));
        else {
            Label notFound = new Label(name + " not found. Please try again.*");
            Label moreInfo = new Label("*Please keep in mind only 2022 MLB players are currently supported. \nMore years and more features to come in later projects.\nThank you!");
            notFound.setFont(Font.font(15));
            notFound.setAlignment(Pos.CENTER);
            moreInfo.setAlignment(Pos.CENTER);
            BorderPane pane = new BorderPane();
            pane.setPrefHeight(prefWidth);
            pane.setPrefWidth(prefWidth);
            pane.setCenter(notFound);
            pane.setBottom(moreInfo);
            notFound.setAlignment(Pos.CENTER);
            moreInfo.setAlignment(Pos.CENTER);
            moreInfo.setTranslateY(-5);
            moreInfo.setTranslateX(5);
            centerPane.getChildren().addAll(pane);
        }
     
//        
//        
//        
//        
//        
//        if (pitcher != null && hitter == null){
//            centerPane.getChildren().add(displayPitcherInfo(pitcher));
//        }
//        else if (pitcher != null && hitter != null) {
//            centerPane.getChildren().add(displayTwoWayPlayerInfo(pitcher, hitter));
//        }
//        else if (pitcher == null && hitter != null) {
//            centerPane.getChildren().add(displayHitterInfo(hitter));
//        }
//        else {
//            Label notFound = new Label(name + " not found. Please try again.*");
//            Label moreInfo = new Label("*Please keep in mind only 2022 MLB players are currently supported. \nMore years and more features to come in later projects.\nThank you!");
//            notFound.setFont(Font.font(15));
//            notFound.setAlignment(Pos.CENTER);
//            moreInfo.setAlignment(Pos.CENTER);
//            BorderPane pane = new BorderPane();
//            pane.setPrefHeight(prefWidth);
//            pane.setPrefWidth(prefWidth);
//            pane.setCenter(notFound);
//            pane.setBottom(moreInfo);
//            notFound.setAlignment(Pos.CENTER);
//            moreInfo.setAlignment(Pos.CENTER);
//            moreInfo.setTranslateY(-5);
//            moreInfo.setTranslateX(5);
//            centerPane.getChildren().addAll(pane);
//        }


    }






    // Future update method that spits out the stats gridpane w only the selected stats.
//    public static GridPane displaySelectedStats(String[] statsToDisplay, Pitcher pitcher) {
//        GridPane statpane = new GridPane();
//        String[] statsAsStrings;
//        for (int i = 0; i < statsToDisplay.length; i++){
////            if (statsToDisplay[i].equalsIgnoreCase())
//        }
//
//        for (int i = 0; i < statsToDisplay.length; i++) {
//            VBox vBox = new VBox();
//            vBox.getChildren().addAll(new Label(statsToDisplay[i]));
//
//        }
//
//        return statpane;
//
//    }

    // Future Update with stat section check boxes.
//    public static VBox buildPitcherStatSectionBoxes() {
        // Build Bottom pane
//        HBox defaultCheckBoxes = new HBox();
//        CheckBox chkWin = new CheckBox("W");
//        CheckBox chkLoss = new CheckBox("L");
//        CheckBox chkERA = new CheckBox("ERA");
//        CheckBox chkGames = new CheckBox("G");
//        CheckBox chkGamesStarted = new CheckBox("GS");
//        CheckBox chkSaves = new CheckBox("SV");
//        CheckBox chkIp = new CheckBox("IP");
//        CheckBox chkSo = new CheckBox("SO");
//        CheckBox chkWhip = new CheckBox("WHIP");
//
//        chkWin.setSelected(true);
//        chkLoss.setSelected(true);
//        chkERA.setSelected(true);
//        chkGames.setSelected(true);
//        chkGamesStarted.setSelected(true);
//        chkSaves.setSelected(true);
//        chkIp.setSelected(true);
//        chkSo.setSelected(true);
//        chkWhip.setSelected(true);
//
//        chkWin.setFont(Font.font(10));
//        chkLoss.setFont(Font.font(10));
//        chkERA.setFont(Font.font(10));
//        chkGames.setFont(Font.font(10));
//        chkGamesStarted.setFont(Font.font(10));
//        chkSaves.setFont(Font.font(10));
//        chkIp.setFont(Font.font(10));
//        chkSo.setFont(Font.font(10));
//        chkWhip.setFont(Font.font(10));
//
//        defaultCheckBoxes.getChildren().addAll(chkWin,chkLoss, chkERA, chkGames, chkGamesStarted, chkSaves, chkIp, chkSo, chkWhip);
//        defaultCheckBoxes.setSpacing(15);
//        defaultCheckBoxes.setAlignment(Pos.CENTER);
//        VBox checkBoxes = new VBox();
//        checkBoxes.getChildren().addAll(defaultCheckBoxes);
////        checkBoxes.setStyle("-fx-border-color: black");
//        checkBoxes.setTranslateY(-5);
//        VBox vBox = new VBox();
//        vBox.getChildren().add(defaultCheckBoxes);
//        return vBox;
//    }

}