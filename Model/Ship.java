package Model;

import java.util.ArrayList;
import java.util.Map;

public class Ship extends Vehicle {
//    public Ship(String vehicleID, String name, double currentFuel, double carryingCapacity, double fuelCapacity, Port currentPort, int totalContainers, ArrayList<Container> containers, Map<Container.ContainerType, Integer> containerCounts) {
//        super(vehicleID, name, currentFuel, carryingCapacity, fuelCapacity, currentPort, totalContainers, containers, containerCounts);
//        if (!vehicleID.matches("^sh\\d+$")) {
//            // '^': the pattern must start from th beginning of the ID
//            // 'sh': force the ID to start with 'sh'
//            // '\\d+': ensures that there is at least 1 digit following the 'sh'
//            // '$': the pattern must reach the end of the ID
//            System.out.println("Invalid vehicle ID. It must be sh-number.");
//        }
//    }


    public Ship(String vehicleID, String name, double carryingCapacity, double fuelCapacity) {
        super(vehicleID, name, carryingCapacity, fuelCapacity);
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
    @Override
    public void move(Port destinationPort) {
        // Calculate the distance to the destination port
        double distance = currentPort.calculateDistance(destinationPort);
        // Calculate the fuel consumption
    }


}
