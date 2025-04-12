import dependencies.Pair;
import entities.*;

import java.util.*;

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

    public void addCinema(int cinemaId, int cityId, int screenCount, int screenRow, int screenColumn){
        List<Screen> screensInCinema = new ArrayList<>();
        for (var i=0; i<screenCount; i++){
            var screen = new Screen(i, screenRow, screenColumn);
            screensInCinema.add(screen);
        }
        var cinema = new Cinema(cinemaId, screensInCinema);
        cinemas.put(cinemaId, cinema);

        var cinsByCity = cinemasByCity.getOrDefault(cityId, new ArrayList<>());
        cinsByCity.add(cinemaId);
        cinemasByCity.put(cityId, cinsByCity);
        return;
    }

    public void addShow(int showId, int movieId, int cinemaId, int screenId, long startTime, long endTime){
        var cinema = cinemas.get(cinemaId);
        var screen = cinema.getScreens().get(screenId);
        shows.put(showId, new Show(showId, movieId, cinemaId, screen.rows, screen.columns, screenId, startTime, endTime));
        List<Integer> shows = showsByCinemaId.getOrDefault(cinemaId, new ArrayList<>());
        shows.add(showId);
        showsByCinemaId.put(cinemaId, shows);
        return;
    }

    public List<Integer> listCinemas(int movieId, int cityId){
        var cinemas = cinemasByCity.getOrDefault(cityId, new ArrayList<>());
        Set<Integer> selectedCinemas = new HashSet<>();
        for(var cinId: cinemas){
            var allShows = showsByCinemaId.getOrDefault(cinId, new ArrayList<>());
            for(var showId: allShows){
                var show = shows.get(showId);
                if (show.getMovieId()==movieId){
                    selectedCinemas.add(cinId);
                }
            }
        }
        return selectedCinemas.stream().toList();
    }

    public List<Integer> listShows(int movieId, int cinemaId){
        Set<Integer> selectedShows = new HashSet<>();
        var allShows = showsByCinemaId.getOrDefault(cinemaId, new ArrayList<>());
        for(var showId: allShows){
            var show = shows.get(showId);
            if (show.getMovieId()==movieId){
                selectedShows.add(showId);
            }
        }
        return selectedShows.stream().toList();
    }

    public int getFreeSeatsCount(int showId){
        var show = shows.get(showId);
        return show.getFreeSeats();
    }

    public ArrayList<Pair<Integer, Integer>> bookTicket(int ticketId, int showId, int ticketsCount){
        var show = shows.get(showId);
        var ticket = new Ticket(ticketId, showId, ticketsCount);
        ArrayList<Pair<Integer, Integer>> bookedSeats = new ArrayList<>();
        if (ticketsCount <= show.getFreeSeats()){
            var seatsInLine = bookSeatsInLine(show, ticketsCount);
            if (!seatsInLine.isEmpty()) {
                bookedSeats = seatsInLine;
            }
            else {
                bookedSeats = getDisconnectedSeats(show, ticketsCount);
            }
        }
        ticket.setSeats(bookedSeats);
        tickets.put(ticketId, ticket);
        show.setFreeSeats(show.getFreeSeats() - bookedSeats.size());
        return bookedSeats;
    }

    public ArrayList<Pair<Integer, Integer>> bookSeatsInLine(Show show, int ticketsCount){
        ArrayList<Pair<Integer, Integer>> bookedSeats = new ArrayList<>();
        for (int i = 0; i < show.getRows(); i++){
            int booked = 0;
            int j=0;
            while(j < show.getCols() && booked < ticketsCount){
                if (!show.getSeats()[i][j]){
                    booked++;
                    bookedSeats.add(new Pair<>(i, j));
                }
                else{
                    booked=0;
                    bookedSeats.clear();
                }
                j++;
            }
            if (booked == ticketsCount){
                break;
            }
        }
        return bookedSeats;
    }

    public ArrayList<Pair<Integer, Integer>> getDisconnectedSeats(Show show, int ticketsCount){
        ArrayList<Pair<Integer, Integer>> bookedSeats = new ArrayList<>();
        for (int i = 0; i < show.getRows(); i++){
            int booked = 0;
            int j=0;
            while(j < show.getCols() && booked < ticketsCount){
                if (show.getSeats()[i][j]){
                    booked++;
                    bookedSeats.add(new Pair<>(i, j));
                }
                j++;
            }
            if (booked == ticketsCount){
                break;
            }
        }
        return bookedSeats;
    }

    public boolean cancelTicket(int ticketId){
        if (!tickets.containsKey(ticketId) || tickets.get(ticketId).isCancelled()) return false;
        var ticket = tickets.get(ticketId);

        var show = shows.get(ticket.getShowId());
        for (var seat: ticket.getSeats()){
            show.getSeats()[seat.first()][seat.second()] = false;
        }

        show.setFreeSeats(show.getFreeSeats() + ticket.getSeats().size());
        ticket.setCancelled(true);
        return true;
    }
}
