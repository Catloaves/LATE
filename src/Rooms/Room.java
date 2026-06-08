package Rooms;

import java.util.List;
import java.util.Map;

import Items.Item;
import Mobs.HostileMob;
import Mobs.PassiveMob;

public class Room {
    private String id;
    private String name;
    private String description;
    private Map<String, String> exits;
    private List<Item> items;
    private HostileMob mob;
    private PassiveMob passiveMob;

    public Room(String id, String name, String description, Map<String, String> exits, List<Item> items) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.exits = exits;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public String getLongDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("\n");
        sb.append(description).append("\n");

        if (passiveMob != null) {
            sb.append("You see a friendly face here: ").append(passiveMob.getName()).append(".\n");
        }

        if (!items.isEmpty()) {
            sb.append("You see: ");
            for (Item item : items) {
                sb.append(item.getName()).append(", ");
            }
            sb.setLength(sb.length() - 2);
            sb.append(".\n");
        }

        if (!exits.isEmpty()) {
            sb.append("Exits: ");
            for (String dir : exits.keySet()) {
                sb.append(dir).append(", ");
            }
            sb.setLength(sb.length() - 2);
            sb.append(".\n");
        }

        return sb.toString();
    }

    public HostileMob getMob() {
        return mob;
    }

    public void setMob(HostileMob mob) {
        this.mob = mob;
    }

    public PassiveMob getPassiveMob() {
        return passiveMob;
    }

    public void setPassiveMob(PassiveMob passiveMob) {
        this.passiveMob = passiveMob;
    }
}