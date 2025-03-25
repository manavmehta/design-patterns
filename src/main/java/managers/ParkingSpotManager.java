package managers;

import entities.ParkingSpot;
import entities.Vehicle;
import strategies.parkingStrategies.ParkingStrategy;

import java.util.List;

public class ParkingSpotManager {
    private final List<ParkingSpot> spots;
    private final ParkingStrategy strategy;
    ParkingSpotManager(List<ParkingSpot> spots, ParkingStrategy strategy){
        this.spots = spots;
        this.strategy = strategy;
    }

    public void addSpot(ParkingSpot spot) {
        spots.add(spot);
    }

    public void removeSpot(ParkingSpot spot) {
        spots.remove(spot);
    }

    public ParkingSpot findSpot() {
        return strategy.findParkingSpot(spots);
    }

    public void parkVehicle(Vehicle vehicle) {
        var parkingSpot = findSpot();
        parkingSpot.parkVehicle(vehicle);
    }
    public void removeVehicle(ParkingSpot parkingSpot, Vehicle vehicle) {
        parkingSpot.removeVehicle(vehicle);
    }
}
