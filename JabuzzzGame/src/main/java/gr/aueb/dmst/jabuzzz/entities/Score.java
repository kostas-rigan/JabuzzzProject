package main.java.gr.aueb.dmst.jabuzzz.entities;

import main.java.gr.aueb.dmst.jabuzzz.utilities.Exit;

/**
 * Class Score changes the team score and checks if the top or bottom target has
 * been reached.
 * 
 * @author Konstantinos_Terlakis
 * @version 1.0 25/11/2021
 */
public class Score {
    /*
     * teamsScore stores the current teams score.
     */
    private int teamsScore;
    /*
     * target is the mark that a team must reach
     */
    private int target;

    /*
     * Class constructor specifying teams target.
     * 
     * @param target
     */
    public Score(int target) {
        teamsScore = 0;
        this.target = target;
    }

    /*
     * correctAnswer is called when a team has given a correct answer and increases
     * the teams score by one.
     */
    public void correctAnswer() {
        teamsScore += 1;
        /*
         * check if theamScore is equal to target.
         */
        if (teamsScore == target) {
            /*
             * exit game
             */
            Exit.exitGame();
        }
    }

    /*
     * wrongAnswer is called when a team has given a wrong answer and decreases the
     * teams score by one.
     */
    public void wrongAnswer() {
        teamsScore -= 1;
        /*
         * check if theamScore is equal bottom mark.
         */
        if (teamsScore == -5) {
            /*
             * exit game
             */
            Exit.exitGame();
        }
    }

    /**
     * @return teamScore value.
     */
    public int getTeamsScore() {
        return teamsScore;
    }

	@Override
	public String toString() {
		return "Score:" + teamsScore;
	}
    
    
}
