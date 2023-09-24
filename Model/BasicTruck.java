
package Model;
import java.util.ArrayList;
import java.util.Map;

import Model.Container.ContainerType;
import Model.Vehicle.VehicleType;

public class BasicTruck extends Vehicle {
  
    public BasicTruck(String vehicleID, String name, double carryingCapacity, double fuelCapacity, String currentPort) {
        super(vehicleID, name, carryingCapacity, fuelCapacity, currentPort);
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

        } else
            return type == Container.ContainerType.OPEN_TOP;

    }
    @Override
    public void move(Port destinationPort) {}

    @Override
    public double estimatedFuelConsumption(double distance) {
        double fuelConsumption = 0.0;

        for (Container container : containers) {
            double containerFuelConsumption = container.calculateFuelConsumption(VehicleType.BASIC_TRUCK, distance);
            fuelConsumption += containerFuelConsumption;
        }

        return fuelConsumption;
    }
}
