package minicraft.item;


import java.util.*;



class DefaultTryAddStrategy implements TryAddStrategy {
    private final Random random = new Random();

    @Override
    public void tryAdd(Inventory inventory, Item item, int chance, int amount, boolean allOrNothing) {
        if (!allOrNothing || random.nextInt(chance) == 0) {
            for (int i = 0; i < amount; i++) {
                if (allOrNothing || random.nextInt(chance) == 0) {
                    inventory.add(1, item.clone());
                }
            }
        }
    }
}