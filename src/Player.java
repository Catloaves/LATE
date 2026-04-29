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

    public boolean isVisible; //disabled with the invis potion - NPCs cannot see you/will not voluntarily interact with you
    
    private int gold;


    public Player(String startingRoomId, String username, String they, String them, String theirs) {
        this.currentRoomId = startingRoomId;
        this.inventory = new ArrayList<>();
        this.username = username;
        this.they = they;
        this.them = them;
        this.theirs = theirs;
        this.stats = new Stats(100, 20, 100);
        this.gold = 10;
    }

    public void displayStatus() {
        System.out.println(username + "'s Status:");
        System.out.println("HP: " + stats.getHp() + "/" + stats.getMaxHp());
        System.out.println("Hunger: " + stats.getHunger() + "/" + stats.getMaxHunger());
        // We won't need to code hunger if we make food heal HP. - Leo
        System.out.println("Strength: " + stats.getStrength());
        System.out.println("Gold: " + gold);
    }

    // Methods for Player information
    public String getUsername() { 
        return username; }
    
    public String getThey() { 
        return they; }

    public String getThem() {
        return them; }

    public String getTheirs() {
        return theirs; }

    
    // Methods for Stats and Room Id
    public Stats getStats() { 
        return stats; }

    public String getCurrentRoomId() {
        return currentRoomId; }

    public void setCurrentRoomId(String roomId) {
        this.currentRoomId = roomId; }

    
    // Methods for Items
    public void addItem(Item item) {
        inventory.add(item); }

    public boolean hasItem(String itemName) {
        return inventory.stream().anyMatch(i -> i.getName().equalsIgnoreCase(itemName)); }

    public void removeItem(Item item) {
        inventory.remove(item); }
    
    public List<Item> getInventory() {
        return inventory; }
    
    
    // Methods for Gold
    public int getGold() {
            return gold; }

    public void addGold(int amount) {
        this.gold += amount;
        System.out.println("You found " + amount + " gold coins!"); }

    public boolean spendGold(int amount) {
        if (this.gold >= amount) {
            this.gold -= amount;
            return true;
        } else {
            System.out.println("You don't have enough gold!");
            return false; 
            }
        }
}