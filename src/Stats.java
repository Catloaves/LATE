public class Stats {

    private int hp;
    private int maxHp;
    private int hunger;
    private int maxHunger;
    private double strength;

    public Stats(int maxHp, int maxHunger, double strength) {
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.maxHunger = maxHunger;
        this.hunger = maxHunger;
        this.strength = strength;
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
    // We won't need to code hunger if we make food heal HP. - Leo

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
    // Hunger instance

    public int getMaxHunger() { 
        return maxHunger;
    }
    // Hunger instance

    public double getStrength() {
        return strength;
    }
    
    public void setStrength(double strength) {
        this.strength = strength;
    }
    
    public boolean isDead() { 
        return hp <= 0; 
    }
}
