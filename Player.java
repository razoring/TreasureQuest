package TreasureQuest;

import static java.lang.System.out;

public class Player {
	private static int lives;
	private static int power;
	private static int points;
	
	public Player() {
		lives = 1;
		power = 100;
		points = 0;
	}
	
	private static int getLife() {
		return lives;
	}
	
	private static int getPower() {
		return power;
	}
	
	private static int getPoints() {
		return points;
	}
	
	public void update(String type, int val) {
		switch(type) {
			case "Life": 
				lives = val;
				break;
			case "Points":
				points = val;
				break;
		}
	}
}
