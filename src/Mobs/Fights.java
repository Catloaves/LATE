package Mobs;

import Game.AdventureGUI;
import Game.Game;
import Game.Player;

public class Fights {
    private Player player;
    private HostileMob mob;
    private AdventureGUI gui;
    private Game game;

    public Fights(Player player, HostileMob mob, AdventureGUI gui, Game game) {
        this.player = player;
        this.mob = mob;
        this.gui = gui;
        this.game = game;
    }

    public HostileMob getMob() {
        return this.mob;
    }

    public void startCombatMessage() {
        gui.printText("Think fast! You see a " + mob.getName() + "... Let the fighting begin!");
    }

    public boolean runTurn() {
        int playerDamage = (int) (Math.random() * 5) + 5;

        if (player.hasItem("sword")) {
            playerDamage += 15;
            gui.printText("You slash the " + mob.getName() + " with your steel sword!");
        } else if (player.hasItem("knife")) {
            playerDamage += 5;
            gui.printText("You stab the " + mob.getName() + " with your utility knife!");
        } else {
            gui.printText("You punch the " + mob.getName() + " with your bare fists!");
        }

        if (Math.random() < 0.2) {
            playerDamage *= 2;
            gui.printText("CRITICAL HIT!");
        }

        mob.subtractHp(playerDamage); 
        gui.printText("You dealt " + playerDamage + " damage! (" + mob.getName() + " HP: " + Math.max(0, mob.getHp()) + ")");

        if (mob.getHp() <= 0) {
            gui.printText("Congrats! You've defeated the " + mob.getName() + "!");
            if (mob.getName().equalsIgnoreCase("Guardian")) {
                gui.printText("\nYou found the cure! You return home and save your family. YOU WIN!");
                game.endGame(); 
            }
            game.endFight();
            return false;
        }

        int mobAttackDmg = mob.getDamage(); 
        player.getStats().loseHP(mobAttackDmg);
        gui.printText("The " + mob.getName() + " attacks back! You've taken " + mobAttackDmg + " damage!");

        if (player.getStats().getHp() <= 0) {
            gui.printText("Uh oh. You died... Back to the start!");
            game.revivePlayer();
            return false;
        }

        return true;
    }
}