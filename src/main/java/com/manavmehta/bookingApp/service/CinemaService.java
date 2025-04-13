package manavmehta.bookingApp.service;

import manavmehta.bookingApp.dao.CinemaDao;
import manavmehta.bookingApp.dao.ShowDao;
import manavmehta.bookingApp.entities.Cinema;
import manavmehta.bookingApp.entities.Screen;
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
    ShowDao showDao;

    public Cinema getCinema(int cinemaId){
        return cinemaDao.getCinemaById(cinemaId);
    }

    public void addCinema(int cinemaId, int cityId, int screenCount, int screenRow, int screenColumn) {
        List<Screen> screens = IntStream.range(0, screenCount)
                .mapToObj(i -> new Screen(i, screenRow, screenColumn))
                .collect(Collectors.toList());

        var cinema = new Cinema(cinemaId, screens);
        cinemaDao.addCinema(cinema);
        return;
    }

    public List<Integer> listShows(int movieId, int cinemaId) {
        var allShowsByCinemaId = cinemaDao.getShowsByCinemaId(cinemaId);
        return allShowsByCinemaId.stream()
                .filter(showId -> showDao.getShow(showId).getMovieId() == movieId)
                .distinct()
                .toList();
    }
    public void addShow(int cinemaId, int showId) {
        cinemaDao.addShow(cinemaId, showId);
    }
}
