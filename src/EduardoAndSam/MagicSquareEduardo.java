package EduardoAndSam;

import java.util.Arrays;

public class MagicSquareEduardo {

	private static boolean[][] unchangeables = new boolean[4][4];
	private static int[][] magicSquare = new int[4][4];
	private static int[][] magicSquareUn = new int[4][4];
	private static boolean[] 16To1Used = new boolean[16];
	
	
	public static int[][] CreateMagicSquareEduardo() {
	
		int[] distinctRow = generateDistinctItemsList(4);
		int[] distinctCol = generateDistinctItemsList(4);

		magicSquare[0][0] = 2;
		magicSquare[0][1] = 7;
		magicSquare[0][2] = 6;
		magicSquare[1][0] = 9;
		magicSquare[1][1] = 5;
		magicSquare[1][2] = 1;
		magicSquare[2][0] = 4;
		magicSquare[2][1] = 3;
		magicSquare[2][2] = 8;
		
//		int numToUse = (int)(16*Math.random()+1);
//		for(int row = 0; row < magicSquare.length; row++){
//			for(int col = 0; col < magicSquare[row].length; col++){
//				int potentialRowSum = arraySum(magicSquare[row])+numToUse;
//				while(!16To1Used[numToUse-1] || potentialRowSum > 34 || potentialRowSum == 34 && col != magicSquare[row].length-1){
//					numToUse = (int)(16*Math.random()+1);
//				}
//				magicSquare[row][col] == numToUse;
//			}
//		}
		
		for(int i = 0; i < 4; i++){
			magicSquareUn[distinctRow[i]-1][distinctCol[i]-1] = magicSquare[distinctRow[i]-1][distinctCol[i]-1];
			unchangeables[distinctRow[i]-1][distinctCol[i]-1] = true;
		}
		return magicSquareUn;
	}
	
//	public static int arraySum(int[] array){
//		if(array.length = 0){
//			return array[1];
//		}
//		return array[array.length-1]+arraySum(Arrays.copyOfRange(array, 0, array.length-1));
//	}
	
	public static int[][] giveCompleteSquare(){
		return magicSquare;
	}
	
	public int[][] giveUserSquare(){
		return magicSquareUn;
	}
	
	public static boolean[][] giveUnchangeables(){
		return unchangeables;
	}
	
	public static int[] generateDistinctItemsList(int arrayLength){
        int[] itemList = new int[arrayLength];
		boolean[] repeatedNumbers = new boolean [arrayLength];
		double varMod = (arrayLength);
		int rand = (int)(varMod*Math.random()+1); 
		for(int i = 0; i < itemList.length; i++){
			while(repeatedNumbers[rand-1] == true){
				rand = (int)(varMod*Math.random()+1);
			}
			repeatedNumbers[rand-1] = true;
			itemList[i] = rand;
		}
        return itemList; 
    }
	
}
