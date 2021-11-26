package gr.aueb.dmst.jabuzzz.game.view;

import java.net.URL;
import java.util.ResourceBundle;

import gr.aueb.dmst.jabuzzz.entities.Team;
import gr.aueb.dmst.jabuzzz.utilities.Buzzer;
import gr.aueb.dmst.jabuzzz.utilities.Timer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

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

	public void handleBuzzer(KeyCode keyCode) {
	   /* Buzzer buzzer = new Buzzer();
	    Label[] labels = {teamAArea, teamBArea, timerLabel};
	    buzzer.buzz(keyCode, labels); */
	    Timer timer = new Timer();
	    timer.startTimer(timerLabel);
	    FontWeight fontWeight = FontWeight.BOLD;
        switch (keyCode) { // inspecting key code
        case A:
            teamAArea.setFont(Font.font(teamAArea.getFont().getFamily(), fontWeight, teamAArea.getFont().getSize()));
            break;
        case L:
            teamBArea.setFont(Font.font(teamBArea.getFont().getFamily(), fontWeight, teamBArea.getFont().getSize()));
            break;
        default:
            break;
        }
	}
	
	@FXML
	private void Exit() {
		System.exit(0);
	}

}
