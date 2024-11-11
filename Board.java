package TreasureQuest;

import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;
import static java.lang.System.out;

/* Raymond So
 * 11.7.24
 * Board Class:
 * TODO: ADD DESC!!!!!!
 */

public class Board {
	Player plr;
	
	protected boolean debug = false; // for debugging

	// positioning
	public int size; // size of board
	public int pos[] = new int[2]; // current position; just player
	protected String grid[][]; // to render
	protected String map[][]; // hidden item map
	protected String disarmed[][]; // inital hidden item map
	public double timeLeft;
	
	// generation logic
	private double mineMax = 6; // difficulty increases
	private double benefitMax = 8; // difficulty decreases
	private boolean prizeMin = false; // minimum treasure requirement
	private boolean powerMin = false;
	public int mineCount = 0;
	public int benefitCount = 0;
	ArrayList<String> icons;

	// icons directory; usage: icon.get("Player") --> "x"
	public static HashMap<String, String> icon = new HashMap<String,String>(){{ // initialize STATIC hash map for easier referencing of icons
		put("empty"," ");
		put("prize","$");
		put("power","P");
		put("mine","*");
		put("plr","+");
		put("%","TBD");
	}};
	private String benefits[] = {"prize", "power" }; // good items: treasure, powerup

	public Board(int s) {
		size = s;
		pos[0] = ((int)(Math.random()*s));
		pos[1] = ((int)(Math.random()*s));
		map = new String[s][s];
		disarmed = new String[s][s];
		grid = new String[s][s];
		plr = new Player(debug?10:1,s*s); // init stats: lives, points
		timeLeft = System.currentTimeMillis();
	}
	
	public void gen(int difficulty) {
		// generate rng table
		difficulty = difficulty+Math.floorDiv(difficulty,2); 
		icons = new ArrayList<String>();
		mineMax = mineMax+difficulty;
		benefitMax = Math.max(benefitMax-difficulty, 2); // make sure two are always present
		plr.updateStat("Lives",Math.min(Math.floorDiv(difficulty,size*2), size*2)); // give player more health depending on difficulty
		
		for (int i = 0;i<(int)(mineMax);i++) {
			icons.add("mine");
		}
		for (int i = 0;i<(int)(benefitMax);i++) {
			icons.add("%");
		}
		for (int i = 0;i<(int)((size*size)-(mineMax+benefitMax));i++) {
			icons.add("empty");
		}
		
		// generate hidden map
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				int rand = (int)(Math.random()*icons.size());
				
				if (x == pos[0] && y == pos[1]) {
					render(pos[0],pos[1],icon.get("plr"));
					update(pos[0],pos[1],icon.get("plr"));
					if (debug) {
						out.println("spawn");
						out.println(pos[1]);
						out.println(pos[0]);
					}
				} else {
					if (icon.get(icons.get(rand)) == "TBD") {
						benefitCount++;
						if (!prizeMin) {
							update(x,y,icon.get("prize"));
							prizeMin = true;
						} else if (!powerMin) {
							update(x,y,icon.get("power"));
							powerMin = true;
						} else { // if there is more opportunity, generate randomly benefits
							update(x,y,icon.get(benefits[(int)(Math.random()*benefits.length)]));
						}
					} else { // gurantee treasure
						if (!prizeMin) {
							benefitCount++;
							update(x,y,icon.get("prize"));
							prizeMin = true;
						} else if (!powerMin) {
							benefitCount++;
							update(x,y,icon.get("power"));
							powerMin = true;
						} else {
							update(x,y,icon.get(icons.get(rand)));
							if (icon.get(icons.get(rand))==icon.get("mine")) {
								mineCount++;
								if (debug) {
									out.println(icon.get(icons.get(rand)));
									out.println(x);
									out.println(y);
								}
							}
						}
					}
					render(x,y,icon.get("empty"));
				}
			}
		}
		if (!debug) {
			update(pos[0],pos[1],icon.get("empty")); // cast plr to empty
		}
	}

	public String display() {
		if ((plr.getLives()<=0&&plr.getPoints()<20) || (benefitCount<=0) ) { debug=true; };
		
		String board = "\n";
		
		board = board+"Time Left: "+(300-(int)((System.currentTimeMillis()-timeLeft)/100))+"s\n";
		board = board+"Lives  Points\n";
		board = board+plr.getLives();
		for (int i = 0;i<"Lives  ".length()-(""+plr.getLives()).length();i++) {
			board = board+" ";
		}
		board = board+plr.getPoints();
		for (int i = 0;i<"Points  ".length()-(""+plr.getLives()).length();i++) {
			board = board+" ";
		}
		board = board+"\n";
		
		for (int row = 0;row<size+1;row++) {
			for (int column = 0;column<size;column++) {
				board = board+"+---";
				if (column==size-1) {
					board = board+"+\n";
				}
			}
			if (row<size) {
				for (int column = 0;column<size;column++) {
					board = board+"| "+(debug?map[row][column]:grid[row][column])+" ";
					if (column==size-1) {
						board = board+"|\n";
					}
				}
			}
		}
		board = board+mineCount+" Mines Left\n"+benefitCount+" Rewards Left";
		
		return board;
	}
	
	private void update(int x, int y, String value) {
		map[y][x] = value;
	}
	
	private void set(int x, int y, String value) {
		disarmed[y][x] = value;
	}
	
	private void render(int x, int y, String value) {
		grid[y][x] = value;
	}
	
	public void processMove(String keystroke) {
		if (keystroke.length()>0) {
			int lastX = pos[0];
			int lastY = pos[1];
			
			int i = 0; // get keybind queue index
			
			// movement logic
			if (keystroke.charAt(i)=='w'||keystroke.charAt(i)=='W') {
				pos[1] = pos[1]>0?pos[1]-1:0;
				if (pos[1]==lastY) {
					return;
				}
			} else if (keystroke.charAt(i)=='s'||keystroke.charAt(i)=='S') {
				pos[1] = pos[1]<size-1?pos[1]+1:size-1;
				if (pos[1]==lastY) {
					return;
				}
			} else if (keystroke.charAt(i)=='a'||keystroke.charAt(i)=='A') {
				pos[0] = pos[0]>0?pos[0]-1:0;
				if (pos[0]==lastX) {
					return;
				}
			} else if (keystroke.charAt(i)=='d'||keystroke.charAt(i)=='D') {
				pos[0] = pos[0]<size-1?pos[0]+1:size-1;
				if (pos[0]==lastX) {
					return;
				}
			}
			
			// icon setter
			plr.updateStat("Points", -1);
			render(lastX,lastY,icon.get("empty"));
			// fetch disarmed
			for (int x = 0;x<size;x++) {
				for (int y = 0;y<size;y++) {
					if (disarmed[y][x]==icon.get("mine")) {
						render(x,y,icon.get("mine"));
					}
				}
			}
			render(pos[0],pos[1],icon.get("plr"));
			
			if (map[pos[1]][pos[0]] != icon.get("empty")) { // if current cell is not empty
				render(pos[0],pos[1],map[pos[1]][pos[0]]); // render current item
				if (map[pos[1]][pos[0]] == icon.get("mine")) {
					//mine function
					mineCount--;
					set(pos[0],pos[1],icon.get("mine"));
					System.err.println("Mine has been disarmed");
					plr.updateStat("Lives", -1);
				} else if (map[pos[1]][pos[0]] == icon.get("prize")) {
					//treasure function
					benefitCount--;
					System.out.println("Treasure opened!");
					plr.updateStat("Points", 50);
				} else if (map[pos[1]][pos[0]] == icon.get("power")) {
					//powerup function
					benefitCount--;
					System.out.println("Extra life!");
					plr.updateStat("Lives", 1);
				}
				map[pos[1]][pos[0]] = icon.get("empty"); // clear item
			}
		}
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