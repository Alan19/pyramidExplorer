package pyramidExplorer;

public class ZhenJosephRoom extends CaveRoomPd8 {

	public ZhenJosephRoom(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}

	public void enter(){
		super.enter();
		System.out.println("You are trapped in this room! \nYou see that the floor might crumble if you step on certain tiles.\nIn order to know where to step, throw these rocks on the tiles to see where the floor can collaspe.");
		
	}
}
