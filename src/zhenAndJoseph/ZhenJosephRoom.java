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
	
	private static final String[] SEQUENCE = {"You are trapped in this room!", "You see that the floor might crumble if you step on certain tiles.", "In order to know where to step, throw these rocks on the tiles to see where the floor can collaspe."};
	
	public void play(){
		//Sets the number of mines
		GameStartEvent.readSequence(SEQUENCE);
		plantMines(mines, 15);
		for (int row = 0; row < tileValues.length; row++) {
			for (int col = 0; col < tileValues[row].length; col++) {
				tileValues[row][col] = JosephMinefieldProccessing.countNearby(mines, row, col);
			}
		}
		boolean isCheating = false;
		System.out.println("Are you ready to play?");
		String input = CaveExplorer.in.nextLine();
		if(input.equals("I want to leave")){
			CaveExplorer.print("Your outrageous input crashes the minefield. All the tiles are now revealed, allowing you to cross the room without any problems");
			printPic(tileValues);
			isCheating = true;
		}
		while(true){
			if(isCheating) break;
			if(ZhenMinefieldUtilities.allStandardTilesRevealed(mines, revealedTiles)){
				System.out.println("The room and the tiles light up, showing you how to cross the room");
				break;
			}
			CaveExplorer.print("Which row would you like to check?");
			int row = ZhenMinefieldUtilities.getNonNegativeIntegerInput(fieldSize);				
			CaveExplorer.print("Which column would you like to check?");
			int col = ZhenMinefieldUtilities.getNonNegativeIntegerInput(fieldSize);
			
			if(mines[row][col]){
				CaveExplorer.print("The ground collapses!");
				printPic(tileValues);
				break;
			}
			else if (revealedTiles[row][col]) {
				CaveExplorer.print("That tile is already revealed");
			}
			else{
				//Use update tiles on each of the tiles around it
				JosephMinefieldProccessing.updateTiles(row, col, tileValues, mines, revealedTiles);
				ZhenMinefieldUtilities.printGrid(mines, revealedTiles, tileValues);
			}
		}
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

	static int isValidAndTrue(boolean[][] mines, int row, int col) {
		if (row >= 0 && col >= 0 && row < mines.length && col < mines[0].length && mines[row][col]){
			return 1;
		}
		else{
			return 0;
		}
	}
	
	
}
