package Items.potions;

public class StrengthPot {
    private String id;
    private String name;
    private String description;

    private boolean isFull = true;
    private int turnsTillEffectGone;

    public StrengthPot(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() { //not typically to be used by player
        return name;
    }

    public boolean isPotUsed(boolean isPotUsed, int turnsTillEffectGone){
    for (int i = 0; i < turnsTillEffectGone; i++){
    if (isPotUsed){
        return true;
    }
    }
    return isPotUsed;
}
}