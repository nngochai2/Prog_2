package Controller;

import Model.*;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ManageVehicle {
    // Apply Singleton instance
    private static ManageVehicle instance;
    // Create a list to store vehicles
    private List<Vehicle> vehicles;
    // Counter for generating unique vehicle IDs
    private int lastAssignedNumber = 0;

    // Private constructor for Singleton patter
    private ManageVehicle() {
        vehicles = new ArrayList<>();
    }

    // Get the Singleton instance
    public static ManageVehicle getInstance() {
        if (instance == null) {
            instance = new ManageVehicle();
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

    // Add a ship to the list and save to a file
    public Ship addShip(String name, double carryingCapacity, double fuelCapacity) {
        Ship vehicle = new Ship(this.generateUniqueVehicleID(), name, carryingCapacity, fuelCapacity);
        vehicles.add(vehicle);
        this.saveVehiclesToFile();
        return vehicle;
    }

    // Add a truck to the list and save to a file
    public BasicTruck addTruck(String name, double carryingCapacity, double fuelCapacity, String type) {
        BasicTruck vehicle = new BasicTruck(this.generateUniqueVehicleID(), name, carryingCapacity, fuelCapacity);
        vehicles.add(vehicle);
        this.saveVehiclesToFile();
        return vehicle;
    }

    // Generate a unique vehicle ID based on existing IDs
    private synchronized String generateUniqueVehicleID() {
        lastAssignedNumber++;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleType().equals(Vehicle.VehicleType.BASIC_TRUCK)&&vehicle.getVehicleType().equals(Vehicle.VehicleType.REEFER_TRUCK)&&vehicle.getVehicleType().equals(Vehicle.VehicleType.TANKER_TRUCK)) {
                return "tr- " + lastAssignedNumber;
            } else if (vehicle.getVehicleType().equals(Vehicle.VehicleType.SHIP)) {
                return "sh-" + lastAssignedNumber;
            }
        } return null;

    }

    public void saveVehiclesToFile() {

    }

}
