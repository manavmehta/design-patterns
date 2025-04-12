package entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
//@AllArgsConstructor
public class Cinema {
    public int id;
    public Map<Integer, Screen> screens;

    public Cinema(int id, List<Screen> screens){
        this.id=id;
        this.screens=new HashMap<>();
        for (var screen: screens){
            this.screens.put(screen.id, screen);
        }
    }
}
