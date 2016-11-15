package EduardoAndSam;

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
}
