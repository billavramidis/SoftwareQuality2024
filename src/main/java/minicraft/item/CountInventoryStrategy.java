// Implements a strategy to count specific items in the inventory.
package minicraft.item;
import java.util.List;

public class CountInventoryStrategy implements InventoryStrategy {
    @Override
    public void execute(List<Item> items, Object... params) {
        // If a single Item is provided, count how many are in the inventory.
        if (params.length == 1 && params[0] instanceof Item) {
            int count = count(items, (Item) params[0]);
            System.out.println("Item count: " + count);
        }
    }

    // Counts the number of occurrences of an item, including stackable items.
    private int count(List<Item> items, Item item) {
        int found = 0;
        for (Item i : items) {
            if (i instanceof StackableItem && ((StackableItem) i).stacksWith(item)) {
                found += ((StackableItem) i).count;  // Count stackable items properly.
            } else if (i.equals(item)) {
                found++;
            }
        }
        return found;
    }
}
