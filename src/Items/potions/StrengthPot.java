package Items.potions;

import Items.Item;

public class StrengthPot extends Item {
    private boolean isFull = true;

    public StrengthPot(String id, String name, String description) {
        super(id, name, description);
    }

    public int usePot() { 
        if (isFull) {
            isFull = false;
            System.out.println("You feel a sudden surge of power coursing through your veins!");
            return 3;
        }
        System.out.println("The strength potion is empty.");
        return 0;
    }
}