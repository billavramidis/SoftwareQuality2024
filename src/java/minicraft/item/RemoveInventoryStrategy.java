package minicraft.item;

import java.util.List;

// Implements the strategy for removing items from the inventory.
public class RemoveInventoryStrategy implements InventoryStrategy {
    @Override
    public void execute(List<Item> items, Object... params) {
        // If a single Integer is provided, remove the item at that index.
        if (params.length == 1 && params[0] instanceof Integer) {
            remove(items, (Integer) params[0]);
        }
        // If a single Item is provided, remove that item from the inventory.
        else if (params.length == 1 && params[0] instanceof Item) {
            removeItem(items, (Item) params[0]);
        }
    }

    // Removes an item at a given index.
    private void remove(List<Item> items, int index) {
        items.remove(index);
    }

    // Removes all occurrences of a specific item.
    private void removeItem(List<Item> items, Item item) {
        items.removeIf(i -> i.equals(item));  // Removes all matching items.
    }
}
