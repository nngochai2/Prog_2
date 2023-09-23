import java.util.ArrayList;
import java.util.Map;

public class BasicTruck extends Vehicle {
    public BasicTruck(String vehicleID, String name, double currentFuel, double carryingCapacity, double fuelCapacity, Port currentPort, int totalContainers, ArrayList<Container> containers, Map<Container.ContainerType, Integer> containerCounts) {
        super(vehicleID, name, currentFuel, carryingCapacity, fuelCapacity, currentPort, totalContainers, containers, containerCounts);
        if (!vehicleID.matches("^tr\\d+$")) {
            System.out.println("Invalid vehicle ID. It must be tr-number.");
        }
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.BASIC_TRUCK;
    }

    @Override
    public double getCarryCapacity() {
        return 0;
    }

    @Override
    public Port getCurretPort() {
        return null;
    }

    @Override
    public Map<Container, Integer> getContainerCounts() {
        return null;
    }

    @Override
    public boolean canLoadContainerType(Container.ContainerType type) {
        // Check suitable containers for Basic Truck
        if (type == Container.ContainerType.DRY_STORAGE) {
            return true;
        } else if (type == Container.ContainerType.OPEN_SIDE) {
            return true;
        } else return type == Container.ContainerType.OPEN_TOP;
    }

    @Override
    public void move(Port destinationPort) {

    }
}
