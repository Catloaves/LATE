package Items.bags;

public class Backpack {
    private String id;
    private String name;
    private String description;

    public Backpack(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }
}