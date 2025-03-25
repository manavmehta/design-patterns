package entities;

import enums.VehicleType;
import factories.ParkingManagerFactory;
import managers.ParkingSpotManager;
import strategies.parkingStrategies.ParkingStrategy;

public class EntryGate {
    private ParkingManagerFactory parkingManagerFactory;
    private ParkingSpotManager parkingSpotManager;

    public ParkingSpot findParkingSpace(VehicleType type, ParkingStrategy strategy) {
        // This is only for structure, ideally this factory should be stored/cached and reused
        parkingManagerFactory = new ParkingManagerFactory();
        parkingSpotManager = parkingManagerFactory.getParkingSpotManager(type, strategy);
        return parkingSpotManager.findSpot();
    }
    public void parkVehicle(Vehicle vehicle, ParkingStrategy parkingStrategy) {
        // Do optimistic locking here to avoid multiple vehicles booking same parking slot
        var parkingSpace = findParkingSpace(vehicle.type, parkingStrategy);
        parkingSpace.parkVehicle(vehicle);
    }
}
