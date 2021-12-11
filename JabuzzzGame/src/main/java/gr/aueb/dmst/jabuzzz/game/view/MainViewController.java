package main.java.gr.aueb.dmst.jabuzzz.game.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import main.java.gr.aueb.dmst.jabuzzz.entities.Score;
import main.java.gr.aueb.dmst.jabuzzz.dbconnector.DBConnector;
import main.java.gr.aueb.dmst.jabuzzz.entities.Team;
import main.java.gr.aueb.dmst.jabuzzz.utilities.Buzzer;

public class MainViewController implements Initializable {

    private static final int INITIAL_SECOND = 5;

    @FXML
    private ToggleGroup Options;

    @FXML
    private Button exitButton;

    @FXML
    private Button buzzerButton;

    @FXML
    private Label teamAArea;

    @FXML
    private Label teamBArea;
    
    @FXML
    private Label scoreAArea;

    @FXML
    private Label scoreBArea;

    @FXML
    private Label questionArea;

    @FXML
    private RadioButton answerA;

    @FXML
    private RadioButton answerB;

    @FXML
    private RadioButton answerC;

    @FXML
    private RadioButton answerD;

    @FXML
    private RadioButton answerE;

    @FXML
    private Label timerLabel;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	DBConnector dbconnector = new DBConnector();
		dbconnector.connect();
		String[] Q = dbconnector.selectQuestion("Geography", 1);
		String[] Q2 = dbconnector.selectQuestion("History", 32);
    	
        Team teamA = new Team(GameSetUpController.nameA);
        Team teamB = new Team(GameSetUpController.nameB);
        
        Score scoreA = new Score(GameSetUpController.goal);
        Score scoreB = new Score(GameSetUpController.goal);

        teamAArea.setText(teamA.getTeamName());
        teamBArea.setText(teamB.getTeamName());
        
       scoreAArea.setText(scoreA.toString());
        scoreBArea.setText(scoreB.toString());
       
        timerLabel.setText(Integer.toString(INITIAL_SECOND));

        changeTraversability();
        disableButtons();
    }

    @FXML
    public void handleBuzzer(KeyEvent keyEvent) {
        Buzzer buzzer = new Buzzer();
        Label[] labels = { teamAArea, teamBArea, timerLabel };
        buzzer.buzz(keyEvent.getCode(), labels);
        enableButtons();
    }

    @FXML
    private void Exit() {
        System.exit(0);
    }

    /*
     * changeTraversability disables focus that was initially on the wrong buttons,
     * and initilises it to the buzzerButton object.
     */
    private void changeTraversability() {
        exitButton.setFocusTraversable(false);
        answerA.setFocusTraversable(false);
        answerB.setFocusTraversable(false);
        answerC.setFocusTraversable(false);
        answerD.setFocusTraversable(false);
        answerE.setFocusTraversable(false);
        buzzerButton.setFocusTraversable(true);
    }
    
    private void disableButtons() {
    	changeButtonStatus(true);
    }
    
    private void enableButtons() {
    	changeButtonStatus(false);
    	
    }
    
    public void changeButtonStatus(boolean a) {
    	answerA.setDisable(a);
    	answerB.setDisable(a);
    	answerC.setDisable(a);
    	answerD.setDisable(a);
    	answerE.setDisable(a);
    }
    
    public void onAnswerGiven() {
    	disableButtons();
    	//TODO: stopTimer(), checkAnswer(), showCorrectAnswer()
    }
    
    public void timeIsUp() {
    	
    }

}
