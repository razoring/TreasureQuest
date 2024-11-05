package TreasureQuest;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class GameControl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JOptionPane frame = new JOptionPane();
		int size = Integer.parseInt(frame.showInputDialog("Enter Board Size: (E.g; 2x2)"));
		
		
		Board board = new Board(size);
	}

}
