package minicraft.item;

import java.util.List;

// Defines a strategy interface for inventory operations.
public interface InventoryStrategy {
    // Each strategy must implement this method to execute an inventory action.
    void execute(List<Item> items, Object... params);
}
