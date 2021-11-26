package gr.aueb.dmst.jabuzzz.game.view;

import java.net.URL;
import java.util.ResourceBundle;

import gr.aueb.dmst.jabuzzz.entities.Team;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class MainViewController implements Initializable {
	
	private static final int INITIAL_SECOND = 5;

    @FXML
    private Button exitButton;

    @FXML
    private Label teamAArea;

    @FXML
    private Label teamBArea;

    @FXML
    private Label questionArea;
    
    @FXML
    private Label awnserA;

    @FXML
    private Label awnserB;

    @FXML
    private Label awnserC;

    @FXML
    private Label awnserD;
    
    @FXML
    private Label timerLabel;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Team teamA = new Team (GameSetUpController.nameA);
    	Team teamB = new Team (GameSetUpController.nameB);
    	
		teamAArea.setText(teamA.getTeamName());
		teamBArea.setText(teamB.getTeamName());
		
		timerLabel.setText(Integer.toString(INITIAL_SECOND));
		
		
		
	}
	
	@FXML
	private void Exit() {
		System.exit(0);
	}

}
