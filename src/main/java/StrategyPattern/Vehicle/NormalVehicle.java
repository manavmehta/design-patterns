package main.java.StrategyPattern.Vehicle;

import main.java.StrategyPattern.DriveStrategy.DriveStrategy;
import main.java.StrategyPattern.DriveStrategy.NormalDriveStrategy;

public class NormalVehicle extends Vehicle {

    public NormalVehicle() {
        super(new NormalDriveStrategy());
    }
}
