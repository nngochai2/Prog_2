package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;



public class TankerTruck extends BasicTruck implements Serializable {
    public TankerTruck(String vehicleID, String name, double carryingCapacity, double fuelCapacity, String portID) {
        super(vehicleID, name, carryingCapacity, fuelCapacity, portID);

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
    public String toString() {
        String portID = getCurretPort() != null ? getCurretPort().getPortID() : "N/A";
        return "Tanker Truck ID: " + getVehicleID() + ", Name: " + getName() + ", Carrying Capacity: " + getCarryingCapacity() + ", Fuel Capacity: " + getFuelCapacity() + ", Port ID: " + portID;
    }



    @Override
    public void move(Port destinationPort) {
        super.move(destinationPort);
    }
}
