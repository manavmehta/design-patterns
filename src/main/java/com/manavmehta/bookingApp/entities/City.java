package manavmehta.bookingApp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class City {
    private int id;
    private List<Integer> cinemaIds;
}
