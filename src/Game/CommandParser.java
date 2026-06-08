package Game;

import java.util.Map;
import Items.Item;
import Items.Tool;
import Mobs.Fights;
import Mobs.HostileMob;
import Rooms.Room;

public class CommandParser {

    public String parse(AdventureGUI gui, String input, Player player, Map<String, Room> rooms, HostileMob target, Game game) {
        String[] words = input.trim().toLowerCase().split("\\s+");
        if (words.length == 0 || words[0].isEmpty()) {
            return "Please enter a command.";
        }

        String command = words[0];
        String resultMessage = "";

        if (game.isFightActive() && (command.equals("go") || command.equals("shop"))) {
            return "You cannot do that right now! You are in the middle of a life-or-death battle!";
        }

        switch (command) {
            case "attack":
            case "fight":
                if (game.isFightActive() && game.getFights() != null) {
                    game.getFights().runTurn();
                    resultMessage = "";
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
                        player.setCurrentRoomId(nextRoomId);
                        Room newRoom = rooms.get(player.getCurrentRoomId());
                        gui.printText("You move " + direction + ".");
                        resultMessage = newRoom.getLongDescription();
                        if (newRoom.getMob() != null && !newRoom.getMob().isDefeated) {
                            game.setFight(new Fights(player, newRoom.getMob(), gui, game));
                        }
                    } else {
                        resultMessage = "Sorry. You can't go that way!";
                    }
                }
                break;

            case "look":
                resultMessage = rooms.get(player.getCurrentRoomId()).getLongDescription();
                break;

            case "inventory":
                if (player.getInventory().isEmpty()) {
                    resultMessage = "Looks like your inventory is empty!";
                } else {
                    resultMessage = "You are carrying:\n";
                    for (Item item : player.getInventory()) {
                        resultMessage += "- " + item.getName() + "\n";
                    }
                }
                break;

            case "take":
            case "get":
            case "pick":
                String itemName = "";
                if (words.length >= 3 && words[1].equals("up")) {
                    itemName = words[2];
                } else if (words.length >= 2) {
                    itemName = words[1];
                }

                if (itemName.isEmpty()) {
                    resultMessage = "Take what?";
                } else {
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
                        if (itemToTake.getName().equalsIgnoreCase("cure")) {
                            game.setFight(new Fights(player, new HostileMob("Guardian", 40, 10), gui, game));
                        }
                    } else {
                        resultMessage = "Alas.. There is no " + itemName + " here!";
                    }
                }
                break;

            case "use":
                if (player.getInventory().isEmpty()) {
                    resultMessage = "You don't have any items to use!";
                } else {
                    resultMessage = "Inventory:\nWhat would you like to use?\n";
                    game.setIsWaiting(true);
                    for (int i = 0; i < player.getInventory().size(); i++) {
                        resultMessage += (i + 1) + ") " + player.getInventory().get(i).getName() + "\n";
                    }
                }
                break;

            case "drop":
                if (words.length < 2) {
                    resultMessage = "Drop what?";
                } else {
                    String dropName = words[1];
                    Item itemToDrop = null;
                    for (Item item : player.getInventory()) {
                        if (item.getName().equalsIgnoreCase(dropName)) {
                            itemToDrop = item;
                            break;
                        }
                    }
                    if (itemToDrop != null) {
                        player.removeItem(itemToDrop);
                        rooms.get(player.getCurrentRoomId()).addItem(itemToDrop);
                        resultMessage = "You've dropped the " + itemToDrop.getName() + ".";
                    } else {
                        resultMessage = "You don't have that.";
                    }
                }
                break;

            case "help":
                resultMessage = "Available commands:\ngo [direction], look, take/get [item], drop [item], use, inventory, attack, shop, help, examine [item]";
                break;

            case "shop":
                if (player.getCurrentRoomId().equalsIgnoreCase("courtyard")) {
                    Shop gameShop = new Shop();
                    gameShop.openShop(player);
                    resultMessage = "You finished browsing the shop.";
                } else {
                    resultMessage = "There is no shop here!";
                }
                break;

            case "examine":
                if (words.length < 2) {
                    resultMessage = "Examine what?";
                } else {
                    String examName = words[1];
                    Item itemToExamine = null;
                    for (Item item : player.getInventory()) {
                        if (item.getName().equalsIgnoreCase(examName)) {
                            itemToExamine = item;
                            break;
                        }
                    }
                    if (itemToExamine == null) {
                        for (Item item : rooms.get(player.getCurrentRoomId()).getItems()) {
                            if (item.getName().equalsIgnoreCase(examName)) {
                                itemToExamine = item;
                                break;
                            }
                        }
                    }
                    if (itemToExamine != null) {
                        resultMessage = itemToExamine.getDescription();
                    } else {
                        resultMessage = "You don't see a " + examName + " here.";
                    }
                }
                break;

            default:
                resultMessage = "I don't understand that command.";
                break;
        }
        return resultMessage;
    }

    public String selectItem(Player player, HostileMob target, String in) {
        try {
            int n = Integer.parseInt(in);
            Item item = player.getInventory().get(n - 1);
            if (item instanceof Tool) {
                if (target != null) {
                    ((Tool) item).setTarget(target);
                    item.useItem();
                    return "You used your " + item.getName() + " against " + target.getName() + "!";
                }
            }
            item.useItem();
            return "You used " + item.getName() + ".";
        } catch (Exception e) {
            return "Invalid selection!";
        }
    }
}