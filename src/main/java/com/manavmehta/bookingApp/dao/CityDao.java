package manavmehta.bookingApp.dao;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CityDao {
    Map<Integer, List<Integer>> cinemasByCity;

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
}
