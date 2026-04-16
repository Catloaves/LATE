package Items.potions;

public class StrengthPot {
    private String id;
    private String name;
    private String description;

    public StrengthPot(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }
}