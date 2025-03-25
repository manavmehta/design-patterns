package strategies.parkingStrategies;

import entities.ParkingSpot;

import java.util.List;

public class DefaultStrategy implements ParkingStrategy {
    @Override
    public ParkingSpot findParkingSpot(List<ParkingSpot> spots) {
        if (spots == null || spots.isEmpty()) return null; // throw custom exception
        for (var spot : spots) {
            if (spot.isEmpty){
                return spot;
            }
        }
        return null; // throw custom exception
    }
}
