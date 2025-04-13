package manavmehta.bookingApp.service;

import manavmehta.bookingApp.dao.CityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    CityDao cityDao;

    public void addCinema(int cityId, int cinemaId){
        cityDao.addCinema(cityId, cinemaId);
    }

    public List<Integer> listCinemasByCityId(int cityId){
        return cityDao.listCinemasByCityId(cityId);
    }

    public List<Integer> listCinemas(int movieId, int cityId) {
        return cityDao.listCinemas(movieId, cityId);
    }
}
