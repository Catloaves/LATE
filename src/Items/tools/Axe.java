package Items.tools;

public class Axe {
    private String id;
    private String name;
    private String description;

    public Axe(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }
}
