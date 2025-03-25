package main.java.Vehicle;

import main.java.DriveStrategy.DriveStrategy;

public class Vehicle {
    private final DriveStrategy strategy;

    Vehicle(DriveStrategy strategy) {
        this.strategy = strategy;
    }

    public void drive() {
        strategy.drive();
    }
}