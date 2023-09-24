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
    public boolean validateVehicleId(String containerId) {
        String regex = "^([a-z]{2})-(\\\\d+)$";
        return containerId.matches(regex);
    }

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
    public boolean addShip(String name, double carryingCapacity, double fuelCapacity, String portID) {
        String vehicleID = generateUniqueVehicleID(Vehicle.VehicleType.SHIP);
        if (vehicleID != null) {
            Ship vehicle = new Ship(vehicleID, name, carryingCapacity, fuelCapacity, portID);
            listVehicle.add(vehicle);
            this.serializeVehiclesToFile("data/vehicles.dat");
            return true;
        } else {
            return false;
        }
    }

    public boolean addTankerTruck(String name, double carryingCapacity, double fuelCapacity, String portID) {
        String vehicleID = generateUniqueVehicleID(Vehicle.VehicleType.TANKER_TRUCK);
        if (vehicleID != null) {
            TankerTruck vehicle = new TankerTruck(vehicleID, name, carryingCapacity, fuelCapacity, portID);
            listVehicle.add(vehicle);
            this.serializeVehiclesToFile("data/vehicles.dat");
            return true;
        } else {
            return false;
        }
    }

    public boolean addReeferTruck(String name, double carryingCapacity, double fuelCapacity, String portID) {
        String vehicleID = generateUniqueVehicleID(Vehicle.VehicleType.REEFER_TRUCK);
        if (vehicleID != null) {
            ReeferTruck vehicle = new ReeferTruck(vehicleID, name, carryingCapacity, fuelCapacity, portID);
            listVehicle.add(vehicle);
            this.serializeVehiclesToFile("data/vehicles.dat");
            return true;
        } else {
            return false;
        }
    }

    public boolean addBasicTruck(String name, double carryingCapacity, double fuelCapacity, String portID) {
        String vehicleID = generateUniqueVehicleID(Vehicle.VehicleType.BASIC_TRUCK);
        if (vehicleID != null) {
            BasicTruck vehicle = new BasicTruck(vehicleID, name, carryingCapacity, fuelCapacity, portID);
            listVehicle.add(vehicle);
            this.serializeVehiclesToFile("data/vehicles.dat");
            return true;
        } else {
            return false;
        }
    }

    public boolean removeVehicle(String vehicleID) {
        boolean value = false;
        for (Vehicle vehicle : listVehicle) {
            if (vehicle.getVehicleID().equals(vehicleID)) {
                listVehicle.remove(vehicle);
                serializeVehiclesToFile("data/vehicles.dat");
                value = true;
                break;
            } else {
                value = false;
            }
        }
        return value;
    }


}
