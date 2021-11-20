package gr.aueb.dmst.jabuzzz.entities;

/**
 * 
 * @author kostas-rigan
 * @version 1.0
 * @since 17/11/2021
 * 
 * Representation of Team concept
 */

public class Team {
	private String teamName;

	/**
	 * Class constructor specifying team's name.
	 * @param teamName team's specified name
	 */
	public Team(String teamName) {
		super();
		this.teamName = teamName;
	}

	/**
	 * Class constructor with default value.
	 */
	public Team() {
		this.teamName = "";
	}

	/**
	 * 
	 * @return team's current name
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * specifies new team name
	 * @param teamName team's specified name
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	/**
	 * returns String representation of Team object
	 */
	@Override
	public String toString() {
		return this.getTeamName();
	}
}
