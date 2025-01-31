package minicraft.item;


import java.util.*;

public class DefaultRemoveStrategy implements RemoveStrategy{
    @Override
    public boolean remove(Inventory inventory, Item item, int amount) {
        List<Item> items = inventory.getItemsInternal();
        int removed = 0;

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).equals(item)) {
                items.remove(i--);
                removed++;
                if (removed == amount) return true;
            }
        }
        return false;
    }
}
