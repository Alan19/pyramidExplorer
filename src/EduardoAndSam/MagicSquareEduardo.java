package EduardoAndSam;

public class MagicSquareEduardo {

	private static boolean[][] unchangeables = new boolean[3][3];
	private static int[][] magicSquare = new int[3][3];
	private static int[][] magicSquareUn = new int[3][3];
	
	public static int[][] CreateMagicSquareEduardo() {
	
		int[] distinctRow = makeRandomDistictIntList(3, 3);
		int[] distinctCol = makeRandomDistictIntList(3, 3);

		magicSquare[0][0] = (2);
		magicSquare[0][1] = (7);
		magicSquare[0][2] = (6);
		magicSquare[1][0] = (9);
		magicSquare[1][1] = (5);
		magicSquare[1][2] = (1);
		magicSquare[2][0] = (4);
		magicSquare[2][1] = (3);
		magicSquare[2][2] = (8);
		for(int i = 0; i < 3; i++){
			magicSquareUn[distinctRow[i]-1][distinctCol[i]-1] = magicSquare[distinctRow[i]-1][distinctCol[i]-1];
			unchangeables[distinctRow[i]-1][distinctCol[i]-1] = true;
		}
		int extraRow = (int)(Math.random()*3+1);
		int extraCol = (int)(Math.random()*3+1);
		return magicSquareUn;
	}
	
	public static int[][] giveCompleteSquare(){
		return magicSquare;
	}
	
	public int[][] giveUserSquare(){
		return magicSquareUn;
	}
	
	public static boolean[][] giveUnchangeables(){
		return unchangeables;
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
	
	public static int[] makeRandomDistictIntList(int length, int max){
		int[] array = new int[length];
		for(int i = 0; i < array.length; i++){
			int rand = (int)(Math.random()*max+1);
			while(itemisNotInList(array,rand)){
				rand = (int)(Math.random()*max+1);
			}
			array[i] = rand;
		}
		return array;
	}

	private static boolean itemisNotInList(int[] array, int rand) {
		for(int i = 0; i < array.length; i++){
			if(array[i] == rand)
				return false;
		}
		return true;
	}
}
