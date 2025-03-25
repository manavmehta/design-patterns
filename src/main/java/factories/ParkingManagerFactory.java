package factories;

import entities.ParkingSpot;
import enums.VehicleType;
import managers.ParkingSpotManager;
import managers.TwoWheelerSpotManager;
import strategies.parkingStrategies.DefaultStrategy;
import strategies.parkingStrategies.ParkingStrategy;

import java.util.List;

public class ParkingManagerFactory {
    public ParkingSpotManager getParkingSpotManager(VehicleType type, ParkingStrategy strategy) {
        return switch (type) {
            // This is dummy code for structure, we must store the parking spots and then return the associated manager
            case TWO_WHEELER -> new TwoWheelerSpotManager(List.of(new ParkingSpot(), new ParkingSpot()), strategy);
            case FOUR_WHEELER -> new TwoWheelerSpotManager(List.of(new ParkingSpot()), strategy);
        };
    }
}
