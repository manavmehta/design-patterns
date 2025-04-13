package manavmehta.bookingApp.service;

import manavmehta.bookingApp.dao.ShowDao;
import manavmehta.bookingApp.entities.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowService {
    @Autowired
    ShowDao showDao;

    @Autowired
    CinemaService cinemaService;

    public Show getShow(int showId){
        return showDao.getShow(showId);
    }

    public int getFreeSeatsCount(int showId) {
        return showDao.getShow(showId).getFreeSeats();
    }

    public void addShow(int showId, int movieId, int cinemaId, int screenId, long startTime, long endTime) {
        var cinema = cinemaService.getCinema(cinemaId);
        if (cinema == null) return;

        var screens = cinema.getScreens();
        if (screens == null) return;

        var desiredScreen = screens.get(screenId);

        var show = new Show(showId, movieId, cinemaId, desiredScreen.getRows(), desiredScreen.getColumns(), screenId, startTime, endTime);
        showDao.addShow(show);
        cinemaService.addShow(cinemaId, showId);
    }

}
