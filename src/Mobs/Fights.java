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
    }

    public void runTurn() {
        gui.printText("A " + mob.getName() + " has appeared!");

        if (mob.getHp() <= 0) {
            return;
        }

        int mobAttackDmg = (int) mob.getStats().getStrength();

        player.getStats().loseHP(mobAttackDmg);
        gui.printText("The " + mob.getName() + " attacks! You've lost " + mobAttackDmg + " damage!");

        if (player.getStats().getHp() <= 0) {
            gui.printText("You died. GAME OVER.");
        } else {
            gui.printText("You defeated your enemy!");
        }
    }

    public HostileMob getMob() {
        return mob;
    }
}
