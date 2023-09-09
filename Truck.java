import java.util.Map;

public class Truck extends Vehicle {
    private final boolean isReeferTruck;
    private final boolean isTankerTruck;
    public Truck(String vehicleID, String name, int currentFuel, int carryingCapacity, int fuelCapacity, Port currentPort, int totalContainers, boolean isReeferTruck, boolean isTankerTruck) {
        super(vehicleID, name, currentFuel, carryingCapacity, fuelCapacity, currentPort, totalContainers);
        this.isReeferTruck = isReeferTruck;
        this.isTankerTruck = isTankerTruck;
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
        if(isReeferTruck) {
            //Only reefer truck can carry refrigerated containers.
            return type == Container.ContainerType.REFRIGERATED;
        }
        else if (isTankerTruck) {
            //Only tanker truck can carry liquid containers.
            return type == Container.ContainerType.LIQUID;
        }
        else {
            //Normal trucks can carry dry storage, open-top, and open side containers.
            return  type == Container.ContainerType.DRY_STORAGE ||
                    type == Container.ContainerType.OPEN_TOP ||
                    type == Container.ContainerType.OPEN_SIDE;
        }
    }

    @Override
    public void move(Port destinationPort) {

    }
}
