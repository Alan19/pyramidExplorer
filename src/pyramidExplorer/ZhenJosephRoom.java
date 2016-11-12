package pyramidExplorer;

public class ZhenJosephRoom extends CaveRoomPd8 {

	public ZhenJosephRoom(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}

	public void enter(){
		super.enter();
		System.out.println("You are trapped in this room! \nYou see that the floor might crumble if you step on certain tiles.\nIn order to know where to step, throw these rocks on the tiles to see where the floor can collaspe.");
		
	}
	
	public void play(){
		int fieldSize = 10;
		boolean[][] revealedTiles = new boolean [fieldSize][fieldSize];
		boolean[][] mines = new boolean[fieldSize][fieldSize];
		String[][] tileValues = new String[fieldSize][fieldSize];
		String[][] unrevealedTiles = new String[fieldSize][fieldSize];
		plantMines(mines);
		
		while(true){
			CaveExplorer.print("Which row would you like to check?");
			int row = getNonNegativeIntegerInput();
			
			CaveExplorer.print("Which column would you like to check?");
			int col = getNonNegativeIntegerInput();
			
			if(mines[row][col]){
				CaveExplorer.print("The ground collapses!");
				break;
			}
			else if (!revealedTiles[row][col]) {
				CaveExplorer.print("That tile is already revealed");
			}
			else{
				revealedTiles[row][col] = true;
				updateTiles(tileValues, unrevealedTiles);
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
				if(value < 0){
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
	
	private static void plantMines(boolean[][] mines) {
		int numberOfMines = 5;
		while(numberOfMines > 0){
			int row = (int)(Math.random() * mines.length);
			int col = (int)(Math.random() * mines[0].length);
			if(!mines[row][col]){
				mines[row][col] = true;
				numberOfMines--;
			}
		}
	}
}
