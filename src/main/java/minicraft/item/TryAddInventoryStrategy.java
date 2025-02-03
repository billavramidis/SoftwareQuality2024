package minicraft.item;

import java.util.List;

// Implements the strategy for trying to add an item to the inventory at a specific position.
public class TryAddInventoryStrategy implements InventoryStrategy {
    @Override
    public void execute(List<Item> items, Object... params) {
        // Check if the parameters are valid (position, item, and quantity)
        if (params.length == 3 && params[0] instanceof Integer && params[1] instanceof Item) {
            int slot = (Integer) params[0];  // The position in the inventory
            Item item = (Item) params[1];    // The item to be added
            int count = (params.length < 3) ? 1 : (Integer) params[2]; // The quantity to be added, default to 1

            // Try to add the item to the inventory
            add(items, slot, item, count);
        }
    }

    // Helper method to add the item to the inventory at the specified position
    private void add(List<Item> items, int slot, Item item, int count) {
        if (item != null) {
            // If the item is stackable, try to stack it with existing items
            if (item instanceof StackableItem) {
                StackableItem stackableItem = (StackableItem) item;
                // Iterate through the inventory items to find if it can be stacked with an existing item
                for (Item invItem : items) {
                    if (invItem instanceof StackableItem && ((StackableItem) invItem).stacksWith(stackableItem)) {
                        // Increase the quantity of the existing stackable item
                        ((StackableItem) invItem).count += count;
                        return;  // Exit after stacking
                    }
                }
            }

            if (slot >= 0 && slot <= items.size()) {
                items.add(slot, item);
            } else {
                items.add(item);  // Add at the end as a fallback
            }
        }
    }
}
