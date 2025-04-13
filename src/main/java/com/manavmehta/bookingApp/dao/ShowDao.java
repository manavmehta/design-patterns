package manavmehta.bookingApp.dao;

import manavmehta.bookingApp.entities.Show;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ShowDao {
    Map<Integer, Show> shows;

    public ShowDao(){
        this.shows = new HashMap<>();
    }

    public void addShow(Show show){
        shows.put(show.getId(), show);
    }
    public Show getShow(int showId){
        return shows.getOrDefault(showId, null);
    }
    public List<Integer> listShows(int cinemaId, int movieId){
        List<Integer> showsByCinemaAndMovie = new ArrayList<>();
        List<Show> allShows = shows.values().stream().toList();
        for (var show: allShows){
            if (show.getCinemaId() == cinemaId && show.getMovieId()==movieId){
                showsByCinemaAndMovie.add(show.getId());
            }
        }
        return showsByCinemaAndMovie;
    }
}
