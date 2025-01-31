package minicraft.item;

class DefaultUpdateStrategy implements UpdateStrategy {
    @Override
    public void updateInventory(Inventory inventory, String itemData) {
        inventory.clear();
        if (itemData.isEmpty()) return;

        for (String itemStr : itemData.split(":")) {
            Item item = Items.get(itemStr);
            if (item != null) inventory.add(1, item);
        }
    }
}