package main.java;

import main.java.Vehicle.NormalVehicle;
import main.java.Vehicle.SportsVehicle;

public class Main {
    public static void main(String[] args){
        var normalVehicle = new NormalVehicle();
        var sportsVehicle = new SportsVehicle();

        normalVehicle.drive();
        sportsVehicle.drive();
    }
}