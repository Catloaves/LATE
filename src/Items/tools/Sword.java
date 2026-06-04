package Items.tools;

import Items.Tool;

public class Sword extends Tool {

    public Sword(String id, String name, String description) {
        super(id, name, description);
    }

    @Override
    public void useItem() {
        if (target == null) {
            System.out.println("You need to input a target!");
            return;
        }
        // method
        target.subtractHp(25);
        System.out.println("You've dealt 25 damage!");
        System.out.print(target.getName() + " is now at " + target.getHp() + "!");
        if (target.isDefeated) {
            System.out.println("You've defeated " + target.getName() + "!");
        }
        target = null;
    }
}