package View;

import Controller.ManageVehicles;
import Model.*;

import java.util.Optional;

public class ManageVehiclesTest {

    public static void main(String[] args) {
        // Create an instance of ManageVehicles
        ManageVehicles manageVehicles = ManageVehicles.getInstance();

        // Test adding a ship
        testAddShip(manageVehicles);

        // Test adding a basic truck
        testAddBasicTruck(manageVehicles);

        // Test retrieving a vehicle by ID
        testGetVehicleByID(manageVehicles);

        // Add more test methods as needed
    }

    private static void testAddShip(ManageVehicles manageVehicles) {
        Ship ship = manageVehicles.addShip("Ship One", 500.0, 1000.0, "PORT1");
        if (ship != null) {
            System.out.println("Ship added successfully:");
            System.out.println(ship);
        } else {
            System.err.println("Failed to add ship.");
        }
    }

    private static void testAddBasicTruck(ManageVehicles manageVehicles) {
        BasicTruck truck = manageVehicles.addTruck("Truck One", 10.0, 100.0, "DryStorage", Vehicle.VehicleType.BASIC_TRUCK);
        if (truck != null) {
            System.out.println("Basic truck added successfully:");
            System.out.println(truck);
        } else {
            System.err.println("Failed to add basic truck.");
        }
    }

    private static void testGetVehicleByID(ManageVehicles manageVehicles) {
        // Add vehicles for testing
        Ship ship = manageVehicles.addShip("Ship One", 500.0, 1000.0, "PORT1");
        BasicTruck truck = manageVehicles.addTruck("Truck One", 10.0, 100.0, "DryStorage", Vehicle.VehicleType.BASIC_TRUCK);

        // Test retrieving a vehicle by ID
        Optional<Vehicle> optionalShip = manageVehicles.getVehicleByID(ship.getVehicleID());
        Optional<Vehicle> optionalTruck = manageVehicles.getVehicleByID(truck.getVehicleID());

        System.out.println("Retrieved Ship by ID:");
        if (optionalShip.isPresent()) {
            System.out.println(optionalShip.get());
        } else {
            System.err.println("Ship not found.");
        }

        System.out.println("Retrieved Basic Truck by ID:");
        if (optionalTruck.isPresent()) {
            System.out.println(optionalTruck.get());
        } else {
            System.err.println("Basic Truck not found.");
        }

    }




}
