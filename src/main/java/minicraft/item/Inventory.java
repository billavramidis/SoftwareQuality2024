package minicraft.item;


import java.util.*;

public class Inventory {
    private final List<Item> items = new ArrayList<>();

    private AdditionStrategy addStrategy = new DefaultAddStrategy();
    private RemoveStrategy removeStrategy = new DefaultRemoveStrategy();
    private TryAddStrategy tryAddStrategy = new DefaultTryAddStrategy();
    private CountStrategy countStrategy = new DefaultCountStrategy();
    private UpdateStrategy updateStrategy = new DefaultUpdateStrategy();

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    protected List<Item> getItemsInternal() {
        return items;
    }

    public void setAddStrategy(AdditionStrategy strategy) {
        this.addStrategy = strategy;
    }

    public void setRemoveStrategy(RemoveStrategy strategy) {
        this.removeStrategy = strategy;
    }

    public void setTryAddStrategy(TryAddStrategy strategy) {
        this.tryAddStrategy = strategy;
    }

    public void setCountStrategy(CountStrategy strategy) {
        this.countStrategy = strategy;
    }

    public void setUpdateStrategy(UpdateStrategy strategy) {
        this.updateStrategy = strategy;
    }

    public void add(int amount, Item item) {
        if (item != null) {
            addStrategy.add(this, item, amount);
        }
    }

    // New method to add all items from another inventory using the same strategy
    public void addAll(Inventory otherInventory) {
        for (Item item : otherInventory.getItems()) {
            // For each item in the other inventory, add it using the same logic as for a single item
            add(items.size(), item); // Add the item at the end of this inventory
        }
    }
    
    public boolean removeItem(Item item, int amount) {
        return removeStrategy.remove(this, item, amount);
    }

    public void tryAdd(int chance, Item item, int amount, boolean allOrNothing) {
        if (item != null) {
            tryAddStrategy.tryAdd(this, item, chance, amount, allOrNothing);
        }
    }

    public int count(Item item) {
        return countStrategy.count(this, item);
    }

    public void updateInventory(String itemData) {
        updateStrategy.updateInventory(this, itemData);
    }

    public int size() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public Item get(int index) {
        return items.get(index);
    }

    public Item remove(int index) {
        return items.remove(index);
    }
}
