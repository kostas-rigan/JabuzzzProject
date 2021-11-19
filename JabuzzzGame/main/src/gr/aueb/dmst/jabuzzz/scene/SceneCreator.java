package gr.aueb.dmst.jabuzzz.scene;

import gr.aueb.dmst.jabuzzz.entities.Team;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class SceneCreator {
	private final double HEIGHT = 673;
	private final double WIDTH = 985;
	private Label teamADisplay;
	private Label teamBDisplay;
	private Label messageLabel;
	private Label timerLabel;

	public Label getTimerLabel() {
		return timerLabel;
	}

	public Label getTeamADisplay() {
		return teamADisplay;
	}

	public Label getTeamBDisplay() {
		return teamBDisplay;
	}

	public Label getMessageLabel() {
		return messageLabel;
	}

	public SceneCreator(Team teamA, Team teamB) {
		super();
		this.teamADisplay = new Label(teamA.getTeamName());
		this.teamBDisplay = new Label(teamB.getTeamName());
		teamADisplay.setFont(Font.font(Font.getDefault().getFamily(), 18));
		teamBDisplay.setFont(Font.font(Font.getDefault().getFamily(), 18));
	}

	public Scene createQuestionScene() {
		// instantiation of an HBox(Horizontal Box) objects
		HBox hBox = new HBox(100, teamADisplay, teamBDisplay);
		// another Label object
		messageLabel = new Label("Who will buzz it?");
		/* instantiation of a VBox(Vertical Box) which will have
		 * the HBox we created and the other label
		*/
		VBox vBox = new VBox(150, hBox, messageLabel);
		// an instance of our buzzer 
		return new Scene(vBox, WIDTH, HEIGHT);
	}

	public Scene createAnswerScene() {
		int interval = 5;
		HBox hBox = new HBox(100, teamADisplay, teamBDisplay);
		messageLabel = new Label("");
		timerLabel = new Label(Integer.toString(interval));
		VBox vBox = new VBox(150, hBox, messageLabel, timerLabel);
		return new Scene(vBox, WIDTH, HEIGHT);
	}
}
