package factories.ordinary;

import entities.Car;
import entities.Factory;
import factories.ordinary.cars.Honda;
import factories.ordinary.cars.Hyundai;

import static factories.ordinary.OrdinaryCars.HONDA;
import static factories.ordinary.OrdinaryCars.HYUNDAI;

public class OrdinaryFactory implements Factory {
    public Car getCar(String carName) {
        return switch (carName) {
            case "HONDA" -> new Honda();
            case "HYUNDAI" -> new Hyundai();
            default -> throw new IllegalArgumentException("Invalid luxury car name.");
        };
    }
}
