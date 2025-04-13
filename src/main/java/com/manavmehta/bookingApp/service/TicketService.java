package manavmehta.bookingApp.service;

import manavmehta.bookingApp.dao.TicketDao;
import manavmehta.bookingApp.dependencies.Pair;
import manavmehta.bookingApp.entities.Show;
import manavmehta.bookingApp.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    TicketDao ticketDao;

    @Autowired
    ShowService showService;

    public List<Pair<Integer, Integer>> bookTicket(int ticketId, int showId, int ticketsCount) {
        var show = showService.getShow(showId);
        Ticket ticket = new Ticket(ticketId, showId, ticketsCount);

        List<Pair<Integer, Integer>> bookedSeats = ticketsCount <= show.getFreeSeats()
                ? bookSeatsInLine(show, ticketsCount)
                : getDisconnectedSeats(show, ticketsCount);

        ticket.setSeats(bookedSeats);
        ticketDao.addTicket(ticket);
        show.setFreeSeats(show.getFreeSeats() - bookedSeats.size());

        return bookedSeats;
    }

    public ArrayList<Pair<Integer, Integer>> bookSeatsInLine(Show show, int ticketsCount) {
        var seats = show.getSeats();
        int rows = show.getRows();
        int cols = show.getCols();

        for (int i = 0; i < rows; i++) {
            ArrayList<Pair<Integer, Integer>> temp = new ArrayList<>();
            int booked = 0;

            for (int j = 0; j < cols && booked < ticketsCount; j++) {
                if (!seats[i][j]) {
                    temp.add(new Pair<>(i, j));
                    booked++;
                } else {
                    temp.clear();
                    booked = 0;
                }
            }

            if (booked == ticketsCount) {
                return temp;
            }
        }

        return new ArrayList<>();
    }

    public ArrayList<Pair<Integer, Integer>> getDisconnectedSeats(Show show, int ticketsCount) {
        var seats = show.getSeats();
        int rows = show.getRows();
        int cols = show.getCols();

        for (int i = 0; i < rows; i++) {
            ArrayList<Pair<Integer, Integer>> temp = new ArrayList<>();
            int booked = 0;

            for (int j = 0; j < cols && booked < ticketsCount; j++) {
                if (seats[i][j]) {
                    temp.add(new Pair<>(i, j));
                    booked++;
                } else {
                    temp.clear();
                    booked = 0;
                }
            }

            if (booked == ticketsCount) {
                return temp;
            }
        }

        return new ArrayList<>();
    }

    public boolean cancelTicket(int ticketId) {
        Ticket ticket = ticketDao.getTicket(ticketId);
        if (ticket == null || ticket.isCancelled()) return false;

        var show = showService.getShow(ticket.getShowId());
        for (Pair<Integer, Integer> seat : ticket.getSeats()) {
            show.getSeats()[seat.first()][seat.second()] = false;
        }

        show.setFreeSeats(show.getFreeSeats() + ticket.getSeats().size());
        ticket.setCancelled(true);
        return true;
    }
}
