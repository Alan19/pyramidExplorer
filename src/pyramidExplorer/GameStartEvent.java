package pyramidExplorer;

public class GameStartEvent implements Playable {

	public static final String[] SEQUENCE_1 = {
		"<A little yellow mouse with brown stries and ",
		"a lightning shaped tail runs up to you.>",
		"Hi. I can see ",
		"that you are not from around here!", 
		"Do you like puzzles?"
	};
	public static final String[] SEQUENCE_2 = {
			"You are going to enjoy my 2D puzzle games.",
			"Here is a map of all the games I've prepared."
	};
	public GameStartEvent() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void play() {
		readSequence(SEQUENCE_1);
		System.out.println("Come on! Tell me you like puzzles.");
		while(CaveExplorer.in.nextLine().toLowerCase().indexOf("yes") < 0){
			CaveExplorer.print("C'mon! You know you like puzzles! Say yes!");
		}
		readSequence(SEQUENCE_2);
		CaveExplorer.inventory.setHasMap(true);
	}
	
	public static void readSequence(String[] seq) {
		for(String s : seq){
			CaveExplorer.print(s);
			CaveExplorer.print("--- press enter ---");
			while (true) {
				String input = CaveExplorer.in.nextLine();
				if(input.equals("")) break;
			}
		}
	}

}
