package types;

import animals.Mammal;
import interfaces.HasWool;

public class Cat extends Mammal implements HasWool {

    public void move() {
        System.out.println("Кошка двигается.");
    }

    @Override
    public void feedWithMilk() {
        System.out.println("Кошка кормит молоком.");
    }

    @Override
    public void describeWool() {
        System.out.println("Кошка имеет шерсть.");
    }
}