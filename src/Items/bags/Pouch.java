package Items.bags;

public class Pouch {
    private String id;
    private String name;
    private String description;

    private int GCPricePouch;

    private int size;
    private String[] pouchItems;

    public Pouch(int size, int GCPricePouch) {
        this.size = size;
        this.GCPricePouch = GCPricePouch;
    }
    public String getName() { //not typically to be used by player
        return name;
    }
    public String getSize() {
        return "Your pouch can hold" + size + "items.";
    }
}