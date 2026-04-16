package Items.tools;

public class Bow {
    private String id;
    private String name;
    private String description;

    public Bow(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }
}

