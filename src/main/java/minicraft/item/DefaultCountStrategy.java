package minicraft.item;

class DefaultCountStrategy implements CountStrategy {
    @Override
    public int count(Inventory inventory, Item item) {
        int found = 0;
        for (Item currentItem : inventory.getItemsInternal()) {
            if (currentItem.equals(item)) {
                found++;
            }
        }
        return found;
    }
}