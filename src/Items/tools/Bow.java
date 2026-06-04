package Items.tools;
//High damage, chance to miss
import Mobs.HostileMob;
public class Bow {
    private String id;
    private String name;
    private String description;
    public Bow(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public String getName() { //not typically to be used by player
        return name;
    }
    public void useBow(HostileMob target){ //takes a hostile mob to deal damage to
    if (Math.random() > 0.475){
        target.subtractHp(30); //attack sucessful
        System.out.println("You've dealt 30 damage!");
        System.out.print(target.getName() + " is now at " + target.getHp() + "!");
    }
    else{
        target.subtractHp(0); //attack misses
        System.out.println("Oh no! Your shot misses. Better luck next time!");
    }
    if (target.isDefeated){
        System.out.println("You've defeated " + target.getName() + "!");
    }
    }
}


