package types;

import interfaces.CarriesCargo;
import interfaces.ItHasAScrew;
import transport.TransportVehicle;

public class Boat extends TransportVehicle implements ItHasAScrew, CarriesCargo {

    @Override
    public void drive() {
        System.out.println("Катер плывет.");
    }

    @Override
    public void describeTheScrew() {
        System.out.println("Катер имеет водяной винт.");
    }

    @Override
    public void describeTheCargo() {
        System.out.println("Катер перевозит пассажиров и различные грузы.");
        System.out.println();
    }
}
