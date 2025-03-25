package factories.luxury.cars;

import entities.Car;

public class Bentley implements Car {
    @Override
    public String name() {
        return "Bentley Bentayga";
    }
}
