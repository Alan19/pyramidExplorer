package EduardoAndSam;

import pyramidExplorer.CaveExplorer;

public class SolveMagicSquareSam {

	public static boolean check(int[][] magicSquare) {
		for (int i = 0; i < magicSquare.length; i++){
			int totalNum = 0;
			for (int j = 0; j < magicSquare[0].length; j++){
				totalNum = totalNum + magicSquare[i][j];
			}
			if (totalNum != 15){
				return false;
			}
		}
		for (int i = 0; i < magicSquare[0].length; i++){
			int totalNum = 0;
			for (int j = 0; j < magicSquare.length; j++){
				totalNum = totalNum + magicSquare[i][j];
			}
			if (totalNum != 15){
				return false;
			}
		}
		return true;
	}

	public static void interpretInput(String in,String column, String row1) {
		int input = Integer.parseInt(in);
		int col = Integer.parseInt(column);
		int row = Integer.parseInt(row1);
		boolean[][] unch = MagicSquareEduardo.giveUnchangeables();
		if(unch[row-1][col-1]){
			CaveExplorer.print("You feel as though you should leave the numbers that are still there...");
		}else{
			EventEduardoAndSam.magicSquare[row-1][col-1] = input;
		} 
	}

}
