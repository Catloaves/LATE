package Items.misc;

public class Key {
    private String id;
    private String name;
    private String description;

    private int keyNum;

    private boolean validKey;

    public Key(String name, int keyNum) {
        this.name = name;
        this.keyNum = keyNum;
    }

    public String getName() { //not typically to be used by player
        return name;
    }

    public int getKeyNum() { //cannot be used by the player
        return keyNum;
    }
    public String useKey() { //can be used by the player
        if (validKey)
            return "It opens!";
        else
            return "Sorry. Wrong key!";
    }
    }

