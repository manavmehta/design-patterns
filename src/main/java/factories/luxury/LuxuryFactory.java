package factories.luxury;

import entities.Car;
import entities.Factory;
import factories.luxury.cars.AstonMartin;
import factories.luxury.cars.Bentley;

public class LuxuryFactory implements Factory {
    public Car getCar(String carName) {
        return switch (carName) {
            case "ASTON_MARTIN" -> new AstonMartin();
            case "BENTLEY" -> new Bentley();
            default -> throw new IllegalArgumentException("Invalid luxury car name.");
        };
    }
}
