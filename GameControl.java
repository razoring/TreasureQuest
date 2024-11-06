package TreasureQuest;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.awt.Color;

public class GameControl {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JOptionPane frame = new JOptionPane();
		int size = Integer.parseInt(frame.showInputDialog("Enter Size of Board: (E.g; 5 = 5x5)"));  // TODO: Error checking; check if mis-input of string
		
		Player plr = new Player();
		Board board = new Board(size);
		
		while (true) {
			String input = frame.showInputDialog(board.updateBoard());
			if (input == null || input.isEmpty() || input.isBlank()) {
				System.exit(0);
			} else {
				board.move(input);
			}
		}
	}

}
