package gr.aueb.dmst.jabuzzz.entities;

import gr.aueb.dmst.jabuzzz.utilities.Exit;

public class Score {
	int teamsScore;
	int target;
	
	public Score(int target) {
		teamsScore = 0;
		this.target = target;
	}
	public void correctAnswer () {
		teamsScore += 1;
		if (teamsScore == target) {
			Exit.exitGame();
		}
	}
	public void wrongAnswer () {
		teamsScore -= 1;
		if (teamsScore == -5) {
			Exit.exitGame();
		}
	}
	public int getTeamsScore() {
		return teamsScore;
	}
}
