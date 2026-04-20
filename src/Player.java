import java.util.ArrayList;
import java.util.List;

import Items.Item;

public class Player {
    private String currentRoomId;
    private List<Item> inventory;
    private String username;
    private String they;
    private String them;
    private String theirs;
    private Stats stats;
    // test


    public Player(String startingRoomId, String username, String they, String them, String theirs) {
        this.currentRoomId = startingRoomId;
        this.inventory = new ArrayList<>();
        this.username = username;
        this.they = they;
        this.them = them;
        this.theirs = theirs;
        this.stats = new Stats(100, 20);
    }

    public void displayStatus() {
        System.out.println(username + "'s Status:");
        System.out.println("HP: " + stats.getHp() + "/" + stats.getMaxHp());
        System.out.println("Hunger: " + stats.getHunger() + "/" + stats.getMaxHunger());
    }

    public Stats getStats() { 
        return stats; }

    public String getUsername() { 
        return username; }
    
    public String getThey() { 
        return they; }

    public String getThem() {
        return them; }

    public String getTheirs() {
        return theirs; }

    public String getCurrentRoomId() {
        return currentRoomId;
    }

    public void setCurrentRoomId(String roomId) {
        this.currentRoomId = roomId;
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public boolean hasItem(String itemName) {
        return inventory.stream().anyMatch(i -> i.getName().equalsIgnoreCase(itemName));
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public List<Item> getInventory() {
        return inventory;
    }
}

//hello