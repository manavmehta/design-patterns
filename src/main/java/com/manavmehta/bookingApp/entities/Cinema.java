package manavmehta.bookingApp.entities;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Cinema {
    private final int id;
    private final Map<Integer, Screen> screens;

    public Cinema(int id, List<Screen> screens){
        this.id=id;
        this.screens=new HashMap<>();
        for (var screen: screens){
            this.screens.put(screen.id, screen);
        }
    }
}
