package types;

import interfaces.CarriesCargo;
import interfaces.ItHasWheels;
import transport.TransportVehicle;

public class Truck extends TransportVehicle implements ItHasWheels, CarriesCargo {

    @Override
    public void drive() {
        System.out.println("Грузовик едет.");
    }

    @Override
    public void describeTheWheels() {
        System.out.println("Грузовик имеет шесть больших колес.");
    }

    @Override
    public void describeTheCargo() {
        System.out.println("Грузовик перевозит различные крупные и мелкие грузы.");
        System.out.println();
    }
}
