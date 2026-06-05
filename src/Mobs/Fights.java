package Mobs;

import Game.Player;

public class Fights {
    private Player player;
    private HostileMob mob;

    public Fights(Player player, HostileMob mob) {
        this.player = player;
        this.mob = mob;
        start();
    }

    public void start() {
        System.out.println("Monster Attack!");

        while (player.getStats().getHp() > 0 && mob.getHp() > 0) {
            
            int heroPunch = (int)player.getStats().getStrength();
            
            mob.subtractHp(heroPunch); 
            System.out.println("You've dealt " + heroPunch + " damage!");

            if (mob.getHp() <= 0) {
                break;
            }

            int monsterBite = (int)mob.getStats().getStrength();
            
            int currentHp = player.getStats().getHp();
            player.getStats().loseHP(currentHp);
            System.out.println("The monster attacks! You've lost " + monsterBite + " damage!");
        }
        
        if (player.getStats().getHp() <= 0) {
            System.out.println("You died. GAME OVER.");
        } else {
            System.out.println("You defeated your enemy!");
        }
    }
}