package entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class City {
    int id;
    List<Integer> cinemaIds;
}
