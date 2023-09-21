import java.util.ArrayList;
import java.util.Map;

public class ReeferTruck extends BasicTruck {
    public ReeferTruck(String vehicleID, String name, int currentFuel, int carryingCapacity, int fuelCapacity, Port currentPort, int totalContainers, ArrayList<Container> containers, Map<Container.ContainerType, Integer> containerCounts) {
        super(vehicleID, name, currentFuel, carryingCapacity, fuelCapacity, currentPort, totalContainers, containers, containerCounts);
        if (!vehicleID.matches("^tr\\d+$")) {
            System.out.println("Invalid vehicle ID. It must be tr-number.");
        }
    }

    public ReeferTruck(String vehicleID, double capacity) {
        super();
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.REEFER_TRUCK;
    }

    @Override
    public int getCarryCapacity() {
        return super.getCarryCapacity();
    }

    @Override
    public Port getCurretPort() {
        return super.getCurretPort();
    }

    @Override
    public Map<Container, Integer> getContainerCounts() {
        return super.getContainerCounts();
    }

    @Override
    public boolean canLoadContainerType(Container.ContainerType type) {
        return type == Container.ContainerType.REFRIGERATED;
    }

    @Override
    public void move(Port destinationPort) {
        super.move(destinationPort);
    }


}
