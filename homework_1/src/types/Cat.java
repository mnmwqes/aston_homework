package types;

import animals.Mammal;
import interfaces.HasWool;
import interfaces.hasASpine;

public class Cat extends Mammal implements hasASpine, HasWool {

    public void move() {
        System.out.println("Кошка двигается.");
    }

    @Override
    public void feedWithMilk() {
        System.out.println("Кошка кормит детенышей молоком.");
    }

    @Override
    public void describeTheSpine() {
        System.out.println("Кошка имеет позвоночник, состоящий из 4-х " +
                "отделов: шейный, грудной, поясничный и хвостовой.");
    }

    @Override
    public void describeWool() {
        System.out.println("Кошка имеет гладкую шерсть с различными окрасами.");
        System.out.println();
    }
}
