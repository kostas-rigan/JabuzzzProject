package main.java.gr.aueb.dmst.jabuzzz.game.view;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import main.java.gr.aueb.dmst.jabuzzz.entities.Question;
import main.java.gr.aueb.dmst.jabuzzz.entities.Score;
import main.java.gr.aueb.dmst.jabuzzz.dbconnector.DBConnector;
import main.java.gr.aueb.dmst.jabuzzz.entities.Team;
import main.java.gr.aueb.dmst.jabuzzz.utilities.Buzzer;

public class MainViewController implements Initializable {

	private static final int INITIAL_SECOND = 5;
    private int quest = 0;
	private Buzzer buzzer = new Buzzer();


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
		String[] Q2 = dbconnector.selectQuestion("Geography", 2);
		Question quest1 = new Question(Q);
		Question quest2 = new Question(Q2);
		Question.shuffleQuestion();
		setNewQA();
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

	/**
	 * handleBuzzer method initiates when a team presses their respective key.
	 * It controls the flow of the game regarding to the question answered.
	 * @param keyEvent the first key pressed by either team.
	 */
	@FXML
	public void handleBuzzer(KeyEvent keyEvent) {
	    if (keyEvent.getCode() == KeyCode.A || keyEvent.getCode() == KeyCode.L) {
	        exitButton.requestFocus();
	        Label[] labels = { teamAArea, teamBArea, timerLabel };
	        buzzer.buzz(keyEvent.getCode(), labels);
	        enableButtons();
        }
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
		stopTimer();
		checkAnswer();
		showCorrectAnswer();
	}

	public void timeIsUp() {

	}

	private void checkAnswer() {
	    RadioButton button = (RadioButton) Options.getSelectedToggle();
	    String correctAnswer = Question.getCorrectAnswer(quest); 
	    // TODO: change score in if else statement
	    if (button.getText().equals(correctAnswer)) {
	        System.out.println("Correct answer");
	    } else {
	        changeBackgroundColor("red", button);
	        System.out.println("Wrong answer");
	    }
	}

	private void setNewQA() {
		answerA.setText(Question.getAnswer(quest, 0));
		answerB.setText(Question.getAnswer(quest, 1));
		answerC.setText(Question.getAnswer(quest, 2));
		answerD.setText(Question.getAnswer(quest, 3));
		answerE.setText(Question.getAnswer(quest, 4));
		questionArea.setText(Question.getQuestions(quest));
		quest++;
	}
	
	private void stopTimer() {
	    buzzer.stop();
	}

	private void showCorrectAnswer() {
	    for (Iterator<Toggle> iterator = Options.getToggles().iterator(); iterator.hasNext();) {
            RadioButton button = (RadioButton) iterator.next();
            if (button.getText().equals(Question.getCorrectAnswer(quest))) {
                changeBackgroundColor("green", button);
            }
        }
	}
	
	private void changeBackgroundColor(String color, RadioButton button) {
	    Background background = new Background(
	            new BackgroundFill(Paint.valueOf(color),
	            new CornerRadii(0.1, true), null));
        button.setBackground(background);
	}
}
