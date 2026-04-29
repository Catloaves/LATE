package Items.misc;

public class QuillAndScroll {
    private String id;
    private String name;
    private String description;

    public String scrollText;

    public QuillAndScroll(String scrollText) {
        this.scrollText = scrollText;
    }

    public String getName() { //not typically to be used by player
        return name;
    }


    public void write(String text){
        scrollText += text;
    }

    public void reWrite(String text){
        scrollText = text;
    }
}

