package states.impl;

import entitites.Item;
import entitites.Note;
import entitites.VendingMachine;
import states.State;

import java.util.List;

public class SelectionState implements State {
    @Override
    public void clickInsertNoteButton(VendingMachine machine) throws Exception {
        throw new IllegalStateException("Please make a selection");
    }

    @Override
    public void clickSelectProductButton(VendingMachine machine) throws Exception {
        return;
    }

    @Override
    public void insertNote(VendingMachine machine, Note note) throws Exception {
        throw new IllegalStateException("Please make a selection or start again");
    }

    @Override
    public void chooseProduct(VendingMachine machine, int productCode) throws Exception {
        var item = machine.inventory.getItem(productCode);

        int moneyByUser = 0;
        for (var note: machine.moneyInserted)
            moneyByUser += note.value;

        if (moneyByUser >= item.price){
            System.out.println(getChange(moneyByUser - item.price));
            machine.state = new DispenseState();
        }
        else{
            refund(machine);
            machine.state = new IdleState();
        }
    }

    @Override
    public Item dispenseProduct(VendingMachine machine, int productCode) throws Exception {
        throw new IllegalStateException("Cannot dispense in current state");
    }

    @Override
    public List<Note> refund(VendingMachine machine) throws Exception {
        return machine.moneyInserted;

    }

    @Override
    public int getChange(int change) throws Exception {
        return change;
    }

    @Override
    public void updateInventory(VendingMachine machine, int productCode) throws Exception {
        throw new IllegalStateException("Please make a selection or start again");
    }
}
