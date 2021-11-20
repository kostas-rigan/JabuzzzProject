package gr.aueb.dmst.jabuzzz.scene;

import gr.aueb.dmst.jabuzzz.entities.Team;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SceneCreator {
	private final double HEIGHT = 673;
	private final double WIDTH = 985;
	private Label teamADisplay;
	private Label teamBDisplay;
	private Label scoreADisplay;
	private Label scoreBDisplay;
	private Label messageLabel;
	private Label timerLabel;
	private Label timerText;
	private int currentSecond = 5;

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
		changeTextSize(teamADisplay, 18);
		changeTextSize(teamBDisplay, 18);
		scoreADisplay = new Label("0");
		scoreBDisplay = new Label("0");
		changeTextSize(scoreADisplay, 20);
		changeTextSize(scoreBDisplay, 20);
		timerLabel = new Label(Integer.toString(currentSecond));
		timerText = new Label("Time");
		changeTextSize(timerLabel, 25);
		changeTextSize(timerText, 30);
	}

	public Scene createQuestionScene() {
		messageLabel = new Label("Who will buzz it?");
		return createScene(messageLabel);		
	}

	public Scene createAnswerScene() {
		messageLabel = new Label("");
		return createScene(messageLabel);
	}

	private Scene createScene(Label messageLabel) {
		// instantiation of an HBox(Horizontal Box) objects
		HBox topLeftHBox = new HBox(50, teamADisplay, scoreADisplay);
		HBox topRightHBox = new HBox(50, teamBDisplay, scoreBDisplay);
		VBox topCenterVBox = new VBox(10, timerText, timerLabel);
		topCenterVBox.setAlignment(Pos.CENTER);
		HBox topHBox = new HBox(275, topLeftHBox, topCenterVBox, topRightHBox);
		topHBox.setAlignment(Pos.CENTER);
		messageLabel.setFont(Font.font(Font.getDefault().getFamily(), 16));
		/*
		 * instantiation of a VBox(Vertical Box) which will have the HBox we created and
		 * the other label
		 */
		VBox vBox = new VBox(150, topHBox, messageLabel);
		BorderPane borderPane = new BorderPane(messageLabel);
		borderPane.setTop(vBox);
		Scene scene = new Scene(borderPane, WIDTH, HEIGHT);

		return scene;
	}

	private void changeTextSize(Label label, double size) {
		label.setFont(Font.font(Font.getDefault().getFamily(), size));
	}

	public void changeFontWeight(char team, FontWeight fontWeight) {
		switch (team) {
		case 'A':
			this.getTeamADisplay().setFont(Font.font(Font.getDefault().getFamily(), fontWeight, 18));
			break;

		case 'B':
			this.getTeamBDisplay().setFont(Font.font(Font.getDefault().getFamily(), fontWeight, 18));
			break;

		default:
			break;
		}
	}
}
