package gr.aueb.dmst.jabuzzz.utilities;

import java.util.Timer;
import java.util.TimerTask;

import gr.aueb.dmst.jabuzzz.entities.Team;
import gr.aueb.dmst.jabuzzz.scene.SceneCreator;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Buzzer {
	private Team playingTeam;
	private int interval = 5;

	public void buzz(KeyCode keyCode, Team[] displays, Stage primaryStage) {
		SceneCreator answerSceneCreator = new SceneCreator(displays[0], displays[1]);
		Scene afterBuzzerScene = answerSceneCreator.createAnswerScene();
		switch (keyCode) {
		case A:
			playingTeam = new Team(answerSceneCreator.getTeamADisplay().getText());
			answerSceneCreator.getTeamADisplay().setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 18));
			break;
		case L:
			playingTeam = new Team(answerSceneCreator.getTeamBDisplay().getText());
			answerSceneCreator.getTeamBDisplay().setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 18));
			break;
		default:
			break;
		} 
		if (keyCode.equals(KeyCode.A) || keyCode.equals(KeyCode.L)) {
			answerSceneCreator.getMessageLabel().setText(playingTeam + " buzzed it!!!");
			primaryStage.setScene(afterBuzzerScene);
			primaryStage.show();
			// making a new Timer object for countdown
			Timer timer = new Timer();
			// this will start the countdown, changing time left at a fixed rate
		    timer.scheduleAtFixedRate(new TimerTask() {
		        public void run() {
		            if(interval > 0)
		            {
		                Platform.runLater(() -> answerSceneCreator.getTimerLabel().setText(Integer.toString(interval)));
		                //System.out.println(interval);
		                interval--;
		            }
		            else
		                timer.cancel();
		        }
		    }, 1000,1000);
		}
	}
}
