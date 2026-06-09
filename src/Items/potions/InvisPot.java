package Items.potions;

import Items.Item;

public class InvisPot extends Item {
    private boolean isFull = true;
    final public static int GCPriceInvisPot = 16;

    public InvisPot(String id, String name, String description) {
        super(id, name, description);
    }

    public int usePot() {
        if (isFull) {
            isFull = false;
            System.out.println("For the next ten turns, mobs cannot see you! That also means they will not attack you and fights will not be initiated.");
            return 5;
        }
        System.out.println("Sorry. Looks like the invisibility potion is empty.");
        return 0;
    }
}