package main.java.gr.aueb.dmst.jabuzzz.game.view;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.TimerTask;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
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
import javafx.util.Duration;
import main.java.gr.aueb.dmst.jabuzzz.entities.Question;
import main.java.gr.aueb.dmst.jabuzzz.entities.Score;
import main.java.gr.aueb.dmst.jabuzzz.dbconnector.DBConnector;
import main.java.gr.aueb.dmst.jabuzzz.entities.Team;
import main.java.gr.aueb.dmst.jabuzzz.utilities.Buzzer;
import main.java.gr.aueb.dmst.jabuzzz.utilities.Timer;

public class MainViewController implements Initializable {

    private static final int INITIAL_SECOND = 5;
    private int quest = 0;
    private Buzzer buzzer = new Buzzer();
    private DBConnector dbconnector = new DBConnector();
    private Score scoreA;
    private Score scoreB;
    private Score playingTeamScore;
    private Label playingTeamScoreArea;
    private String playingTeam;
    private Timer timer;
    private int currentSecond = INITIAL_SECOND;
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(INITIAL_SECOND);
    private Timeline timeline;
    private int pointsToFinish;

    @FXML
    private ToggleGroup Options;

    @FXML
    private Button exitButton;

    @FXML
    private Button nextButton;

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
        dbconnector.connect();
        loadQuestions();
        Question.shuffleQuestion();
        setNewQA();
        Team teamA = new Team(GameSetUpController.nameA);
        Team teamB = new Team(GameSetUpController.nameB);

        scoreA = new Score();
        scoreB = new Score();

        teamAArea.setText(teamA.getTeamName());
        teamBArea.setText(teamB.getTeamName());

        scoreAArea.setText(scoreA.toString());
        scoreBArea.setText(scoreB.toString());

        // timerLabel.setText(Integer.toString(INITIAL_SECOND));
        timerLabel.textProperty().bind(timeSeconds.asString());
        timer = new Timer(timerLabel);

        pointsToFinish = GameSetUpController.getFinishPoints();

        changeTraversability();
        disableButtons();
    }

    /**
     * handleBuzzer method initiates when a team presses their respective key. It
     * controls the flow of the game regarding to the question answered.
     * 
     * @param keyEvent the first key pressed by either team.
     */
    @FXML
    public void handleBuzzer(KeyEvent keyEvent) throws InterruptedException {
        if (keyEvent.getCode() == KeyCode.A || keyEvent.getCode() == KeyCode.L) {
            exitButton.requestFocus();
            if (keyEvent.getCode() == KeyCode.A) {
                playingTeamScore = scoreA;
                playingTeamScoreArea = scoreAArea;
                playingTeam = teamAArea.getText();
            } else {
                playingTeamScore = scoreB;
                playingTeamScoreArea = scoreBArea;
                playingTeam = teamBArea.getText();
            }

            Label[] labels = { teamAArea, teamBArea };
            buzzer.buzz(keyEvent.getCode(), labels);
            // important to reinitialize timer object for controlTimeUp
            // TODO(Kostas): check if it can be done without a timer object
            timer = new Timer(timerLabel);
            initiateTimer();
            enableButtons();
            controlTimeUp();
        }
    }

    /**
     * Initiates actions when the playing team answers the question.
     * 
     * Stops the timer and disables the answer buttons, checks if
     * response is correct or wrong and evaluates if the team won
     * (reached maximum points) or lost(reached -5), giving the win
     * to the other team.
     * 
     * @throws InterruptedException
     */
    @FXML
    public void onAnswerGiven() throws InterruptedException {
        disableButtons();
        stopTimer();
        checkAnswer();
        showCorrectAnswer();
        nextButton.setOpacity(1);
        nextButton.setDisable(false);
        checkGameOver();
    }

    @FXML
    private void Exit() {
        System.exit(0);
    }

    /**
     * Changes to the next question after a round is finished.
     * 
     * @param event the button pressed to advance to the next round
     */
    @FXML
    void setNextQuestion(ActionEvent event) {
        timeSeconds.set(INITIAL_SECOND);
        quest++;
        setNewQA();
        nextButton.setOpacity(0);
        nextButton.setDisable(true);
        unselectButton();
        resetRadioButtonBGColour();
    }

    /*
     * Initiates count down when either team has pressed their respective key to answer the question.
     */
    private void initiateTimer() {
        if (timeline != null) {
            timeline.stop();
        }
        timeSeconds.set(INITIAL_SECOND);
        timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(INITIAL_SECOND + 1), new KeyValue(timeSeconds, 0)));
        timeline.playFromStart();
    }

    /*
     * changeTraversability disables focus that was initially on the wrong buttons,
     * and initilises it to the buzzerButton object.
     */
    private void changeTraversability() {
        exitButton.setFocusTraversable(false);
        for (Iterator<Toggle> iterator = Options.getToggles().iterator(); iterator.hasNext();) {
            RadioButton radioButton = (RadioButton) iterator.next();
            radioButton.setFocusTraversable(false);
        }
        buzzerButton.setFocusTraversable(true);
    }

    // disables radio buttons
    private void disableButtons() {
        changeButtonStatus(true);
    }

    // enable radio buttons
    private void enableButtons() {
        changeButtonStatus(false);

    }

    /*
     * Depending on the boolean value, it disables(true) or enables(false)
     * the radio buttons on screen.
     */
    private void changeButtonStatus(boolean a) {
        for (Iterator<Toggle> iterator = Options.getToggles().iterator(); iterator.hasNext();) {
            RadioButton radioButton = (RadioButton) iterator.next();
            radioButton.setDisable(a);
        }
    }

    /*
     * Controls actions used when timer's count down is over.
     * Makes the proper changes to the interface when the playing team hasn't answered
     * and checks if there is a game over condition.
     */
    private void timeIsUp() {
        disableButtons();
        showCorrectAnswer();
        currentSecond = INITIAL_SECOND;
        playingTeamScore.wrongAnswer();
        Platform.runLater(new Runnable() {
            
            @Override
            public void run() {
                playingTeamScoreArea.setText(playingTeamScore.toString());
            }
        });
        nextButton.setOpacity(1);
        nextButton.setDisable(false);
        checkGameOver();
    }

    /*
     * When the team answers a question, their answer is checked to see if
     * it was correct or wrong and alters their score accordingly.
     */
    private void checkAnswer() {
        RadioButton button = (RadioButton) Options.getSelectedToggle();
        String correctAnswer = Question.getCorrectAnswer(quest);
        if (button.getText().equals(correctAnswer)) {
            playingTeamScore.correctAnswer();
        } else {
            playingTeamScore.wrongAnswer();
            changeBackgroundColor("#f17b8f", button);
        }
        playingTeamScoreArea.setText(playingTeamScore.toString());
    }

    /*
     * At the beginning of each turn loads a question from the list and displays it on screen.
     */
    private void setNewQA() {
        int answer = 0;
        for (Iterator<Toggle> iterator = Options.getToggles().iterator(); iterator.hasNext();) {
            RadioButton radioButton = (RadioButton) iterator.next();
            radioButton.setText(Question.getAnswer(quest, answer));
            answer++;
        }
        questionArea.setText(Question.getQuestions(quest));
    }

    /*
     * Cancels current timeline object count down if the playing team has answered their question.
     */
    private void stopTimer() {
        if (Options.getSelectedToggle() != null) {
            timeline.stop();
        }
        currentSecond = INITIAL_SECOND;
    }

    /*
     * Shows to both teams which was the correct answer either responded or not.
     * On screen it is highlighted with a green colour.
     * #b3f17b -> in hexadecimal system it is a greenish colour.
     */
    private void showCorrectAnswer() {
        for (Iterator<Toggle> iterator = Options.getToggles().iterator(); iterator.hasNext();) {
            RadioButton button = (RadioButton) iterator.next();
            if (button.getText().equals(Question.getCorrectAnswer(quest))) {
                changeBackgroundColor("#b3f17b", button);
            }
        }
    }

    /*
     * Given a colour and a radio button, it highlights the background colour of the button,
     * sharpening a bit the corners giving a more round look.
     */
    private void changeBackgroundColor(String color, RadioButton button) {
        Background background = new Background(
                new BackgroundFill(Paint.valueOf(color), new CornerRadii(0.1, true), null));
        button.setBackground(background);
    }

    /*
     * Loads questions from database, depending on categories given in game setup.
     * 
     * TODO: change it to load questions more dynamically.
     */
    private void loadQuestions() {
        for (int i = 1; i <= 5; i++) {
            new Question(dbconnector.selectQuestion("Geography", i));
        }
    }

    /*
     * Handles the event of timer finishing its count down without a question
     * being answered.
     */
    private void controlTimeUp() {
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                if (currentSecond > 0) {
                    currentSecond--;
                } else {
                    if (timerLabel.getText().equals("0")) {
                        timeIsUp();
                    }
                    timer.cancel();
                }
            }
        }, 500, 1000); 
    }

    /**
     * After an answer is given, it clears the selection of every button
     * TODO: find a way to not loop through every button
     */
    private void unselectButton() {
        for (Iterator<Toggle> iterator = Options.getToggles().iterator(); iterator.hasNext();) {
            RadioButton button = (RadioButton) iterator.next();
            button.setSelected(false);
        }
    }

    /*
     * After each turn has passed, it clears the background colour of every radio button.
     * Loop is used because when a wrong answer is given, there are two radio buttons with
     * a background colour.
     */
    private void resetRadioButtonBGColour() {
        for (Iterator<Toggle> iterator = Options.getToggles().iterator(); iterator.hasNext();) {
            RadioButton button = (RadioButton) iterator.next();
            Background background = new Background(new BackgroundFill(null, null, null));
            button.setBackground(background);
        }
    }

    /*
     * Check if the playing team has reached maximum points or lowest points
     * and determine the winner team.
     */
    private void checkGameOver() {
        if (playingTeamScore.getTeamsScore() == pointsToFinish) {
            hideAndDisableInWinner();
            showWinner(playingTeam);
        } else if (playingTeamScore.getTeamsScore() == -5){
            hideAndDisableInWinner();
            if (playingTeam.equals(teamAArea.getText())) {
                showWinner(teamBArea.getText());
            } else {
                showWinner(teamAArea.getText());
            }
        }
    }

    /*
     * Show the winning team, which is given by a String representation.
     */
    private void showWinner(String winnerTeam) {
        String winningMessage = "\tΤέλος Παιχνιδιού\nΗ ομάδα " + winnerTeam + " κέρδισε!!";
        questionArea.setText(winningMessage);
    }

    /*
     * Hide every unnecessary object when a winning team has been determined.
     */
    private void hideAndDisableInWinner() {
        for (Iterator<Toggle> iterator = Options.getToggles().iterator(); iterator.hasNext();) {
            RadioButton button = (RadioButton) iterator.next();
            button.setOpacity(0);
        }
        scoreAArea.setOpacity(0);
        scoreBArea.setOpacity(0);
        teamAArea.setOpacity(0);
        teamBArea.setOpacity(0);
        timerLabel.setOpacity(0);
        nextButton.setDisable(true);
        nextButton.setOpacity(0);
    }
}
