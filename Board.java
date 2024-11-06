package TreasureQuest;

import java.util.Arrays;
import static java.lang.System.out;
import static java.lang.Math.sqrt;

public class Board {
	private final String CONTAINER = "⃣";
	public String icon[] = {"  "+CONTAINER,"x"+CONTAINER,"*"+CONTAINER,"P"+CONTAINER,"↯"+CONTAINER}; // Blank, Player, Mine, Power-up, Power-up Alt
	public String items[] = {"Mine","Powerup",null,null};
	public int size;
	public String annoucement;

	int score = 100; //TODO: Make this dynamic
	int lives = 1; //TODO: Make this dynamic
	
	protected String cells[];
	protected String cellsHidden[];
	protected int pos;
	
	public Board() {
		this.size = 0;
		this.pos = 0;
		this.cells = null;
		this.cellsHidden = null;
		this.annoucement = "";
	}
	
	public Board(int x) {
		this.cells = new String[x*x];
		this.cellsHidden = new String[x*x];
		this.size = (int)(sqrt(this.cells.length));
		this.annoucement = "";
		this.genCells();
	}
	
	protected void genCells() {
		for (int i = 0;i<this.cells.length;i++) {
			int center = (int)(Math.ceil(this.cells.length/2));
			if (i==center) {
				this.cells[i] = icon[1];
				this.pos = center;
			} else {
				this.cells[i] = this.icon[0];
			}
		}

		for (int i = 0;i<this.cellsHidden.length;i++) {
			int center = (int)(Math.ceil(this.cellsHidden.length/2));
			if (i!=center) {
				this.cellsHidden[i] = items[(int)(Math.random()*items.length)];
			}
		}
		//out.println(Arrays.toString(cellsHidden));
		
		this.updateBoard();
	}
	
	public String updateBoard() {
		String board = "Treasure Quest";

		// Padding
		board = board+"\n"+this.annoucement+"\n";
		
		// Stats Display
		// TODO: Make this functional
		board = board+"♥ (x"+lives+")   ✪ "+score+"\n";
		
		// Row Display
		for (int row = 0;row<this.size;row++) {
			for (int i = this.size*row;i<this.size*(row+1);i++) {
				board = board+"   "+this.cells[i];
			}
			board = board+"\n";
		}
		
		// Padding
		board = board+"\n";
		return board;
	}
	
	public int getPlrPos() {
		return this.pos;
	}
	
	public void updateCell(int cell, String value) {
		if (value.isEmpty()) {
			if (canMove()) {
				this.cells[this.getPlrPos()] = icon[0];
				this.cells[cell] = icon[1];
				this.pos = cell;
			} else {
				this.cells[this.getPlrPos()] = icon[0];
				this.cells[cell] = icon[2];
				this.pos = cell;
			}
		} else {
			this.cells[cell] = value;
		}
	}
	
	/*
	protected Boolean canMove() {
		int item = (int)(Math.random()*items.length);
		if (items[item] == "Mine") {
			// what happens for mine
			return false;
		} else if (items[item] == "Powerup") {
			// what happens for power-up
		}
		return true;
	}*/
	
	protected Boolean canMove() {
		if (cellsHidden[getPlrPos()]==items[0]) {
			// Mine code
			this.annoucement = "𐄂 Touched a mine!";
			return false;
		} else  if (cellsHidden[getPlrPos()]==items[1]) {
			// Power-up code
			this.annoucement = icon[4]+" Recieved an exta life!";
			return false;
		}
		return true;
	}
	
	public void move(String input) {
		for (int i = 0;i<input.length();i++) {
			int desiredPos = 0;
			if (input.charAt(i) == 'w' || input.charAt(i) == 'W') {
				desiredPos = this.getPlrPos()-this.size;
				this.updateCell(desiredPos>0?desiredPos:this.getPlrPos(), ""); // if in bounds, go to desired
			} else if (input.charAt(i) == 'a' || input.charAt(i) == 'A') {
				desiredPos = this.getPlrPos()-1;
				this.updateCell(desiredPos>=0 && this.getPlrPos()%this.size!=0?desiredPos:this.getPlrPos(), ""); // if in bounds and not at end, go to desired
			} else if (input.charAt(i) == 'd' || input.charAt(i) == 'D') {
				desiredPos = this.getPlrPos()+1;
				this.updateCell(desiredPos>=0 && (this.getPlrPos()+1)%this.size!=0?desiredPos:this.getPlrPos(), ""); // if in bounds and not at end, go to desired; constant 1 accounts for java arrays starting at 0
			} else if (input.charAt(i) == 's' || input.charAt(i) == 'S') {
				desiredPos = this.getPlrPos()+this.size;
				this.updateCell(desiredPos<this.cells.length?desiredPos:this.getPlrPos(), ""); // if in bounds, go to desired
			} else {
				continue;
			}
		}
	}
}
