package TreasureQuest;

import java.util.HashMap;
import static java.lang.System.out;
import java.util.Arrays;

/* Raymond So
 * 11.7.24
 * Board Class:
 * TODO: ADD DESC!!!!!!
 */

public class Board {
	Player plr = new Player();
	
	public int size; // size of board
	public String status; // "YOU HIT A MINE!" || "YOU GOT AN EXTRA LIFE!"

	public int pos[] = new int[2]; // current position; just player
	protected String grid[][]; // to render
	protected String map[][]; // hidden item map
	
	// icons directory; usage: icon.get("Player") --> "x"
	private String icons[] = { "empty", "empty", "empty", "mine", "mine", "mine", "%" }; // probability: empty: 45%, mine:45%, powerup: 5%, treasure:5%
	public static HashMap<String, String> icon=new HashMap<String,String>(){{
		put("empty"," ");
		put("prize","$");
		put("power","P");
		put("mine","*");
		put("plr","x");
		put("%","TBD");
	}};
	private String benefits[] = {"prize", "power" }; // good items: treasure, powerup

	public Board(int s) {
		size = s;
		status = "";
		pos[0] = 0;
		pos[1] = 0;
		map = new String[s][s];
		grid = new String[s][s];
	}
	
	public void gen() {
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				int rand = (int) (Math.random() * icons.length);
				//TODO: Randomize plr starting position
				if (x == 0 && y == 0) {
					update(0,0,icon.get("plr"));
					render(0,0,icon.get("plr"));
				} else {
					if (icon.get(icons[rand]) == "TBD") {
						update(x,y,icon.get(benefits[(int) (Math.random() * benefits.length)]));
					} else {
						update(x,y,icon.get(icons[rand]));
					}
					render(x,y,icon.get("empty"));
				}
			}
		}
	}

	public String display() {
		String board = status+"\n";
		board = board+"Lives  Score  Points\n";
		board = board+plr.getLife();
		for (int i = 0;i<"Lives  ".length()-(""+plr.getLife()).length();i++) {
			board = board+" ";
		}
		board = board+plr.getStamina();
		for (int i = 0;i<"Score  ".length()-(""+plr.getStamina()).length();i++) {
			board = board+" ";
		}
		board = board+plr.getScore()+"\n";
		
		for (int row = 0;row<size+1;row++) {
			for (int column = 0;column<size;column++) {
				board = board+"+---";
				if (column==size-1) {
					board = board+"+\n";
				}
			}
			if (row<size) {
				for (int column = 0;column<size;column++) {
					board = board+"| "+grid[row][column]+" ";
					if (column==size-1) {
						board = board+"|\n";
					}
				}
			}
		}
		return board;
	}
	
	private void update(int x, int y, String value) {
		map[y][x] = value;
	}
	
	private void render(int x, int y, String value) {
		grid[y][x] = value;
	}
	
	public void processMove(String queue) {
		int posX = pos[0];
		int posY = pos[1];
		
		int i = 0;
		out.println(i);
		
		// movement logic
		if (queue.charAt(i)=='w'||queue.charAt(i)=='W') {
			pos[1] = pos[1]>0?pos[1]-1:0;
		} else if (queue.charAt(i)=='s'||queue.charAt(i)=='S') {
			pos[1] = pos[1]<size-1?pos[1]+1:size-1;
		} else if (queue.charAt(i)=='a'||queue.charAt(i)=='A') {
			pos[0] = pos[0]>0?pos[0]-1:0;
		} else if (queue.charAt(i)=='d'||queue.charAt(i)=='D') {
			pos[0] = pos[0]<size-1?pos[0]+1:size-1;
		}
		render(pos[0],pos[1],icon.get("plr"));
		
		// icon setter
		if (map[pos[1]][pos[0]] != icon.get("empty")) {
			render(pos[0],pos[1],map[pos[1]][pos[0]]);
			if (map[pos[1]][pos[0]] == icon.get("mine")) {
				//TODO: function
				if (grid[pos[1]][pos[0]] == icon.get("mine")) {
					plr.updateStat("Lives", -1);
				}
			} else if (map[pos[1]][pos[0]] == icon.get("prize")) {
				//TODO: function
				plr.updateStat("Score", 50);
			} else if (map[pos[1]][pos[0]] == icon.get("power")) {
				//TODO: function
				plr.updateStat("Power", 5);
			}
		}
		
		render(posX,posY,map[posY][posX] != icon.get("mine")?icon.get("empty"):icon.get("mine"));
	}
}

/*
 * 
 * YOU HIT A MINE!
 * Lives Score Points
 * 1     100   500
 * +-----+-----+-----+
 * |  *  |     |     |
 * +-----+-----+-----+ 
 * |  x  |  $  |     |
 * +-----+-----+-----+
 * |  P  |     |     |
 * +-----+-----+-----+
 * 
 */