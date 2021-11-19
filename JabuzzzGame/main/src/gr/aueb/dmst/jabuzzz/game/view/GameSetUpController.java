package gr.aueb.dmst.jabuzzz.game.view;

import java.io.IOException;

import gr.aueb.dmst.jabuzzz.game.Main;
import gr.aueb.dmst.jabuzzz.scene.SceneCreator;
import gr.aueb.dmst.jabuzzz.utilities.Buzzer;
import gr.aueb.dmst.jabuzzz.entities.Team;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class GameSetUpController {
	
	@FXML
	private void goBack() throws IOException {
		
		Main.showMainMenu();
		
	}
	
	@FXML
	private void start() {
		
		Team teamA = new Team("Sakis Rouvas");
		Team teamB = new Team("Elena Paparizou");
		SceneCreator questionSceneCreator = new SceneCreator(teamA, teamB);
		
		// an instance of our buzzer 
		Buzzer buzzer = new Buzzer();
		// setting up the scene using SceneCreator's method
		Scene buzzerScene = questionSceneCreator.createQuestionScene(); 
		// this method will handle the key presses in buzzerScenes
		buzzerScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// call of the actual buzz method in our buzzer
				Team[] displays = {teamA, teamB};
				buzzer.buzz(event.getCode(), displays, Main.primaryStage);
				
			}
		});
		Main.primaryStage.setScene(buzzerScene);
		Main.primaryStage.show();
	}
}

