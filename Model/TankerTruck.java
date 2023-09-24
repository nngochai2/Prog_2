package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;



public class TankerTruck extends BasicTruck implements Serializable {
    public TankerTruck(String vehicleID, String name, double carryingCapacity, double fuelCapacity, String portID) {
        super(vehicleID, name, carryingCapacity, fuelCapacity, portID);
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
    public double estimatedFuelConsumption(double distance) {
        double fuelConsumption = 0.0;

        for (Container container : containers) {
            double containerFuelConsumption = container.calculateFuelConsumption(VehicleType.TANKER_TRUCK, distance);
            fuelConsumption += containerFuelConsumption;
        }

        return fuelConsumption;
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
