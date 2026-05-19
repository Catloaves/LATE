import java.util.Scanner;
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
            System.out.println("Purchase successful: " + name);
        }
    }

    public void openShop(Player p) {
        Scanner scanner = new Scanner(System.in);
        boolean isShopping = true;

        while (isShopping) {
            System.out.println("SHOP");
            System.out.println("You have " + p.getGold() + " gold");
            System.out.println("1. Torch: " + TORCH_PRICE + " gold");
            System.out.println("2. Food: " + FOOD_PRICE + " gold");
            System.out.println("3. Arrows: " + ARROW_PRICE + " gold");
            System.out.println("4. Basic Weapon: " + WEAPON_PRICE + " gold");
            System.out.println("5. Basic Armour: " + ARMOUR_PRICE + " gold");
            System.out.println("6. Exit");

            String input = scanner.nextLine();

            if (input.equals("1")) {
                buyItem(p, "Torch", TORCH_PRICE);
            } else if (input.equals("2")) {
                buyItem(p, "Food", FOOD_PRICE);
            } else if (input.equals("3")) {
                buyItem(p, "Arrows", ARROW_PRICE);
            } else if (input.equals("4")) {
                buyItem(p, "Basic Weapon", WEAPON_PRICE);
            } else if (input.equals("5")) {
                buyItem(p, "Basic Armour", ARMOUR_PRICE);
            } else if (input.equals("6")) {
                isShopping = false;
            } else {
                System.out.println("Invalid selection.");
            }
        }
    }
}