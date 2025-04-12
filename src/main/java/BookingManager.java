import dependencies.Pair;
import entities.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BookingManager {
    Map<Integer, Cinema> cinemas;
    Map<Integer, List<Integer>> showsByCinemaId;
    Map<Integer, List<Integer>> cinemasByCity;
    Map<Integer, Show> shows;
    Map<Integer, Ticket> tickets;

    static BookingManager INSTANCE = new BookingManager();

    private BookingManager() {
        cinemas = new HashMap<>();
        showsByCinemaId = new HashMap<>();
        cinemasByCity = new HashMap<>();
        shows = new HashMap<>();
        tickets = new HashMap<>();
    }

    public static BookingManager getInstance() {
        return INSTANCE;
    }

    public void addCinema(int cinemaId, int cityId, int screenCount, int screenRow, int screenColumn) {
        List<Screen> screens = IntStream.range(0, screenCount)
                .mapToObj(i -> new Screen(i, screenRow, screenColumn))
                .collect(Collectors.toList());

        var cinema = new Cinema(cinemaId, screens);
        cinemas.put(cinemaId, cinema);

        cinemasByCity.computeIfAbsent(cityId, _ -> new ArrayList<>()).add(cinemaId);
    }

    public void addShow(int showId, int movieId, int cinemaId, int screenId, long startTime, long endTime) {
        var screen = cinemas.get(cinemaId).getScreens().get(screenId);

        var show = new Show(showId, movieId, cinemaId, screen.getRows(), screen.getColumns(), screenId, startTime, endTime);
        shows.put(showId, show);

        showsByCinemaId.computeIfAbsent(cinemaId, _ -> new ArrayList<>()).add(showId);
    }

    public List<Integer> listCinemas(int movieId, int cityId) {
        return cinemasByCity.getOrDefault(cityId, List.of()).stream()
                .filter(cinemaId -> showsByCinemaId.getOrDefault(cinemaId, List.of()).stream()
                        .map(shows::get)
                        .anyMatch(show -> show.getMovieId() == movieId))
                .distinct()
                .toList();
    }

    public List<Integer> listShows(int movieId, int cinemaId) {
        return showsByCinemaId.getOrDefault(cinemaId, List.of()).stream()
                .filter(showId -> shows.get(showId).getMovieId() == movieId)
                .distinct()
                .toList();
    }

    public int getFreeSeatsCount(int showId) {
        return shows.get(showId).getFreeSeats();
    }

    public List<Pair<Integer, Integer>> bookTicket(int ticketId, int showId, int ticketsCount) {
        var show = shows.get(showId);
        Ticket ticket = new Ticket(ticketId, showId, ticketsCount);

        List<Pair<Integer, Integer>> bookedSeats = ticketsCount <= show.getFreeSeats()
                ? bookSeatsInLine(show, ticketsCount)
                : getDisconnectedSeats(show, ticketsCount);

        ticket.setSeats(bookedSeats);
        tickets.put(ticketId, ticket);
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
        Ticket ticket = tickets.get(ticketId);
        if (ticket == null || ticket.isCancelled()) return false;

        var show = shows.get(ticket.getShowId());
        for (Pair<Integer, Integer> seat : ticket.getSeats()) {
            show.getSeats()[seat.first()][seat.second()] = false;
        }

        show.setFreeSeats(show.getFreeSeats() + ticket.getSeats().size());
        ticket.setCancelled(true);
        return true;
    }
}
