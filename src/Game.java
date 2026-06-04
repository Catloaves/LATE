import java.util.Map;
import java.util.Scanner;

import Rooms.Room;
import Rooms.RoomLoader;

public class Game {
    private Map<String, Room> rooms;
    private Player player;
    private CommandParser commandParser;

    public Game() {
        RoomLoader loader = new RoomLoader();
        rooms = loader.loadRooms("rooms.json");
        player = new Player("home", null, null, null, null);
        commandParser = new CommandParser();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Text Adventure Game!");
        Room currentRoom = rooms.get(player.getCurrentRoomId());
        System.out.println(currentRoom.getLongDescription());

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            commandParser.parse(input, player, rooms);
        }
    }

    public String getCurrentRoomDescription() {
        Room currentRoom = rooms.get(player.getCurrentRoomId());
        if (currentRoom != null) {
            return currentRoom.getLongDescription();
        }
        return "You are in an unknown empty space.";
    }

    public String processCommand(String input) {
        return commandParser.parse(input, player, rooms);
    }

    public Player getPlayer() {
        return player;
    }

    public Room getCurrentRoom() {
        return rooms.get(player.getCurrentRoomId());
    }
}