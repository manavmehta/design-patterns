package manavmehta.bookingApp.dao;


import manavmehta.bookingApp.entities.Cinema;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CinemaDao {
    Map<Integer, Cinema> cinemas;
    Map<Integer, List<Integer>> showsByCinemaId;

    public CinemaDao(){
        cinemas = new HashMap<>();
        showsByCinemaId = new HashMap<>();
    }

    public void addCinema(Cinema cinema){
        cinemas.put(cinema.getId(), cinema);
    }

    public Cinema getCinemaById(int cinemaId){
        return cinemas.getOrDefault(cinemaId, null);
    }

    public List<Integer> getShowsByCinemaId(int cinemaId){
        return showsByCinemaId.getOrDefault(cinemaId, new ArrayList<>());
    }

    public void addShow(int cinemaId, int showId) {
        var shows = showsByCinemaId.getOrDefault(cinemaId, new ArrayList<>());
        shows.add(showId);
        showsByCinemaId.put(cinemaId, shows);
    }
}
