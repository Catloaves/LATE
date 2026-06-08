package Game;

import javax.swing.JOptionPane;
import Items.Item;
import Items.misc.Torch;
import Items.tools.Sword;

public class Shop {
    private final int TORCH_PRICE = 10;
    private final int FOOD_PRICE = 5;
    private final int ARROW_PRICE = 15;
    private final int WEAPON_PRICE = 50;
    private final int ARMOUR_PRICE = 75;

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
                    + "You have " + p.getGold() + " gold\n\n"
                    + "1. Torch: " + TORCH_PRICE + " gold\n"
                    + "2. Food: " + FOOD_PRICE + " gold\n"
                    + "3. Arrows: " + ARROW_PRICE + " gold\n"
                    + "4. Steel Sword: " + WEAPON_PRICE + " gold\n"
                    + "5. Basic Armour: " + ARMOUR_PRICE + " gold\n"
                    + "6. Exit\n\n"
                    + "Enter the number of your choice:";

            String input = JOptionPane.showInputDialog(null, menu);

            if (input == null || input.equals("6")) {
                isShopping = false;
            } else if (input.equals("1")) {
                buyItem(p, new Torch("torch", "Torch", "A bright shop-bought torch."), TORCH_PRICE);
            } else if (input.equals("2")) {
                buyItem(p, new Item("food", "Food", "Delicious rations."), FOOD_PRICE);
            } else if (input.equals("3")) {
                buyItem(p, new Item("arrows", "Arrows", "Sharp ammunition."), ARROW_PRICE);
            } else if (input.equals("4")) {
                // FIXED: Creates a real Sword object with lowercase ID so Fights.java activates it!
                buyItem(p, new Sword("sword", "sword", "A sharp steel arming sword."), WEAPON_PRICE);
            } else if (input.equals("5")) {
                buyItem(p, new Item("armour", "Basic Armour", "Protective leather gear."), ARMOUR_PRICE);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid selection.");
            }
        }
    }
}