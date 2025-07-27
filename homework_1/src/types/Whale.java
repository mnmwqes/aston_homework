package types;

import animals.Mammal;
import interfaces.LivesInWater;

public class Whale extends Mammal implements LivesInWater  {

    public void move() {
        System.out.println("Кит двигается.");
    }

    @Override
    public void feedWithMilk() {
        System.out.println("Кит кормит детёнышей молоком.");
    }

    public void haveASpine() {
        System.out.println("Кит имеет позвоночник.");
    }

    @Override
    public void toSwim() {
        System.out.println("Кит обитает в воде.");
    }
}