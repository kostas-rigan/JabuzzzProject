package gr.aueb.dmst.jabuzzz.utilities;

import java.util.Timer;
import java.util.TimerTask;

import gr.aueb.dmst.jabuzzz.entities.Team;
import gr.aueb.dmst.jabuzzz.scene.SceneCreator;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Buzzer is the class that implements the main game's utility of deciding
 *  which team plays.
 * <p>
 * In question scene a Buzzer object is used for teams to press it
 * and be able to answer
 * the question.
 * @author kostas-rigan
 * @version 1.0 20/11/2021
 */

public class Buzzer {
    /**
     *  playingTeam is the Team that pressed the buzzer first to
     *  answer the question. */
    private Team playingTeam;
    /**
     *  STARTING_SECOND is the initial value of currentSecond field,
     *  which is the 5th second.
     */
    private static final int STARTING_SECOND = 5;
    /**
     *  currentSecond is time(in seconds) that is left in timer.
     *  Used after buzzer is pressed, to track the time the playing team
     *  has to answer the question.
     */
    private int currentSecond = STARTING_SECOND;
    /**
     *  DELAY is time(in milliseconds) that timer has to wait
     *  until it commences its action.
     */
    private static final long DELAY = 1000; // in milliseconds
    /**
     * PERIOD is time(in milliseconds) timer has to wait
     * to continue its actions.
     * 1000 milliseconds = 1 second.
     */
    private static final long PERIOD = 1000; // in milliseconds

    /**
     * Checks which one of the teams is going to answer the question.
     * <p>
     * This method will not execute any action if any key other than A or L
     * is pressed.
     * When a correct key is pressed, this method will initiate the next scene
     * of the game, where the playing team is going to answer the question.
     * @param keyCode
     * @param displays
     * @param primaryStage
     */
    public void buzz(final KeyCode keyCode,
        final Team[] displays, final Stage primaryStage) {
        // creating a new Scene
        SceneCreator answerSceneCreator
            = new SceneCreator(displays[0], displays[1]);
        Scene afterBuzzerScene = answerSceneCreator.createAnswerScene();
        FontWeight fontWeight = FontWeight.BOLD;
        switch (keyCode) { // inspecting key code
        case A:
            playingTeam
            = new Team(answerSceneCreator.getTeamADisplay().getText());
            answerSceneCreator
            .changeFontWeight('A', fontWeight);
            // sets team A's font weight to bold
            break;
        case L:
            playingTeam =
            new Team(answerSceneCreator.getTeamBDisplay().getText());
            answerSceneCreator
            .changeFontWeight('B', fontWeight);
            // sets team B's font weight to bold
            break;
        default:
            break;
        }
        // executing timer only if buzzer actually is pressed, A or L
        if (keyCode.equals(KeyCode.A) || keyCode.equals(KeyCode.L)) {
            answerSceneCreator.getMessageLabel()
            .setText(playingTeam + " buzzed it!!!");
            primaryStage.setScene(afterBuzzerScene);
            primaryStage.show();
            // making a new Timer object for countdown
            Timer timer = new Timer();
            /* this will start the countdown,
             *  changing time left at a fixed rate
             */
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    if (currentSecond > 0) {
                        Platform.runLater(
                                () -> answerSceneCreator
                                .getTimerLabel()
                                .setText(Integer.toString(currentSecond)));
                        currentSecond--;
                    } else {
                        timer.cancel();
                    }
                }
            }, DELAY, PERIOD);
        }
    }
}
