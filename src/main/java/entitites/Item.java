package entitites;

public class Item {
    public ItemType type;
    public int price;

    // TODO: make private members and public setters and getters

    public Item(ItemType type, int price) {
        this.type = type;
        this.price = price;
    }
}
