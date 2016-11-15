package EduardoAndSam;

import pyramidExplorer.CaveExplorer;

public class EventEduardoAndSam {

	private static final String[] SEQUENCE_1 = {"Suddenly, all the doors slam shut.", "The closest door seems to have some sort of strange mark on it.", "On further inspection, you see that the mark is actually an half-filled grid of numbers", "As you lean in, you realize that there's a faint bit of text underneath the puzzle:", "'To leave this chamber, prove your mastery of the ancient art of magic (squares)!'"}; 
	private static final String[] SEQUENCE_2 = {"As you scratch the last number into the square, the doors grind open.", "'Well, well, well' you hear, as you let out a sigh of relief.", "This should prove most... interesting", "You continue through the tunnels."};
	
	
	public EventEduardoAndSam() {
		// TODO Auto-generated constructor stub
	}
	
	public void play() {
		readSequence(SEQUENCE_1);
		MagicSquareEduardo magicSquare = new MagicSquareEduardo();
		SolveMagicSquareSam.check(magicSquare);
		readSequence(SEQUENCE_2);
		CaveExplorer.inventory.setHasMap(true);
	}
	
	public static void readSequence(String[] seq){
		for(String s : seq){
			CaveExplorer.print(s);
			CaveExplorer.print("- - - press enter - - -");
			CaveExplorer.in.nextLine();
		}
	}
}
