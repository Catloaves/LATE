package Game.Shop;

import javax.swing.JOptionPane;

import Game.Player;
import Items.Item;
import Items.tools.Axe;
import Items.tools.Sword;

public class Blacksmith extends Shop {

    private final int SWORD_PRICE = Sword.GCPriceSword;
    private final int AXE_PRICE = Axe.GCPriceAxe;

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
                    + "\t1) Sword: " + SWORD_PRICE + " gold\n"
                    + "\t2) Axe: " + AXE_PRICE + " gold\n"
                    + "\t3) 𝕰𝖝𝖎𝖙\n\n"
                    + "Enter the number of your choice:";

            String input = JOptionPane.showInputDialog(null, menu);

            if (input == null || input.equals("3")) {
                isShopping = false;
            } else if (input.equals("1")) {
                buyItem(p,
                        new Sword("sword", "Sword", "Increases your inventory space by 5 (exclusive) slots!"),
                        SWORD_PRICE);
            } else if (input.equals("2")) {
                buyItem(p, new Axe("axe", "Axe", "Increases your inventory space by 2 (exclusive) slots!"),
                        AXE_PRICE);
                JOptionPane.showMessageDialog(null, "Uh oh. Invalid selection - please try again or exit the shop.");
            }
        }
    }
}
