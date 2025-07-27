import types.*;

public class Main {
    public static void main(String[] args) {
        Airplane airplane = new Airplane();
        airplane.drive();
        airplane.describeTheWheels();
        airplane.describeTheScrew();
        airplane.describeTheWings();
        airplane.describeTheCargo();

        Helicopter helicopter = new Helicopter();
        helicopter.drive();
        helicopter.describeTheWheels();
        helicopter.describeTheScrew();
        helicopter.describeTheCargo();

        Boat boat = new Boat();
        boat.drive();
        boat.describeTheScrew();
        boat.describeTheCargo();

        Tanker tanker = new Tanker();
        tanker.drive();
        tanker.describeTheScrew();
        tanker.describeTheCargo();

        Truck truck = new Truck();
        truck.drive();
        truck.describeTheWheels();
        truck.describeTheCargo();

        Taxi taxi = new Taxi();
        taxi.drive();
        taxi.describeTheWheels();
    }
}
