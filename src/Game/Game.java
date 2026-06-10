package Game;

import java.io.*;
import java.util.Map;
import Mobs.HostileMob;
import Mobs.PetAlligator;
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
    private PetAlligator gator;
    private Object currentRoomId;

    public Game() {
        RoomLoader loader = new RoomLoader();
        rooms = loader.loadRooms("src/Rooms/rooms.json");

        if (rooms == null || rooms.isEmpty()) {
            rooms = loader.loadRooms("src/Rooms/rooms.json");
        }

        if (rooms != null) {
            if (rooms.containsKey("dungeon"))
                rooms.get("dungeon").setDark(true);
            if (rooms.containsKey("vault"))
                rooms.get("vault").setLocked(true);
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
        gui.endScreen();
        System.exit(0);

    }

    public Fights getFights() {
        return fight;
    }

    public void start() {
        System.out.println("Game engine initialized successfully.");
        if (gui != null) {
            gui.printText(
                    "Welcome!\n\n In this town, there is one special member. Not a human, but an alligator. And a very special alligator indeed - for he is kind and clear-minded; sapient-like and worthy. Unlike - cough cough - a certain king... \n\n Nobely, the alligator has partaken in numerous battles and done great deeds for the town. You are his caretaker, for you were the one who rescued him when he was little. Despite all that, the king does not care for him. I mean, does the king care about anyone, really...? But you do. And your alligator does!\n\n");

            // player.setCurrentRoomId("home");
            Room currentRoom = rooms.get("home");
            player.setCurrentRoomId("home");
            gui.printText(currentRoom.getLongDescription() + "\n\nExits: " + currentRoom.getExitsString() + ".");
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
        gator = new PetAlligator(player, gui);
    }

    public void healAlligator() {
        if (gator != null) {
            gator.healAlligator();
        } else {
            System.out.println("Uh oh.. where is your alligator?");
        }
    }

    public void revivePlayer() {
        fightActive = false;
        fight = null;

        if (player != null && player.getStats() != null) {
            player.getStats().setHp(player.getStats().getMaxHp());
        }
        player.setCurrentRoomId("home");
    }

    // public void saveGame() {
    // try {
    // SaveCurrGame data = new SaveCurrGame();
    // data.roomId = player.getCurrentRoomId();
    // data.hp = player.getStats().getHp();
    // data.items = player.getInventory().stream().map(i -> i.getName()).toList();
    // ObjectOutputStream out = new ObjectOutputStream(new
    // FileOutputStream("elixir_of_the_alligator_save.dat"));
    // out.writeObject(data);
    // out.close();
    // // gui.printText("Your current game data has been saved!");
    // } catch (Exception e) {
    // gui.printText("Uh oh! Saving failed - you might want to try again.");
    // }
    // }

    // public void loadGame() {
    // try {
    // ObjectInputStream in = new ObjectInputStream(new
    // FileInputStream("elixir_of_the_alligator_save.dat"));
    // SaveCurrGame saveData = (SaveCurrGame) in.readObject();
    // in.close();

    // player.setCurrentRoomId(saveData.roomId);
    // player.getStats().setHp(saveData.hp);
    // player.getInventory().clear();
    // // for (String itemName : saveData.items) {
    // // }
    // // gui.printText("You have successfully loaded your game!");
    // gui.updateRoomDisplay();

    // } catch (Exception e) {
    // gui.printText("Uh oh! Your game has failed to load - you might want to try
    // again.");
    // }
}
