package Items.tools;

public class Shield {
    private String id;
    private String name;
    private String description;

    public Shield(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }
}
