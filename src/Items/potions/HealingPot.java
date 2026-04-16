package Items.potions;

public class HealingPot {
    private String id;
    private String name;
    private String description;

    public HealingPot(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }
}

