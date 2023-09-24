package View;
import Controller.ManageTrips;
import Model.Trip;
import java.util.Date;


import java.util.ArrayList;
import Model.*;


public class ManageTripsTest {
    public static void main(String[] args) {
        // Create some sample data for testing
        Port port1 = new Port("PORT1", "Port 1", 40.7128, -74.0060, 1000, true);
        Port port2 = new Port("PORT2", "Port 2", 34.0522, -118.2437, 800, true);

        Vehicle vehicle1 = new Ship("SHIP1", "Ship 1", 1000, 2000, "PORT1");
        Vehicle vehicle2 = new BasicTruck("TRUCK1", "Truck 1", 100, 500, "PORT2");

        Date now = new Date();
        Date oneWeekLater = new Date(now.getTime() + 7 * 24 * 60 * 60 * 1000);

        Trip trip1 = new Trip("TRIP1", vehicle1, now, oneWeekLater, port1, port2, Trip.tripStatus.IN_PROGRESS);
        Trip trip2 = new Trip("TRIP2", vehicle2, now, oneWeekLater, port2, port1, Trip.tripStatus.COMPLETED);

        // Get an instance of ManageTrips
        ManageTrips manageTrips = ManageTrips.getInstance();

        // Add the trips to the manager
        manageTrips.addTrip(trip1);
        manageTrips.addTrip(trip2);

        // List all trips
        ArrayList<Trip> allTrips = manageTrips.listTrips();
        System.out.println("All Trips:");
        for (Trip trip : allTrips) {
            System.out.println(trip.getId());
        }

        // List trips from a specific port
        ArrayList<Trip> tripsFromPort1 = manageTrips.listTripsFromPort(port1);
        System.out.println("\nTrips from Port 1:");
        for (Trip trip : tripsFromPort1) {
            System.out.println(trip.getId());
        }

        // List trips to a specific port
        ArrayList<Trip> tripsToPort2 = manageTrips.listTripsToPort(port2);
        System.out.println("\nTrips to Port 2:");
        for (Trip trip : tripsToPort2) {
            System.out.println(trip.getId());
        }

        // List trips between specific dates
        ArrayList<Trip> tripsBetweenDates = manageTrips.listTripsBetweenDates(now, oneWeekLater);
        System.out.println("\nTrips between dates:");
        for (Trip trip : tripsBetweenDates) {
            System.out.println(trip.getId());
        }
    }
}
