package zhenAndJoseph;

public class JosephMinefieldProccessing {
	
	static void updateTiles(int row, int col, String[][] tileValues, boolean[][] mines, boolean[][] revealedTiles) {
//		System.out.println(tileValues[row][col]);
		if(tileValues[row][col].equals("0")){
			for (int i = row-1; i < row+2; i++) {
				for (int j = col-1; j < col+2; j++) {
					if(ZhenMinefieldUtilities.isValidTile(mines, i, j) && tileValues[i][j].equals("0") && !revealedTiles[i][j]){
						revealedTiles[i][j] = true;
						updateTiles(i, j, tileValues, mines, revealedTiles);
//						printGrid();
					}
					else if (ZhenMinefieldUtilities.isValidTile(mines, i, j) /*&& !mines[i][j]*/){						
						revealedTiles[i][j] = true;
					}
					
				}
			}			
		} 
		else{
				revealedTiles[row][col] = true;
		}
	}
	
	
}
