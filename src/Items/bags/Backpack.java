package Items.bags;

import Items.Item;

public class Backpack extends Item {
    private int GCPriceBackpack;
    private int size;
    public String[] packItems;

    public Backpack(String id, String name, String description, int size, int GCPriceBackpack) {
        super(id, name, description);
        this.size = size;
        this.GCPriceBackpack = GCPriceBackpack;
        this.packItems = new String[size]; 
    }
    
    public int getSize() {
        return size;
    }
    
    public int getGCPriceBackpack() {
        return GCPriceBackpack;
    }
}