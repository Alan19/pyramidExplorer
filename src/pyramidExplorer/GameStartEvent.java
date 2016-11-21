package pyramidExplorer;

public class GameStartEvent implements Playable {

	public static final String[] SEQUENCE_1 = {
		"<You wake up find yourself in a place you've never seen.",
		"You find a map next to you.",
		"It seems that you are in a pyramid and there is a way out not too far away from you!>"
	};
	public GameStartEvent() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void play() {
		readSequence(SEQUENCE_1);
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
