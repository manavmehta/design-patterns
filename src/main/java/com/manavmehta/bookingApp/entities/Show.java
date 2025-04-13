package manavmehta.bookingApp.entities;

import lombok.Data;

@Data
public class Show {
    private int id;
    private int movieId;
    private int cinemaId;
    private int rows;
    private int cols;
    private int screenIndex;
    private long startTime;
    private long endTime;
    private boolean[][] seats;
    private int freeSeats;

    public Show(int id, int movieId, int cinemaId, int rows, int cols, int screenIndex, long startTime, long endTime){
        this.id = id;
        this.movieId = movieId;
        this.cinemaId = cinemaId;
        this.rows = rows;
        this.cols = cols;
        this.screenIndex = screenIndex;
        this.startTime = startTime;
        this.endTime = endTime;
        this.seats = new boolean[rows][cols];
        this.freeSeats = rows*cols;
    }
}
