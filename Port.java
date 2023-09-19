import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Port implements IPort{
    private String portID;
    private String name;
    private double latitude;
    private double longitude;
    private int storingCapacity;
    private boolean landingAbility;
    private int containersCount;
    private int vehiclesCount;
    private ArrayList<Trip> pastTrips;
    private ArrayList<Trip> currentTrips;
    private ArrayList<Container> containers;
    private ArrayList<Vehicle> vehicles;

    public Port(String portID, String name, double latitude, double longitude, int storingCapacity, boolean landingAbility, int containersCount, int vehiclesCount, ArrayList<Trip> pastTrips, ArrayList<Trip> currentTrips, ArrayList<Container> containers, ArrayList<Vehicle> vehicles) {
        if (!portID.matches("^p\\d+$")) {
            System.out.println("Invalid port ID. It must be p-number.");
        }
        this.portID = portID;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.storingCapacity = storingCapacity;
        this.landingAbility = landingAbility;
        this.containersCount = containersCount;
        this.vehiclesCount = vehiclesCount;
        this.pastTrips = pastTrips;
        this.currentTrips = currentTrips;
        this.containers = containers;
        this.vehicles = vehicles;
    }

    public String getPortID() {
        return portID;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getStoringCapacity() {
        return storingCapacity;
    }

    @Override
    public boolean hasLandingAbility() {
        return false;
    }

    @Override
    public double calculateDistance(Port otherPort) {
        return 0;
    }

    public boolean isLandingAbility() {
        return landingAbility;
    }

    public int getContainersCount() {
        return containersCount;
    }

    public int getVehiclesCount() {
        return vehiclesCount;
    }

    public List<Trip> getPastTrips() {
        return pastTrips;
    }

    public List<Trip> getCurrentTrips() {
        return currentTrips;
    }

    public boolean canLandTrucks() {
        return landingAbility;
    }

    public void addNewTrip(Trip trip) {
        currentTrips.add(trip);
    }

    public void completeTrip(Trip trip) {
        currentTrips.remove(trip);
        pastTrips.add(trip);
    }

    public void addContainers(int amount) {
        containersCount += amount;
        vehiclesCount++;
    }

    @Override
    public void decreaseContainer(int amount) {
        containersCount -= amount;
        vehiclesCount--;
    }

    public double distanceCalculator(Port otherPort) {
        // Calculating the distance between the current port to another.

        // Convert the coordinates to Radian
        double lat1 = Math.toRadians(this.latitude);
        double lon1 = Math.toRadians(this.longitude);
        double lat2 = Math.toRadians(otherPort.latitude);
        double lon2 = Math.toRadians(otherPort.longitude);

        // Calculate the distance with provided formula
        return 6378 * Math.acos((Math.sin(lat1) * Math.sin(lat2)) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1));
    }

    // Count the total number of containers
    public int countTotalContainers() {
        return containers.size();
    }

    // Calculate the total weight
    public double calculateTotalWeight() {
        double totalWeight = 0.0;
        for (Container container : containers) {
            totalWeight += container.getWeight();
        }
        return totalWeight;
    }

    // Count the vehicles divided by types
    public Map<Vehicle.VehicleType, Integer> countVehicleByType() {
        Map<Vehicle.VehicleType, Integer> vehicleCounts = new HashMap<>();

        // Initialize counts
        for (Vehicle.VehicleType type : Vehicle.VehicleType.values()) {
            vehicleCounts.put(type, 0);
        }

        // Making a for loop and counting by type
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Ship) {
                vehicleCounts.put(Vehicle.VehicleType.SHIP, vehicleCounts.get(Vehicle.VehicleType.SHIP) + 1);
            } else if (vehicle instanceof BasicTruck) {
                vehicleCounts.put(Vehicle.VehicleType.BASIC_TRUCK, vehicleCounts.getOrDefault(Vehicle.VehicleType.BASIC_TRUCK, 0) + 1);

                // Check for ReeferTruck or TankerTruck and update accordingly
                if (vehicle instanceof ReeferTruck) {
                    vehicleCounts.put(Vehicle.VehicleType.REEFER_TRUCK, vehicleCounts.getOrDefault(Vehicle.VehicleType.REEFER_TRUCK, 0) + 1);
                } else if (vehicle instanceof TankerTruck) {
                    vehicleCounts.put(Vehicle.VehicleType.TANKER_TRUCK, vehicleCounts.getOrDefault(Vehicle.VehicleType.TANKER_TRUCK, 0) + 1);
                }
            }
        }
        return vehicleCounts;
    }
}
