package Items.tools;

import Items.Tool;

public class Bow extends Tool {

    public Bow(String id, String name, String description) {
        super(id, name, description);
    }

    @Override
    public void useItem() {
        if (target == null) {
            System.out.println("You need to input a target!");
            return;
        }
        // method
        if (Math.random() > 0.475) {
            target.subtractHp(30); // attack sucessful
            System.out.println("You've dealt 30 damage!");
            System.out.print(target.getName() + " is now at " + target.getHp() + "!");
        } else {
            target.subtractHp(0); // attack misses
            System.out.println("Oh no! Your shot misses. Better luck next time!");
        }
        if (target.isDefeated) {
            System.out.println("You've defeated " + target.getName() + "!");
        }
        target = null;
    }
}