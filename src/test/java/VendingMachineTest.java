import entitites.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import states.impl.DispenseState;
import states.impl.IdleState;
import states.impl.InputNotesStage;
import states.impl.SelectionState;

import java.util.List;

import static entitites.ItemType.COCA_COLA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VendingMachineTest {
    private VendingMachine machine;
    ItemShelf cocaCola;
    ItemShelf biscuits;
    ItemShelf juice;
    ItemShelf seeds;

    @BeforeEach
    void setup() {
        machine = new VendingMachine();

        cocaCola = (new ItemShelf(101, new Item(COCA_COLA, 40), 2));
        biscuits = (new ItemShelf(102, new Item(ItemType.BISCUITS, 10), 1));
        juice = (new ItemShelf(103, new Item(ItemType.JUICE, 20), 2));
        seeds = (new ItemShelf(104, new Item(ItemType.SEEDS, 50), 0));

        machine.inventory.initInventory(List.of(cocaCola, biscuits, juice, seeds));
    }

    @Test
    public void happyFlow() throws Exception {
        assertEquals(machine.state.getClass(), IdleState.class);
        machine.state.clickInsertNoteButton(this.machine);

        assertEquals(machine.state.getClass(), InputNotesStage.class);
        machine.state.insertNote(machine, Note.TWENTY);

        assertEquals(machine.state.getClass(), InputNotesStage.class);
        machine.state.insertNote(machine, Note.TWENTY);

        assertEquals(machine.state.getClass(), InputNotesStage.class);
        machine.state.clickSelectProductButton(this.machine);

        assertEquals(machine.state.getClass(), SelectionState.class);
        machine.state.chooseProduct(machine, 101);

        assertEquals(machine.state.getClass(), DispenseState.class);
        var product = machine.state.dispenseProduct(machine, 101);

        assertEquals(product, cocaCola.item);

        assertEquals(machine.state.getClass(), IdleState.class);
    }

    @Test
    public void productUnavailable() throws Exception {
        machine.state.clickInsertNoteButton(this.machine);
        machine.state.insertNote(machine, Note.TWENTY);
        machine.state.clickSelectProductButton(this.machine);
        assertThrows(Exception.class, () -> machine.state.chooseProduct(machine, 104));
    }
}
