package factories;

import entities.Factory;
import factories.luxury.LuxuryFactory;
import factories.ordinary.OrdinaryFactory;

public class PrimeFactory {
    public Factory getFactory(FactoryType type){
        return switch (type) {
            case LUXURY -> new LuxuryFactory();
            case ORDINARY -> new OrdinaryFactory();
            default -> throw new IllegalArgumentException("Invalid factory type.");
        };
    }
}
