package Items.food;

public class Rations {
    private String id;
    private String name;
    private String description;

    public Rations(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }
}