package Game.Shop;

import javax.swing.JOptionPane;

import Game.Player;
import Items.Item;
import Items.bags.Knapsack;
import Items.bags.Pouch;
import Items.misc.StartingMap;

public class Mercery extends Shop {

    private final int KNAPSACK_PRICE = Knapsack.GCPriceKnapsack;
    private final int POUCH_PRICE = Pouch.GCPricePouch;
    private final int STARTINGMAP_PRICE = StartingMap.GCPriceStMap;

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
                    + "\t1) Knapsack: " + KNAPSACK_PRICE + " gold\n"
                    + "\t2) Pouch: " + POUCH_PRICE + " gold\n"
                    + "\t3) Starting Map: " + STARTINGMAP_PRICE + " gold\n"
                    + "\t4) 𝕰𝖝𝖎𝖙\n\n"
                    + "Enter the number of your choice:";

            String input = JOptionPane.showInputDialog(null, menu);

            if (input == null || input.equals("4")) {
                isShopping = false;
            } else if (input.equals("1")) {
                buyItem(p,
                        new Knapsack("knapsack", "Knapsack", "Increases your inventory space by 5 (exclusive) slots!"),
                        KNAPSACK_PRICE);
            } else if (input.equals("2")) {
                buyItem(p, new Pouch("pouch", "Pouch", "Increases your inventory space by 2 (exclusive) slots!"),
                        POUCH_PRICE);
            } else if (input.equals("3")) {
                buyItem(p, new StartingMap("starting_map", "Starting Map", "A handy little map with some detail on how to naviagate your way to and through the King's castle!"),
                        STARTINGMAP_PRICE);
                JOptionPane.showMessageDialog(null, "Uh oh. Invalid selection - please try again or exit the shop.");
            }
        }
    }
}
