package Items.misc;

public class PuzzleScroll {
    private String id;
    private String name;
    private String description;

    public String decipheredScroll;
    public String puzzleScroll;

    public String scrollQuestion;
    
    public int qNum1;
    public int qNum2;
    public int qNum3;

    public PuzzleScroll(String decipheredScroll, String puzzleScroll) {
        this.decipheredScroll = decipheredScroll;
        this.puzzleScroll = puzzleScroll;
    }

    public String getName() { //not typically to be used by player
        return name;
    }
    public String getQuestion(){
        return scrollQuestion;
    }

}

