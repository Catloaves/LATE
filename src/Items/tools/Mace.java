package Items.tools;

public class Mace {
    private String id;
    private String name;
    private String description;

    public Mace(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() { //not typically to be used by player
        return name;
    }
}
