import dependencies.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookingManagerTest {
    BookingManager bookingManager;
    @BeforeEach
    void setUp(){
        bookingManager = BookingManager.getInstance();
    }

    @Test
    void check(){
        bookingManager.addCinema(0, 1, 4,5, 10);
        bookingManager.addShow(1,4, 0, 1, 1710516108725L, 1710523308725L);
        bookingManager.addShow(2,11, 0, 3, 1710516108725L, 1710523308725L);

        assertTrue(bookingManager.listCinemas(0, 1).isEmpty());
        assertEquals(List.of(1), bookingManager.listShows(4, 0));
        assertEquals(List.of(2), bookingManager.listShows(11, 0));

        assertEquals(50, bookingManager.getFreeSeatsCount(1));
        var ticket1Seats = bookingManager.bookTicket(1, 1, 4);
        assertEquals(List.of(new Pair<>(0, 0), new Pair<>(0, 1), new Pair<>(0, 2), new Pair<>(0, 3)), ticket1Seats);

        assertEquals(46, bookingManager.getFreeSeatsCount(1));
        assertTrue(bookingManager.cancelTicket(1));
        assertEquals(50, bookingManager.getFreeSeatsCount(1));
    }
}
