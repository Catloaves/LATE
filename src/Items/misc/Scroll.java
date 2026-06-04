package Items.misc;

import Items.Item;

public class Scroll extends Item {
    public String scrollText;

    public Scroll(String id, String name, String description, String scrollText) {
        super(id, name, description);
        this.scrollText = scrollText;
    }

    public String getText() {
        return scrollText;
    }
}