package Model;

import java.util.ArrayList;
import java.util.Map;

public class TankerTruck extends BasicTruck {
    public TankerTruck(String vehicleID, String name, double currentFuel, double carryingCapacity, double fuelCapacity, Port currentPort, int totalContainers, ArrayList<Container> containers, Map<Container.ContainerType, Integer> containerCounts) {
        super(vehicleID, name, currentFuel, carryingCapacity, fuelCapacity, currentPort, totalContainers, containers, containerCounts);
        if (!vehicleID.matches("^tr\\d+$")) {
            System.out.println("Invalid vehicle ID. It must be tr-number.");
        }
    }

    public VehicleType getVehicleType() {
        return VehicleType.TANKER_TRUCK;
    }

    @Override
    public double getCarryCapacity() {
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
        return type == Container.ContainerType.LIQUID;
    }

    @Override
    public void move(Port destinationPort) {
        super.move(destinationPort);
    }
}
