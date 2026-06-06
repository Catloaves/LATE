package Game;

import java.util.Map;

import Items.Item;
import Items.Tool;
import Mobs.Fights;
import Mobs.HostileMob;
import Rooms.Room;

public class CommandParser {

    public String parse(AdventureGUI gui, String input, Player player, Map<String, Room> rooms, HostileMob target,
            Game game) {
        String[] words = input.trim().toLowerCase().split("\\s+");
        if (words.length == 0 || words[0].isEmpty()) {
            return "Please enter a command.";
        }

        String command = words[0];
        String resultMessage = "";

        switch (command) {
            case "go":
                if (words.length < 2) {
                    resultMessage = "Go where?";
                } else {
                    String direction = words[1];
                    Room currentRoom = rooms.get(player.getCurrentRoomId());
                    String nextRoomId = currentRoom.getExits().get(direction);

                    if (nextRoomId != null) {
                        player.setCurrentRoomId(nextRoomId);
                        Room newRoom = rooms.get(player.getCurrentRoomId());
                        resultMessage = "You move " + direction + ".\n\n";
                        gui.printText(resultMessage);
                        resultMessage = newRoom.getLongDescription();
                        gui.printText(resultMessage);
                        resultMessage = "";
                        if (newRoom.getMob() != null && !newRoom.getMob().isDefeated) {
                            game.setFight(new Fights(player, newRoom.getMob(), gui, game));
                        }
                    } else {
                        resultMessage = "Sorry. You can't go that way!";
                    }
                }
                break;

            case "look":
                Room currentRoom = rooms.get(player.getCurrentRoomId());
                resultMessage = currentRoom.getLongDescription();
                break;

            case "inventory":
                if (player.getInventory().isEmpty()) {
                    resultMessage = "Looks like your inventory is empty!";
                } else {
                    resultMessage = "You are carrying:\n\n";
                    for (Item item : player.getInventory()) {
                        resultMessage += "- " + item.getName() + "\n";
                    }
                }
                break;

            case "take":
                if (words.length < 2) {
                    resultMessage = "Take what?";
                } else {
                    String itemName = words[1];
                    Room room = rooms.get(player.getCurrentRoomId());
                    Item itemToTake = null;

                    for (Item item : room.getItems()) {
                        if (item.getName().equalsIgnoreCase(itemName)) {
                            itemToTake = item;
                            break;
                        }
                    }
                    if (itemToTake != null) {
                        room.removeItem(itemToTake);
                        player.addItem(itemToTake);
                        resultMessage = "You take the " + itemToTake.getName() + ".";
                    } else {
                        resultMessage = "Alas.. There is no " + itemName + " here!";
                    }
                }
                break;

            case "use":
                resultMessage += "Inventory:\nWhat would you like to use?\n\t";
                game.setIsWaiting(true);
                for (int i = 0; i < player.getInventory().size(); i++) {
                    Item item = player.getInventory().get(i);
                    resultMessage += "\t" + (i + 1) + ") " + item.getName() + "\n";
                }
                gui.printText(resultMessage);
                resultMessage = "";
                break;

            case "drop":
                if (words.length < 2) {
                    resultMessage = "Drop what?";
                } else {
                    String itemName = words[1];
                    Item itemToDrop = null;
                    for (Item item : player.getInventory()) {
                        if (item.getName().equalsIgnoreCase(itemName)) {
                            itemToDrop = item;
                            break;
                        }
                    }
                    if (itemToDrop != null) {
                        player.removeItem(itemToDrop);
                        Room room = rooms.get(player.getCurrentRoomId());
                        room.addItem(itemToDrop);
                        resultMessage = "You've drop the " + itemToDrop.getName() + ".";
                    } else {
                        resultMessage = "Looks like you don't have a " + itemName + " to drop!";
                    }
                }
                break;

            case "help":
                resultMessage = "Available commands:\ngo [direction], look, take [item], drop [item], inventory, help";
                break;

            default:
                resultMessage = "I don't understand that command.";
                break;
        }

        return resultMessage;
    }

    public String selectItem(Player player, HostileMob target, String in) {
        String resultMessage = "";
        try {
            int n = Integer.parseInt(in);
            if (n > player.getInventory().size())
                throw new Exception();
            Item item = player.getInventory().get(n - 1);
            if (item instanceof Tool) {
                ((Tool) item).setTarget(target); // casting to a Tool
            }
        } catch (Exception e) {
            resultMessage = "Please select something from the inventory!";
        }
        return resultMessage;
    }
}