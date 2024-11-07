package TreasureQuest;

import java.util.Scanner;

public class GameControl {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner inp = new Scanner(System.in);
		
		System.out.println("Enter Size of Board: (E.g; 5 = 5x5)");
		int size = inp.nextInt();  // TODO: Error checking; check if mis-input of string
		inp.nextLine();

		Board board = new Board(size);
		
		while (true) {
			if (board.annoucement.isEmpty()) {
				System.out.println("Enter movement(s):");
				String input = inp.nextLine();
				System.out.println(board.updateBoard());
				if (input == null || input.isEmpty()) {
					System.exit(0);
				} else {
					board.move(input);
				}
			} else {
				System.out.println(board.updateBoard());
				board.updateCell(board.getPlrPos(), board.icon[1]);
				board.annoucement = "";
			}
		}
	}

}
