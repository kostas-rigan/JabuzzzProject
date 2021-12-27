package main.java.gr.aueb.dmst.jabuzzz.game.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import main.java.gr.aueb.dmst.jabuzzz.entities.Category;
import main.java.gr.aueb.dmst.jabuzzz.entities.DifficultyOfGame;
import main.java.gr.aueb.dmst.jabuzzz.game.Main;

public class GameSetUpController implements Initializable {

    static String diffLevel;
    static String nameA;
    static String nameB;
    static int goal;
    private static Category geo;
    private static Category hist;
    private static Category myth;

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

    // TODO: Handle the event where no category is selected, until there is at least one selected
    @FXML
    public void start() throws IOException {
        nameA = teamAField.getText();
        nameB = teamBField.getText();
        goal = (int) pointsToFinish.getValue();
        myth.setSelected(mythology.isSelected());
        geo.setSelected(geography.isSelected());
        hist.setSelected(history.isSelected());
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
        geo = new Category("Geography");
        hist = new Category("History");
        myth = new Category("Mythology");
    }

    // method that creates the difficulty object
    public void setDifficultyOfGame() {
        if (diffLevel.equals("Εύκολο")) {
            final DifficultyOfGame.Difficulty easy = DifficultyOfGame.Difficulty.EASY;
        } else if (diffLevel.equals("Κανονικό")) {
            final DifficultyOfGame.Difficulty easy = DifficultyOfGame.Difficulty.EASY;
        } else {
            final DifficultyOfGame.Difficulty easy = DifficultyOfGame.Difficulty.EASY;
        }

    }

    public static int getFinishPoints() {
        return goal;
    }
    
    /**
     * Examines all of the categories if they are selected, in which case
     * they are added in an array list and are returned to the user.
     * @return an ArrayList of String that contains the names of selected categories.
     */
    public static ArrayList<String> categoryNames() {
        ArrayList<String> catNames = new ArrayList<String>();
        if (geo.getSelected()) {
            catNames.add(geo.getCategoryName());
        }
        if (hist.getSelected()) {
            catNames.add(hist.getCategoryName());
        }
        if (myth.getSelected()) {
            catNames.add(myth.getCategoryName());
        }
        
        return catNames;
    }
}
