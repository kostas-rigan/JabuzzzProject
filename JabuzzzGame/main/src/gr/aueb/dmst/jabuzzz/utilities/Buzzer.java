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

public class Buzzer {
	private Team playingTeam;
	private int currentSecond = 5;
	private final long DELAY = 1000; // in milliseconds
	private final long PERIOD = 1000; // in milliseconds

	public void buzz(KeyCode keyCode, Team[] displays, Stage primaryStage) {
		// creating a new Scene
		SceneCreator answerSceneCreator = new SceneCreator(displays[0], displays[1]);
		Scene afterBuzzerScene = answerSceneCreator.createAnswerScene();
		FontWeight fontWeight = FontWeight.BOLD;
		switch (keyCode) { // inspecting key code
		case A:
			playingTeam = new Team(answerSceneCreator.getTeamADisplay().getText());
			answerSceneCreator.changeFontWeight('A', fontWeight); // sets team A's font weight to bold
			break;
		case L:
			playingTeam = new Team(answerSceneCreator.getTeamBDisplay().getText());
			answerSceneCreator.changeFontWeight('B', fontWeight); // sets team B's font weight to bold
			break;
		default:
			break;
		}
		// executing timer only if buzzer actually is pressed, A or L
		if (keyCode.equals(KeyCode.A) || keyCode.equals(KeyCode.L)) {
			answerSceneCreator.getMessageLabel().setText(playingTeam + " buzzed it!!!");
			primaryStage.setScene(afterBuzzerScene);
			primaryStage.show();
			// making a new Timer object for countdown
			Timer timer = new Timer();
			// this will start the countdown, changing time left at a fixed rate
			timer.scheduleAtFixedRate(new TimerTask() {
				public void run() {
					if (currentSecond > 0) {
						Platform.runLater(
								() -> answerSceneCreator.getTimerLabel().setText(Integer.toString(currentSecond)));
						currentSecond--;
					} else
						timer.cancel();
				}
			}, DELAY, PERIOD);
		}
	}
}
