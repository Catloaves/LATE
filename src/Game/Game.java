package Game;

import java.util.Map;

import Mobs.HostileMob;
import Rooms.Room;
import Rooms.RoomLoader;

public class Game {
    private Map<String, Room> rooms;
    private Player player;
    private CommandParser commandParser;
    private AdventureGUI gui;

    public Game() {
        RoomLoader loader = new RoomLoader();
        rooms = loader.loadRooms("src/Rooms/rooms.json"); 
        
        if (rooms == null || rooms.isEmpty()) {
            rooms = loader.loadRooms("Rooms/rooms.json");
        }

        player = new Player("home", null, null, null, null);
        commandParser = new CommandParser();
    }

    public void start() {
        System.out.println("Game engine initialized successfully.");
    }

    public String getCurrentRoomDescription() {
        Room currentRoom = rooms.get(player.getCurrentRoomId());
        if (currentRoom != null) {
            return currentRoom.getLongDescription();
        }
        return "You are in an unknown empty space.";
    }

    public String processCommand(String input, HostileMob target) {
        return commandParser.parse(gui, input, player, rooms, target);
    }

    public Player getPlayer() {
        return player;
    }

    public Room getCurrentRoom() {
        return rooms.get(player.getCurrentRoomId());
    }

    public void setGUI(AdventureGUI gui){
        this.gui = gui;
    }
}