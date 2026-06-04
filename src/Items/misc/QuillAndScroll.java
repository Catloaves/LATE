package Items.misc;

import Items.Item;

public class QuillAndScroll extends Item {
    public String scrollText;

    public QuillAndScroll(String id, String name, String description, String scrollText) {
        super(id, name, description);
        this.scrollText = scrollText;
    }

    public void write(String text) {
        scrollText += text;
    }

    public void reWrite(String text) {
        scrollText = text;
    }
}