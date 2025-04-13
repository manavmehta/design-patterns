package manavmehta.bookingApp.dao;


import manavmehta.bookingApp.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CityDao {
    Map<Integer, List<Integer>> cinemasByCity;

    @Autowired
    CinemaService cinemaService;

    public CityDao(){
        cinemasByCity = new HashMap<>();
    }

    public void addCinema(int cityId, int cinemaId){
        var cinByCity = cinemasByCity.getOrDefault(cityId, new ArrayList<>());
        cinByCity.add(cinemaId);
        cinemasByCity.put(cityId, cinByCity);
    }

    public List<Integer> listCinemasByCityId(int cityId){
        return cinemasByCity.getOrDefault(cityId, new ArrayList<>());
    }

    public List<Integer> listCinemas(int movieId, int cityId) {
        return cinemasByCity.getOrDefault(cityId, List.of()).stream()
                .filter(cinemaId -> !cinemaService.listShows(movieId, cinemaId).isEmpty())
                .toList();
    }
}
