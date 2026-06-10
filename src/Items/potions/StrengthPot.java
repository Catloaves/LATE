package Items.potions;

import Items.Item;

public class StrengthPot extends Item {
    private boolean isFull = true;
    final public static int GCPriceStrengthPot = 10;

    public StrengthPot(String id, String name, String description) {
        super(id, name, description);
    }

    public int usePot() {
        if (isFull) {
            isFull = false;
            System.out.println("Your attacks now have a 35% buff for the next four consecutive turns!");
            return 3;
        }
        System.out.println("Sorry. Looks like the strength potion is empty.");
        return 0;
    }
}