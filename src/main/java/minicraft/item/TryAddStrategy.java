package minicraft.item;

public interface TryAddStrategy {
    void tryAdd(Inventory inventory, Item item, int amount, int amount2, boolean allOrNothing);
}
