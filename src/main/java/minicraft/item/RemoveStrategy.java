package minicraft.item;


public interface RemoveStrategy {
    boolean remove(Inventory inventory, Item item, int amount);
}
