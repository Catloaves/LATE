package Game;

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

    public void eat(int amount) {
        hunger += amount;
        if (hunger > maxHunger) {
            hunger = maxHunger;
        }
    }

    public void addStrength(double amount) {
        this.strength += amount;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
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