package gr.aueb.dmst.jabuzzz.game.view;

import java.io.IOException;

import Game.Main;
import javafx.fxml.FXML;

public class GameSetUpController {
	
	@FXML
	private void goBack() throws IOException {
		
		Main.showMainMenu();
		
	}

}
