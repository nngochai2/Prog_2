package Controller;

import Model.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManageVehicles {
    // Apply Singleton instance
    private static ManageVehicles instance;
    // Create a list to store vehicles
    private List<Vehicle> vehicles;
    // Counter for generating unique vehicle IDs
    private int LastUsedID = 0;

    // Private constructor for Singleton patter
    private ManageVehicles() {
        vehicles = new ArrayList<>();
    }


    public static ManageVehicles getInstance() {
        if (instance == null) {
            instance = new ManageVehicles();
        }
        return instance;
    }

    // Get all vehicles
    public Optional<Vehicle> getVehicleByID(String vehicleID) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleID().equals(vehicleID)) {
                return Optional.of(vehicle);
            }
        }
        return Optional.empty();
    }

    // Check if a vehicle with a given ID exists
    public boolean contains(String vehicleID) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleID().equals(vehicleID)) {
                return true;
            }
        }
        return false;
    }

    // Generate a unique vehicle ID based on existing IDs
    private synchronized String generateUniqueVehicleID() {
        LastUsedID++;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleType().equals(Vehicle.VehicleType.BASIC_TRUCK)
                    && vehicle.getVehicleType().equals(Vehicle.VehicleType.REEFER_TRUCK)
                    && vehicle.getVehicleType().equals(Vehicle.VehicleType.TANKER_TRUCK)) {

                return "tr- " + lastAssignedNumber; // Truck IDs must follow this format
            } else if (vehicle.getVehicleType().equals(Vehicle.VehicleType.SHIP)) {
                return "sh-" + lastAssignedNumber; // Ship IDs must follow this format
            }
        }
        return null;

    }

    public void serializeVehiclesToFile() {
        try (FileOutputStream fileOutputStream = new FileOutputStream("dataFile/vehicles.dat");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(vehicles);

            System.out.println("Vehicles have been serialized and saved to dataFile/ports.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deserializeVehiclesFromFile() {
        try (FileInputStream fileInputStream = new FileInputStream("data/vehicles.dat");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            ArrayList<Vehicle> importedVehicles = (ArrayList<Vehicle>) objectInputStream.readObject();

            vehicles = importedVehicles;
            System.out.println("Vehicles have been deserialized and imported from data/vehicles.dat");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
  
    // Add a ship to the list and save to a file
    public Ship addShip(String name, double carryingCapacity, double fuelCapacity, String portID) {
        Ship vehicle = new Ship(this.generateUniqueVehicleID(), name, carryingCapacity, fuelCapacity, portID);
        vehicles.add(vehicle);
        this.serializeVehiclesToFile();
        return vehicle;
    }

    // Add a truck to the list and save to a file
    public BasicTruck addTruck(String name, double carryingCapacity, double fuelCapacity, String type, String portID) {
        BasicTruck vehicle;

        if ("TankerTruck".equalsIgnoreCase(type)) {
            vehicle = new TankerTruck(this.generateUniqueVehicleID(), name, carryingCapacity, fuelCapacity, portID);
        } else if ("ReeferTruck".equalsIgnoreCase(type)) {
            vehicle = new ReeferTruck(this.generateUniqueVehicleID(), name, carryingCapacity, fuelCapacity, portID);
        } else {
            // Handle invalid truck type
            System.err.println("Invalid truck type: " + type);
            return null; // Return null or throw an exception as needed
        }

        vehicles.add(vehicle);
        this.serializeVehiclesToFile();
        return vehicle;
    }
}
