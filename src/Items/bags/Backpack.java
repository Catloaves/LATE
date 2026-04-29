package Items.bags;

public class Backpack {
    private String id;
    private String name;
    private String description;


    private int GCPriceBackpack;

    private int size;
    public String[] packItems;

    public Backpack(int size, int GCPriceBackpack) {
        this.size = size;
        this.GCPriceBackpack = GCPriceBackpack;
    }
    public String getName() { //not typically to be used by player
        return name;
    }
    public String getSize() {
        return "Your backpack can hold" + size + "items.";
    }
}