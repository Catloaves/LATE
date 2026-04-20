package Items.bags;

public class Backpack {
    private String id;
    private String name;
    private String description;

    private int size;
    public String[] packItems;

    public Backpack(int size) {
        this.size = size;
    }

    public String getSize() {
        return "Your backpack can hold" + size + "items.";
    }
}