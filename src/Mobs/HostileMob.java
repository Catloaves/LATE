package Mobs;

public class HostileMob {
    private String mobName;
    private int mobHp;
    public boolean isDefeated;

    public HostileMob(String mobName, int mobHp) {
        this.mobName = mobName;
        this.mobHp = mobHp;
        this.isDefeated = false;
    }
    
    public String getName() {
        return mobName;
    }

    public int getHp() { 
        return mobHp;
    }

    public boolean isDefeated() {
        return isDefeated;
    }

    public void subtractHp(int amount) {
        mobHp -= amount;
        if (mobHp <= 0) {
            mobHp = 0;
            isDefeated = true;
        }
    }
    public void HpBleedDmg(int turns, int amount){
        // subtracts a certain amount of Hp each turn until it expires or until mob is defeated
        for (int i = 0; i <= turns; i++){
            mobHp -= amount;
            if (mobHp <= 0){
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
}