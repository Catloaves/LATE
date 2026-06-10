package Rooms;

import com.google.gson.*;
import Items.Item;
import Items.tools.Axe;
import Items.tools.Bow;
import Items.tools.Knife;
import Items.tools.Mace;
import Items.tools.Sword;
import Items.misc.Torch;
import Items.misc.Key;
import Items.misc.PuzzleScroll;
import Items.food.Rations;
import Items.bags.Knapsack;
import Items.bags.Pouch;
import Mobs.HostileMob;

import java.io.FileReader;
import java.util.*;

public class RoomLoader {
    public Map<String, Room> loadRooms(String filePath) {
        Map<String, Room> rooms = new HashMap<>();
        try {
            Gson gson = new Gson();
            JsonObject data = gson.fromJson(new FileReader(filePath), JsonObject.class);

            for (String key : data.keySet()) {
                JsonObject obj = data.getAsJsonObject(key);
                String name = obj.get("name").getAsString();
                String desc = obj.get("description").getAsString();

                Map<String, String> exits = new HashMap<>();
                if (obj.has("exits")) {
                    JsonObject exitsJson = obj.getAsJsonObject("exits");
                    for (String dir : exitsJson.keySet()) {
                        exits.put(dir, exitsJson.get(dir).getAsString());
                    }
                }

                List<Item> items = new ArrayList<>();
                if (obj.has("items")) {
                    JsonArray itemArray = obj.getAsJsonArray("items");
                    for (JsonElement e : itemArray) {
                        JsonObject i = e.getAsJsonObject();

                        String itemId = i.get("id").getAsString();
                        String itemName = i.get("name").getAsString();
                        String itemDesc = i.get("description").getAsString();

                        switch (itemId.toLowerCase()) {
                            case "knife":
                                items.add(new Knife(itemId, itemName, itemDesc));
                                break;
                            case "sword":
                                items.add(new Sword(itemId, itemName, itemDesc));
                                break;
                            case "axe":
                                items.add(new Axe(itemId, itemName, itemDesc));
                                break;
                            case "Mace":
                                items.add(new Mace(itemId, itemName, itemDesc));
                                break;
                            case "Bow":
                                items.add(new Bow(itemId, itemName, itemDesc));
                                break;
                            case "torch":
                                items.add(new Torch(itemId, itemName, itemDesc));
                                break;
                            case "key":
                                items.add(new Key(itemId, itemName, itemDesc, 1));
                                break;
                            case "rations":
                                items.add(new Rations(itemId, itemName, itemDesc));
                                break;
                            case "backpack":
                                items.add(new Knapsack(itemId, itemName, itemDesc));
                                break;
                            case "pouch":
                                items.add(new Pouch(itemId, itemName, itemDesc));
                                break;
                            case "puzzlescroll":
                                items.add(new PuzzleScroll(itemId, itemName, itemDesc, "Deciphered text",
                                        "Encrypted riddle"));
                                break;
                            default:
                                items.add(new Item(itemId, itemName, itemDesc));
                                break;
                        }
                    }
                }
                Room room = new Room(key, name, desc, exits, items);
                if (obj.has("mob")) {
                    JsonObject mobJson = obj.getAsJsonObject("mob");

                    String mobName = mobJson.get("name").getAsString();
                    int mobHp = mobJson.get("hp").getAsInt();
                    int mobDmg = mobJson.get("damage").getAsInt();
                    HostileMob mob = new HostileMob(mobName, mobHp, mobDmg);
                    room.setMob(mob);
                }
                rooms.put(key, room);
            }
        } catch (Exception e) {
            System.out.println("Error reading room file path: " + filePath);
            e.printStackTrace();
            return null;
        }
        return rooms;
    }
}