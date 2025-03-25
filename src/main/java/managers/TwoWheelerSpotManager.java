package managers;

import entities.ParkingSpot;
import entities.Vehicle;
import strategies.parkingStrategies.ParkingStrategy;

import java.util.EventListener;
import java.util.List;

public class TwoWheelerSpotManager extends ParkingSpotManager {

    public TwoWheelerSpotManager(List<ParkingSpot> spots, ParkingStrategy strategy) {
        super(spots, strategy);
    }
}
