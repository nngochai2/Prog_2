
package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Vehicle implements IVehicle {
    protected String vehicleID;

    protected String name;
    protected double currentFuel;
    protected double carryingCapacity;
    protected double fuelCapacity;
    protected String currentPort;
    protected int totalContainers;
    protected List<Container> containers = new ArrayList<Container>();
    protected Map<Container.ContainerType, Integer> containerCounts;
    // Used to keep track of the number of each type of container that a vehicle is carrying, store and manage the counts of different container types.
    
    public Vehicle(String vehicleID, String name, double currentFuel, double carryingCapacity, String currentPort) {
        this.vehicleID = vehicleID;
        this.name = name;
        this.currentFuel = fuelCapacity;
        this.fuelCapacity = fuelCapacity;
        this.carryingCapacity = carryingCapacity;
        this.currentPort = currentPort;
    }

    // Define the vehicle types
    public enum VehicleType {
        SHIP,
        BASIC_TRUCK,
        TANKER_TRUCK,
        REEFER_TRUCK
    }


    public abstract VehicleType getVehicleType();

    public String getVehicleID() {
        return vehicleID;
    }

    public String getName() {
        return name;
    }

    public double getCurrentFuel() {
        return currentFuel;
    }

    public double getCarryingCapacity() {
        return carryingCapacity;
    }

    public double getFuelCapacity() {
        return fuelCapacity;
    }

    public String getCurrentPort() {
        return currentPort;
    }

    public int getTotalContainers() {
        return totalContainers;
    }

    public List<Container> getContainers() {
        return containers;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrentFuel(int currentFuel) {
        this.currentFuel = currentFuel;
    }

    public void setCarryingCapacity(int carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public void setCurrentPort(String currentPort) {
        this.currentPort = currentPort;
    }

    public void setTotalContainers(int totalContainers) {
        this.totalContainers = totalContainers;
    }


    public boolean canLoadContainerType(Container.ContainerType type) {

        // Check whether a vehicle can carry a specific container type
        return true;
    };
    public void loadContainer(Container.ContainerType type) {
        // Increment the count of a specific container type
        if(canLoadContainerType(type)) {
            containerCounts.put(type, containerCounts.get(type) + 1);
            totalContainers++;
        }
        else {
            // Show error message if the container does not match the requirements
            System.err.println("Error: This vehicle cannot load " + type + " container.");
        }
    }

    // Unload a container from a vehicle
    public void unloadContainer(Container.ContainerType type) {
        if(containerCounts.get(type) > 0) {
            // Can only unload if there is at least one container
            containerCounts.put(type, containerCounts.get(type) - 1);
            totalContainers--;
        }
        else {
            System.err.println("Error: No " + type + " container to unload.");
        }
    }

    // Calculate the total weight of container on a container
    public double calculateTotalWeight() {
        double totalWeight = 0.0;
        for (Container container : containers) {
            totalWeight += container.getWeight();
        }
        return totalWeight;
    }

// Calculate the total weight for each type of containers (return a HashMap, might be useful)
//    public Map<model.Container.ContainerType, Double> calculateTotalWeightForEachType() { // The method returns a 'Map' the associates each ContainerType with its total weight as 'Double'
//        // Initialize a new HashMap
//        Map<model.Container.ContainerType, Double> weightForEachType = new HashMap<>();
//
//        // Iterates over all possible container types defined in the enum
//        for (model.Container.ContainerType type : model.Container.ContainerType.values()) {
//            double totalWeightForType = 0.0;
//
//            // Iterates over all the containers stored in the ArrayList
//            for (model.Container container : containers) {
//                if (container.getType() == type) {
//                    totalWeightForType += container.getWeight();
//                }
//            }
//            weightForEachType.put(type, totalWeightForType);
//        }
//        return weightForEachType;
//    }

    // Calculate total weight
    public double calculateTotalWeight(Container.ContainerType containerType) {
        // Calculate the total weight for the given container type
        double totalWeight = 0.0;
        for (Container container : containers) {
            if (container.getType() == containerType) {
                totalWeight += container.getWeight();
            }
        }
        return totalWeight;
    }


    // Estimate the fuel consumption based on the containers carried by a vehicle, will be implemented differently by each type of vehicle
    public abstract double estimatedFuelConsumption(double distance);

    // Check whether a vehicle can start a trip or not
    public boolean canMove(Port currentPort, Port destinationPort) {
        // Check if the ID is valid
        if (!currentPort.getPortID().equals(destinationPort.getPortID())) {
            if (currentFuel == 0) {
                System.out.println("Vehicle is out of fuel!");
                return false;
            }

            // Calculate total weight of all containers on a vehicle
            double totalWeight = calculateTotalWeight();

            // Check if the total weight exceeds the carrying capacity
            if (totalWeight > carryingCapacity) {
                System.out.println("Vehicle is overloaded.");
                return false;
            }

            // Calculate estimated fuel consumption
            double estimatedFuelConsumption = estimatedFuelConsumption(destinationPort.calculateDistance(destinationPort));

            // Check if the current fuel is enough
            if (currentFuel < estimatedFuelConsumption) {
                System.out.println("This vehicle can't leave. Not enough fuel!");
                return false;
            }
        }
        return false;
    }

    // Add or update a container count in the map
    public void updateContainerCount(Container.ContainerType type, int count) {
        containerCounts.put(type, count);
    }

    // Get the container count for a specific type
    public int getContainerCount(Container.ContainerType type) {
        return containerCounts.getOrDefault(type, 0);
    }

    // Remove a container count from the map
    public void removeContainerCount(Container.ContainerType type) {
        containerCounts.remove(type);
    }
}
