package Items.potions;

public class HealingPot {
    private String id;
    private String name;
    private String description;

    private boolean isFull = true;

    public HealingPot(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() { //not typically to be used by player
        return name;
    }

    public String usePot(int hp, int maxHp) {
        int oldHp = hp;
        String potMessage = "";
        if (isFull){
            hp += hp*0.3;
            isFull = false;
            potMessage =  "You have regained " + (hp - oldHp)  + " hp.";
            if (hp > maxHp){
                hp = maxHp;
                isFull = true;
                potMessage = "You are already at your max hp!";
            }
        }
        return potMessage;
    }
}