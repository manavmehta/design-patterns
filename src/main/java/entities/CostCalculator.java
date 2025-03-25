package entities;

import java.time.LocalDateTime;

import static java.lang.Math.min;

// This class can accommodate calculation strategy using strategy pattern -> by minute, by hour, by day, etc
public class CostCalculator {
    public int calculateCost(Ticket ticket) {
        // Implement calculation logic based on duration and handle edge cases
        var hoursParked = min(1, LocalDateTime.now().getHour() - ticket.entryTime.getHour());
        return ticket.parkingSpot.price * hoursParked;
    }
}
