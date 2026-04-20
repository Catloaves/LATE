public class Stats {

    private int hp;
    private int maxHp;
    private int hunger;
    private int maxHunger;

    public Stats(int maxHp, int maxHunger) {
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.maxHunger = maxHunger;
        this.hunger = maxHunger;
    }

    public void loseHP(int amount) {
        hp -= amount;
        if (hp < 0) {
            hp = 0;
        }
    }

    public void heal(int amount) {
        hp += amount;
        if (hp > maxHp) {
            hp = maxHp;
        }
    }

    public void loseHunger(int amount) {
        hunger -= amount;
        if (hunger < 0) {
            hunger = 0;
        }
    }
    // HP penalty if hunger hits 0. Have to be added

    public void eat(int amount) {
        hunger += amount;
        if (hunger > maxHunger) {
            hunger = maxHunger;
        }
    }

    public int getHp() { 
        return hp;
    }
    
    public int getMaxHp() { 
        return maxHp;
    }

    public int getHunger() { 
        return hunger;
    }

    public int getMaxHunger() { 
        return maxHunger;
    }
    
    public boolean isDead() { 
        return hp <= 0; 
    }
}