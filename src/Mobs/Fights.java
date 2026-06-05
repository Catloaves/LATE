package Mobs;

import Game.AdventureGUI;
import Game.Player;

public class Fights {
    private Player player;
    private HostileMob mob;
    private AdventureGUI gui;

    public Fights(Player player, HostileMob mob, AdventureGUI gui) {
        this.player = player;
        this.mob = mob;
        this.gui = gui;
        start();
    }

    public void start() {
        gui.printText("Monster Attack!");

        while (player.getStats().getHp() > 0 && mob.getHp() > 0) {
        gui.handleInput(mob);

            if (mob.getHp() <= 0) {
                break;
            }

            int mobAttackDmg = (int) mob.getStats().getStrength();

            player.getStats().loseHP(mobAttackDmg);
            gui.printText("The monster attacks! You've lost " + mobAttackDmg + " damage!");

        }

        if (player.getStats().getHp() <= 0) {
            gui.printText("You died. GAME OVER.");
        } else {
            gui.printText("You defeated your enemy!");
        }
    }
}
