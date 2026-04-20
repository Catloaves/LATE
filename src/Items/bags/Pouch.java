package Items.bags;

public class Pouch {
    private String id;
    private String name;
    private String description;

    private int size;
    private String[] pouchItems;

    public Pouch(int size) {
        this.size = size;
    }

    public String getSize() {
        return "Your pouch can hold" + size + "items.";
    }
}