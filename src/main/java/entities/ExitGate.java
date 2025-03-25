package entities;

public class ExitGate {
    private final CostCalculator costCalculator;
    private PaymentHandler paymentHandler;
    public ExitGate(){
        costCalculator = new CostCalculator();
        paymentHandler = new PaymentHandler();
    }
    public int getCost(Ticket ticket) {
        var cost = costCalculator.calculateCost(ticket);
        paymentHandler.makePayment(cost);
        ticket.parkingSpot.removeVehicle(ticket.parkedVehicle);
        return cost;
    }
}
