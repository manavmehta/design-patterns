package states.impl;

import entitites.Item;
import entitites.Note;
import entitites.VendingMachine;
import states.State;

import java.util.List;

public class InputNotesStage implements State {
    @Override
    public void clickInsertNoteButton(VendingMachine machine) throws Exception {
        throw new IllegalStateException("Already in insertion mode, please insert notes.");
    }

    @Override
    public void clickSelectProductButton(VendingMachine machine) throws Exception {
        machine.state = new SelectionState();
    }

    @Override
    public void insertNote(VendingMachine machine, Note note) throws Exception {
        machine.moneyInserted.add(note);
    }

    @Override
    public void chooseProduct(VendingMachine machine, int productCode) throws Exception {
        throw new IllegalStateException("Please insert notes first.");
    }

    @Override
    public Item dispenseProduct(VendingMachine machine, int productCode) throws Exception {
        throw new IllegalStateException("Please insert notes first.");
    }

    @Override
    public List<Note> refund(VendingMachine machine) throws Exception {
        var refund = machine.moneyInserted;
        machine.moneyInserted.clear();
        machine.state = new IdleState();
        return refund;
    }

    @Override
    public int getChange(int change) throws Exception {
        throw new IllegalStateException("Please ask for full refund.");
    }

    @Override
    public void updateInventory(VendingMachine machine, int productCode) throws Exception {
        throw new IllegalStateException("Please insert notes first.");
    }
}
