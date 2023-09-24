package View;

import Controller.ManageVehicles;
import Model.*;

import java.util.Optional;

public class ManageVehiclesTest {

    public static void main(String[] args) {
        // Create an instance of ManageVehicles
        ManageVehicles manageVehicles = ManageVehicles.getInstance();

        // Add vehicles for testing
        Ship ship1 = manageVehicles.addShip("Ship One", 500.0, 1000.0, "PORT1");
        BasicTruck truck1 = manageVehicles.addTruck("Truck One", 10.0, 100.0, "DryStorage", "PORT2");
        TankerTruck tankerTruck = (TankerTruck) manageVehicles.addTruck("Tanker Truck", 8.0, 90.0, "TankerTruck", "PORT3");
        ReeferTruck reeferTruck = (ReeferTruck) manageVehicles.addTruck("Reefer Truck", 12.0, 120.0, "ReeferTruck", "PORT4");

        // Test retrieving a vehicle by ID
        testGetVehicleByID(manageVehicles, "sh-1"); // Valid ship ID
        testGetVehicleByID(manageVehicles, "tr-2"); // Valid truck ID
        testGetVehicleByID(manageVehicles, "tr-10"); // Invalid ID

        // Test serializing and deserializing vehicles
        testSerializeAndDeserializeVehicles(manageVehicles);

        // Print vehicle details after deserialization
        System.out.println("Vehicle details after deserialization:");
        for (Vehicle vehicle : manageVehicles.getAllVehicles()) {
            System.out.println(vehicle.toString());
        }
    }

    // Test retrieving a vehicle by ID
    private static void testGetVehicleByID(ManageVehicles manageVehicles, String vehicleID) {
        Optional<Vehicle> optionalVehicle = manageVehicles.getVehicleByID(vehicleID);
        if (optionalVehicle.isPresent()) {
            System.out.println("Vehicle with ID " + vehicleID + ": " + optionalVehicle.get());
        } else {
            System.out.println("No vehicle found with ID " + vehicleID);
        }
    }

    // Test serializing and deserializing vehicles
    private static void testSerializeAndDeserializeVehicles(ManageVehicles manageVehicles) {
        // Serialize vehicles to a file
        manageVehicles.serializeVehiclesToFile("data/vehicles.dat");
        System.out.println("Vehicles have been serialized and saved to data/vehicles.dat");

        // Deserialize vehicles from the file
        manageVehicles.deserializeVehiclesFromFile();
    }
}
