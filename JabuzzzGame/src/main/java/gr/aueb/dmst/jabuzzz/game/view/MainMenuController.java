package main.java.gr.aueb.dmst.jabuzzz.game.view;

import java.io.IOException;

import javafx.fxml.FXML;
import main.java.gr.aueb.dmst.jabuzzz.game.Main;

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
