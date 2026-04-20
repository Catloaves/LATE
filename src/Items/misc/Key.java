package Items.misc;

public class Key {
    private String id;
    private String name;
    private String description;

    private int keyNum;

    public Key(String name, int keyNum) {
        this.name = name;
        this.keyNum = keyNum;
    }

    public String getName() {
        return name;
    }
}

