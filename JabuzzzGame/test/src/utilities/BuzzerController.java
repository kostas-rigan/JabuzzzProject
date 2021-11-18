package utilities;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.Timer;
import java.util.TimerTask;

import gr.aueb.dmst.jabuzzz.entities.Team;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class BuzzerController {
	private Team playingTeam;
	private int interval = 5;

	public void buzz(KeyCode keyCode, String[] displays, Stage primaryStage) {
		// setting up a new scene
		Label teamADisplay = new Label(displays[0]);
		teamADisplay.setFont(Font.font(Font.getDefault().getFamily(), 18));
		Label teamBDisplay = new Label(displays[1]);
		teamBDisplay.setFont(Font.font(Font.getDefault().getFamily(), 18));
		HBox hBox = new HBox(100, teamADisplay, teamBDisplay);
		Label messageLabel = new Label("");
		Label timerLabel = new Label(Integer.toString(interval));
		VBox vBox = new VBox(150, hBox, messageLabel, timerLabel);
		Scene afterBuzzerScene = new Scene(vBox, 400, 400);
		// using try catch to handle NullPointerException
		try {
			// using switch to determine who will prevail!!
			switch (keyCode) {
			case A:
				playingTeam = new Team(teamADisplay.getText());
				teamADisplay.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 18));
				break;
			case L:
				playingTeam = new Team(teamBDisplay.getText());
				teamBDisplay.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 18));
				break;
			default:
				break;
			} 
			// checking if either A or L was pressed to proceed with the scene change
			if (keyCode.equals(KeyCode.A) || keyCode.equals(KeyCode.L)) {
				messageLabel.setText(playingTeam + " buzzed it!!!");
				primaryStage.setScene(afterBuzzerScene);
				primaryStage.show();
				// making a new Timer object for countdown
				Timer timer = new Timer();
				// this will start the countdown, changing time left at a fixed rate
			    timer.scheduleAtFixedRate(new TimerTask() {
			        public void run() {
			            if(interval > 0)
			            {
			                Platform.runLater(() -> timerLabel.setText(Integer.toString(interval)));
			                //System.out.println(interval);
			                interval--;
			            }
			            else
			                timer.cancel();
			        }
			    }, 1000,1000);
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println("At least one of them is null");
			System.out.println("teamADisplay: " + teamADisplay);
			System.out.println("teamBDisplay: " + teamBDisplay);
		}

	}
}
