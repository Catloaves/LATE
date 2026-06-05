package Mobs;

import Game.Stats;

public class HostileMob {
    private String mobName;
    private int mobHp;
    public boolean isDefeated;
    private Stats stats;

    public HostileMob(String mobName, int mobHp, int mobDamage) {
        mobHp = stats.getHp();
        stats = new Stats(mobHp, 0, mobDamage);
    }

    public String getName() {
        return mobName;
    }

    public int getHp() {
        return stats.getHp();
    }

    public void subtractHp(int amount) {
        mobHp -= amount;
        if (mobHp <= 0) {
            isDefeated = true;
        }
    }

    public void HpBleedDmg(int turns, int amount) {
        for (int i = 0; i <= turns; i++) {
            mobHp -= amount;
            if (mobHp <= 0) {
                isDefeated = true;
            }
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
