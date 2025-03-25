package entities;

import java.util.concurrent.ThreadLocalRandom;

public class ParkingSpot {
    int id;
    public boolean isEmpty;
    Vehicle vehicle;
    int price;

    public ParkingSpot() {
         id = ThreadLocalRandom.current().nextInt();
         isEmpty = false;
         vehicle = null;
         price = 10;
    }
    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        isEmpty = false;
    }

    public void removeVehicle(Vehicle vehicle) {
        this.vehicle = null;
        isEmpty = true;
    }
}
