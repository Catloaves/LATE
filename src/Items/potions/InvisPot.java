package Items.potions;

import Items.Item;

import Stats;

public class InvisPot extends Item {

    private boolean isFull = true;
    private int turnsTillEffectGone;

    public InvisPot(String id, String name, String description) {
        super(id, name, description);
    }

    public void useInvisPot(Stats stats, int turnsTillEffectGone) {
        Stats.setVisibility(false);
        System.out.println("You are now invisible!");
        for (int i = 0; i < turnsTillEffectGone; i++) {
        }
        stats.setVisibility(true);
        System.out.println("Your invisibility has expired!");
        isFull = false;
            }
        {
    }
}