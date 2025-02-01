package minicraft.item;
// Manages inventory items and executes strategies.
import java.util.*;

public class Inventory {
    private final List<Item> items = new ArrayList<>();  // Holds all inventory items.
    private final Map<Class<? extends InventoryStrategy>, InventoryStrategy> strategies = new HashMap<>();

    // Initializes the inventory with available strategies.
    public Inventory() {
        strategies.put(AddInventoryStrategy.class, new AddInventoryStrategy());
        strategies.put(RemoveInventoryStrategy.class, new RemoveInventoryStrategy());
        strategies.put(CountInventoryStrategy.class, new CountInventoryStrategy());
        strategies.put(UpdateInventoryStrategy.class, new UpdateInventoryStrategy());
        strategies.put(TryAddInventoryStrategy.class, new TryAddInventoryStrategy()); 
    }

    // Returns a copy of the items list to prevent external modifications.
    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    // Returns the number of items in the inventory.
    public int size() {
        return items.size();
    }

    // Clears all items from the inventory.
    public void clear() {
        items.clear();
    }

    // Retrieves an item at a specific index.
    public Item get(int index) {
        return items.get(index);
    }

    // Executes a strategy dynamically based on the provided class.
    public int executeStrategy(Class<? extends InventoryStrategy> strategyClass, Object... params) {
        InventoryStrategy strategy = strategies.get(strategyClass);
        if (strategy != null) {
            strategy.execute(items, params);
        }
                return 0;
    }

    public void addAll(Inventory other) {
        for (Item item : other.getItems()) {
            executeStrategy(AddInventoryStrategy.class, item.clone());
        }
    }

}
