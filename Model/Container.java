package Model;

public class Container {
    private String containerID;
    private ContainerType type;
    private double weight;
    private String location;

    public enum ContainerType {
        // Represents the different container types.
        DRY_STORAGE,
        OPEN_TOP,
        OPEN_SIDE,
        REFRIGERATED,
        LIQUID
    }

    public Container(String containerID, ContainerType type, double weight, String location) {
        if (!containerID.matches("^c\\d+$")) {
            System.out.println("Invalid container ID. It must be c-number.");
        }
        this.containerID = containerID;
        this.type = type;
        this.weight = weight;
        this.location = location;
    }

    public void load(Vehicle from, Port to) {
        // if weight < Port.storingCapacity
        // Update Container.location
        // Update Port.containersCount
        // Update Vehicle.totalContainers
        this.location = to.getPortID();
    }

    public void load(Port from, Ship to) {
        // if weight < Ship.carryingCapacity
        // Update Container.location
        // Update Port.containersCount
        // Update Ship.totalContainers
    }

    public String getContainerID() {
        return containerID;
    }

    public ContainerType getType() {
        return type;
    }

    public double getWeight() {
        return weight;
    }

    public void load(ContainerType type) {
        // Implement loading logic based on container type.
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setContainerID(String containerID) {
        this.containerID = containerID;
    }

    public void setContainerType(ContainerType containerType) {
        this.type = containerType;
    }

    public double calculateFuelConsumption(Vehicle vehicle) {
        // Define fuel consumption rate
        double fuelRate = switch (type) {
            // 'switch' is used to test the value of 'type' against different cases

            case DRY_STORAGE -> (vehicle instanceof Ship) ? 3.5 : 4.6;
            // If the vehicle is a ship, '3.5' is assigned to fuelRate.
            // If the vehicle is not a ship, 4.6 is assigned to fuelRate.

            case OPEN_TOP -> (vehicle instanceof Ship) ? 2.8 : 3.2;
            case OPEN_SIDE -> (vehicle instanceof Ship) ? 2.7 : 3.2;
            case REFRIGERATED -> (vehicle instanceof Ship) ? 4.5 : 5.4;
            case LIQUID -> (vehicle instanceof Ship) ? 4.8 : 5.3;
        };

        // Default value
        return fuelRate * weight;
    }
}
