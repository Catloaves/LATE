package Items.tools;
//Medium damage
import Mobs.HostileMob;
public class Sword {
    private String id;
    private String name;
    private String description;
    public Sword(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public String getName() { //not typically to be used by player
        return name;
    }
    public void useSword(HostileMob target){ //takes a hostile mob to deal damage to
        target.subtractHp(25);
        System.out.println("You've dealt 25 damage!");
        System.out.print(target.getName() + " is now at " + target.getHp() + "!");
        if (target.isDefeated){
        System.out.println("You've defeated " + target.getName() + "!");
        }
    }
}

