package Mobs;

import Game.Stats;

public class HostileMob {
    private String mobName;
    public boolean isDefeated;
    private Stats stats;

    public HostileMob(String mobName, int mobHp, int mobDamage) {
        stats = new Stats(mobHp, 0, mobDamage);
    }

    public String getName() {
        return mobName;
    }

    public int getHp() {
        return stats.getHp();
    }

    public void subtractHp(int amount) {
        stats.loseHP(amount);
        isDefeated = stats.isDead();
    }

    public void HpBleedDmg(int turns, int amount) {
        for (int i = 0; i <= turns; i++) {
            stats.loseHP(amount);
            isDefeated = stats.isDead();
        }
    }

    public void applyBleedTick(int amount) {
        if (!isDefeated) {
            subtractHp(amount);
            System.out.println(mobName + " takes " + amount + " bleeding damage!");
        }

    }

    public Stats getStats() {
        return stats;
    }

}
