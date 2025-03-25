import entities.Factory;
import factories.FactoryType;
import factories.PrimeFactory;
import factories.luxury.LuxuryCars;
import factories.ordinary.OrdinaryCars;
import factories.ordinary.OrdinaryFactory;
import factories.ordinary.cars.Hyundai;

public class Main {
    public static void main(String[] args){
        var primeFactory = new PrimeFactory();
        var ordinaryFactory = primeFactory.getFactory(FactoryType.ORDINARY);
        var luxuryFactory = primeFactory.getFactory(FactoryType.LUXURY);

        var honda = ordinaryFactory.getCar(OrdinaryCars.HONDA.toString());
        System.out.println("Honda has: " + honda.name());

        var hyundai = ordinaryFactory.getCar(OrdinaryCars.HYUNDAI.name());
        System.out.println("Hyundai has: " + hyundai.name());

        var bentley = luxuryFactory.getCar(LuxuryCars.BENTLEY.toString());
        System.out.println("Bentley has: " + bentley.name());

        var astonMartin = luxuryFactory.getCar(LuxuryCars.ASTON_MARTIN.name());
        System.out.println("Aston has: " + astonMartin.name());

    }
}