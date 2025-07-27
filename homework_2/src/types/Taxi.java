package types;

import interfaces.CarriesCargo;
import interfaces.ItHasWheels;
import transport.TransportVehicle;

public class Taxi extends TransportVehicle implements ItHasWheels, CarriesCargo {

    @Override
    public void drive() {
        System.out.println("Такси едет.");
    }

    @Override
    public void describeTheWheels() {
        System.out.println("Такси имеет четыре колеса.");
    }

    @Override
    public void describeTheCargo() {
        System.out.println("Такси перевозит пассажиров.");
        System.out.println();
    }
}
