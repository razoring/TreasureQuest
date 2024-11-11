package TreasureQuest;

import static java.lang.System.out;

public class Player {
	private static int lives; // power
	private static int points; // score
	
	public Player(int l, int p) {
		lives = l;
		points = p;
	}
	
	public int getLives() {
		return lives;
	}
	public int getPoints() {
		return points;
	}
	
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
