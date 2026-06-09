package Items.tools;

import Items.Tool;

public class Knife extends Tool {

    public Knife(String id, String name, String description) {
        super(id, name, description);
    }

    @Override
    public void useItem() {
        if (this.target == null) {
            System.out.println("You need to input a target!");
            return;
        }

        this.target.subtractHp(25);
        System.out.println("You've dealt 8 damage!");
        System.out.print(this.target.getName() + " is now at " + this.target.getHp() + "!");
        
        if (this.target.getHp() <= 0) {
            System.out.println("You've defeated " + this.target.getName() + "!");
        }
        this.target = null;
    }
}