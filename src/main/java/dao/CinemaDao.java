package dao;

import entities.Cinema;
import entities.Screen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class CinemaDao {
    Map<Integer, Cinema> cinemas;
    Map<Integer, List<Integer>> showsByCinemaId;

    public CinemaDao(){
        cinemas = new HashMap<>();
        showsByCinemaId = new HashMap<>();
    }

    public void addCinema(int cinemaId, int cityId, int screenCount, int screenRow, int screenColumn){
        List<Screen> screens = new ArrayList<>();
        for (var i=0; i<screenCount; i++){
            screens.add(new Screen(ThreadLocalRandom.current().nextInt(), screenRow, screenColumn));
        }
        Cinema cinema = new Cinema(cinemaId, screens);
        cinemas.put(cinemaId, cinema);
    }

    public Cinema getCinemaById(int cinemaId){
        return cinemas.getOrDefault(cinemaId, null);
    }

    public List<Integer> getShowsByCinemaId(int cinemaId){
        return showsByCinemaId.getOrDefault(cinemaId, List.of());
    }

    public void addShow(int cinemaId, int showId) {

    }
}
