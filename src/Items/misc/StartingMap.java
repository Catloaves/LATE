package Items.misc;

public class StartingMap {
    private String id;
    private String name;
    private String description;

    public StartingMap(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }
}

