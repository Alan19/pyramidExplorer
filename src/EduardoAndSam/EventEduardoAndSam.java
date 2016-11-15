package EduardoAndSam;

import pyramidExplorer.CaveExplorer;
import java.util.Scanner;

public class EventEduardoAndSam {

	private static final String[] SEQUENCE_1 = {"Suddenly, all the doors slam shut.", "The closest door seems to have some sort of strange mark on it.", "On further inspection, you see that the mark is actually an half-filled grid of numbers", "As you lean in, you realize that there's a faint bit of text underneath the puzzle:", "'To leave this chamber, prove your mastery of the ancient art of magic (squares)!'"}; 
	private static final String[] SEQUENCE_2 = {"As you scratch the last number into the square, the doors grind open.", "'Well, well, well' you hear, as you let out a sigh of relief.", "This should prove most... interesting", "You continue through the tunnels."};
	
	
	public static void main(String[] args){
		int[][] magicSquare = MagicSquareEduardo.CreateMagicSquareEduardo();
		boolean squareComplete = SolveMagicSquareSam.check(magicSquare);
		printSquare(magicSquare);
	}
	
	public void play() {
		readSequence(SEQUENCE_1);
		int[][] magicSquare = MagicSquareEduardo.CreateMagicSquareEduardo();
		boolean squareComplete = SolveMagicSquareSam.check(magicSquare);
		printSquare(magicSquare);
		readSequence(SEQUENCE_2);
		CaveExplorer.inventory.setHasMap(true);
	}
	
	//
	private static void printSquare(int[][] magicSquare) {
		String[][] toPrint = new String[7][7];
		for(int i = 0; i < toPrint.length; i++){
			for(int j = 0; j < toPrint[i].length; j++){
				toPrint[i][j] = " ";
			}
		}
		for(int i = 0; i < toPrint.length; i += 2){
			for(int j = 1; j < toPrint[i].length; j +=2){
				toPrint[i][j] = "_";
			}
		}
		for(int i = 1; i < toPrint.length; i ++){
			for(int j = 0; j < toPrint[i].length; j+= 2){
				toPrint[i][j] = "|";
			}
		}
		for(int i = 0; i < magicSquare.length; i++){
			for(int j = 0; j < magicSquare[0].length; j++){
				if(magicSquare[i][j] != 0){
					toPrint[(i*2)+1][(j*2)+1] = ""+magicSquare[i][j];
				}
			}
		}
		for(int i = 0; i < toPrint.length; i++){
			for(int j = 0; j < toPrint[0].length; j++){
				System.out.print("" + toPrint[i][j]);
			}
			System.out.println("");
		}
		
	}

	public static void readSequence(String[] seq){
		for(String s : seq){
			CaveExplorer.print(s);
			CaveExplorer.print("- - - press enter - - -");
			CaveExplorer.in.nextLine();
		}
	}
	
	
}
