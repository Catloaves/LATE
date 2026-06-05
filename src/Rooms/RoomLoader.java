package Rooms;

import com.google.gson.*;
import Items.Item;
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

                        String itemName = i.get("name").getAsString();
                        String itemDesc = i.get("description").getAsString();

                        items.add(new Item(itemName, itemName, itemDesc));
                    }
                }
                Room room = (new Room(key, name, desc, exits, items));
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