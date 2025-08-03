package types;

import interfaces.CarriesCargo;
import interfaces.ItHasAScrew;
import interfaces.ItHasWheels;
import interfaces.ItHasWings;
import transport.TransportVehicle;

public class Airplane extends TransportVehicle implements ItHasWheels, ItHasAScrew, ItHasWings, CarriesCargo {

    @Override
    public void drive() {
        System.out.println("Самолет летит.");
    }

    @Override
    public void describeTheWheels() {
        System.out.println("Самолет имеет колеса, называемые - шасси.");
    }

    @Override
    public void describeTheScrew() {
        System.out.println("Самолет имеет большой воздушный винт.");
    }

    @Override
    public void describeTheWings() {
        System.out.println("Самолет имеет большие крылья.");
    }

    @Override
    public void describeTheCargo() {
        System.out.println("Самолет перевозит пассажиров и грузы.");
        System.out.println();
    }
}
