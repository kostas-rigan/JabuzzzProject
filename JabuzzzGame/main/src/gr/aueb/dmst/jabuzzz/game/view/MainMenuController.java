package gr.aueb.dmst.jabuzzz.game.view;

import java.io.IOException;

import gr.aueb.dmst.jabuzzz.game.Main;
import gr.aueb.dmst.jabuzzz.scene.SceneCreator;
import javafx.fxml.FXML;

public class MainMenuController {
	
	@FXML
	private void Play() throws IOException {
		Main.showGameSetUp();
	}
	
	@FXML
	private void Instructions() throws IOException {
		Main.showInstructions();
	}
	
	@FXML
	private void Exit() {
		System.exit(0);
	}

}
