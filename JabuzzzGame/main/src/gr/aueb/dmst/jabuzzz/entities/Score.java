package gr.aueb.dmst.jabuzzz.entities;

public class Score {
	int teamsScore;
	
	public Score() {
		teamsScore = 0;
	}
	public void correctAnswer () {
		teamsScore += 1;
	}
	public void wrongAnswer () {
		teamsScore -= 1;
	}
	public int getTeamsScore() {
		return teamsScore;
	}
}
