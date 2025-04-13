import manavmehta.bookingApp.BookingApplication;
import manavmehta.bookingApp.dao.CinemaDao;
import manavmehta.bookingApp.dao.CityDao;
import manavmehta.bookingApp.dao.ShowDao;
import manavmehta.bookingApp.dao.TicketDao;
import manavmehta.bookingApp.service.CinemaService;
import manavmehta.bookingApp.service.CityService;
import manavmehta.bookingApp.service.ShowService;
import manavmehta.bookingApp.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = BookingApplication.class)
public class BookingManagerTest {
    @Autowired
    CinemaService cinemaService;

    @Autowired
    CityService cityService;

    @Autowired
    ShowService showService;

    @Autowired
    TicketService ticketService;


    @Autowired
    CinemaDao cinemaDao;

    @Autowired
    CityDao cityDao;

    @Autowired
    ShowDao showDao;

    @Autowired
    TicketDao ticketDao;

    @BeforeEach
    void setUp(){

    }

    @Test
    void check(){
        cinemaService.addCinema(0, 1, 4,5, 10);
        cinemaService.addShow(1,4, 0, 1, 1710516108725L, 1710523308725L);
        cinemaService.addShow(2,11, 0, 3, 1710516108725L, 1710523308725L);

        assertTrue(cityService.listCinemas(0, 1).isEmpty());
        assertEquals(List.of(1), cinemaService.listShows(4, 0));
        assertEquals(List.of(2), cinemaService.listShows(11, 0));

        assertEquals(50, showService.getFreeSeatsCount(1));
        var ticket1Seats = ticketService.bookTicket(1, 1, 4);
        assertEquals(List.of(Pair.of(0, 0), Pair.of(0, 1), Pair.of(0, 2), Pair.of(0, 3)), ticket1Seats);

        assertEquals(46, showService.getFreeSeatsCount(1));
        assertTrue(ticketService.cancelTicket(1));
        assertEquals(50, showService.getFreeSeatsCount(1));
    }
}
