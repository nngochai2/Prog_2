package Model;

import java.util.ArrayList;
import java.util.Map;



public class ReeferTruck extends BasicTruck {
    public ReeferTruck(String vehicleID, String name, double carryingCapacity, double fuelCapacity) {
        super(vehicleID, name, carryingCapacity, fuelCapacity);
        if (!vehicleID.matches("^tr\\d+$")) {
            System.out.println("Invalid vehicle ID. It must be tr-number.");
        }
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.REEFER_TRUCK;
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
        return type == Container.ContainerType.REFRIGERATED;
    }

    @Override
    public void move(Port destinationPort) {
        super.move(destinationPort);
    }
}
