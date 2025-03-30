package states.impl;

import entitites.Item;
import entitites.Note;
import entitites.VendingMachine;
import states.State;

import java.util.List;

public class IdleState implements State {

    public IdleState() {
        System.out.println("Vending machine is idle.");
    }

    @Override
    public void clickInsertNoteButton(VendingMachine machine) throws Exception {
        machine.state = new InputNotesStage();
    }

    @Override
    public void clickSelectProductButton(VendingMachine machine) throws Exception {
        throw new IllegalStateException("Please press Insert Note Button and load money.");
    }

    @Override
    public void insertNote(VendingMachine machine, Note note) throws Exception {
        throw new IllegalStateException("Please press Insert Note Button and load money.");
    }

    @Override
    public void chooseProduct(VendingMachine machine, int productCode) throws Exception {
        throw new IllegalStateException("Please press Insert Note Button and load money.");
    }

    @Override
    public Item dispenseProduct(VendingMachine machine, int productCode) throws Exception {
        throw new IllegalStateException("Please press Insert Note Button and load money.");
    }

    @Override
    public List<Note> refund(VendingMachine machine) throws Exception {
        throw new IllegalStateException("Please press Insert Note Button and load money.");
    }

    @Override
    public int getChange(int change) throws Exception {
        throw new IllegalStateException("Please press Insert Note Button and load money.");
    }

    @Override
    public void updateInventory(VendingMachine machine, int productCode) throws Exception {
        throw new IllegalStateException("Please press Insert Note Button and load money.");
    }
}
