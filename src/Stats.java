public class Stats {

    private int hp;
    private int maxHp;
    private int hunger;
    private int maxHunger;
    private double strength;
    private boolean isVisible; //disabled with the invis potion - NPCs cannot see you/will not voluntarily interact with you
    

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

    public void incrementHp(int hp){
        this.hp += hp;
    }

    public int getHunger() { 
        return hunger;
    }

    public int getMaxHunger() { 
        return maxHunger;
    }

    public void incrementHunger(int hunger){
        this.hunger += hunger;
    }

    public double getStrength() {
        return strength;
    }
    
    public void setStrength(double strength) {
        this.strength = strength;
    }

    public void incrementStrength(int strength){
        this.strength += strength;
    }
    
    public void setVisibility(boolean isVisible){
    this.isVisible = isVisible;
    }

    public boolean isVisible(){
    return isVisible;
    }
    public boolean isAliveAndWell() { 
        return hp > 0; 
    }
    
}
