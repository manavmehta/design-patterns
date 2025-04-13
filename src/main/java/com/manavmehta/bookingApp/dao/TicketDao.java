package manavmehta.bookingApp.dao;

import manavmehta.bookingApp.entities.Ticket;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TicketDao {

    Map<Integer, Ticket> tickets;

    public TicketDao(){
        this.tickets = new HashMap<>();
    }

    public void addTicket(Ticket ticket){
        tickets.put(ticket.getId(), ticket);
    }

    public Ticket getTicket(int ticketId){
        return tickets.get(ticketId);
    }
}
