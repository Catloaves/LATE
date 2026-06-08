package Mobs;

public class PassiveMob {
    public String mobName;
    public boolean isDefeated;
    // for all mobs that CANNOT be attacked by the PLAYER use this class
    // hostile mobs can deal damage and be dealt damage to; passive mobs do not attack nor can be attacked

    public PassiveMob(String mobName, int mobHp){
        this.mobName = mobName;
    }

    public String getName() {
        return mobName;
    }
}