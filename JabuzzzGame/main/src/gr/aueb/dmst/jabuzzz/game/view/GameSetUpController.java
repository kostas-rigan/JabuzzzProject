package gr.aueb.dmst.jabuzzz.game.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gr.aueb.dmst.jabuzzz.entities.DifficultyOfGame;
import gr.aueb.dmst.jabuzzz.game.Main;
import gr.aueb.dmst.jabuzzz.utilities.Buzzer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;


public class GameSetUpController implements Initializable {
	
	static String diffLevel;
	static String nameA;
	static String nameB;
	static int goal;
	static boolean myth;
	static boolean geo;
	static boolean hist;
	
	@FXML
	private TextField teamAField;
	
	@FXML
	private TextField teamBField;
	
	@FXML
    private CheckBox mythology;

    @FXML
    private CheckBox geography;

    @FXML
    private CheckBox history;

    @FXML
    private Slider pointsToFinish;
    
    @FXML
    private ChoiceBox<String> difficulty;
    
    @FXML
    public void start() throws IOException {
    	nameA = teamAField.getText();
    	nameB = teamBField.getText();
    	goal = (int) pointsToFinish.getValue();
    	myth = mythology.isSelected();
    	geo = geography.isSelected();
    	hist = history.isSelected();
    	diffLevel = difficulty.getValue();
    	
    	
    	LetsGo();
    }
  
    
	@FXML
	private void goBack() throws IOException {
		Main.showMainMenu();
	}
	
	
	private void LetsGo() throws IOException {
		Main.showMainView();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		difficulty.getItems().addAll("Εύκολο", "Κανονικό", "Δύσκολο");
		difficulty.setValue("Κανονικό");
		teamAField.setText("Ομάδα Α");
		teamBField.setText("Ομάδα Β");
	}
	//method that creates the difficulty object
	public void setDifficultyOfGame() {
		if (diffLevel.equals("Εύκολο")) {
			final DifficultyOfGame.Difficulty easy = DifficultyOfGame.Difficulty.EASY;
		} else if(diffLevel.equals("Κανονικό")) {
			final DifficultyOfGame.Difficulty easy = DifficultyOfGame.Difficulty.EASY;
		} else {
			final DifficultyOfGame.Difficulty easy = DifficultyOfGame.Difficulty.EASY;
		}

	}

}

