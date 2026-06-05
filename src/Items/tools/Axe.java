package Items.tools;

import Items.Tool;

public class Axe extends Tool { 

    public Axe(String id, String name, String description) {
        super(id, name, description);
    }

    @Override
    public void useItem(){
        if (target == null){
            System.out.println("You need to input a target!");
            return;
        }
        target.subtractHp(10);
        target.HpBleedDmg(5, 5);
        System.out.println("You've dealt 10 damage with 5 bleed damage!");
        System.out.print(target.getName() + " is now at " + target.getHp() + "!");
        if (target.isDefeated){
            System.out.println("You've defeated " + target.getName() + "!");
        }
        target = null;
    }
}
