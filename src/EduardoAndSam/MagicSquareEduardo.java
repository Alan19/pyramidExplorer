package EduardoAndSam;

public class MagicSquareEduardo {

	boolean[][] unchangeables = new boolean[3][3];
	static int[][] magicSquare = new int[3][3];
	
	public static int[][] CreateMagicSquareEduardo() {
		int[] distinctNum = generateDistinctItemsList(4, 9);
		int[] distinctRow = generateDistinctItemsList(3, 3);
		int[] distinctCol = generateDistinctItemsList(3, 3);
		
		for(int i = 0; i < 3; i++){
			magicSquare[distinctRow[i]-1][distinctCol[i]-1] = distinctNum[i];
		}
		return magicSquare;
	}
	
	public static int[] generateDistinctItemsList(int n, int m){
        int[] itemList = new int[n];
		boolean[] repeatedNumbers = new boolean [m];
		double varMod = (m);
		for(int i = 0; i < itemList.length; i++){
			int rand = (int)(varMod*Math.random()+1); 
			while(repeatedNumbers[rand-1] == true){
				rand = (int)(varMod*Math.random()+1);
			}
			repeatedNumbers[rand-1] = true;
			itemList[i] = rand;
		}
        return itemList; 
    }
}
