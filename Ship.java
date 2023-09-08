public class Ship extends Vehicle {
    public Ship(String vehicleID, String name, int currentFuel, int carryingCapacity, int fuelCapacity, Port currentPort, int totalContainers) {
        super(vehicleID, name, currentFuel, carryingCapacity, fuelCapacity, currentPort, totalContainers);
    }

    @Override
    public boolean canLoadContainer(Container.ContainerType type) {
        //Ship can load all the containers.
        return true;
    }


}
