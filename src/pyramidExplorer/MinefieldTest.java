package pyramidExplorer;

public class MinefieldTest {

	private static int fieldSize = 5;
	
	public static void main(String[] args) {
		boolean[][] revealedTiles = new boolean [fieldSize][fieldSize];
		boolean[][] mines = new boolean[fieldSize][fieldSize];
		String[][] tileValues = new String[fieldSize][fieldSize];
		String[][] unrevealedTiles = new String[fieldSize][fieldSize];
		plantMines(mines, 3);
		for (int row = 0; row < tileValues.length; row++) {
			for (int col = 0; col < tileValues[row].length; col++) {
				if(mines[row][col]) tileValues[row][col] = "X";
				else tileValues[row][col] = countNearby(mines, row, col);
			}
		}
		printPic(tileValues);
//		while(true){
//			CaveExplorer.print("Which row would you like to check?");
//			int row = getNonNegativeIntegerInput();
//			
//			CaveExplorer.print("Which column would you like to check?");
//			int col = getNonNegativeIntegerInput();
//			
//			if(mines[row][col]){
//				CaveExplorer.print("The ground collapses!");
//				break;
//			}
//			else if (!revealedTiles[row][col]) {
//				CaveExplorer.print("That tile is already revealed");
//			}
//			else{
//				revealedTiles[row][col] = true;
//				updateTiles(tileValues, unrevealedTiles);
//			}
//		}

	}

	public static void printPic(String[][] pic){
		for (String[] row : pic) {
			for (String col : row) {
				System.out.print(col);
			}
			System.out.println();
		}
	}
	
	private static int getNonNegativeIntegerInput() {
		System.out.println("Please enter an non-negative integer.");
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

	private static int isValidAndTrue(boolean[][] mines, int i, int j) {
		if (i >= 0 && j >= 0 && i < mines.length && j < mines[0].length && mines[i][j]){
			return 1;
		}
		else{
			return 0;
		}
	}
}
