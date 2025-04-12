package dao;

import entities.Show;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowDao {
    Map<Integer, Show> shows;

    public ShowDao(){
        this.shows = new HashMap<>();
    }

    public void addShow(int showId, int movieId, int cinemaId, int screenId, long startTime, long endTime){

    }
    public Show getShow(int showId){
        return shows.getOrDefault(showId, null);
    }
    public List<Integer> listShows(int cinemaId, int movieId){
        List<Integer> showsByCinemaAndMovie = new ArrayList<>();
        List<Show> allShows = shows.values().stream().toList();
        for (var show: allShows){
            if (show.cinemaId == cinemaId && show.movieId==movieId){
                showsByCinemaAndMovie.add(show.id);
            }
        }
        return showsByCinemaAndMovie;
    }
}
