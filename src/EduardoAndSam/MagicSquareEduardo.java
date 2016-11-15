package EduardoAndSam;

public class MagicSquareEduardo {

	boolean[][] unchangeables = new boolean[3][3];
	int[][] magicSquare = new int[3][3];
	
	public MagicSquareEduardo() {
		generateDistictItems
	}
	
	public static int[] generateDistinctItemsList(int n){
        /**
         * This method needs to generate an int[] of length n that contains distinct, random integers
         * between 1 and 2n 
         * The method will be tested by verifying that the array you return is n items long,
         * contains only entries between 1 and 2n (inclusive) and has no duplicates
         * 
         * */
        int[] itemList = new int[n];
		boolean[] repeatedNumbers = new boolean [2*n];
		double varMod = (2*n);
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
