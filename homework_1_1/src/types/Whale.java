package types;

import animals.Mammal;
import interfaces.LivesInWater;
import interfaces.hasASpine;

public class Whale extends Mammal implements hasASpine, LivesInWater  {

    public void move() {
        System.out.println("Кит двигается.");
    }

    @Override
    public void feedWithMilk() {
        System.out.println("Кит кормит детёнышей молоком.");
    }

    @Override
    public void describeTheSpine() {
        System.out.println("Кит имеет позвоночник, состоящий из 4-х " +
                "отделов: шейный, грудной, поясничный и хвостовой.");
    }

    @Override
    public void toSwim() {
        System.out.println("Кит обитает в воде.");
        System.out.println();
    }
}