package gr.aueb.dmst.jabuzzz.game.view;

import java.io.IOException;

import Game.Main;
import javafx.fxml.FXML;

public class MainMenuController {
	
	@FXML
	private void Play() throws IOException {
		
		Main.showGameSetUp();
		
	}

}
