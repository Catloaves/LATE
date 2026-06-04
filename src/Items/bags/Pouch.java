package Items.bags;

import Items.Item;

public class Pouch extends Item {
    private int GCPricePouch;
    private int size;
    private String[] pouchItems;

    public Pouch(String id, String name, String description, int size, int GCPricePouch) {
        super(id, name, description);
        this.size = size;
        this.GCPricePouch = GCPricePouch;
        this.pouchItems = new String[size];
    }

    public String getSize() {
        return "Your pouch can hold " + size + " items.";
    }

    public int getGCPricePouch() {
        return GCPricePouch;
    }
}