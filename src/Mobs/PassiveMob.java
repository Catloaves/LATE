package Mobs;

public class PassiveMob {
    public String mobName;

    // For all mobs that CANNOT be attacked by the PLAYER use this class
    // Hostile mobs can deal damage and be dealt damage to; passive mobs do not attack nor can be attacked
    public PassiveMob(String mobName) {
        this.mobName = mobName;
    }

    public String getName() {
        return mobName;
    }
}