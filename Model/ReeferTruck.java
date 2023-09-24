package Model;

import java.util.ArrayList;
import java.util.Map;



public class ReeferTruck extends BasicTruck {
<<<<<<< Updated upstream
    public ReeferTruck(String vehicleID, String name, double carryingCapacity, double fuelCapacity) {
        super(vehicleID, name, carryingCapacity, fuelCapacity);
=======
    public ReeferTruck(String vehicleID, String name, double carryingCapacity, double fuelCapacity, String portID) {
        super(vehicleID, name, carryingCapacity, fuelCapacity, portID);

>>>>>>> Stashed changes
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
    public double estimatedFuelConsumption(double distance) {
        double fuelConsumption = 0.0;

        for (Container container : containers) {
            double containerFuelConsumption = container.calculateFuelConsumption(VehicleType.TANKER_TRUCK, distance);
            fuelConsumption += containerFuelConsumption;
        }

        return fuelConsumption;
    }
    @Override
    public void move(Port destinationPort) {
        super.move(destinationPort);
    }
}
