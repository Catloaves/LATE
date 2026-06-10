package Mobs;

import Game.AdventureGUI;
import Game.Game;
import Game.Player;

public class PetAlligator {

    private Game game = new Game();
    private AdventureGUI gui = new AdventureGUI(game);
    public static int alligatorHp = 0;
    public static int gatorHpMax = 150;
    Player player = new Player();

    public void healAlligator() {

        if (player.hasItem("cure") && player.hasItem("the power of friendship")) {
            alligatorHp = gatorHpMax;
            gui.printText(
                    "ℂ𝕠𝕟𝕘𝕣𝕒𝕥𝕤!!!\nYour alligator is now saved! The magic will erase the corruption from him and he will be free from the clutches of the king. Now... who else needs saving? You look around and suddenly realize that a light, sheer layer of some kind of red, slub-like coating has now shrouded over everyone... and your hands start to feel funny.\n\n𝕋ℍ𝔼 𝔼ℕ𝔻 𝕆𝔽 ℙ𝔸ℝ𝕋 𝕆ℕ𝔼\n\n𝕋ℍ𝔸ℕ𝕂𝕊 𝔽𝕆ℝ ℙ𝕃𝔸𝕐𝕀ℕ𝔾");
        } else {
            gui.printText("Hmm... you're missing something.");
        }
    }
}