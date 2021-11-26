package gr.aueb.dmst.jabuzzz.utilities;

import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.TimerTask;

import gr.aueb.dmst.jabuzzz.scene.SceneCreator;
/**
 * Timer class(different from java.lang.Timer) is used
 * to function the timer utility used from the buzz method of
 * a Buzzer object.
 * @author NicholasKarakostas
 *
 */
public class Timer {
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
     * This method initiates the timer used in buzz method of
     * Buzzer class.
     * @param answerSceneCreator scene used where Timer starts.
     */
    public void startTimer(final Label timeLabel) {
        // making a new Timer object for countdown
        java.util.Timer timer = new java.util.Timer();
        /* this will start the countdown,
         *  changing time left at a fixed rate
         */
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (currentSecond > 0) {
                    Platform.runLater(
                            () -> timeLabel
                            .setText(Integer.toString(currentSecond)));
                    currentSecond--;
                } else {
                    timer.cancel();
                }
            }
        }, DELAY, PERIOD);
    }
}
