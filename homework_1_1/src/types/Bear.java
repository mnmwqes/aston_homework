package types;

import animals.Mammal;
import interfaces.hasASpine;
import interfaces.HasWool;

public class Bear extends Mammal implements hasASpine, HasWool {

    public void move() {
        System.out.println("Медведь двигается.");
    }

    @Override
    public void feedWithMilk() {
        System.out.println("Медведь кормит детенышей молоком.");
    }

    @Override
    public void describeTheSpine() {
        System.out.println("Медведь имеет позвоночник, состоящий из " +
                "5-х отделов: шейный, грудной, поясничный, " +
                "крестцовый и хвостовой.");
    }

    @Override
    public void describeWool() {
        System.out.println("Медведь имеет густую шерсть.");
        System.out.println();
    }
}
