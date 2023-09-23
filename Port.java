import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Port implements IPort {
    private String portID;
    private String name;
    private double latitude;
    private double longitude;
    private double storingCapacity;
    private int currentTotalWeight;
    private boolean landingAbility;
    private int containersCount;
    private int vehiclesCount;
    private ArrayList<Trip> pastTrips;
    private ArrayList<Trip> currentTrips;
    private ArrayList<Container> containers;
    private ArrayList<Vehicle> vehicles;

    public Port(String portID, String name, double latitude, double longitude, int storingCapacity,
            boolean landingAbility, int containersCount, int vehiclesCount, ArrayList<Trip> pastTrips,
            ArrayList<Trip> currentTrips, ArrayList<Container> containers, ArrayList<Vehicle> vehicles) {
        if (!portID.matches("^p\\d+$")) {
            System.out.println("Invalid port ID. It must be p-number.");
        } else {
            this.portID = portID;
            this.name = name;
            this.latitude = latitude;
            this.longitude = longitude;
            this.storingCapacity = storingCapacity;
            this.currentTotalWeight = 0;
            this.landingAbility = landingAbility;
            this.containersCount = containersCount;
            this.vehiclesCount = vehiclesCount;
            this.pastTrips = pastTrips;
            this.currentTrips = currentTrips;
            this.containers = new ArrayList<>();
        }
    }

    public Port(String portID, String name, double latitude, double longitude, int storingCapacity,
            boolean landingAbility, int containersCount, int vehiclesCount, ArrayList<Trip> pastTrips,
            ArrayList<Trip> currentTrips) {
    }

    // Getter & Setter
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

    public double getStoringCapacity() {
        return storingCapacity;
    }

    public int getCurrentTotalWeight() {
        return currentTotalWeight;
    }

    public ArrayList<Container> getContainers() {
        return containers;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setPortID(String portID) {
        this.portID = portID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setStoringCapacity(double storingCapacity) {
        this.storingCapacity = storingCapacity;
    }

    public void setCurrentTotalWeight(int currentTotalWeight) {
        this.currentTotalWeight = currentTotalWeight;
    }

    public void setContainersCount(int containersCount) {
        this.containersCount = containersCount;
    }

    public void setVehiclesCount(int vehiclesCount) {
        this.vehiclesCount = vehiclesCount;
    }

    public void setPastTrips(ArrayList<Trip> pastTrips) {
        this.pastTrips = pastTrips;
    }

    public void setCurrentTrips(ArrayList<Trip> currentTrips) {
        this.currentTrips = currentTrips;
    }

    public void setContainers(ArrayList<Container> containers) {
        this.containers = containers;
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

    public boolean getLandingAbility() {
        return this.landingAbility;
    }

    public void setLandingAbility(boolean landingAbility) {
        this.landingAbility = landingAbility;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
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

    // Methods
    public void addNewTrip(Trip trip) {
        currentTrips.add(trip);
    }

    public void completeTrip(Trip trip) {
        currentTrips.remove(trip);
        pastTrips.add(trip);
    }

    // Add containers
    @Override
    public int addContainers(List<Container> containersToAdd) {
        // Calculate the total weight of containers being added
        double totalWeightToAdd = containersToAdd.stream()
                .mapToDouble(Container::getWeight)
                .sum();

        // Calculate the total weight of containers currently in the port
        double currentTotalWeight = calculateTotalWeight();

        // Check if adding these containers would exceed the storing capacity
        if (currentTotalWeight + totalWeightToAdd <= storingCapacity) {
            containers.addAll(containersToAdd); // Add the containers to the port
            containersCount += containersToAdd.size(); // Update the containers count
            return containersToAdd.size(); // Return the number of containers added
        } else {
            return 0; // Adding containers would exceed the storing capacity, return 0
        }
    }

    @Override
    public void removeContainer(Container container) {
        containers.remove(container);
    }

    public double distanceCalculator(Port otherPort) {
        // Calculating the distance between the current port to another.

        // Convert the coordinates to Radian
        double lat1 = Math.toRadians(this.latitude);
        double lon1 = Math.toRadians(this.longitude);
        double lat2 = Math.toRadians(otherPort.latitude);
        double lon2 = Math.toRadians(otherPort.longitude);

        // Calculate the distance with provided formula
        return 6378 * Math
                .acos((Math.sin(lat1) * Math.sin(lat2)) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1));
    }

    // Count the total number of containers
    public int countTotalContainers() {
        return containers.size();
    }

    // Count the numbers of containers divided by type
    public Map<Container.ContainerType, Integer> countContainersByType() {
        Map<Container.ContainerType, Integer> containerCounts = new HashMap<>();

        // Initialize counts
        for (Container.ContainerType type : Container.ContainerType.values()) {
            containerCounts.put(type, 0);
        }

        // Loop through containers and count by type
        for (Container container : containers) {
            Container.ContainerType type = container.getType();
            containerCounts.put(type, containerCounts.getOrDefault(type, 0) + 1);
        }

        return containerCounts;
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
                vehicleCounts.put(Vehicle.VehicleType.BASIC_TRUCK,
                        vehicleCounts.getOrDefault(Vehicle.VehicleType.BASIC_TRUCK, 0) + 1);

                // Check for ReeferTruck or TankerTruck and update accordingly
                if (vehicle instanceof ReeferTruck) {
                    vehicleCounts.put(Vehicle.VehicleType.REEFER_TRUCK,
                            vehicleCounts.getOrDefault(Vehicle.VehicleType.REEFER_TRUCK, 0) + 1);
                } else if (vehicle instanceof TankerTruck) {
                    vehicleCounts.put(Vehicle.VehicleType.TANKER_TRUCK,
                            vehicleCounts.getOrDefault(Vehicle.VehicleType.TANKER_TRUCK, 0) + 1);
                }
            }
        }
        return vehicleCounts;
    }
}
