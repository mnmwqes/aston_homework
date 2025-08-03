package types;

import interfaces.CarriesCargo;
import interfaces.ItHasWheels;
import transport.TransportVehicle;

public class Taxi extends TransportVehicle implements ItHasWheels {

    @Override
    public void drive() {
        System.out.println("Такси едет.");
    }

    @Override
    public void describeTheWheels() {
        System.out.println("Такси имеет четыре колеса.");
    }
}
