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

/**
 * <h1>FrontEnd</h1>
 * This class contains all the front end code for the stats viewer.
 *
 * <p>Last updated 6/20/23</p>
 *
 * @author Nate Elison
 */
public class FrontEnd extends Application {
    // Import files and generate arraylists
    File pitchersFile = new File("22PitcherStats.csv");
    ArrayList<Pitcher> pitchers = Utility.generatePitcherArray(pitchersFile);
    File hittersFile = new File("22HitterStats.csv");
    ArrayList<PositionPlayer> hitters = Utility.generateHitterArray(hittersFile);
    String [][] translateArray = Utility.generateTeamTranslateArray();

    // Create center pane
    Pane centerPane = new Pane();
    int prefWidth = 620;
    int prefHeight = 500;

    public FrontEnd() throws FileNotFoundException {
    }

    /**
     * This method is used to launch the JavaFx program, if needed by the compiler.
     * @param args
     */
    public static void main(String [] args) {
        launch(args);
    }

    /**
     * This method is used to build the nodes used in the JavaFx program.
     * The method builds the scene using a main border pane (borderPane).
     * The method is organized by first building the top pane,
     * then the right, left, and finally the bottom pane, which are placed in borderPane.
     *
     * @param primaryStage (Stage; stage the scene is placed on)
     * @param centerPane (Pane; pane displayed in the center of the program.)
     * @param hitters (ArrayList<PositionPlayer>; generated from the 22HittersStats.csv file that holds all the position player objects from the year 2022.)
     * @param pitchers (ArrayList<Pitcher>; generated from the 22PitchersStats.csv file that holds all the pitcher objects from the year 2022.)
     * @param pitchersFile (File; file that contains the pitching stats.)
     * @param hittersFile (File; file that contains the hitting stats.)
     * @param translateArray (String[][]; used to translate between the full team name and the team abbreviations)
     * @param prefWidth (double; the preferred width of the centerPane.)
     * @param prefHeight (double; the preferred height of the centerPane.)
     * @param hBoxTop (HBox; box that holds and center's the project's title)
     * @param title (Label; Label that holds the title text)
     * @param playerSearchTF (textField; textField where the user can search for a specific player by name)
     * @param rightPane (BorderPane; Pane that holds the nodes on the right side of the main borderPane.)
     * @param statLeaders (VBox; VBox that contains all the nodes used to display the stat leaders.)
     * @param cbYears (ComboBox; allows user to chose the year of the stat leaders they'd like to display.)
     * @param yearsVBox (VBox; holds the years comboBox and the years label in statLeaders)
     * @param pitchingStats (RadioButton; allows users to select if they want to see pitching stat leaders)
     * @param hittingStats (RadioButton; allows users to select if they want to see hitting stat leaders)
     * @param statsTypeGroup (ToggleGroup; groups the pitchingStats and hittingStats radiobuttons to get which is selected.)
     * @param statTypeVBox (VBox; holds the pitchingStats and hittingStats radiobuttons in the statLeaders VBox.)
     * @param statLeadersLabel (Label; Label for the statLeaders VBox.)
     * @param stats (Label; Label for the stat select comboBox in the statsLeaders VBox.)
     * @param statsCb (ComboBox; allows users to select which stat they'd like to see the leaders of.)
     * @param statsCbVBox (VBox; holds the stats label and statsCb in the statLeaders VBox.)
     * @param filters (Label; used to label the filters section of the StatLeaders VBox.)
     * @param rbMLB (RadioButton; allows user to select if they'd like to see the stat leaders of all MLB.)
     * @param rbNL (RadioButton; allows users to select if they'd like to see the stat leaders of only the NL.)
     * @param rbAL (RadioButton; allows users to select if they'd like to see the stat leaders of only the AL.)
     * @param leagueGroup (ToggleGroup; groups rbMLB, rbNL, rbAL, to get the selected filter.)
     * @param filtersBox (VBox; Holds all the filtering elements in the statsLeaders VBox.)
     * @param filtersTeam (ComboBox; Allows users to show only the stat leaders from a specific team.)
     * @param team (Label; Labels the filtersTeam ComboBox)
     * @param go (Button; Triggers the displaying of the filtered stat leaders.)
     * @param paMinTF (TextField; where users can input if they want to add a minimum plate appearances parameter.)
     * @param ipMinTF (TextField; where users can input if they want to add a minimum innings pitched requirement.)
     * @param minIp (Double; gotten from the input of the ipMinTF. If nothing is input by the user, set to 0.)
     * @param minPa (int; gotten from the input of the paMinTF. If nothing is input by the user, set to 0.)
     * @param min (VBox; used to store the paMinTF or ipMinTF.)
     * @param leaders (String[][]; Holds a list of players and 1 stat to be displayed. Sorted descending or ascending by the stat.)
     * @param stat (String; The stat the user selected to see the leaders of.)
     * @param selectedLeague (RadioButton; The selected radiobutton from the leagueGroup toggleGroup).
     * @param league (String; The text associated with selectedLeague.)
     * @param selectedTeam (String; Used to get the team selected from the comboBox in String format.)
     * @param filteredTeam (String; The team selected from the comboBox )
     * @param vBox (VBox; VBox to hold all the nodes of the left pane)
     * @param playerSearchLabel (Label; Title for the left pane (player search))
     * @param yearLabel (Label; Label for the year comboBox in the player search pane. )
     * @param cbYear (ComboBox<Integet>; combobox used to select the year in the player search pane.)
     * @param teamLabel (Label; label for the team select combobox in the player search pane.)
     * @param cbTeam (ComboBox<String>; used to select the team in the player search pane.)
     * @param playerLabel (Label; labels the combobox used to select the player in the player search pane.)
     * @param cbPlayer (ComboBox<String>; used to select a player in the player search pane.)
     * @param pitcher (Pitcher; pitcher selected by the user to display their stats.)
     * @param hitter (PositionPlayer; hitter selected by user to display their stats.)
     * @param playerType (int; used to decide which stats to be shown (hitter, pitcher, or two way player))
     * @param explanation (Label; gives an explanation on where to find players in the player search comboboxes. )
     * @param years (VBox; Holds the yearLabel and cbYear in the player search pane.)
     * @param teams (VBox; Holds teamLabel and cbTeam in the player search pane.)
     * @param players (VBox; Holds playerLabel and cbPlayer in the player search pane.)
     * @param hBoxBottom (HBox; the HBox placed in the bottom portion of the overall borderpane.)
     * @param byLine (Label; displays the byline at the bottom right of the scene.)
     * @param borderPane (BorderPane; Holds all the nodes in the scene.)
     * @param scene (Scene; holds the main borderPane.)
	 *
     * @throws FileNotFoundException
     */
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
        
        ComboBox cbYears = new ComboBox();
        cbYears.getItems().addAll("2022");
        VBox yearsVBox = new VBox();
        yearsVBox.getChildren().addAll(new Label("Year:"), cbYears);
        
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
        // TODO Can I remove filtereTeam nad just use selectedTeam?
        String selectedTeam = (String) filtersTeam.getValue();
        String filterTeam = selectedTeam;

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
        vBox.setMinWidth(160);
        Label playerSearchLabel = new Label("View Player Stats");
        playerSearchLabel.setFont(Font.font(20));
        Label yearLabel = new Label("Year:");
        ComboBox<Integer> cbYear = new ComboBox<Integer>();
        cbYear.getItems().addAll(2022);
        Label teamLabel = new Label("Team:");
        ComboBox<String> cbTeam = new ComboBox<String>();
        cbTeam.setMaxWidth(150);
        for (int i = 0; i < translateArray.length; i++) {
    		cbTeam.getItems().add(translateArray[i][0]);
    	}
      
        Label playerLabel = new Label("Player:");
        ComboBox<String> cbPlayer = new ComboBox<String>();
        cbPlayer.setMaxWidth(150);

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
        Label explanation = new Label("Players can be found listed \nunder the last team they hit \nfor " +
                "and/or the last team they \npitched for during the \nselected year.");
        explanation.setTranslateY(150);
        vBox.setSpacing(5);
        vBox.setTranslateX(5);
        vBox.setAlignment(Pos.CENTER_LEFT);
        VBox years = new VBox();
        VBox teams = new VBox();
        VBox players = new VBox();

        years.getChildren().addAll(yearLabel, cbYear);
        teams.getChildren().addAll(teamLabel, cbTeam);
        players.getChildren().addAll(playerLabel, cbPlayer);


        vBox.getChildren().addAll(playerSearchLabel, years, teams, players, explanation);

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
    
    /**
     * This method takes in the selected target stat, league, team, and a 2d array of the stat leaders, 
     * returns a borderPane displaying the stat leaders.
     * 
     * @param statPane (BorderPane; pane that is built and returned by the method.)
     * @param targetStatLabel (Label; header title.)
     * @param fileName (String; the string used to find the correct image to display in the header.)
     * @param imageInputStream (FileInputStream; input stream used to access the correct .png file.)
     * @param headerImage (Image; image displayed in the header.)
     * @param imageView (ImageView; used to display the image.)
     * @param translateArray (String[][]; used to translate the full team name to the team abbreviation)
     * @param index (int; index of translateArray to be saved as the needed string, which is used to find the correct image to be displayed.)
     * @param header (HBox; holds the headers nodes: image and title.)
     * @param displayAmount (int; number of leaders to be displayed. Checked to ensure the program doesn't try to display more players than available )
     * @param gridPane (GridPane; pane that holds to leader's names and stats.)
     * @param yearLabel (Label; )
     * @param yearLabel (Label; )

     * @param leaders (String[][]; holds the leader's name and stat to be displayed.)
     * @param targetStat (String; the name of the selected target stat)
     * @param league (String; the name of the string of the filtered league)
     * @param team (String; the name of the filtered team, if selected)
     * @return Pane 
     * @throws FileNotFoundException
     */
    
    public Pane displayStatsLeader( String[][] leaders, String targetStat, String league, String team) throws FileNotFoundException {
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

    	Button reverse = new Button("Reverse");
    	VBox bottomPane = new VBox();
    	bottomPane.setMinHeight(200);
    	bottomPane.getChildren().add(reverse);
    	bottomPane.setAlignment(Pos.CENTER_RIGHT);
        bottomPane.setTranslateX(-10);

    	reverse.setOnAction(e ->{
    		gridPane.getChildren().clear();
    		String[][] reversedLeaders = Utility.reverseLeaders(leaders);
    		for (int i = 0; i < 10; i++)	{
        		Label number = new Label((i + 1) + ".");
        		number.setFont(Font.font(15));
        		Label name = new Label(reversedLeaders[i + 1][0]);
        		name.setFont(Font.font(15));
        		Label stat = new Label(reversedLeaders[i + 1][1]);
        		stat.setFont(Font.font(15));

        		gridPane.add(number, 0, i);
        		gridPane.add(name, 1, i);
        		gridPane.add(stat, 2, i);
        	}
    		Label reversal = new Label("Press Go! to revert");
            bottomPane.getChildren().addAll(reversal);

    	});
    	if (leaders.length == 0) {
            gridPane.getChildren().clear();
            Label noPlayersFound = new Label("No Players Found with given parameters. Please try again");
            noPlayersFound.setFont(Font.font(20));
            gridPane.add(noPlayersFound, 0, 0);
        }
    	if (targetStat.equals("null")) {
            gridPane.getChildren().clear();
            Label noStatFound = new Label("Please select a stat and try again");
            noStatFound.setFont(Font.font(20));
            gridPane.add(noStatFound, 0, 0);
        }
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



        VBox bothStatLines = new VBox();
        bothStatLines.setSpacing(20);
        bothStatLines.getChildren().addAll(pitcherStats, hitterStats);

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
            notFound.setFont(Font.font(20));
            notFound.setAlignment(Pos.CENTER);
            moreInfo.setAlignment(Pos.CENTER);
            BorderPane pane = new BorderPane();
            pane.setPrefHeight(prefWidth);
            pane.setPrefWidth(prefWidth);
            pane.setCenter(notFound);
            pane.setBottom(moreInfo);
            notFound.setAlignment(Pos.CENTER);
            moreInfo.setAlignment(Pos.CENTER);
            moreInfo.setTranslateY(-50);
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