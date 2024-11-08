package TreasureQuest;

import static java.lang.System.out;

public class Player {
	private static int lives;
	private static int power;
	private static int score;
	
	public Player() {
		lives = 1;
		power = 25;
		score = 0;
	}
	
	public int getLife() {
		return lives;
	}
	
	public int getStamina() {
		return power;
	}
	
	public int getScore() {
		return score;
	}
	
	public void updateStat(String type, int val) {
		switch(type) {
			case "Lives": 
				lives += val;
				break;
			case "Score":
				score += val;
				break;
			case "Power":
				power += val;
				break;
		}
	}
}
