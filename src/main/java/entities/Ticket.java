package entities;

import dependencies.Pair;

import java.util.List;

public class Ticket {
    public int id;
    public int showId;
    public int ticketsCount;
    public boolean cancelled;
    public List<Pair<Integer, Integer>> seats;

    public Ticket(int id, int showId, int ticketsCount){
        this.id=id;
        this.showId=showId;
        this.ticketsCount=ticketsCount;
    }

    public void setSeats(List<Pair<Integer, Integer>> seats) {
        this.seats = seats;
    }
}
