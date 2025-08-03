package types;

import interfaces.CarriesCargo;
import interfaces.ItHasAScrew;
import interfaces.ItHasWheels;
import transport.TransportVehicle;

public class Helicopter extends TransportVehicle implements ItHasWheels, ItHasAScrew, CarriesCargo {

    @Override
    public void drive() {
        System.out.println("Вертолет летает.");
    }

    @Override
    public void describeTheWheels() {
        System.out.println("Вертолет имеет колеса, называемые - шасси.");
    }

    @Override
    public void describeTheScrew() {
        System.out.println("Вертолет имеет воздушный винт.");
    }

    @Override
    public void describeTheCargo() {
        System.out.println("Вертолет перевозит различные грузы.");
        System.out.println();
    }
}
