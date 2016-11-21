package javiyAndAhmed;

import java.util.Scanner;

import pyramidExplorer.CaveExplorer;
import pyramidExplorer.CaveRoomPd8;
import pyramidExplorer.Playable;

public class JaviyAhmedRoom extends CaveRoomPd8 implements Playable{
	
	public static final String[] SEQUENCE_1 = {
			"<A silhouette of a person passes by you in this pyramid ",
			"there's an ominous feeling that went down your spine.>",
			"A deep voice speaks to you ",
			"You're not around here please follow the shadows", 
			"It will lead to your freedom."
		};
	public static final String[] SEQUENCE_2 = {
			"The silhouette disappears into the wall and NOW ALL THE DOORS SHUT DOWN ON YOU",
			"WHO ARE YOU, why are you here?.",
			"I am the King of Games everyone calls me The Pharaoh.",
			"Please brave challenger duel and deafeat my enemy in the Shadow Realm",
			"Will you please duel in my place?",
			"If you win, I will show you the way out."
	};
		public static final String[] SEQUENCE_3 = {
				"You are in a foggy enviroment ",
				"All your monsters must suvive and you must destory all of theirs",
				"Please pick coordinates and win this duel!"
		};

	public static boolean checkWin = false;
	public static Scanner in;	

	public JaviyAhmedRoom(String description) {
		super(description);
	}
	
	public void play() {
		readSequence(SEQUENCE_1);
		System.out.println("Would you like to follow the silhouette?");
		while(CaveExplorer.in.nextLine().toLowerCase().indexOf("yes") < 0){
			CaveExplorer.print("IF YOU DON'T FOLLOW HIM YOU MAY BE TRAPPED IN THIS MAZE FOREVER");
		}
		readSequence(SEQUENCE_2);
		System.out.println("Come on! Tell me you like to duel.");
		while(CaveExplorer.in.nextLine().toLowerCase().indexOf("yes") < 0){
			CaveExplorer.print("C'mon! You know you should duel! Say yes!");
		}
		readSequence(SEQUENCE_3);
		BoardGen.buildBoards();
		print(BoardGen.playerBoardString);
		System.out.println();
		print(BoardGen.compBoardString);
		
		while(!checkWin){
			PlayerAndAI.playGame();	
		}
	}
	
	public static void CheckWin(int[][] intArray) {
		int amountOfShips = 0;
		for(int row = 0;row<intArray.length;row++){
			for(int col = 0;col<intArray[0].length;col++){
				if(intArray[row][col] == 3){
					amountOfShips++;
				}
			}
		}
		if(amountOfShips==0){
			checkWin = true;
			if(intArray.equals(BoardGen.compBoard)){
				System.out.println("You Won, sorry I lied I don't know my way out.");
				System.out.println("I've been trapped here for 10,000 years YOU ARE SO SCREWED!");
			}else{
				System.out.println("You LOST, now you will never leave! Be banished to the Shodow Realm");
				CaveExplorer.lose = true;
			}
		}
	}

	public void enter(){
		super.enter();
		System.out.println(" ");
	}

	public static void print(Object[][] arr){
		for(Object[] row:arr){
			for(Object col:row){
				System.out.print(col);
			}
			System.out.println();
		}
	}
	
	public static void readSequence(String[] seq) {
		for(String s : seq){
			CaveExplorer.print(s);
			
		}
		CaveExplorer.print("--- press enter ---");
		
	}
}