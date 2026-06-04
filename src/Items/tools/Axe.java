package Items.tools;
//Low damage, chance for bleed
import Mobs.HostileMob;
public class Axe {
    private String id;
    private String name;
    private String description;
    public Axe(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public String getName() { //not typically to be used by player
        return name;
    }
    public void useAxe(HostileMob target){ //takes a hostile mob to deal damage to
        target.subtractHp(10);
        target.HpBleedDmg(5, 5);
        System.out.println("You've dealt 10 damage with 5 bleed damage!");
        System.out.print(target.getName() + " is now at " + target.getHp() + "!");
        if (target.isDefeated){
            System.out.println("You've defeated " + target.getName() + "!");
        }
    }
}
