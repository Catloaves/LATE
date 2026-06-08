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

        if (rooms != null) {
            if (rooms.containsKey("dungeon")) rooms.get("dungeon").setDark(true);
            if (rooms.containsKey("vault")) rooms.get("vault").setLocked(true);
        }

        player = new Player(); 
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

    public void endGame() {
        gui.printText("\n--- THANK YOU FOR PLAYING! ---");
        System.exit(0);
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
        if (gui != null) {
            gui.printText("Your family has fallen gravely ill with a mysterious sickness. " +
                    "Your only hope is to reach the King's Castle and find the legendary cure " +
                    "capable of healing any disease in the world.\n\n" +
                    "After a long journey, you arrive at your destination...\n");
            gui.printText(getCurrentRoomDescription());
        }
    }

    public String getCurrentRoomDescription() {
        if (player != null && rooms != null) {
            Room currentRoom = rooms.get(player.getCurrentRoomId());
            if (currentRoom != null) {
                return currentRoom.getLongDescription();
            }
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
        fightActive = false;
        fight = null;

        if (player != null && player.getStats() != null) {
            player.getStats().setHp(player.getStats().getMaxHp());
        }
        player.setCurrentRoomId("home");
    }
}