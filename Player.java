package TreasureQuest;

import static java.lang.System.out;

public class Player {
	private static int lives;
	private static int stamina;
	private static int points;
	
	public Player() {
		lives = 1;
		stamina = 25;
		points = 0;
	}
	
	public int getLife() {
		return lives;
	}
	
	public int getStamina() {
		return stamina;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void update(String type, int val) {
		switch(type) {
			case "Life": 
				lives += val;
				break;
			case "Points":
				points += val;
				break;
			case "Stamina":
				stamina += val;
				break;
		}
	}
}
