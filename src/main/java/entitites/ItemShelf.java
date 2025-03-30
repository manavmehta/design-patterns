package entitites;

public class ItemShelf {
    public int code;
    public Item item;
    public int unitsLeft;

    public ItemShelf(int code, Item item, int unitsLeft){
        this.code = code;
        this.item = item;
        this.unitsLeft = unitsLeft;
    }

}
