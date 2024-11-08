package TreasureQuest;

import java.util.Scanner;

public class GameControl {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner reader = new Scanner(System.in);
		System.out.println(""
				+ "▄▄▄▄▄▄▄▄▄  ▄▄▄ . ▄▄▄· .▄▄ · ▄• ▄▌▄▄▄  ▄▄▄ .    .▄▄▄  ▄• ▄▌▄▄▄ ..▄▄ · ▄▄▄▄▄\n"
				+ " •██  ▀▄ █·▀▄.▀·▐█ ▀█ ▐█ ▀. █▪██▌▀▄ █·▀▄.▀·    ▐▀•▀█ █▪██▌▀▄.▀·▐█ ▀. •██  \n"
				+ "  ▐█.▪▐▀▀▄ ▐▀▀▪▄▄█▀▀█ ▄▀▀▀█▄█▌▐█▌▐▀▀▄ ▐▀▀▪▄    █▌·.█▌█▌▐█▌▐▀▀▪▄▄▀▀▀█▄ ▐█.▪\n"
				+ "  ▐█▌·▐█•█▌▐█▄▄▌▐█ ▪▐▌▐█▄▪▐█▐█▄█▌▐█•█▌▐█▄▄▌    ▐█▪▄█·▐█▄█▌▐█▄▄▌▐█▄▪▐█ ▐█▌·\n"
				+ "  ▀▀▀ .▀  ▀ ▀▀▀  ▀  ▀  ▀▀▀▀  ▀▀▀ .▀  ▀ ▀▀▀     ·▀▀█.  ▀▀▀  ▀▀▀  ▀▀▀▀  ▀▀▀ ");
		System.out.println("// INSTRUCTIONS:\n"
				+ "- WASD to navigate \n"
				+ " (Ability to queue multiple keystrokes in one line) \n"
				+ "- Death upon mine \n"
				+ "- Death upon score zero \n");
		System.out.println("// TO BEGIN:\n"
				+ "Enter an integer to represent the map size (E.g: 5 = 5x5)");
		int size = reader.nextInt();  // TODO: Error checking; check if mis-input of string
		Board board = new Board(size);
		board.gen();
		while (true) {
			String input = reader.nextLine();
			board.processMove(input);
			System.out.println(board.display());
		}
	}
}
