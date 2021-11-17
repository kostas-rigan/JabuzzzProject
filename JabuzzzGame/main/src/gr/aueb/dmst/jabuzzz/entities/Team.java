package gr.aueb.dmst.jabuzzz.entities;

public class Team {
	private String teamName;

	public Team(String teamName) {
		super();
		this.teamName = teamName;
	}

	public Team() {
		this.teamName = "";
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	@Override
	public String toString() {
		return this.getTeamName();
	}
}
