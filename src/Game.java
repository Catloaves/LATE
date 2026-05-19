import java.util.Map;

import Rooms.Room;
import Rooms.RoomLoader;

public class Game {
    private Map<String, Room> rooms;
    private Player player;

    public Game() {
        RoomLoader loader = new RoomLoader();
        rooms = loader.loadRooms("rooms.json");
        player = new Player("home");
    }

    public String processCommand(String input) {
        return CommandParser.parse(input, player, rooms);
    }

    public Player getPlayer() {
        return player;
    }

    public Room getCurrentRoom() {
        return rooms.get(player.getCurrentRoomId());
    }
}