package main.java.StrategyPattern.Vehicle;

import main.java.StrategyPattern.DriveStrategy.DriveStrategy;
import main.java.StrategyPattern.DriveStrategy.SportsDriveStrategy;

public class SportsVehicle extends Vehicle {

    public SportsVehicle() {
        super(new SportsDriveStrategy());
    }
}
