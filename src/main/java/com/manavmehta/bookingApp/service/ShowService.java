package manavmehta.bookingApp.service;

import manavmehta.bookingApp.dao.ShowDao;
import manavmehta.bookingApp.entities.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowService {
    @Autowired
    ShowDao showDao;

    public Show getShow(int showId){
        return showDao.getShow(showId);
    }

    public int getFreeSeatsCount(int showId) {
        return showDao.getShow(showId).getFreeSeats();
    }

    public void addShow(Show show) {
        showDao.addShow(show);
    }

}
