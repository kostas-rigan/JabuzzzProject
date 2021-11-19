package utilities;

// importing libraries used

import gr.aueb.dmst.jabuzzz.entities.Team;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BuzzerTest extends Application{
	// launching the application
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//FXMLLoader loader = new FXMLLoader(getClass().getResource("BuzzerScene.fxml"));
		//Parent root = loader.load();
		
		// instantiation of 2 Team objects
		Team teamA = new Team("Sakis Rouvas");
		Team teamB = new Team("Elena Paparizou");
		/* instantiation of 2 Label objects
		 * calling a method to set the font family
		 * and font size
		 */
		Label teamADisplay = new Label(teamA.toString());
		teamADisplay.setFont(Font.font(Font.getDefault().getFamily(), 18));
		Label teamBDisplay = new Label(teamB.toString());
		teamBDisplay.setFont(Font.font(Font.getDefault().getFamily(), 18));
		// instantiation of an HBox(Horizontal Box) objects
		HBox hBox = new HBox(100, teamADisplay, teamBDisplay);
		// another Label object
		Label messageLabel = new Label("Who will buzz it?");
		/* instantiation of a VBox(Vertical Box) which will have
		 * the HBox we created and the other label
		 */
		VBox vBox = new VBox(150, hBox, messageLabel);
		// an instance of our buzzer 
		BuzzerController buzzer = new BuzzerController();
		// setting up the scene with the VBox
		Scene buzzerScene = new Scene(vBox, 400, 400);
		//Scene buzzerScene = new Scene(root, 400, 400);
		// this method will handle the key presses in buzzerScenes
		buzzerScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// call of the actual buzz method in our buzzer
				Team[] displays = {teamA, teamB};
				buzzer.buzz(event.getCode(), displays, primaryStage);
				
			}
		});
		primaryStage.setScene(buzzerScene);
		primaryStage.show();
	}
}
