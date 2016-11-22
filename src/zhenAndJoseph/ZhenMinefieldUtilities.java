package zhenAndJoseph;

import pyramidExplorer.CaveExplorer;

public class ZhenMinefieldUtilities {
	public static boolean isValidTile(boolean[][] mines, int row, int col) {
		if(row >= 0 && col >= 0 && row < mines.length && col < mines[0].length){
			return true;
		}
		else {
			return false;
		}
	}
	
	private static void printGrid(boolean[][] mines, boolean[][] revealedTiles, String[][] tileValues) {
		System.out.print("  ");
		for (int col = 0; col < tileValues.length; col++) {
			System.out.print(col + " ");
		}
		System.out.println("");
		for (int row = 0; row < mines.length; row++) {
			System.out.print(row + " ");
			for (int col = 0; col < mines[row].length; col++) {
				if (revealedTiles[row][col]) {
					System.out.print(tileValues[row][col] + " ");
				}
				else{
					System.out.print("  ");
				}
			}
			System.out.println();
		}
	}
	
	
	
	private static boolean allStandardTilesRevealed(boolean[][] mines, boolean revealedTiles[][]) {
		//Returns true if all non mine tiles are revealed
		for (int row = 0; row < mines.length; row++) {
			for (int col = 0; col < mines[row].length; col++) {
				if(!revealedTiles[row][col] && !mines[row][col]){
					return false;
				}
			}
		}
		return true;
	}
	
	public static void playMinesweeper(boolean isCheating, boolean[][] mines, boolean[][] revealedTiles, int fieldSize, String[][] tileValues){
		while(true){
			if(isCheating){
				CaveExplorer.print("Your refusal to play the game causes all of the tiles to light up, allowing you to cross the room");
				revealAll(revealedTiles);
				printGrid(mines, revealedTiles, tileValues);
				break;
			}
			if(ZhenMinefieldUtilities.allStandardTilesRevealed(mines, revealedTiles)){
				System.out.println("The room and the tiles light up, showing you how to cross the room");
				break;
			}
			
			CaveExplorer.print("Which row would you like to check?");
			int row = ZhenJosephRoom.getNonNegativeIntegerInput(fieldSize);				
			CaveExplorer.print("Which column would you like to check?");
			int col = ZhenJosephRoom.getNonNegativeIntegerInput(fieldSize);
			
			//Input processing
			if(mines[row][col]){
				CaveExplorer.print("The ground collapses!");
				revealAll(revealedTiles);
				printGrid(mines, revealedTiles, tileValues);
				CaveExplorer.lose = true;
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

	private static void revealAll(boolean[][] revealedTiles) {
		for (int row = 0; row < revealedTiles.length; row++) {
			for (int col = 0; col < revealedTiles[row].length; col++) {
				revealedTiles[row][col] = true;
			}
		}
	}
}
