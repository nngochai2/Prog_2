package Controller;

public class ManageVehicles {

<<<<<<< Updated upstream
=======
    // Private constructor for Singleton patter
    private ManageVehicles() {
        vehicles = new ArrayList<>();
    }

    // Get the Singleton instance
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

    // Add a ship to the list and save to a file
    public Ship addShip(String name, double carryingCapacity, double fuelCapacity, String currentPort) {
        Ship vehicle = new Ship(this.generateUniqueVehicleID(), name, carryingCapacity, fuelCapacity, currentPort);
        vehicles.add(vehicle);
        this.serializeVehiclesToFile();
        ;
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

    // Generate a unique vehicle ID based on existing IDs
    private synchronized String generateUniqueVehicleID() {
        lastAssignedNumber++;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleType().equals(Vehicle.VehicleType.BASIC_TRUCK)
                    && vehicle.getVehicleType().equals(Vehicle.VehicleType.REEFER_TRUCK)
                    && vehicle.getVehicleType().equals(Vehicle.VehicleType.TANKER_TRUCK)) {
                return "TR- " + lastAssignedNumber;
            } else if (vehicle.getVehicleType().equals(Vehicle.VehicleType.SHIP)) {
                return "SH-" + lastAssignedNumber;
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
>>>>>>> Stashed changes
}
