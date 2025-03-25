package factories.ordinary.cars;

import entities.Car;

public class Honda implements Car {
    @Override
    public String name() {
        return "Honda Civic";
    }
}
