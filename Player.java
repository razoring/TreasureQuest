package TreasureQuest;

public class Player {
	private static int health = 1;
	private static int power = 100;
	private static int points = 0;
	
	private static int getLife() {
		return health;
	}
	
	private static int getPower() {
		return power;
	}
	
	private static int getPoints() {
		return points;
	}
	
	public void update(int type, int val) {
		switch(type) {
			case 1: 
				health = val;
				break;
			case 2: 
				power = val;
				break;
			case 3:
				points = val;
				break;
		}
	}
}
