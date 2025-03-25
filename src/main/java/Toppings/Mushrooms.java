package Toppings;


import BasePizza.BasePizza;

public class Mushrooms implements ToppingDecorator{
    BasePizza basePizza;
    int toppingCost = 10;

    public Mushrooms(BasePizza basePizza) {
        this.basePizza = basePizza;
    }

    @Override
    public int cost() {
        return basePizza.cost()+this.toppingCost;
    }
}
