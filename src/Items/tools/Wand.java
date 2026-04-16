package Items.tools;

public class Wand {
    private String id;
    private String name;
    private String description;

    public Wand(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }
}

