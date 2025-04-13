package manavmehta.bookingApp.entities;

import lombok.Data;
import org.springframework.data.util.Pair;

import java.util.List;

@Data
public class Ticket {
    private int id;
    private int showId;
    private int ticketsCount;
    private boolean cancelled;
    private List<Pair<Integer, Integer>> seats;

    public Ticket(int id, int showId, int ticketsCount){
        this.id=id;
        this.showId=showId;
        this.ticketsCount=ticketsCount;
    }
}
