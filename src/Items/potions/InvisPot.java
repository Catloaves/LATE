package Items.potions;

import Items.Item;

public class InvisPot extends Item {
    private boolean isFull = true;

    public InvisPot(String id, String name, String description) {
        super(id, name, description);
    }

    public int usePot() { 
        if (isFull) {
            isFull = false;
            System.out.println("You drink the potion and vanish from sight!");
            return 5;
        }
        System.out.println("The invisibility potion is empty.");
        return 0;
    }
}