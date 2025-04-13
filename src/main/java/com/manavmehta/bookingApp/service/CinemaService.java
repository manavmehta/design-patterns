package manavmehta.bookingApp.service;

import manavmehta.bookingApp.dao.CinemaDao;
import manavmehta.bookingApp.entities.Cinema;
import manavmehta.bookingApp.entities.Screen;
import manavmehta.bookingApp.entities.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CinemaService {

    @Autowired
    CinemaDao cinemaDao;

    @Autowired
    ShowService showService;

    public Cinema getCinema(int cinemaId){
        return cinemaDao.getCinemaById(cinemaId);
    }

    public void addCinema(int cinemaId, int cityId, int screenCount, int screenRow, int screenColumn) {
        List<Screen> screens = IntStream.range(0, screenCount)
                .mapToObj(i -> new Screen(i, screenRow, screenColumn))
                .collect(Collectors.toList());

        var cinema = new Cinema(cinemaId, screens);
        cinemaDao.addCinema(cinema);
    }

    public List<Integer> listShows(int movieId, int cinemaId) {
        var allShowsByCinemaId = cinemaDao.getShowsByCinemaId(cinemaId);
        return allShowsByCinemaId.stream()
                .filter(showId -> showService.getShow(showId).getMovieId() == movieId)
                .distinct()
                .toList();
    }
    public void addShow(int showId, int movieId, int cinemaId, int screenId, long startTime, long endTime) {
        var cinema = getCinema(cinemaId);
        if (cinema == null) return;

        cinemaDao.addShow(cinemaId, showId);

        var screens = cinema.getScreens();
        if (screens == null) return;

        var desiredScreen = screens.get(screenId);

        var show = new Show(showId, movieId, cinemaId, desiredScreen.getRows(), desiredScreen.getColumns(), screenId, startTime, endTime);
        showService.addShow(show);
    }
}
