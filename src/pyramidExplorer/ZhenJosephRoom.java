package pyramidExplorer;

public class ZhenJosephRoom extends CaveRoomPd8 {
	private static int fieldSize = 12;
	private boolean[][] revealedTiles = new boolean [fieldSize][fieldSize];
	private boolean[][] mines = new boolean[fieldSize][fieldSize];
	private String[][] tileValues = new String[fieldSize][fieldSize];
	private String[][] unrevealedTiles = new String[fieldSize][fieldSize];
	
	public ZhenJosephRoom(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}

	public void enter(){
		super.enter();
		
		System.out.println("You are trapped in this room! \nYou see that the floor might crumble if you step on certain tiles.\nIn order to know where to step, throw these rocks on the tiles to see where the floor can collaspe.");
		play();
	}
	
	public void play(){
		
		plantMines(mines, 10);
		for (int row = 0; row < tileValues.length; row++) {
			for (int col = 0; col < tileValues[row].length; col++) {
				tileValues[row][col] = countNearby(mines, row, col);
			}
		}
		
		while(true){
			CaveExplorer.print("Which row would you like to check?");
			int row = getNonNegativeIntegerInput();
			
			CaveExplorer.print("Which column would you like to check?");
			int col = getNonNegativeIntegerInput();
			
			if(mines[row][col]){
				CaveExplorer.print("The ground collapses!");
				break;
			}
			else if (revealedTiles[row][col]) {
				CaveExplorer.print("That tile is already revealed");
			}
			else{
				revealedTiles[row][col] = true;
				//Use update tiles on each of the tiles around it
				updateTiles(mines, tileValues, unrevealedTiles, row, col);
				printGrid();
			}
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

	private void updateTiles(boolean[][] mines, String[][] tileValues, String[][] unrevealedTiles, int row, int col) {
//		System.out.println(tileValues[row][col]);
		if(tileValues[row][col].equals("0")){
			for (int i = row-1; i < row+2; i++) {
				for (int j = col-1; j < col+2; j++) {
					if(isValidTile(mines, i, j) && tileValues[i][j].equals("0") && !revealedTiles[i][j]) updateTiles(mines, tileValues, unrevealedTiles, i, j);
					else if (isValidTile(mines, i, j) && !mines[i][j]) revealedTiles[i][j] = true;
				}
			}			
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
	
	private static boolean isValidTile(boolean[][] mines, int row, int col) {
		if(row >= 0 && col >= 0 && row < mines.length && col < mines[0].length){
			return true;
		}
		else {
			return false;
		}
	}
}
