import java.util.Map;

public abstract class Vehicle implements IVehicle {
    protected String vehicleID;
    protected String name;
    protected int currentFuel;
    protected int carryingCapacity;
    protected int fuelCapacity;
    protected Port currentPort;
    protected int totalContainers;
    protected Map<Container.ContainerType, Integer> containerCounts;
    // Used to keep track of the number of each type of container that a vehicle is carrying, store and manage the counts of different container types.

//    Constructor
    public Vehicle(String vehicleID, String name, int currentFuel, int carryingCapacity, int fuelCapacity, Port currentPort, int totalContainers) {
        this.vehicleID = vehicleID;
        this.name = name;
        this.currentFuel = currentFuel;
        this.carryingCapacity = carryingCapacity;
        this.fuelCapacity = fuelCapacity;
        this.currentPort = currentPort;
        this.totalContainers = totalContainers;

        for (Container.ContainerType type : Container.ContainerType.values()) {
            containerCounts.put(type, 0);
        }

    }

//    Getter & Setter

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
//    public boolean canMove() {
//        // Check whether a vehicle can make a trip or not
//        if (currentFuel <= Container.)
//    }
//    public void move(Port destinationPort) {
//        if (canMove)
//    }
}