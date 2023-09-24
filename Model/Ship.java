
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class Ship extends Vehicle implements Serializable {
    public Ship(String vehicleID, String name, double currentFuel, double carryingCapacity, String currentPort) {
        super(vehicleID, name, currentFuel, carryingCapacity, currentPort);
        if (!vehicleID.matches("^sh\\d+$")) {
            // '^': the pattern must start from th beginning of the ID
            // 'sh': force the ID to start with 'sh'
            // '\\d+': ensures that there is at least 1 digit following the 'sh'
            // '$': the pattern must reach the end of the ID
            System.out.println("Invalid vehicle ID. It must be sh-number.");
        }
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.SHIP;
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
        // Ship can load all types of containers.
        return true;
    }

    // Calculate the total fuel consumption of a ship
    @Override
    public double estimatedFuelConsumption(double distance) {
        double fuelConsumption = 0.0;

        for (Container container : containers) {
            double containerFuelConsumption = container.calculateFuelConsumption(VehicleType.SHIP, distance);
            fuelConsumption += containerFuelConsumption;
        }

        return fuelConsumption;
    }

    @Override
    public void move(Port destinationPort) {
//        // Calculate the distance to the destination port
//        double distance = currentPort.calculateDistance(destinationPort);
//        // Calculate the fuel consumption
    }


}
