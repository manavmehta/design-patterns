package entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.GenericDeclaration;

@Data
//@AllArgsConstructor
public class Show {
    public int id;
    public int movieId;
    public int cinemaId;
    public int rows;
    public int cols;
    public int screenIndex;
    public long startTime;
    public long endTime;
    public boolean[][] seats;
    public int freeSeats;

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
