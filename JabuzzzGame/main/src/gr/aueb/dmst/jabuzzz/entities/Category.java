package gr.aueb.dmst.jabuzzz.entities;
/**
 * Class Category is responsible for which categories the player will play.
 * @author Konstantinos_Terlakis
 * @version 1.0 25/11/2021
 */
public class Category {
	/*
	 * mythology displays whether the player has selected this category or not.
	 */
	private boolean mythology;
	/*
	 * geography displays whether the player has selected this category or not.
	 */
	private boolean geography;
	/*
	 * history displays whether the player has selected this category or not.
	 */
	private boolean history;
	/*
	 * Class constructor with default values.
	 */
	public Category() {
		mythology = false;
		geography = false;
		history = false;
	}
    /**
     * Specifies new geography value.
     * @param new value for geography.
     */
	public void setGeography(boolean geography) {
		this.geography = geography;
	}
    /**
     * Specifies new history value.
     * @param new value for history.
     */
	public void setHistory(boolean history) {
		this.history = history;
	}
    /**
     * Specifies new mythology value.
     * @param new value for mythology.
     */
	public void setMythology(boolean mythology) {
		this.mythology = mythology;
	}
    /**
    * @return geography current value.
    */
	public boolean getGeography() {
		return geography;
	}
    /**
    * @return history current value.
    */
	public boolean getMythology() {
		return mythology;
	}
    /**
    * @return history current value.
    */
	public boolean getHistory() {
		return history;
	}
	

}
