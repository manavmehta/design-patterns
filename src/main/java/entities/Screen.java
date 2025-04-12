package entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//@AllArgsConstructor
public class Screen {
    public int id;
    public int rows;
    public int columns;

    public Screen(int id, int rows, int columns){
        this.id = id;
        this.rows = rows;
        this.columns = columns;
    }
}
