package types;

import interfaces.CarriesCargo;
import interfaces.ItHasAScrew;
import transport.TransportVehicle;

public class Tanker extends TransportVehicle implements ItHasAScrew, CarriesCargo {

    @Override
    public void drive() {
        System.out.println("Танк плывет.");
    }

    @Override
    public void describeTheScrew() {
        System.out.println("Танк имеет большой гребной винт.");
    }

    @Override
    public void describeTheCargo() {
        System.out.println("Танкер перевозит нефть, бензин и другие жидкие грузы.");
        System.out.println();
    }
}
