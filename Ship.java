import java.util.Map;

public class Ship extends Vehicle {
    public Ship(String vehicleID, String name, int currentFuel, int carryingCapacity, int fuelCapacity, Port currentPort, int totalContainers) {
        super(vehicleID, name, currentFuel, carryingCapacity, fuelCapacity, currentPort, totalContainers);
    }

    @Override
    public int getCarryCapacity() {
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
    public boolean canLoadContainer(Container.ContainerType type) {
        // Ship can load all types of containers.
        return true;
    }


    @Override
    public void move(Port destinationPort) {
        // Calculate the distance to the destination port
        double distance = currentPort.calculateDistance(destinationPort);
        // Calculate the fuel consumption
    }


}
