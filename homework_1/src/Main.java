import types.Cat;
import types.Whale;
import types.Fish;
import types.Bear;

public class Main {
    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.move();
        cat.feedWithMilk();
        cat.describeWool();

        Whale whale = new Whale();
        whale.move();
        whale.haveASpine();
        whale.feedWithMilk();
        whale.toSwim();

        Fish fish = new Fish();
        fish.move();
        fish.toSwim();

        Bear bear = new Bear();
        bear.move();
        bear.feedWithMilk();
        bear.describeWool();
    }
}