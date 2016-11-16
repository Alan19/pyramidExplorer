package EduardoAndSam;

import java.util.Scanner;

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
		EventEduardoAndSam.magicSquare[row-1][col-1] = input;
	}

}
