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
        Team teamA = new Team(GameSetUpController.nameA);
        Team teamB = new Team(GameSetUpController.nameB);

        teamAArea.setText(teamA.getTeamName());
        teamBArea.setText(teamB.getTeamName());

        timerLabel.setText(Integer.toString(INITIAL_SECOND));

        changeTraversability();
    }

    @FXML
    public void handleBuzzer(KeyEvent keyEvent) {
        Buzzer buzzer = new Buzzer();
        Label[] labels = { teamAArea, teamBArea, timerLabel };
        buzzer.buzz(keyEvent.getCode(), labels);
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

}
