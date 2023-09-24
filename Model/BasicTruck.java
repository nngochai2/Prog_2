
package Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import Model.Container.ContainerType;
import Model.Vehicle.VehicleType;

public class BasicTruck extends Vehicle implements Serializable {
  
    public BasicTruck(String vehicleID, String name, double carryingCapacity, double fuelCapacity, String currentPort) {
        super(vehicleID, name, carryingCapacity, fuelCapacity, currentPort);

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

    public void setVehicleType(VehicleType vehicleType) {
    }

    @Override
    public String toString() {
        String portID = getCurretPort() != null ? getCurretPort().getPortID() : "N/A";
        return "Basic Truck ID: " + getVehicleID() + ", Name: " + getName() + ", Carrying Capacity: " + getCarryingCapacity() + ", Fuel Capacity: " + getFuelCapacity() + ", Port ID: " + portID;
    }



}
