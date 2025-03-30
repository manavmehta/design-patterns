package states;

import entitites.Item;
import entitites.Note;
import entitites.VendingMachine;

import java.util.List;

public interface State {
    void clickInsertNoteButton(VendingMachine machine) throws Exception;
    void clickSelectProductButton(VendingMachine machine) throws Exception;
    void insertNote(VendingMachine machine, Note note) throws Exception;
    void chooseProduct(VendingMachine machine, int productCode) throws Exception;
    Item dispenseProduct(VendingMachine machine, int productCode) throws Exception;
    List<Note> refund(VendingMachine machine) throws Exception;
    int getChange(int change) throws Exception;
    void updateInventory(VendingMachine machine, int productCode) throws Exception;
}
