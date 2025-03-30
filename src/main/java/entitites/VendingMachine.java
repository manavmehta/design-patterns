package entitites;

import states.State;
import states.impl.IdleState;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {
    public State state;
    public List<Note> moneyInserted = new ArrayList<>();
    public Inventory inventory;

    public VendingMachine() {
        this.state = new IdleState();
        this.inventory = new Inventory();
    }
}
