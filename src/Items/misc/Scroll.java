package Items.misc;

public class Scroll {
    private String id;
    private String name;
    private String description;

    public String scrollText;

    public Scroll(String scrollText) {
        this.scrollText = scrollText;
    }

    public String getName() { //not typically to be used by player
        return name;
    }
    public String getText() {
        return scrollText;
    }
}

