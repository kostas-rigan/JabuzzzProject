package gr.aueb.dmst.jabuzzz.scene;

import gr.aueb.dmst.jabuzzz.entities.Team;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * This class is used to assist in creating question and answer scenes.
 * A SceneCreator objects sets everything for both needed scenes, because FXML
 * files make an issue in these scenes with an exception that hasn't been easy
 * to handle.
 * @author kostas-rigan
 * @version 1.0 20/11/2021
 */
public class SceneCreator {
    /**
     * HEIGHT is the initial height value of the window.
     */
    private static final double HEIGHT = 673;
    /**
     * WIDTH is the initial width value of the window.
     */
    private static final double WIDTH = 985;
    /**
     * INITIAL_SECOND is the display of Timer label
     * and shows where the count is starting from.
     */
    private static final int INITIAL_SECOND = 5;
    /**
     * teamADisplay is where Team A's name is going to be shown on screen.
     */
    private Label teamADisplay;
    /**
     * teamBDisplay is where Team B's name is going to be shown on screen.
     */
    private Label teamBDisplay;
    /**
     * scoreADisplay is where Team A's total points are going to be displayed.
     */
    private Label scoreADisplay;
    /**
     * scoreBDisplay is where Team A's total points are going to be displayed.
     */
    private Label scoreBDisplay;
    /**
     * messageLabel is a Label object that contains the main context of the
     * screen, for example the question posed.
     */
    private Label messageLabel;
    /**
     * timerLabel holds how much time is left for a team to answer a question.
     * Its initial value is 5.
     */
    private Label timerLabel;
    /**
     * TIMER_TEXT is a final label which displays a constant message which is
     * "Timer".
     */
    private static final Label TIMER_TEXT = new Label("Timer");

    /**
     * Returns the Timer Label of a scene.
     * @return timer label
     */
    public Label getTimerLabel() {
        return timerLabel;
    }

    /**
     * Returns the Label of Team A's name.
     * @return Team A's name display label
     */
    public Label getTeamADisplay() {
        return teamADisplay;
    }

    /**
     * Returns the Label of Team B's name.
     * @return Team B's name display label
     */
    public Label getTeamBDisplay() {
        return teamBDisplay;
    }

    /**
     * Returns the Label for the main message.
     * @return Label object that holds main context.
     */
    public Label getMessageLabel() {
        return messageLabel;
    }

    /**
     * A constructor that initialises and sets up labels used
     * in question and answer scenes of the game.
     * Everything else except for team names have a default value,
     * which is why Team objects are used to define teams' names.
     * @param teamA is the first team
     * @param teamB is the second team
     */
    public SceneCreator(final Team teamA, final Team teamB) {
        super();
        this.teamADisplay = new Label(teamA.getTeamName());
        this.teamBDisplay = new Label(teamB.getTeamName());
        changeTextSize(teamADisplay, FontSize.TEAM_DISPLAY_FONT_SIZE);
        changeTextSize(teamBDisplay, FontSize.TEAM_DISPLAY_FONT_SIZE);
        scoreADisplay = new Label("0");
        scoreBDisplay = new Label("0");
        changeTextSize(scoreADisplay, FontSize.SCORE_DISPLAY_FONT_SIZE);
        changeTextSize(scoreBDisplay, FontSize.SCORE_DISPLAY_FONT_SIZE);
        timerLabel = new Label(Integer.toString(INITIAL_SECOND));
        changeTextSize(timerLabel, FontSize.TIMER_FONT_SIZE);
        changeTextSize(TIMER_TEXT, FontSize.TIMER_TEXT_FONT_SIZE);
    }

    /**
     * This method creates from scratch the question scene.
     * It uses a private method to set up the template of both
     * question and answer scenes.
     * @return question scene to be displayed on screen
     */
    public Scene createQuestionScene() {
        messageLabel = new Label("Who will buzz it?");
        return createScene(messageLabel);
    }

    /**
     * This method creates from scratch the answer scene.
     * It uses a private method to set up the template of both
     * question and answer scenes.
     * @return answer scene to be displayed on screen
     */
    public Scene createAnswerScene() {
        messageLabel = new Label("");
        return createScene(messageLabel);
    }

    private Scene createScene(final Label msgLabel) {
        // instantiation of an HBox(Horizontal Box) objects
        HBox topLeftHBox = new HBox(
                PaddingSize.SMALL_HBOX_PSIZE.getPaddingSize(),
                teamADisplay, scoreADisplay);
        HBox topRightHBox = new HBox(
                PaddingSize.SMALL_HBOX_PSIZE.getPaddingSize(),
                teamBDisplay, scoreBDisplay);
        VBox topCenterVBox = new VBox(
                PaddingSize.SMALL_VBOX_PSIZE.getPaddingSize(),
                TIMER_TEXT, timerLabel);
        topCenterVBox.setAlignment(Pos.CENTER);
        HBox topHBox = new HBox(
                PaddingSize.BIG_HBOX_PSIZE.getPaddingSize(),
                topLeftHBox, topCenterVBox, topRightHBox);
        topHBox.setAlignment(Pos.CENTER);
        changeTextSize(messageLabel, FontSize.MESSAGE_FONT_SIZE);
        /*
         * instantiation of a VBox(Vertical Box) which will have the HBox we
         * created and the other label
         */
        VBox vBox = new VBox(
                PaddingSize.BIG_VBOX_PSIZE.getPaddingSize(),
                topHBox, messageLabel);
        BorderPane borderPane = new BorderPane(messageLabel);
        borderPane.setTop(vBox);
        Scene scene = new Scene(borderPane, WIDTH, HEIGHT);

        return scene;
    }

    private void changeTextSize(final Label label, final FontSize fontSize) {
        label.setFont(Font.font(
                Font.getDefault().getFamily(), fontSize.getFontSize()));
    }

    /**
     * This method uses an identifier for the team display('A': Team A,
     * 'B': for Team B) and a FontWeight value(example given BOLD) and
     * sets the Label's text with given weight.
     * @param team a character that identifies the team('A' or 'B')
     * @param fontWeight a FontWeight enum value that is given for weight
     */
    public void changeFontWeight(final char team, final FontWeight fontWeight) {
        switch (team) {
        case 'A':
            this.getTeamADisplay().setFont(Font.font(
                    Font.getDefault().getFamily(), fontWeight,
                    FontSize.TEAM_DISPLAY_FONT_SIZE.getFontSize()));
            break;

        case 'B':
            this.getTeamBDisplay().setFont(Font.font(
                    Font.getDefault().getFamily(), fontWeight,
                    FontSize.TEAM_DISPLAY_FONT_SIZE.getFontSize()));
            break;

        default:
            break;
        }
    }
}
