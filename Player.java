package TreasureQuest;

import static java.lang.System.out;

public class Player {
	private static int lives = 1;
	private static int lvl = 0;
	private static int points = 0;
	
	private static int getLife() {
		return lives;
	}
	
	private static int getPower() {
		return lvl;
	}
	
	private static int getPoints() {
		return points;
	}
	
	public void update(int type, int val) {
		switch(type) {
			case 1: 
				lives = val;
				break;
			case 2: 
				lvl = val;
				break;
			case 3:
				points = val;
				break;
		}
	}
	
	public Player() {
		lives = 1;
		lvl = 100;
		points = 0;
	}
}
