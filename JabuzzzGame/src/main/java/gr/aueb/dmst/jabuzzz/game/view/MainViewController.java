package main.java.gr.aueb.dmst.jabuzzz.game.view;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

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
import javafx.scene.paint.Color;
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
    private Timer timer;
    private int currentSecond = INITIAL_SECOND;
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(INITIAL_SECOND);
    private Timeline timeline;

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

        scoreA = new Score(GameSetUpController.goal);
        scoreB = new Score(GameSetUpController.goal);

        teamAArea.setText(teamA.getTeamName());
        teamBArea.setText(teamB.getTeamName());

        scoreAArea.setText(scoreA.toString());
        scoreBArea.setText(scoreB.toString());

        // timerLabel.setText(Integer.toString(INITIAL_SECOND));
        timerLabel.textProperty().bind(timeSeconds.asString());
        timer = new Timer(timerLabel);

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
            } else {
                playingTeamScore = scoreB;
                playingTeamScoreArea = scoreBArea;
            }

            Label[] labels = { teamAArea, teamBArea };
            buzzer.buzz(keyEvent.getCode(), labels);
            timer = new Timer(timerLabel);
            // timer.startTimer();
            initiateTimer();
            enableButtons();
            controlTimeUp();
        }
    }

    @FXML
    private void Exit() {
        System.exit(0);
    }

    @FXML
    void setNextQuestion(ActionEvent event) {
        // timerLabel.setText(Integer.toString(INITIAL_SECOND));
        quest++;
        setNewQA();
        nextButton.setOpacity(0);
        nextButton.setDisable(true);
        unselectButton();
        resetRadioButtonBGColour();
        // timer.resetNotInterrupted();
        // timer.resetInitialSecond();
    }

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

    private void disableButtons() {
        changeButtonStatus(true);
    }

    private void enableButtons() {
        changeButtonStatus(false);

    }

    public void changeButtonStatus(boolean a) {
        for (Iterator<Toggle> iterator = Options.getToggles().iterator(); iterator.hasNext();) {
            RadioButton radioButton = (RadioButton) iterator.next();
            radioButton.setDisable(a);
        }
    }

    public void onAnswerGiven() throws InterruptedException {
        disableButtons();
        stopTimer();
        checkAnswer();
        showCorrectAnswer();
        nextButton.setOpacity(1);
        nextButton.setDisable(false);
    }

    public void timeIsUp() {
        disableButtons();
        showCorrectAnswer();
        currentSecond = INITIAL_SECOND;
        nextButton.setOpacity(1);
        nextButton.setDisable(false);
    }

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

    private void setNewQA() {
        int answer = 0;
        for (Iterator<Toggle> iterator = Options.getToggles().iterator(); iterator.hasNext();) {
            RadioButton radioButton = (RadioButton) iterator.next();
            radioButton.setText(Question.getAnswer(quest, answer));
            answer++;
        }
        questionArea.setText(Question.getQuestions(quest));
    }

    // TODO(Nikos, Maryanna): change method utility to stop timer after answer is
    // given
    private void stopTimer() {
        timer.stopTimer();
    }

    private void showCorrectAnswer() {
        for (Iterator<Toggle> iterator = Options.getToggles().iterator(); iterator.hasNext();) {
            RadioButton button = (RadioButton) iterator.next();
            if (button.getText().equals(Question.getCorrectAnswer(quest))) {
                changeBackgroundColor("#b3f17b", button);
            }
        }
    }

    private void changeBackgroundColor(String color, RadioButton button) {
        Background background = new Background(
                new BackgroundFill(Paint.valueOf(color), new CornerRadii(0.1, true), null));
        button.setBackground(background);
    }

    // TODO(Nikos, Maryanna): fix question loading from database, to show questions properly
    private void loadQuestions() {
        for (int i = 1; i <= 5; i++) {
            new Question(dbconnector.selectQuestion("Geography", i));
        }
    }

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

    private void unselectButton() {
        for (Iterator<Toggle> iterator = Options.getToggles().iterator(); iterator.hasNext();) {
            RadioButton button = (RadioButton) iterator.next();
            button.setSelected(false);
        }
    }

    private void resetRadioButtonBGColour() {
        for (Iterator<Toggle> iterator = Options.getToggles().iterator(); iterator.hasNext();) {
            RadioButton button = (RadioButton) iterator.next();
            Background background = new Background(new BackgroundFill(null, null, null));
            button.setBackground(background);
        }
    }
}
