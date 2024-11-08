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
		// TODO Auto-generated method stub
		System.out.println(""
				+ "▄▄▄▄▄▄▄▄▄  ▄▄▄ . ▄▄▄· .▄▄ · ▄• ▄▌▄▄▄  ▄▄▄▀.   .▄▄▄  ▄• ▄▌▄▄▄ ..▄▄· ▄▄▄▄▄\n"
				+ " •██  ▀▄ █·▀▄.▀·▐█ ▀█ ▐█ ▀. █▪██▌▀▄ █·▀▄.·    ▐▀•▀█ █▪██▌▀▄.▀·▐█ ▀. •██  \n"
				+ "  ▐█.▪▐▀▀▄ ▐▀▀▪▄▄█▀▀█ ▄▀▀▀█▄█▌▐█▌▐▀▀▄ ▐▀▀▪    █▌·.█▌█▌▐█▌▐▀▀▪▄▄▀▀▀█▄ ▐█.▪\n"
				+ "  ▐█▌·▐█•█▌▐█▄▄▌▐█ ▪▐▌▐█▄▪▐█▐█▄█▌▐█•█▌▐█▄▄▌    ▐█▪▄█·▐█▄█▌▐█▄▄▌▐█▄▪▐█ ▐█▌·\n"
				+ "  ▀▀▀ .▀  ▀ ▀▀▀  ▀  ▀  ▀▀▀▀  ▀▀▀ .▀  ▀ ▀▀▀     ·▀▀█.  ▀▀▀  ▀▀▀  ▀▀▀▀  ▀▀▀ ");
		System.out.println("// INSTRUCTIONS:\n"
				+ "- WASD to navigate \n"
				+ " (Ability to queue multiple keystrokes in one line) \n"
				+ "- Death upon mine \n"
				+ "- Death upon score zero \n");
		System.out.println("// TO BEGIN:\n"
				+ "Enter an integer to represent the map size (E.g: 5 = 5x5)");
		
		Board board = new Board(check());
		board.gen();
		while (true) {
			System.out.println(board.display());
			String input = reader.nextLine();
			board.processMove(input);
		}
	}
}
