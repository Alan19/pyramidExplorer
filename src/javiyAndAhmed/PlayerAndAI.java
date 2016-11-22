package javiyAndAhmed;

import java.util.Scanner;

import pyramidExplorer.CaveExplorer;

public class PlayerAndAI extends JaviyAhmedRoom {

	private static boolean playerHit;
	private static boolean compHit;
	private static boolean shipDestroyed = false;
	private static int[][] compGuesses = new int[10][10]; //2 means hit, 1 means miss, 0 means not hit/miss
	
	public static boolean isPlayerHit() {
		return playerHit;
	}

	public static void setPlayerHit(boolean playerHit) {
		PlayerAndAI.playerHit = playerHit;
	}

	public static boolean isCompHit() {
		return compHit;
	}

	public static void setCompHit(boolean compHit) {
		PlayerAndAI.compHit = compHit;
	}

	public PlayerAndAI(String description) {
		super(description);
	}
	
	
	private static void checkBoard(int[] coordinateChecker,int[][] intArray) {
		if(intArray[coordinateChecker[0]][coordinateChecker[1]]==1 || intArray[coordinateChecker[0]][coordinateChecker[1]]==2){
			System.out.println("You have already used this coordinate please select a new one.");
		}else if(intArray[coordinateChecker[0]][coordinateChecker[1]]==3){
			intArray[coordinateChecker[0]][coordinateChecker[1]]=2;
			System.out.println("Hit");
			if(intArray.equals(BoardGen.getPlayerBoard())){
				shipDestroyed = true;
				compGuesses[coordinateChecker[0]][coordinateChecker[1]] = 2;
			}
		}else{
			intArray[coordinateChecker[0]][coordinateChecker[1]]=1;
			System.out.println("Missed");
			if(intArray.equals(BoardGen.getPlayerBoard())){
				compHit = false;
				compGuesses[coordinateChecker[0]][coordinateChecker[1]] = 1;
			}else{
				playerHit = false;
			}
		}
	}
	
	public static void playGame() {
		compHit = true;
		playerHit = true;

		while(playerHit){
			in = new Scanner(System.in);
			String input = in.nextLine();
			interpretActions(input);
			BoardGen.updateBoards(BoardGen.getCompBoard(),BoardGen.getCompBoardString());
			CheckWin(BoardGen.getCompBoard());
			System.out.println("Computer's Board");
			print(BoardGen.getCompBoardString());
		}
		while(compHit){
			compAction();
			BoardGen.updateBoards(BoardGen.getPlayerBoard(),BoardGen.getPlayerBoardString());
			CheckWin(BoardGen.getPlayerBoard());
			System.out.println("PLayer's Board");
			print(BoardGen.getPlayerBoardString());
		}
		
	}
	
	public static void compAction(){
		int[] coordinate = new int[2];
			if(shipDestroyed==true){
				coordinate[0] = destroyBordering()[0];
				coordinate[1] = destroyBordering()[1];
				checkBoard(coordinate,BoardGen.getPlayerBoard());
			}else{
				coordinate[0] = createValidCoords()[0];
				coordinate[1] = createValidCoords()[1];
				checkBoard(coordinate,BoardGen.getPlayerBoard());
			}	
	}
	
	private static int[] destroyBordering(){
		int[] coords = new int[2];
		for(int row=0; row<compGuesses.length; row++){
			for(int col=0; col<compGuesses[0].length; col++){
				if(compGuesses[row][col] == 2){
					if(checkForOutOfBound(row-1, col)){
						coords[0] = row-1;
						coords[1] = col;
						return coords;
					}else if(checkForOutOfBound(row+1, col)){
						coords[0] = row+1;
						coords[1] = col;
						return coords;
					}else if(checkForOutOfBound(row, col-1)){
						coords[0] = row;
						coords[1] = col-1;
						return coords;
					}else if(checkForOutOfBound(row, col+1)){
						coords[0] = row;
						coords[1] = col+1;
						return coords;
					}
				}
			}
		}
		coords[0] = createValidCoords()[0];
		coords[1] = createValidCoords()[1];
		shipDestroyed = false;
		return coords;
	}

	private static int[] createValidCoords(){
		int[] coords = new int[2];
		int randomOne = (int)(Math.random()*10);
		int randomTwo = (int)(Math.random()*10);
		while(BoardGen.getPlayerBoard()[randomOne][randomTwo]==2 || BoardGen.getPlayerBoard()[randomOne][randomTwo]==1){
			randomOne = (int)(Math.random()*10);
			randomTwo = (int)(Math.random()*10);
		}
		coords[0] = randomOne;
		coords[1] = randomTwo;
		return coords;
	}

	private static boolean checkForOutOfBound(int indexOne, int indexTwo){
		if(indexOne >=0 && indexOne<compGuesses[0].length && indexTwo >=0 && indexTwo<compGuesses[0].length && (compGuesses[indexOne][indexTwo] !=2 && compGuesses[indexOne][indexTwo] !=1)){
			return true;
		}
		return false;
	}

	private static int[] getCoordinates(String input) {
		String[] keysAlpha = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
		String[] keysNum = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
		int[] coordinate = new int[2];
		for(int row = 0;row<keysAlpha.length;row++){
			if(input.substring(0,1).toLowerCase().equals(keysAlpha[row]))coordinate[0]=row;
			for(int col = 0;col<keysNum.length;col++){
				if(input.substring(1).equals(keysNum[col]))coordinate[1]=col;
			}
		}
		return coordinate;
	}

	
	public static void interpretActions(String input) {
		if(input.toLowerCase().equals("swords of revealing light")){
			BoardGen.setReveal(true);
			playerHit = false;
		}else if(input.toLowerCase().equals("bomb")){
			for(int row = 0;row<BoardGen.getCompBoard().length;row++){
				for(int col = 0;col<BoardGen.getCompBoard()[0].length;col++){
						if(BoardGen.getCompBoard()[row][col]==3){
							BoardGen.getCompBoard()[row][col] = 2;
						}
					}
				}
			JaviyAhmedRoom.setCheckWin(true);
			playerHit = false;
			compHit = false;
			System.out.println("You wonned. You're now king of games. Unfortunately, the parliament of games holds all the power.");
		}
		else if(input.toLowerCase().equals("seppuku")){
			JaviyAhmedRoom.setCheckWin(true);
			playerHit = false;
			compHit = false;
			System.out.println("You Lose, sorry I lied I don't know my way out.");
			System.out.println("I've been trapped here for 10,000 years YOU ARE SO SCREWED!");
			CaveExplorer.lose = true;
		}
		else{
		while(!isValid(input)){
			CaveExplorer.print("Please enter a valid coordinate.");
			input = CaveExplorer.in.nextLine();
		}
		
		checkBoard(getCoordinates(input),BoardGen.getCompBoard());
		}
	}


	public static boolean isValid(String input) {
		String[] keysAlpha = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
		String[] keysNum = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
		for (String key : keysAlpha) {
			if(input.substring(0,1).toLowerCase().equals(key)||input.toLowerCase().equals("swords of revealing light")){
				for(String keyNum : keysNum){
					if(input.substring(1).equals(keyNum)||input.toLowerCase().equals("swords of revealing light")||input.toLowerCase().equals("bomb")||input.toLowerCase().equals("seppuku")) return true;
				}
			}
		}
		return false;
	}
}
