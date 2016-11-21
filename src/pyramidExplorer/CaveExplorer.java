package pyramidExplorer;

import java.util.Scanner;

import EduardoAndSam.EventEduardoAndSam;
import zhenAndJoseph.ZhenJosephRoom;

public class CaveExplorer {
	public static CaveRoomPd8[][] caves;
	public static Scanner in;
	public static CaveRoomPd8 currentRoom;
	public static InventoryNockles inventory;
	

	public static void main(String[] args) {
		in = new Scanner(System.in);
		caves = new CaveRoomPd8[5][5];
		for (int row = 0; row < caves.length; row++) {
			for (int col = 0; col < caves[0].length; col++) {
				caves[row][col] = new CaveRoomPd8("This room has coordinates " + row + ", " + col);
			}
		}
		caves[1][3] = new EventRoom("This is where you found the map!", new GameStartEvent());
		caves[1][4] = new EventRoom("You see a room with many tiles.", new ZhenJosephRoom(null));
		caves[3][2] = new EventRoom("You see a room with many tiles.", new ZhenJosephRoom(null));
//		caves[3][4] = new EventRoom("This is the room you had to prove your mastery of math!", new EventEduardoAndSam());
		caves[1][2].setConnection(CaveRoomPd8.WEST, caves[1][1], new Door());
		caves[1][2].setConnection(CaveRoomPd8.SOUTH, caves[2][2], new Door());
		caves[1][2].setConnection(CaveRoomPd8.EAST, caves[1][3], new Door());
		caves[1][4].setConnection(CaveRoomPd8.WEST, caves[1][3], new Door());
		caves[2][3].setConnection(CaveRoomPd8.NORTH, caves[1][3], new Door());
		caves[3][3].setConnection(CaveRoomPd8.NORTH, caves[2][3], new Door());
		caves[3][2].setConnection(CaveRoomPd8.EAST, caves[3][3], new Door());
		inventory = new InventoryNockles();
		currentRoom = caves[1][3];
		currentRoom.enter();
		startExploring();
	}


	private static void startExploring() {
		while (true) {
			print(inventory.getDescription());
			print(currentRoom.getDescription());
			print("What would you like to do?");
			String input = in.nextLine();
			act(input);
		}
		
	}
	private static void act(String input) {
		currentRoom.interpretAction(input);
	}


	public static void print(String text) {
		System.out.println(text);
	}
	
}
