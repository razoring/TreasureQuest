package TreasureQuest;

import java.util.Scanner;
import static java.lang.System.out;

public class GameControl {
	static Scanner reader = new Scanner(System.in);
	
	private static int check() {
		String presize = reader.nextLine();
		try {
			return Integer.parseInt(presize);
		} catch(NumberFormatException e) {
			System.out.println("Please enter a valid integer");
		}
		return check();
	}
	
	public static void main(String[] args) {
		boolean alive = true;
		// TODO Auto-generated method stub
		System.out.println(""
				+ "▄▄▄▄▄▄▄▄▄  ▄▄▄ . ▄▄▄· .▄▄ · ▄• ▄▌▄▄▄  ▄▄▄▀.   .▄▄▄  ▄• ▄▌▄▄▄ ..▄▄· ▄▄▄▄▄\n"
				+ " •██  ▀▄ █·▀▄.▀·▐█ ▀█ ▐█ ▀. █▪██▌▀▄ █·▀▄.·    ▐▀•▀█ █▪██▌▀▄.▀·▐█ ▀. •██  \n"
				+ "  ▐█.▪▐▀▀▄ ▐▀▀▪▄▄█▀▀█ ▄▀▀▀█▄█▌▐█▌▐▀▀▄ ▐▀▀▪    █▌·.█▌█▌▐█▌▐▀▀▪▄▄▀▀▀█▄ ▐█.▪\n"
				+ "  ▐█▌·▐█•█▌▐█▄▄▌▐█ ▪▐▌▐█▄▪▐█▐█▄█▌▐█•█▌▐█▄▄▌    ▐█▪▄█·▐█▄█▌▐█▄▄▌▐█▄▪▐█ ▐█▌·\n"
				+ "  ▀▀▀ .▀  ▀ ▀▀▀  ▀  ▀  ▀▀▀▀  ▀▀▀ .▀  ▀ ▀▀▀     ·▀▀█.  ▀▀▀  ▀▀▀  ▀▀▀▀  ▀▀▀ ");
		System.out.println("// INSTRUCTIONS:\n"
				+ "- WASD to navigate \n"
				+ "- Death upon mine \n"
				+ "- Death upon score zero \n");
		System.out.println("// TO BEGIN:\n"
				+ "Enter an integer to represent the map size (E.g: 5 = 5x5)");
		Board board = new Board(check());
		board.gen();
		while (alive) {
			System.out.println(board.display());
			System.out.println("Enter your next movement (WASD):");
			String input = reader.nextLine();
			board.processMove(input);
			board.plr.updateStat("Power", -1);
			
			if (board.plr.getLife() <= 0) {
				System.out.println(board.display());
				System.err.println("You died!");
				
				if (board.plr.getScore() >= 20) {
					if (deathCheck()) {
						board.plr.updateStat("Score", -20);
						board.plr.updateStat("Lives", 1);
					} else {
						alive = false;
					}
				} else {
					alive = false;
				}
			} 
			
			if (board.plr.getPower() <= 0) {
				System.out.println(board.display());
				System.err.println("You ran out of power!");
				alive = false;
			}
		}
		
		System.out.println("Game over!");
	}
	
	public static boolean deathCheck() {
		System.out.println("Would you like to spend 20pts to buy another life? (Y/N)");
		String response = reader.nextLine();
		switch(response.toUpperCase()) {
			case "Y":
				return true;
			case "N":
				return false;
			default:
				System.out.println("Invalid input.");
				return deathCheck();
		}
	}
}
