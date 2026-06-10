package Game;

import java.util.Map;
import Mobs.Fights;
import Mobs.HostileMob;
import Rooms.Room;
import Items.Item;

public class CommandParser {

    public String parse(AdventureGUI gui, String input, Player player, Map<String, Room> rooms, HostileMob target,
            Game game) {
        String[] words = input.trim().toLowerCase().split("\\s+");
        if (words.length == 0 || words[0].isEmpty())
            return "Please enter a command.";

        String command = words[0];
        String resultMessage = "";

        if (game.isFightActive() && (command.equals("go") || command.equals("shop"))) {
            return "Sorry - you cannot do that right now. You are in the middle of a battle!";
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

                            String roomInfo = nextRoom.getLongDescription() + "\nExits: " + nextRoom.getExitsString()
                                    + ".";

                            if (nextRoom.getMob() != null && !nextRoom.getMob().isDefeated) {
                                Fights fight = new Fights(player, nextRoom.getMob(), gui, game);
                                game.setFight(fight);
                                gui.printText(roomInfo);
                                fight.startCombatMessage();
                                return "";
                            }
                            resultMessage = roomInfo;
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

            case "inventory":
                resultMessage = "Your inventory: " + player.getInventoryString();
                break;

            case "take":
                if (words.length < 2) {
                    resultMessage = "Take what?";
                } else {
                    String itemName = words[1];
                    Room room = rooms.get(player.getCurrentRoomId());
                    Item item = player.findItemInList(room.getItems(), itemName);
                    if (item != null) {
                        player.addItem(item);
                        room.removeItem(item);
                        resultMessage = "You picked up the " + itemName + ".";
                    } else {
                        resultMessage = "There is no " + itemName + " here.";
                    }
                }
                break;

            case "drop":
                if (words.length < 2) {
                    resultMessage = "Drop what?";
                } else {
                    String itemName = words[1];
                    Item item = player.getItem(itemName);
                    if (item != null) {
                        player.removeItem(item);
                        rooms.get(player.getCurrentRoomId()).addItem(item);
                        resultMessage = "You dropped the " + itemName + ".";
                    } else {
                        resultMessage = "You don't have that.";
                    }
                }
                break;

            case "use":
                if (words.length < 2) {
                    resultMessage = "Use what?";
                } else {
                    String itemName = words[1];
                    if (player.hasItem(itemName)) {
                        resultMessage = "You used the " + itemName + ".";
                    } else {
                        resultMessage = "You don't have a " + itemName + ".";
                    }
                }
                break;

            case "examine":
                if (words.length < 2) {
                    resultMessage = "Examine what?";
                } else {
                    String itemName = words[1];
                    Item item = player.findItemInList(player.getInventory(), itemName);
                    if (item != null) {
                        resultMessage = item.getDescription();
                    } else {
                        resultMessage = "You don't have that.";
                    }
                }
                break;

            case "help":
                resultMessage = "Commands: go, look, take, drop, use, inventory, examine, attack, help.";
                break;

            default:
                resultMessage = "I don't understand that command.";
                break;
        }
        return resultMessage;
    }
}