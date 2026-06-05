package Items.tools;

import Items.Tool;

public class Mace extends Tool {

    public Mace(String id, String name, String description) {
        super(id, name, description);
    }

    @Override
    public void useItem() {
        if (target == null) {
            System.out.println("You need to input a target!");
            return;
        }
        if ((Math.random()) < 0.3) {
            target.subtractHp(24);
            System.out.println("Critical hit! You've dealt 24 damage!");
            System.out.print(target.getName() + " is now at " + target.getHp() + "!");
        } else {
            target.subtractHp(12);
            System.out.println("You've dealt 12 damage!");
            System.out.print(target.getName() + " is now at " + target.getHp() + "!");
        }
        if (target.isDefeated) {
            System.out.println("You've defeated " + target.getName() + "!");
        }
        target = null;
    }
}