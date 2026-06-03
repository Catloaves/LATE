package Mobs;

public class HostileMob {
    public String mobName;
    public int mobHp;
    public boolean isDefeated;

    // for all mobs that can be attacked by the PLAYER use this class
    // hostile mobs can deal damage and be dealt damage to; passive mobs do not attack nor can be attacked

    public HostileMob(String mobName, int mobHp){
        mobName = this.mobName;
        mobHp = this.mobHp;
    }
    
    public String getName(){
        return mobName;
    }
    public int getHp(){ 
        return mobHp;
    }

    public void subtractHp(int amount){
        mobHp -= amount;
        if (mobHp <= 0){
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
    }
