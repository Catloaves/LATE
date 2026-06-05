package Items.potions;

import Items.Item;

public class HealingPot extends Item {
    private boolean isFull = true;

    public HealingPot(String id, String name, String description) {
        super(id, name, description);
    }

    public String usePot(int hp, int maxHp) {
        if (hp >= maxHp) {
            return "You are already at your max hp!";
        }
        
        if (isFull) {
            int oldHp = hp;
            hp += (int)(maxHp * 0.3);
            
            if (hp > maxHp) {
                hp = maxHp;
            }
            
            isFull = false;
            return "You have regained " + (hp - oldHp) + " hp.";
        }
        
        return "The potion bottle is empty!";
    }
}