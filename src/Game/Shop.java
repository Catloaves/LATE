package Game;
import javax.swing.JOptionPane;

import Items.Item;

public class Shop {
    private final int TORCH_PRICE = 10;
    private final int FOOD_PRICE = 5;
    private final int ARROW_PRICE = 15;
    private final int WEAPON_PRICE = 50;
    private final int ARMOUR_PRICE = 75;

    private void buyItem(Player p, String name, int cost) {
        if (p.spendGold(cost)) {
            p.addItem(new Item(name, name, name));
            JOptionPane.showMessageDialog(null, "Purchase successful: " + name);
        }
    }

    public void openShop(Player p) {
        boolean isShopping = true;

        while (isShopping) {
            String menu = "SHOP\n\n"
                    + "You have " + p.getGold() + " gold\n\n"
                    + "1. Torch: " + TORCH_PRICE + " gold\n"
                    + "2. Food: " + FOOD_PRICE + " gold\n"
                    + "3. Arrows: " + ARROW_PRICE + " gold\n"
                    + "4. Basic Weapon: " + WEAPON_PRICE + " gold\n"
                    + "5. Basic Armour: " + ARMOUR_PRICE + " gold\n"
                    + "6. Exit\n\n"
                    + "Enter the number of your choice:";

            String input = JOptionPane.showInputDialog(null, menu);

            if (input == null || input.equals("6")) {
                isShopping = false;
            } else if (input.equals("1")) {
                buyItem(p, "Torch", TORCH_PRICE);
            } else if (input.equals("2")) {
                buyItem(p, "Food", FOOD_PRICE);
            } else if (input.equals("3")) {
                buyItem(p, "Arrows", ARROW_PRICE);
            } else if (input.equals("4")) {
                buyItem(p, "Basic Weapon", WEAPON_PRICE);
            } else if (input.equals("5")) {
                buyItem(p, "Basic Armour", ARMOUR_PRICE);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid selection.");
            }
        }
    }
}