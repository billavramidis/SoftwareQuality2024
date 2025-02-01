package minicraft.item;
import java.util.List;

// Implements a strategy to update the inventory based on external data.
public class UpdateInventoryStrategy implements InventoryStrategy {
    @Override
    public void execute(List<Item> items, Object... params) {
        // If a single String parameter is provided, update the inventory.
        if (params.length == 1 && params[0] instanceof String) {
            update(items, (String) params[0]);
        }
    }

    // Updates the inventory by clearing and repopulating it with new items.
    private void update(List<Item> items, String itemData) {
        items.clear();  // Clears all current items.
        if (itemData.length() == 0) return;  // If empty, do nothing.
        for (String item : itemData.split(":")) {
            items.add(Items.get(item));  // Adds new items from the provided data.
        }
    }
}
