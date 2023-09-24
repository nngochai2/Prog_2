package Controller;

import Model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManageVehicles {
    // Apply Singleton instance
    private static ManageVehicles instance;
    // Create a list to store vehicles
    private List<Vehicle> listVehicle;
    // Counter for generating unique vehicle IDs

    private int lastUsedID = 0;

    public List<Vehicle> getAllVehicles() {
        return listVehicle;
    }


    // Private constructor for Singleton patter
    private ManageVehicles() {
        listVehicle = new ArrayList<>();
    }


    public static ManageVehicles getInstance() {
        if (instance == null) {
            instance = new ManageVehicles();
        }
        return instance;
    }

    // Get all vehicles
    public Optional<Vehicle> getVehicleByID(String vehicleID) {
        for (Vehicle vehicle : listVehicle) {
            if (vehicle.getVehicleID().equals(vehicleID)) {
                return Optional.of(vehicle);
            }
        }
        return Optional.empty();
    }

    // Check if a vehicle with a given ID exists
    public boolean contains(String vehicleID) {
        for (Vehicle vehicle : listVehicle) {
            if (vehicle.getVehicleID().equals(vehicleID)) {
                return true;
            }
        }
        return false;
    }

    // Generate a unique vehicle ID based on existing IDs
    private synchronized String generateUniqueVehicleID() {


        lastUsedID++;

        for (Vehicle vehicle : listVehicle) {
            if (vehicle.getVehicleType().equals(Vehicle.VehicleType.BASIC_TRUCK)
                    && vehicle.getVehicleType().equals(Vehicle.VehicleType.REEFER_TRUCK)
                    && vehicle.getVehicleType().equals(Vehicle.VehicleType.TANKER_TRUCK)) {

                return "tr- " + lastUsedID; // Truck IDs must follow this format
            } else if (vehicle.getVehicleType().equals(Vehicle.VehicleType.SHIP)) {
                return "sh-" + lastUsedID; // Ship IDs must follow this format
            }
        }
        return null;

    }
    public void serializeVehiclesToFile(String filePath) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs(); // Create parent directories if they don't exist
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(listVehicle);

            System.out.println("Containers have been saved to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error: Unable to save containers to " + filePath);
        }
    }



    public void deserializeVehiclesFromFile() {
        try (FileInputStream fileInputStream = new FileInputStream("data/vehicles.dat");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            ArrayList<Vehicle> importedVehicles = (ArrayList<Vehicle>) objectInputStream.readObject();

            listVehicle = importedVehicles;
            System.out.println("Vehicles have been deserialized and imported from data/vehicles.dat");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
  
    // Add a ship to the list and save to a file
    public Ship addShip(String name, double carryingCapacity, double fuelCapacity, String portID) {
        String uniqueVehicleID = generateUniqueVehicleID(); // Generate a unique vehicle ID
        if (uniqueVehicleID != null) {
            Ship vehicle = new Ship(uniqueVehicleID, name, carryingCapacity, fuelCapacity, portID);
            listVehicle.add(vehicle);
            this.serializeVehiclesToFile("data/vehicles.dat");
            return vehicle;
        } else {
            System.err.println("Error: Unable to generate a unique vehicle ID.");
            return null;
        }
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

        listVehicle.add(vehicle);
        this.serializeVehiclesToFile("data/vehicles.dat");
        return vehicle;
    }
}
