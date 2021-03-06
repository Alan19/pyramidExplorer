package EduardoAndSam;

import pyramidExplorer.CaveExplorer;
import pyramidExplorer.CaveRoomPd8;
import pyramidExplorer.Playable;

import java.util.Scanner;

public class EventEduardoAndSam extends CaveRoomPd8 implements Playable {

	public EventEduardoAndSam(String description) {
		super(description);
	}

	private static final String[] SEQUENCE_1 = {"Suddenly, all the doors slam shut.", "The closest door seems to have some sort of strange mark on it.", "On further inspection, you see that the mark is actually an half-filled grid of numbers", "As you lean in, you realize that there's a faint bit of text underneath the puzzle:", "'To leave this chamber, prove your mastery of the ancient art of magic (squares)! [Hint: Always start with a six or a seven next to each other in the top row!]'"}; 
	private static final String[] SEQUENCE_2 = {"As you scratch the last number into the square, the doors grind open.", "'Well, well, well' you hear, as you let out a sigh of relief.", "This should prove most... interesting", "You continue through the tunnels."};
	private static Scanner in = new Scanner(System.in);
	public static int[][] magicSquare;
	
	public void play() {
		readSequence(SEQUENCE_1);
		magicSquare = MagicSquareEduardo.CreateMagicSquareEduardo();
		printSquare(magicSquare);
		while(!SolveMagicSquareSam.check(magicSquare)){
			if(checkLoss(magicSquare) == false){
				System.out.println("You seem to be having some issues...");
			}
			askForInput();
			printSquare(magicSquare);
			
		}
		readSequence(SEQUENCE_2);
	}
	
	//
	private static void printSquare(int[][] magicSquare) {
		String[][] toPrint = new String[8][8];
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
		for(int i = 0; i < seq.length; i++){
			CaveExplorer.print(seq[i]);
			CaveExplorer.print("- - - press enter - - -");
			in.nextLine();
		}
	}

	public static void askForInput() {
		System.out.println("What row do you want to put your answer in?");
		String row = in.nextLine();
		System.out.println("What column do you want to put your answer in?");
		String column = in.nextLine();
		System.out.println("What number do you want to put there?");
		String input = in.nextLine();
		SolveMagicSquareSam.interpretInput(input, column, row);
	}
	public boolean checkLoss(int[][] magicSquare){
		int squaresFilled = 0;
		for(int i = 0; i < magicSquare.length; i++){
			for(int j = 0; j < magicSquare[i].length; j++){
				if (magicSquare[i][j] != 0){
					squaresFilled++;
				}
			}
		}
		if(squaresFilled == 16){
			return false;
		}
		return true;
	}
	
}
