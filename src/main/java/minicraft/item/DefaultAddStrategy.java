package minicraft.item;


import java.util.*;


import org.jetbrains.annotations.Nullable;
import org.tinylog.Logger;

import minicraft.entity.furniture.Furniture;
import minicraft.item.StackableItem;

public class DefaultAddStrategy implements AdditionStrategy{
    @Override
    public void add(Inventory inventory, Item item, int amount) {
        if (item instanceof StackableItem stackItem) {
            for (Item existingItem : inventory.getItemsInternal()) {
                if (existingItem instanceof StackableItem existingStack && existingStack.stacksWith(item)) {
                    existingStack.count += stackItem.count;
                    return;
                }
            }
        }
        for (int i = 0; i < amount; i++) {
            inventory.getItemsInternal().add(item.clone());
        }
    }
}
