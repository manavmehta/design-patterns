package entitites;

import java.util.List;

public class Inventory {
    public List<ItemShelf> inventory;

    public void initInventory(List<ItemShelf> inventory) {
        this.inventory = inventory;
    }
    public Item getItem(int codeNumber) throws Exception {
        for (var shelf: inventory){
            if (shelf.code == codeNumber){
                if (shelf.unitsLeft == 0){
                    throw new Exception("Item is not in inventory");
                }
                updateInventory(shelf.code, shelf.unitsLeft - 1);
                return shelf.item;
            }
        }
        throw new Exception("Invalid item code");
    }
    public void updateInventory(int codeNumber, int quantity){
        for (var shelf: inventory){
            if (shelf.code == codeNumber){
                shelf.unitsLeft = quantity;
            }
        }
    }
}
