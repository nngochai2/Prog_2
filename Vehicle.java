import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class Vehicle implements IVehicle {
    protected String vehicleID;
    protected String name;
    protected int currentFuel;
    protected int carryingCapacity;
    protected int fuelCapacity;
    protected Port currentPort;
    protected int totalContainers;

    protected ArrayList<Container> containers;
    protected Map<Container.ContainerType, Integer> containerCounts;
    // Used to keep track of the number of each type of container that a vehicle is carrying, store and manage the counts of different container types.

    public Vehicle(String vehicleID, String name, int currentFuel, int carryingCapacity, int fuelCapacity, Port currentPort, int totalContainers, ArrayList<Container> containers, Map<Container.ContainerType, Integer> containerCounts) {
        this.vehicleID = vehicleID;
        this.name = name;
        this.currentFuel = currentFuel;
        this.carryingCapacity = carryingCapacity;
        this.fuelCapacity = fuelCapacity;
        this.currentPort = currentPort;
        this.totalContainers = totalContainers;
        this.containers = containers;
        this.containerCounts = containerCounts;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public String getName() {
        return name;
    }

    public int getCurrentFuel() {
        return currentFuel;
    }

    public int getCarryingCapacity() {
        return carryingCapacity;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public Port getCurrentPort() {
        return currentPort;
    }

    public int getTotalContainers() {
        return totalContainers;
    }

    public ArrayList<Container> getContainers() {
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

    public void setCurrentPort(Port currentPort) {
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

    // Calculate the total weight for each type of containers
//    public Map<Container.ContainerType, Double> calculateWeightForEachType() { // The method returns a 'Map' the associates each ContainerType with its total weight as 'Double'
//        // Initialize a new HashMap
//        Map<Container.ContainerType, Double> weightForEachType = new HashMap<>();
//
//        // Iterates over all possible container types defined in the enum
//        for (Container.ContainerType type : Container.ContainerType.values()) {
//            double totalWeightForType = 0.0;
//
//            // Iterates over all the containers stored in the ArrayList
//            for (Container container : containers) {
//                if (container.getType() == type) {
//                    totalWeightForType += container.getWeight();
//                }
//            }
//            weightForEachType.put(type, totalWeightForType);
//        }
//        return weightForEachType;
//    }

    public double calculateTotalWeightForEachType(Container.ContainerType containerType) {
        // Calculate the total weight for the given container type
        double totalWeight = 0.0;
        for (Container container : containers) {
            if (container.getType() == containerType) {
                totalWeight += container.getWeight();
            }
        }
        return totalWeight;
    }

    public double estimatedFuelConsumption(Port destinationPort) {
        double distance = currentPort.distanceCalculator(destinationPort);
        double fuelConsumption = 0.0;
        for (Container container : containers) {
            double containerFuelConsumption = container.calculateFuelConsumption(this, distance);
            fuelConsumption += containerFuelConsumption;
        }
        return fuelConsumption;
    }

    // Check whether a vehicle can start a trip or not
    public boolean canMove() {
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
        double estimatedFuelConsumption = estimatedFuelConsumption(currentPort);

        if (currentFuel < estimatedFuelConsumption) {
            System.out.println("This vehicle can't leave. Not enough fuel!");
        }
        return true;
    }
}
