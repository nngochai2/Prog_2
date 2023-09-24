package Controller;

import Model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import Model.Vehicle;

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


    private synchronized String generateUniqueVehicleID(Vehicle.VehicleType vehicleType) {
        lastUsedID++;

        String prefix = "";
        switch (vehicleType) {
            case SHIP:
                prefix = "sh-";
                break;
            case BASIC_TRUCK:
            case TANKER_TRUCK:
            case REEFER_TRUCK:
                prefix = "tr-";
                break;
            default:
                System.err.println("Invalid vehicle type: " + vehicleType);
                return null;

        }

        return prefix + lastUsedID;
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
        String vehicleID = generateUniqueVehicleID(Vehicle.VehicleType.SHIP);
        if (vehicleID != null) {
            Ship vehicle = new Ship(vehicleID, name, carryingCapacity, fuelCapacity, portID);
            listVehicle.add(vehicle);
            this.serializeVehiclesToFile("data/vehicles.dat");
            return vehicle;
        }
        return null;
    }



    // Add a truck to the list and save to a file
    public BasicTruck addTruck(String name, double carryingCapacity, double fuelCapacity, String portID, Vehicle.VehicleType type) {
        Vehicle.VehicleType vehicleType = null;

        if (type == Vehicle.VehicleType.TANKER_TRUCK) {
            vehicleType = Vehicle.VehicleType.TANKER_TRUCK;
        } else if (type == Vehicle.VehicleType.REEFER_TRUCK) {
            vehicleType = Vehicle.VehicleType.REEFER_TRUCK;
        } else if (type == Vehicle.VehicleType.BASIC_TRUCK) {
            vehicleType = Vehicle.VehicleType.BASIC_TRUCK;
        } else {
            System.err.println("Invalid truck type: " + type);
            return null;
        }

        String vehicleID = generateUniqueVehicleID(vehicleType);
        if (vehicleID != null) {
            BasicTruck vehicle = new BasicTruck(vehicleID, name, carryingCapacity, fuelCapacity, portID);
            vehicle.setVehicleType(vehicleType); // Set the VehicleType separately
            listVehicle.add(vehicle);
            this.serializeVehiclesToFile("data/vehicles.dat");
            return vehicle;
        }
        return null;

    }

}
