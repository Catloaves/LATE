package Rooms;

import Items.Item;
import Mobs.HostileMob;
import java.util.*;

public class Room {
    private String id, name, description;
    private Map<String, String> exits;
    private List<Item> items;
    private HostileMob mob;
    private boolean isLocked, isDark;

    public Room(String id, String name, String description, Map<String, String> exits, List<Item> items) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.exits = exits;
        this.items = items;
        this.isLocked = false;
        this.isDark = false;
    }

    public String getExitsString() {
        if (exits == null || exits.isEmpty()) {
            return "none";
        }
        return String.join(", ", exits.keySet());
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public boolean isDark() {
        return isDark;
    }

    public void setDark(boolean dark) {
        isDark = dark;
    }

    public String getId() {
        return id;
    }

    public String getLongDescription() {
        return name + "\n" + description;
    }

    public Map<String, String> getExits() {
        return exits;
    }

    public List<Item> getItems() {
        return items;
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public HostileMob getMob() {
        return mob;
    }

    public void setMob(HostileMob mob) {
        this.mob = mob;
    }
}