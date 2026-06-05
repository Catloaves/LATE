package Mobs;

import Game.Game;
import Game.AdventureGUI;
import Game.Player;
import Game.Stats;

public class Fights {
    private Player player;
    private HostileMob mob;
    private AdventureGUI gui;
    private Game game;

    public Fights(Player player, HostileMob mob, AdventureGUI gui) {
        gui.printText("Think fast! You see a " + mob.getName() + "... Let the fighting begin!");
        this.player = player;
        this.mob = mob;
        this.gui = gui;
    }

    public boolean runTurn() { //returns true or false depending on whether or not the fight is still active
        // gui.printText("A " + mob.getName() + " has appeared!");

        if (mob.getHp() <= 0) {
            return false;
        }

        int mobAttackDmg = (int) mob.getStats().getStrength();

        player.getStats().loseHP(mobAttackDmg);
        gui.printText("The " + mob.getName() + " attacks! You've taken " + mobAttackDmg + " damage!");

        if (player.getStats().getHp() <= 0) {
            gui.printText("Uh oh. You died... Back to the start!");
            game.revivePlayer();
            return false;
        }
        if (mob.isDefeated && !(player.getStats().isDead())) {
            gui.printText("Congrats! You've defeated the " + mob.getName() + "!");
            return false;
        }
        return true;
    }

    public HostileMob getMob() {
        return mob;
    }
}
