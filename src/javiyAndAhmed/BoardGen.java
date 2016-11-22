package javiyAndAhmed;

public class BoardGen extends JaviyAhmedRoom {

	private static int[][] playerBoard;
	private static int[][] compBoard;
	private static String[][] playerBoardString;
	private static String[][] compBoardString;
	private static int[] directions = {1,2}; //1 for NORTH/SOUTH and 2 for EAST/WEST
	private static int[] boatArray = {1,2,3,4,5};
	private static boolean reveal = false;
	
	public static int[][] getPlayerBoard() {
		return playerBoard;
	}

	public static void setPlayerBoard(int[][] playerBoard) {
		BoardGen.playerBoard = playerBoard;
	}

	public static int[][] getCompBoard() {
		return compBoard;
	}

	public static void setCompBoard(int[][] compBoard) {
		BoardGen.compBoard = compBoard;
	}

	public static String[][] getPlayerBoardString() {
		return playerBoardString;
	}

	public static void setPlayerBoardString(String[][] playerBoardString) {
		BoardGen.playerBoardString = playerBoardString;
	}

	public static String[][] getCompBoardString() {
		return compBoardString;
	}

	public static void setCompBoardString(String[][] compBoardString) {
		BoardGen.compBoardString = compBoardString;
	}

	public static int[] getDirections() {
		return directions;
	}

	public static void setDirections(int[] directions) {
		BoardGen.directions = directions;
	}

	public static int[] getBoatArray() {
		return boatArray;
	}

	public static void setBoatArray(int[] boatArray) {
		BoardGen.boatArray = boatArray;
	}

	public static boolean isReveal() {
		return reveal;
	}

	public static void setReveal(boolean reveal) {
		BoardGen.reveal = reveal;
	}

	public BoardGen(String description) {
		super(description);
		
	}
	
	public static void buildBoards(){
		playerBoard = new int[10][10];
		compBoard = new int[10][10];
		
		playerBoardString = new String[10][10];
		compBoardString = new String[10][10];
		
		//For player. Initializes the board w/ boats
		setPlayerBoard();
		setCompBoard();
		updateBoards(playerBoard,playerBoardString);
		updateBoards(compBoard,compBoardString);
	}

	private static void setCompBoard() {
		for(int i=0;i<5;i++){
			int randomDirection = directions[(int)(Math.random()*directions.length)];
			putInRandomDirection(randomDirection,boatArray[i],compBoard);
		}
	}
	
	
	private static void setPlayerBoard() {
		for(int i=0;i<5;i++){
			int randomDirection = directions[(int)(Math.random()*directions.length)];
			putInRandomDirection(randomDirection,boatArray[i],playerBoard);
		}
	}
	
	//0 means nothing 1 means miss 2 means hit and 3 means theres a boat there
	public static void updateBoards(int[][] intArray,String[][] stringArray) {
		for(int row = 0;row<stringArray.length;row++){
			for(int col = 0;col<stringArray[0].length;col++){
				if(intArray[row][col] == 0 || intArray[row][col] == 4){
					stringArray[row][col] = "[ ]";
				}else if(intArray[row][col] == 1){
					stringArray[row][col] = "[O]";
				}else if(intArray[row][col] == 2){
					stringArray[row][col] = "[X]";
				}else if(intArray[row][col] == 3){
					if(stringArray.equals(playerBoardString)){
						stringArray[row][col] = "[S]";
					}else if(reveal==true){
						stringArray[row][col] = "[S]";
					}
					else{
						stringArray[row][col] = "[ ]";
					}
				}
			}
		}
	}
	
	//This method with stop the for loop if the piece goes off board or hits another piece
		//Use two if conditions and a while loop to do this
		private static void putInRandomDirection(int randomDirection, int boatLength, int[][] intArray) {
			int randomOne = 0; 
			int randomTwo = 0;
			while(true){
				randomOne = (int)(Math.random()*10);
				randomTwo = (int)(Math.random()*10);
				if(intArray[randomOne][randomTwo] < 3){
					break;
				}
			}
			if(randomDirection==1){
				int boatLengthVar = boatLength;
				int directionChangeIndex = 1;
				int i=0;
				while(boatLengthVar>0){
					if(randomOne-i>=0 && randomOne-i<intArray[0].length && intArray[randomOne-i][randomTwo]<3){
						intArray[randomOne-i][randomTwo] = 3;
						boatLengthVar--;
						i++;
					}else if(randomOne+directionChangeIndex>0 && randomOne+directionChangeIndex<intArray[0].length && intArray[randomOne+directionChangeIndex][randomTwo]<3){
						intArray[randomOne+directionChangeIndex][randomTwo] = 3;
						directionChangeIndex++;
						boatLengthVar--;
					}else{
						boatLengthVar--;
					}
				}
			}
			if(randomDirection==2){
				int boatLengthVar = boatLength;
				int directionChangeIndex = 1;
				int i=0;
				while(boatLengthVar>0){
					if(randomTwo-i>=0 && randomTwo-i<intArray[0].length && intArray[randomOne][randomTwo-i]<3){
						intArray[randomOne][randomTwo-i] = 3;
						boatLengthVar--;
						i++;
					}else if(randomTwo+directionChangeIndex>0 && randomTwo+directionChangeIndex<intArray[0].length && intArray[randomOne][randomTwo+directionChangeIndex]<3){
						intArray[randomOne][randomTwo+directionChangeIndex] = 3;
						directionChangeIndex++;
						boatLengthVar--;
					}else{
						boatLengthVar--;
					}
				}
			}
			restrictAdjacentPanels(intArray);
		}
		
		private static void restrictAdjacentPanels(int[][] intArray){
			for(int row=0; row<intArray.length; row++){
				for(int col=0; col<intArray[row].length; col++){
					if(intArray[row][col] == 3){
						for(int adjRow=row-1; adjRow<row+2; adjRow++){
							for(int adjCol=col-1; adjCol<col+2; adjCol++){
								if(adjRow >= 0 && adjCol >= 0 && adjRow < intArray.length && adjCol < intArray[0].length && intArray[adjRow][adjCol] <3){
									intArray[adjRow][adjCol] = 4;
								}
							}
						}
					}
				}
			}
		}
}
