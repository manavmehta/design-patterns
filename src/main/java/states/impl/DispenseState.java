package states.impl;

import entitites.Item;
import entitites.Note;
import entitites.VendingMachine;
import states.State;

import java.util.List;

public class DispenseState  implements State {
    @Override
    public void clickInsertNoteButton(VendingMachine machine) throws Exception {
        throw new IllegalStateException("Cannot click insert notes button in current state");
    }

    @Override
    public void clickSelectProductButton(VendingMachine machine) throws Exception {
        throw new IllegalStateException("Please wait for your product");
    }

    @Override
    public void insertNote(VendingMachine machine, Note note) throws Exception {
        throw new IllegalStateException("Please wait for your product");
    }

    @Override
    public void chooseProduct(VendingMachine machine, int productCode) throws Exception {
        throw new IllegalStateException("Product already chosen");
    }

    @Override
    public Item dispenseProduct(VendingMachine machine, int productCode) throws Exception {
        var item = machine.inventory.getItem(productCode);
        System.out.println("Dispensing: " + item.type);
        machine.state = new IdleState();
        return item;
    }

    @Override
    public List<Note> refund(VendingMachine machine) throws Exception {
        throw new IllegalStateException("No refund in current state");
    }

    @Override
    public int getChange(int change) throws Exception {
        throw new IllegalStateException("Cannot get change in current state");
    }

    @Override
    public void updateInventory(VendingMachine machine, int productCode) throws Exception {

    }
}
