package types;

import animals.Mammal;
import interfaces.HasWool;

public class Bear extends Mammal implements HasWool {

    public void move() {
        System.out.println("Медведь двигается.");
    }

    @Override
    public void feedWithMilk() {
        System.out.println("Медведь кормит молоком.");
    }

    @Override
    public void describeWool() {
        System.out.println("Медведь имеет густую шерсть.");
    }
}
