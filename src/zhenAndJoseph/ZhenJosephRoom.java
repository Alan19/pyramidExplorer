package zhenAndJoseph;

import pyramidExplorer.CaveExplorer;
import pyramidExplorer.CaveRoomPd8;
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
	
	public void play(){
		//Sets the number of mines
		plantMines(mines, 15);
		for (int row = 0; row < tileValues.length; row++) {
			for (int col = 0; col < tileValues[row].length; col++) {
				tileValues[row][col] = countNearby(mines, row, col);
			}
		}
		boolean isCheating = false;
		System.out.println("Are you ready to play?");
		String input = CaveExplorer.in.nextLine();
		if(input.equals("I want to leave")){
			CaveExplorer.print("Your outrageous input crashes the minefield. All the tiles are now revealed, allowing you to cross the room without any problems");
			ZhenMinefieldUtilities.printPic(tileValues);
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
				ZhenMinefieldUtilities.printPic(tileValues);
				break;
			}
			else if (revealedTiles[row][col]) {
				CaveExplorer.print("That tile is already revealed");
			}
			else{
				//Use update tiles on each of the tiles around it
				updateTiles(row, col);
				printGrid();
			}
		}
	}

	

	private void updateTiles(int row, int col) {
//		System.out.println(tileValues[row][col]);
		if(tileValues[row][col].equals("0")){
			for (int i = row-1; i < row+2; i++) {
				for (int j = col-1; j < col+2; j++) {
					if(ZhenMinefieldUtilities.isValidTile(mines, i, j) && tileValues[i][j].equals("0") && !revealedTiles[i][j]){
						revealedTiles[i][j] = true;
						updateTiles(i, j);
//						printGrid();
					}
					else if (ZhenMinefieldUtilities.isValidTile(mines, i, j) /*&& !mines[i][j]*/){						
						revealedTiles[i][j] = true;
					}
					
				}
			}			
		}
		else{
				revealedTiles[row][col] = true;
		}
	}
	private void printGrid() {
		for (int row = 0; row < mines.length; row++) {
			for (int col = 0; col < mines[row].length; col++) {
				if (revealedTiles[row][col]) {
					System.out.print(tileValues[row][col]);
				}
				else{
					System.out.print(" ");
				}
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
	
	private static String countNearby(boolean[][] mines, int row, int col) {
		int count = 0;
//		
		//This method allows you to be most specific. For example, you only want north and east
		if(mines[row][col]){
			return "X";
		}
		else{
			count += isValidAndTrue(mines, row+1, col);
			count += isValidAndTrue(mines, row-1, col);
			count += isValidAndTrue(mines, row, col-1);
			count += isValidAndTrue(mines, row, col+1);
			count += isValidAndTrue(mines, row+1, col-1);
			count += isValidAndTrue(mines, row-1, col+1);
			count += isValidAndTrue(mines, row+1, col+1);
			count += isValidAndTrue(mines, row-1, col-1);
			return "" + count;
		}
	}

	private static int isValidAndTrue(boolean[][] mines, int row, int col) {
		if (row >= 0 && col >= 0 && row < mines.length && col < mines[0].length && mines[row][col]){
			return 1;
		}
		else{
			return 0;
		}
	}
	
	
}
