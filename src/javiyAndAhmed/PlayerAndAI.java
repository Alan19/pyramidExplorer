package javiyAndAhmed;

import java.util.Scanner;

import pyramidExplorer.CaveExplorer;

public class PlayerAndAI extends JaviyAhmedRoom {

	private static boolean playerHit;
	private static boolean compHit;
	private static boolean compConseqHit = false;
	private static int[] coordinates = new int[2];
	private static int counter=0;
	
	
	public PlayerAndAI(String description) {
		super(description);
	}
	
	public static void compAction(){
		int[] coordinate = new int[2];
			if(compConseqHit==true){
				coordinate[0] = runItDown()[0];
				coordinate[1] = runItDown()[1];
				checkBoard(coordinate,BoardGen.playerBoard);
			}else{
				coordinate[0] = createValidCoords()[0];
				coordinate[1] = createValidCoords()[1];
				checkBoard(coordinate,BoardGen.playerBoard);
			}	
	}

	private static int[] createValidCoords(){
		int[] coords = new int[2];
		int randomOne = (int)(Math.random()*10);
		int randomTwo = (int)(Math.random()*10);
		while(BoardGen.playerBoard[randomOne][randomTwo]==2 || BoardGen.playerBoard[randomOne][randomTwo]==1){
			randomOne = (int)(Math.random()*10);
			randomTwo = (int)(Math.random()*10);
		}
		coords[0] = randomOne;
		coords[1] = randomTwo;
		return coords;
	}
	
	private static int[] runItDown() {
		int[] coords = new int[2];
		int randomDirection = (int) (Math.random()*2); //0 for N/S, 1 for E/W
		int randomSubDirection = (int) (Math.random()*2); //0 for N/E, 1 for S/W
		if(randomDirection==0 && checkForOutOfBounds(coordinates[0]-1, coordinates[1]) && checkForOutOfBounds(coordinates[0]+1, coordinates[1])){
			if(randomSubDirection == 0){
				if(checkForOutOfBounds(coordinates[0]-1, coordinates[1])){
					coords[0] = coordinates[0]-1;
					coords[1] = coordinates[1];
					counter++;
				}
				else{
					coords[0] = createValidCoords()[0];
					coords[1] = createValidCoords()[1];
				}
			}
			else{
				if(checkForOutOfBounds(coordinates[0]+1, coordinates[1])){
					coords[0] = coordinates[0]+1;
					coords[1] = coordinates[1];
					counter++;
				}
				else{
					coords[0] = createValidCoords()[0];
					coords[1] = createValidCoords()[1];
				}
			}
		}
		else if(randomDirection==1 && checkForOutOfBounds(coordinates[0], coordinates[1]+1) && checkForOutOfBounds(coordinates[0], coordinates[1]-1)){
			if(randomSubDirection == 1){
				if(checkForOutOfBounds(coordinates[0], coordinates[1]+1)){
					coords[0] = coordinates[0];
					coords[1] = coordinates[1]+1;
					counter++;
				}else{
					coords[0] = createValidCoords()[0];
					coords[1] = createValidCoords()[1];
				}
			}
			else{
				if(checkForOutOfBounds(coordinates[0], coordinates[1]-1)){
					coords[0] = coordinates[0];
					coords[1] = coordinates[1]-1;
					counter++;
				}else{
					coords[0] = createValidCoords()[0];
					coords[1] = createValidCoords()[1];
				}
			}
		}else{
			counter=4;
			coords[0] = createValidCoords()[0];
			coords[1] = createValidCoords()[1];
		}
		return coords;
	}
	
	private static boolean checkForOutOfBounds(int indexOne, int indexTwo){
		if(indexOne >=0 && indexOne<BoardGen.playerBoard[0].length && indexTwo >=0 && indexTwo<BoardGen.playerBoard[0].length && (BoardGen.playerBoard[indexOne][indexTwo] !=2 || BoardGen.playerBoard[indexOne][indexTwo] !=1)){
			return true;
		}
		return false;
	}

		//swords of revealing light
	private static int[] getCoordinates(String input) {
		String[] keysAlpha = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
		String[] keysNum = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		int[] coordinate = new int[2];
		for(int row = 0;row<keysAlpha.length;row++){
			if(input.substring(0,1).toLowerCase().equals(keysAlpha[row]))coordinate[0]=row;
			for(int col = 0;col<keysNum.length;col++){
				if(input.substring(1).equals(keysNum[col]))coordinate[1]=col;
			}
		}
		return coordinate;
	}
	
	private static void checkBoard(int[] coordinateChecker,int[][] intArray) {
		if(intArray[coordinateChecker[0]][coordinateChecker[1]]==1 || intArray[coordinateChecker[0]][coordinateChecker[1]]==2){
			System.out.println("You have already used this coordinate please select a new one.");
		}else if(intArray[coordinateChecker[0]][coordinateChecker[1]]==3){
			intArray[coordinateChecker[0]][coordinateChecker[1]]=2;
			System.out.println("Hit");
			if(intArray.equals(BoardGen.playerBoard)){
				compConseqHit = true;
				coordinates[0] = coordinateChecker[0];
				coordinates[1] = coordinateChecker[1];
			}
		}else{
			intArray[coordinateChecker[0]][coordinateChecker[1]]=1;
			System.out.println("Missed");
			if(intArray.equals(BoardGen.playerBoard)){
				compHit = false;
				if(counter==4){
					compConseqHit = false;
					counter = 0;
				}
			}else{
				playerHit = false;
			}
		}
	}
	
	public static void playGame() {
		compHit = true;
		playerHit = true;
		
		//CHECK ANYTHING FROM HERE DOWN
		while(playerHit){
			in = new Scanner(System.in);
			String input = in.nextLine();
			interpretActions(input);
			BoardGen.updateBoards(BoardGen.compBoard,BoardGen.compBoardString);
			CheckWin(BoardGen.compBoard);
			System.out.println("Computer's Board");
			print(BoardGen.compBoardString);
		}
		while(compHit){
			compAction();
			BoardGen.updateBoards(BoardGen.playerBoard,BoardGen.playerBoardString);
			CheckWin(BoardGen.playerBoard);
			System.out.println("PLayer's Board");
			print(BoardGen.playerBoardString);
		}
		
	}
	
	public static void interpretActions(String input) {
		if(input.toLowerCase().equals("swords of revealing light")){
			BoardGen.reveal = true;
			playerHit = false;
		}else if(input.toLowerCase().equals("bomb")){
			for(int row = 0;row<BoardGen.compBoard.length;row++){
				for(int col = 0;col<BoardGen.compBoard[0].length;col++){
						if(BoardGen.compBoard[row][col]==3){
							BoardGen.compBoard[row][col] = 2;
						}
					}
				}
		}else{
		while(!isValid(input)){
			CaveExplorer.print("Please enter a valid coordinate.");
			input = CaveExplorer.in.nextLine();
		}
		
		checkBoard(getCoordinates(input),BoardGen.compBoard);
		}
	}

	
	public static boolean isValid(String input) {
		String[] keysAlpha = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
		String[] keysNum = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		for (String key : keysAlpha) {
			if(input.substring(0,1).toLowerCase().equals(key)||input.toLowerCase().equals("swords of revealing light")){
				for(String keyNum : keysNum){
					if(input.substring(1).equals(keyNum)||input.toLowerCase().equals("swords of revealing light")||input.toLowerCase().equals("bomb")) return true;
				}
			}
		}
		return false;
	}
}
