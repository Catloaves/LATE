package Mobs;

import Game.Game;
import Game.AdventureGUI;
import Game.Player;

public class Fights {
    private Player player;
    private HostileMob mob;
    private AdventureGUI gui;
    private Game game;

    public Fights(Player player, HostileMob mob, AdventureGUI gui, Game game) {
        gui.printText("Think fast! You see a " + mob.getName() + "... Let the fighting begin!");
        this.player = player;
        this.mob = mob;
        this.gui = gui;
        this.game = game;
    }

    public boolean runTurn() {
        if (mob.getHp() <= 0 && !(player.getStats().isDead())) {
            gui.printText("Congrats! You've defeated the " + mob.getName() + "!");
            
            if (mob.getName().equalsIgnoreCase("Guardian")) {
                gui.printText("\nYou found the cure! With the medicine safely secured in your hands, " +
                             "you can finally return home to save your family.");
                game.endFight();
            }
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

        return true;
    }

    public HostileMob getMob() {
        return mob;
    }
}