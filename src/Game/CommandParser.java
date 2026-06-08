package Game;

import java.util.Map;
import Mobs.Fights;
import Mobs.HostileMob;
import Rooms.Room;

public class CommandParser {

    public String parse(AdventureGUI gui, String input, Player player, Map<String, Room> rooms, HostileMob target,
            Game game) {
        String[] words = input.trim().toLowerCase().split("\\s+");
        if (words.length == 0 || words[0].isEmpty())
            return "Please enter a command.";

        String command = words[0];
        String resultMessage = "";

        if (game.isFightActive() && (command.equals("go") || command.equals("shop"))) {
            return "You cannot do that right now! You are in the middle of a battle!";
        }

        switch (command) {
            case "attack":
            case "fight":
                if (game.isFightActive() && game.getFights() != null) {
                    game.getFights().runTurn();
                } else {
                    resultMessage = "Calm down! There is nothing here to fight.";
                }
                break;

            case "go":
                if (words.length < 2) {
                    resultMessage = "Go where?";
                } else {
                    String direction = words[1];
                    Room currentRoom = rooms.get(player.getCurrentRoomId());
                    String nextRoomId = currentRoom.getExits().get(direction);

                    if (nextRoomId != null) {
                        Room nextRoom = rooms.get(nextRoomId);
                        if (nextRoom.isLocked() && !player.hasItem("key")) {
                            resultMessage = "The door is locked. You need a key.";
                        } else if (nextRoom.isDark() && !player.hasItem("torch")) {
                            resultMessage = "It's too dark to enter! You need a light source.";
                        } else {
                            player.setCurrentRoomId(nextRoomId);
                            gui.printText("You move " + direction + ".");
                            resultMessage = nextRoom.getLongDescription() + "\nExits: " + nextRoom.getExitsString()
                                    + ".";

                            if (nextRoom.getMob() != null && !nextRoom.getMob().isDefeated) {
                                Fights fight = new Fights(player, nextRoom.getMob(), gui, game);
                                game.setFight(fight);
                                fight.startCombatMessage();
                            }
                        }
                    } else {
                        resultMessage = "Sorry. You can't go that way!";
                    }
                }
                break;

            case "look":
                Room currentRoom = rooms.get(player.getCurrentRoomId());
                resultMessage = currentRoom.getLongDescription() + "\nExits: " + currentRoom.getExitsString() + ".";
                break;
        }
        return resultMessage;
    }
}