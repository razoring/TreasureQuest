package TreasureQuest;

/**
 * Jiawei Chen, Raymond So <p>
 * 11/14/2024 <p>
 * Basic class containing methods related to player stats.
 */

public class Player {
	private static int lives; // power
	private static int points; // score
	
	/**
	 * Constructor initializing the player's starting points and lives.
	 * @param l Initial lives count
	 * @param p Initial points count
	 */
	public Player(int l, int p) {
		lives = l;
		points = p;
	}
	
	/**
	 * Returns the number of lives the player has remaining.
	 * @return # of lives
	 */
	public int getLives() {
		return lives;
	}
	
	/**
	 * Returns the number of points the player has remaining.
	 * @return # of points
	 */
	public int getPoints() {
		return points;
	}
	
	/**
	 * Updates the player's stats given the stat name and incremental value. <p>
	 * For example, to increase lives by one, you would use updateStat("Lives", 1) <p>
	 * To decrease lives by one, you would use updateStat("Lives", -1). <p>
	 * @param type Statistic to be updated
	 * @param val Increment to update statistic by
	 */
	public void updateStat(String type, int val) {
		switch(type) {
			case "Lives":
				lives += val;
				break;
			case "Points":
				points += val;
				break;
		}
	}
}
