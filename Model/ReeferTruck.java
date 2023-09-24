package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import Model.Vehicle.VehicleType;


public class ReeferTruck extends BasicTruck implements Serializable {
    public ReeferTruck(String vehicleID, String name, double carryingCapacity, double fuelCapacity, String currentPort) {
        super(vehicleID, name, carryingCapacity, fuelCapacity, currentPort);

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
    public double estimatedFuelConsumption(double distance) {
        double fuelConsumption = 0.0;

        for (Container container : containers) {
            double containerFuelConsumption = container.calculateFuelConsumption(VehicleType.TANKER_TRUCK, distance);
            fuelConsumption += containerFuelConsumption;
        }

        return fuelConsumption;
    }
    @Override
    public String toString() {
        String portID = getCurretPort() != null ? getCurretPort().getPortID() : "N/A";
        return "Reefer Truck ID: " + getVehicleID() + ", Name: " + getName() + ", Carrying Capacity: " + getCarryingCapacity() + ", Fuel Capacity: " + getFuelCapacity() + ", Port ID: " + portID;
    }



    @Override
    public void move(Port destinationPort) {
        super.move(destinationPort);
    }
}
