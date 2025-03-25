package main.java.StrategyPattern.Vehicle;

import main.java.StrategyPattern.DriveStrategy.DriveStrategy;

public class Vehicle {
    private final DriveStrategy strategy;

    Vehicle(DriveStrategy strategy) {
        this.strategy = strategy;
    }

    public void drive() {
        strategy.drive();
    }
}