package Game.Shop;

import javax.swing.JOptionPane;

import Game.Player;
import Items.Item;
import Items.potions.HealingPot;
import Items.potions.InvisPot;
import Items.potions.StrengthPot;

public class Apothecary extends Shop {

    private final int HEALINGPOT_PRICE = HealingPot.GCPriceHealingPot;
    private final int INVISPOT_PRICE = InvisPot.GCPriceInvisPot;
    private final int STRENGTHPOT_PRICE = StrengthPot.GCPriceStrengthPot;

    private void buyItem(Player p, Item item, int cost) {
        if (p.spendGold(cost)) {
            p.addItem(item);
            JOptionPane.showMessageDialog(null, "Purchase successful: " + item.getName());
        }
    }

    public void openShop(Player p) {
        boolean isShopping = true;

        while (isShopping) {
            String menu = "SHOP\n\n"
                    + "You have " + p.getGold() + " gold as of current!\n\n"
                    + "\t1) Healing Potion: " + HEALINGPOT_PRICE + " gold\n"
                    + "\t2) Invisibility Potion: " + INVISPOT_PRICE + " gold\n"
                    + "\t3) Strength Potion: " + STRENGTHPOT_PRICE + " gold\n"
                    + "\t4) 𝕰𝖝𝖎𝖙\n\n"
                    + "Enter the number of your choice:";

            String input = JOptionPane.showInputDialog(null, menu);

            if (input == null || input.equals("3")) {
                isShopping = false;
            } else if (input.equals("1")) {
                buyItem(p,
                        new HealingPot("healing_pot", "Healing Potion", "Increases your HP by 30%, up to your maximum!"),
                        HEALINGPOT_PRICE);
            } else if (input.equals("2")) {
                buyItem(p, new InvisPot("invis_pot", "Invisibility Potion", "Drink this to become invisible to mobs for your next ten turns!"),
                        INVISPOT_PRICE);
            } else if (input.equals("2")) {
                buyItem(p, new StrengthPot("strength_pot", "Strength Potion", ""),
                        STRENGTHPOT_PRICE);
                JOptionPane.showMessageDialog(null, "Uh oh. Invalid selection - please try again or exit the shop.");
            }
        }
    }
}