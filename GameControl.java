package TreasureQuest;

import java.util.Scanner;
import static java.lang.System.out;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
 * TODO: Exclaimation Mark represents priority
 * !!! Code Comments
 * !!! Headers for each file (credits, desc, date)
 * !!! Response for EVERY error
 * !!! Input checking for EVERY input
 * !!! Function Docstrings???? (I lost marks for this last time so just in case)
 * !! Remove all commented out code
 * !! Optimize (If Possible)
 * !! Fix Min/Max silent error handling and tell user to re-input in this file
 * !! Make timer into 5:00 instead of 300s
 * ! Colored icons
 * ! Make it prettier
 */

public class GameControl {
	static Scanner reader = new Scanner(System.in);

	private static int check() {
		String preInt = reader.nextLine();
		try {
			return Integer.parseInt(preInt);
		} catch (NumberFormatException e) {
			System.out.println("Please enter a valid integer");
		}
		return check();
	}

	public static void main(String[] args) {
		boolean alive = true;
		// TODO Auto-generated method stub
		System.out.println("" + "▄▄▄▄▄▄▄▄▄  ▄▄▄ . ▄▄▄· .▄▄ · ▄• ▄▌▄▄▄  ▄▄▄▀.   .▄▄▄  ▄• ▄▌▄▄▄ ..▄▄· ▄▄▄▄▄\n"
				+ " •██  ▀▄ █·▀▄.▀·▐█ ▀█ ▐█ ▀. █▪██▌▀▄ █·▀▄.·    ▐▀•▀█ █▪██▌▀▄.▀·▐█ ▀. •██  \n"
				+ "  ▐█.▪▐▀▀▄ ▐▀▀▪▄▄█▀▀█ ▄▀▀▀█▄█▌▐█▌▐▀▀▄ ▐▀▀▪    █▌·.█▌█▌▐█▌▐▀▀▪▄▄▀▀▀█▄ ▐█.▪\n"
				+ "  ▐█▌·▐█•█▌▐█▄▄▌▐█ ▪▐▌▐█▄▪▐█▐█▄█▌▐█•█▌▐█▄▄▌    ▐█▪▄█·▐█▄█▌▐█▄▄▌▐█▄▪▐█ ▐█▌·\n"
				+ "  ▀▀▀ .▀  ▀ ▀▀▀  ▀  ▀  ▀▀▀▀  ▀▀▀ .▀  ▀ ▀▀▀     ·▀▀█.  ▀▀▀  ▀▀▀  ▀▀▀▀  ▀▀▀ ");
		System.out.println("// INSTRUCTIONS:\n" + "* WASD to navigate \n"
				+ "- Deactivated mines are persistent depicted as '*' \n"
				+ "- Treasures are persistent depicted as '$' \n" + "- Power-ups are depicted as 'P' \n"
				+ "- Time limit of 5 minutes \n" + "- Win by collecting all the rewards \n"
				+ "- Death upon zero lives/points \n" + "- Points are required to move \n"
				+ "- Points are used to purchase lives \n" + "- Lives are used to resurrect \n"
				+ "- Lives can be used to replenish points \n" + "- Treasure gives 50 points \n"
				+ "- Power-ups give 20 points and an extra life \n" + "- Extra lives depending on difficulty \n");

		try {
			String scores = "// SCORES:\n";
			File score = new File("highscores.txt");
			score.createNewFile();

			Scanner scoreReader = new Scanner(score);
			while (scoreReader.hasNextLine()) {
				String data = scoreReader.nextLine();
				scores = scores + data + "\n";
			}
			scoreReader.close();
			out.println(scores);
		} catch (IOException e) {
			System.err.println("An error occurred.");
			e.printStackTrace();
		}

		System.out.println(
				"// TO BEGIN:\n" + "Enter an integer to represent the map size (E.g: 5 = 5x5) \n" + "(Minimum of 5x5)");
		Board board = new Board(Math.max(check(), 5)); // auto 3 or best size
		System.out.println("// SELECT DIFFICULTY:\n"
				+ "Enter an integer to represent difficulty multiplier (E.g: 0 = Easy) \n" + "(Maximum of 15)");

		board.gen(Math.min(check(), 15)); // auto or best setting
		while (alive) {
			System.out.println(board.display());
			System.out.println("Enter your next movement (WASD):");
			String input = reader.nextLine();
			board.processMove(input);

			if (board.plr.getLives() <= 0 || board.plr.getPoints() <= 0) {
				System.out.println(board.display());
				System.err.println("You died!");

				if (board.plr.getPoints() >= 20 && board.plr.getLives() <= 0) { // transform points to lives to move
					System.err.println("You were resurrected sparing 20 of your points");
					board.plr.updateStat("Points", -20);
					board.plr.updateStat("Lives", 1);
				} else if (board.plr.getLives() > 1 && board.plr.getPoints() <= 0) { // transform lives to points to
																						// move
					System.err.println("You were resurrected sparing 1 of your lives");
					board.plr.updateStat("Lives", -1);
					board.plr.updateStat("Points", board.size * board.size);
				} else {
					alive = false;
				}
			} else if (board.benefitCount <= 0 && board.plr.getLives() >= 1) {
				System.out.println("Rewards Cleared! You Win!");
				alive = false;
			}
			/*
			 * if (board.plr.getLives() <= 0) { System.out.println(board.display());
			 * System.err.println("You ran out of lives!"); alive = false; }
			 */
		}

		out.println(board.display());
		System.out.println("Game over!");
		try {
			String curFile = "";
			File score = new File("highscores.txt");
			score.createNewFile();

			Scanner reader = new Scanner(score);
			while (reader.hasNextLine()) {
				String data = reader.nextLine();
				curFile = curFile + data + "\n";
			}
			reader.close();

			out.println("Enter your name to save score:");
			Scanner name = new Scanner(System.in);

			FileWriter myWriter = new FileWriter("highscores.txt");
			myWriter.write(name.nextLine() + ": " + (board.plr.getPoints() + board.plr.getLives() * 20)
					+ " (Difficulty:" + board.difficulty + " Elapsed:"
					+ ((int) ((System.currentTimeMillis() - board.timeLeft) / 100)) + "s)\n" + curFile);
			myWriter.close();
		} catch (IOException e) {
			System.err.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
