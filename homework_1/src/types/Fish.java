package types;

import animals.Animal;
import interfaces.LivesInWater;

public class Fish  extends Animal implements LivesInWater{

    @Override
    public void move() {
        System.out.println("Рыба двигается.");
    }

    @Override
    public void toSwim() {
        System.out.println("Рыба обитает в воде.");
    }
}
