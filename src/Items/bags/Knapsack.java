package Items.bags;

import Items.Item;

public class Knapsack extends Item {
    final public static int GCPriceKnapsack = 10;
    final public static int size = 5;
    public String[] packItems;

    public Knapsack(String id, String name, String description) {
        super(id, name, description);
    }
    
    // public int getSize() {
    //     return size;
    // }
    
    // public int getGCPriceKnapsack() {
    //     return GCPriceKnapsack;
    // }
}