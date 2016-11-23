package zhenAndJoseph;

import pyramidExplorer.CaveExplorer;
import pyramidExplorer.CaveRoomPd8;
import pyramidExplorer.GameStartEvent;
import pyramidExplorer.Playable;

public class ZhenJosephRoom extends CaveRoomPd8 implements Playable{
	private static int fieldSize = 10;
	private boolean[][] revealedTiles = new boolean [fieldSize][fieldSize];
	private boolean[][] mines = new boolean[fieldSize][fieldSize];
	private String[][] tileValues = new String[fieldSize][fieldSize];
	public ZhenJosephRoom(String description) {
		super(description);
	}

	public void enter(){

	} 
	
	private static final String[] SEQUENCE = {"You are trapped in this room!",
		"You notice that the tiles have numbers that you cannot see as it is too dark",
		"You also see some holes in the ground",
		"However, they seem to light up when something is on top of them",
		"You find a pile of rocks nearby.",
		"In order to know where to step, throw these rocks on the tiles",
		"to see where the floor can collaspe.",
		"On the wall, it says that for when three stones are thrown, all the surrounding tiles will be revealed."};
	
	public void play(){
		int numberOfMines = 15;
		//Sets the number of mines
		GameStartEvent.readSequence(SEQUENCE);
		plantMines(mines, 15);
		for (int row = 0; row < tileValues.length; row++) {
			for (int col = 0; col < tileValues[row].length; col++) {
				tileValues[row][col] = JosephMinefieldProccessing.countNearby(mines, row, col);
			}
		}
		boolean isCheating = false;
		System.out.println("Are you planning on leaving?");
		String input = CaveExplorer.in.nextLine();
		if(input.equals("I want to leave")) isCheating = true;
		ZhenMinefieldUtilities.playMinesweeper(isCheating, mines, revealedTiles, fieldSize, tileValues);
	}
	
	public static void printPic(String[][] pic){
		for (String[] row : pic) {
			for (String col : row) {
				System.out.print(col);
			}
			System.out.println();
		}
	}
	
	private static void plantMines(boolean[][] mines, int numberOfMines) {
		while(numberOfMines > 0){
			int row = (int)(Math.random() * mines.length);
			int col = (int)(Math.random() * mines[0].length);
			if(!mines[row][col]){
				mines[row][col] = true;
				numberOfMines--;
			}
		}
	}

	public static int isValidAndTrue(boolean[][] mines, int row, int col) {
		if (row >= 0 && col >= 0 && row < mines.length && col < mines[0].length && mines[row][col]){
			return 1;
		}
		else{
			return 0;
		}
	}
	public static int getNonNegativeIntegerInput(int fieldSize) {
		String integerString = CaveExplorer.in.nextLine();
		boolean isInteger = false;
		boolean isPositive = false;
		int value = 0;
		while(!isInteger || !isPositive){
			try{
				value = Integer.parseInt(integerString);
				//will not continue if an error above is thrown
				isInteger = true;//exits loop if entry is valid
				if(value < 0 || value > fieldSize){
					isPositive = false;
					CaveExplorer.print("You must enter an non-negative integer.");
					integerString = CaveExplorer.in.nextLine();
				}
				else{
					isPositive = true;
				}
			}catch(NumberFormatException e){
				CaveExplorer.print("You must enter an non-negative integer.");
				integerString = CaveExplorer.in.nextLine();
			}
		}
		return value;
	}
}
