// Implements the strategy for adding items to the inventory.
package minicraft.item;
import java.util.List;

public class AddInventoryStrategy implements InventoryStrategy {
    @Override
    public void execute(List<Item> items, Object... params) {
        // Checks if a single Item is provided, then calls the appropriate add method.
        if (params.length == 1 && params[0] instanceof Item) {
            add(items, (Item) params[0]);
        }
        // Checks if an index (Integer) and an Item are provided, then calls another add method.
        else if (params.length == 2 && params[0] instanceof Integer && params[1] instanceof Item) {
            add(items, (Integer) params[0], (Item) params[1]);
        }
    }

    // Adds an item at the last available position in the inventory.
    private void add(List<Item> items, Item item) {
        if (item != null) {
            add(items, items.size(), item);  // Adds at the end of the list.
        }
    }

    // Adds an item at a specific position in the inventory.
    private void add(List<Item> items, int slot, Item item) {
        // If the item is stackable, try merging it with an existing stack.
        if (item instanceof StackableItem) {
            StackableItem toTake = (StackableItem) item;
            for (Item invItem : items) {
                if (invItem instanceof StackableItem && ((StackableItem) invItem).stacksWith(toTake)) {
                    ((StackableItem) invItem).count += toTake.count; // Increase count if it stacks.
                    return;
                }
            }
        }
        // If not stackable or no stack was found, add it to the specified slot.
        items.add(slot, item);
    }
}
