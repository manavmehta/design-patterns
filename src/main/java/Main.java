import BasePizza.Margherita;
import Toppings.ExtraCheese;
import Toppings.Mushrooms;

public class Main {
    public static void main(String[] args){
        var basePizza = new Margherita();
        var withMushroom = new Mushrooms(basePizza);
        var withExtraCheese = new ExtraCheese(withMushroom);
        System.out.println("cost of Base + Mushroom + ExtraCheese = " + withExtraCheese.cost());
    }
}