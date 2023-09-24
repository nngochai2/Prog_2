
package Model;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;


public class Container {
    private String containerID;
    private ContainerType type;
    private double weight;
    private String location;

    private List<Container> containerList;

    public Container(String containerID, double weight, ContainerType type) {

    }

    public enum ContainerType {
        // Represents the different container types.
        DRY_STORAGE,
        OPEN_TOP,
        OPEN_SIDE,
        REFRIGERATED,
        LIQUID
    }

    public Container(String containerID, ContainerType type, double weight, String location) {
        if (!containerID.matches("^c\\d+$")) {
            System.out.println("Invalid container ID. It must be c-number.");
        }
        this.containerID = containerID;
        this.type = type;
        this.weight = weight;
        this.location = location;
    }

    // Define fuel consumption rates
    public enum FuelRate {
        SHIP(3.5, 4.6),
        BASIC_TRUCK(4.6, 3.5),
        TANKER_TRUCK(4.8, 3.7),
        REEFER_TRUCK(5.0, 3.9);

        private final double shipRate;
        private final double truckRate;

        FuelRate(double shipRate, double truckRate) {
            this.shipRate = shipRate;
            this.truckRate = truckRate;
        }

        public double getShipRate() {
            return shipRate;
        }

        public double getTruckRate() {
            return truckRate;
        }
    }

    public void load(Vehicle from, Port to) {

        // if weight < Port.storingCapacity
        // Update Container.location
        // Update Port.containersCount
        // Update Vehicle.totalContainers

        this.location = to.getPortID();
    }

    public void load(Port from, Ship to) {

        // if weight < Ship.carryingCapacity
        // Update Container.location
        // Update Port.containersCount
        // Update Ship.totalContainers

    }

    public String getContainerID() {
        return containerID;
    }

    public ContainerType getType() {
        return type;
    }

    public double getWeight() {
        return weight;
    }


    public void load(ContainerType type) {
        // Implement loading logic based on container type.
    }


    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setContainerID(String containerID) {
        this.containerID = containerID;
    }

    public void setContainerType(ContainerType containerType) {
        this.type = containerType;
    }

    public double calculateFuelConsumption(Vehicle.VehicleType type, double distanceInKm) {
        FuelRate fuelRate = null;
        if (type == Vehicle.VehicleType.SHIP) {
            fuelRate = FuelRate.SHIP;
        } else if (type == Vehicle.VehicleType.BASIC_TRUCK) {
            fuelRate = FuelRate.BASIC_TRUCK;
        } else if (type == Vehicle.VehicleType.TANKER_TRUCK) {
            fuelRate = FuelRate.TANKER_TRUCK;
        } else if (type == Vehicle.VehicleType.REEFER_TRUCK) {
            fuelRate = FuelRate.REEFER_TRUCK;
        }

        if (fuelRate != null) {
            double rate = (type == Vehicle.VehicleType.SHIP) ? fuelRate.getShipRate() : fuelRate.getTruckRate();
            return rate * weight * distanceInKm;
        } else {
            return -1.0; // Return -1 to indicate invalid input
        }
    }
}

