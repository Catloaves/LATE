package Game;

import java.util.Map;

import Mobs.HostileMob;
import Rooms.Room;
import Rooms.RoomLoader;
import Mobs.Fights;

public class Game {
    private Map<String, Room> rooms;
    private Player player;
    private CommandParser commandParser;
    private AdventureGUI gui;
    private boolean fightActive;
    private Fights fight;
    private boolean isWaiting = false;

    public Game() {
        RoomLoader loader = new RoomLoader();
        rooms = loader.loadRooms("src/Rooms/rooms.json");

        if (rooms == null || rooms.isEmpty()) {
            rooms = loader.loadRooms("Rooms/rooms.json");
        }

        player = new Player("home", null, null, null, null);
        commandParser = new CommandParser();
    }

    public boolean isFightActive() {
        return fightActive;
    }

    public void setFight(Fights fight) {
        this.fight = fight;
        fightActive = true;
    }

    public void endFight() {
        fightActive = false;
    }

    public Fights getFights() {
        return fight;
    }

    public boolean getIsWaiting() {
        return isWaiting;
    }

    public void setIsWaiting(boolean isWaiting) {
        this.isWaiting = isWaiting;
    }

    public void start() {
        System.out.println("Game engine initialized successfully.");
        gui.printText("Your family has fallen gravely ill with a mysterious sickness. " +
                "Your only hope is to reach the King's Castle and find the legendary cure " +
                "capable of healing any disease in the world.\n\n" +
                "After a long journey, you arrive at your destination...\n");
    }

    public String getCurrentRoomDescription() {
        Room currentRoom = rooms.get(player.getCurrentRoomId());
        if (currentRoom != null) {
            return currentRoom.getLongDescription();
        }
        return "You are in an unknown empty space.";
    }

    public String processCommand(String input, HostileMob target) {
        if (isWaiting) {
            isWaiting = false;
            return commandParser.selectItem(player, target, input);
        }
        return commandParser.parse(gui, input, player, rooms, target, this);
    }

    public Player getPlayer() {
        return player;
    }

    public Room getCurrentRoom() {
        return rooms.get(player.getCurrentRoomId());
    }

    public void setGUI(AdventureGUI gui) {
        this.gui = gui;
    }

    public void revivePlayer() {
        player.getStats().setHp(player.getStats().maxHp);
        player.setCurrentRoomId("home");
    }
}