package Items.misc;

import Items.Item;

public class Key extends Item {
    private int keyNum;
    private boolean validKey;

    public Key(String id, String name, String description, int keyNum) {
        super(id, name, description);
        this.keyNum = keyNum;
        this.validKey = true;
    }

    public int getKeyNum() { 
        return keyNum;
    }

    public String useKey() { 
        if (validKey) {
            return "It opens!";
        } else {
            return "Sorry. Wrong key!";
        }
    }
}