package Items.misc;

import Items.Item;

public class PuzzleScroll extends Item {
    public String decipheredScroll;
    public String puzzleScroll;
    public String scrollQuestion;
    
    public int qNum1;
    public int qNum2;
    public int qNum3;

    public PuzzleScroll(String id, String name, String description, String decipheredScroll, String puzzleScroll) {
        super(id, name, description);
        this.decipheredScroll = decipheredScroll;
        this.puzzleScroll = puzzleScroll;
    }

    public String getQuestion() {
        return scrollQuestion;
    }
}