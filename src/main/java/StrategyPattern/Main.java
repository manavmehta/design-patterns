package main.java.StrategyPattern;

import main.java.StrategyPattern.DriveStrategy.SportsDriveStrategy;
import main.java.StrategyPattern.Vehicle.NormalVehicle;
import main.java.StrategyPattern.Vehicle.SportsVehicle;
import main.java.StrategyPattern.Vehicle.Vehicle;

public class Main {
    public static void main(String[] args){
        var normalVehicle = new NormalVehicle();
        var sportsVehicle = new SportsVehicle();

        normalVehicle.drive();
        sportsVehicle.drive();
    }
}