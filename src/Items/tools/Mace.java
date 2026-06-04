package Items.tools;
// Low damage, chance for bleed
import Mobs.HostileMob;
public class Mace {
    private String id;
    private String name;
    private String description;
    public Mace(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public String getName() { //not typically to be used by player
        return name;
    }
        public void useMace(HostileMob target){ //takes a hostile mob to deal damage to
        if ((Math.random()) < 0.3){
            target.subtractHp(24); //crit hit, double the dmg
            System.out.println("Critical hit! You've dealt 24 damage!");
            System.out.print(target.getName() + " is now at " + target.getHp() + "!");
         } else{
            target.subtractHp(12);
            System.out.println("You've dealt 12 damage!");
            System.out.print(target.getName() + " is now at " + target.getHp() + "!");
        }
            if (target.isDefeated){
            System.out.println("You've defeated " + target.getName() + "!");
        }
        }
    }