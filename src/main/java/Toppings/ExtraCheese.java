package Toppings;


import BasePizza.BasePizza;

public class ExtraCheese implements ToppingDecorator{
    BasePizza basePizza;
    int toppingCost = 40;

    public ExtraCheese(BasePizza basePizza) {
        this.basePizza = basePizza;
    }

    @Override
    public int cost() {
        return basePizza.cost()+this.toppingCost;
    }
}
