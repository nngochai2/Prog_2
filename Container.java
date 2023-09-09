import java.util.Map;

public class Container {
    private final String containerID;
    private final ContainerType type;
    private final String contents;
    private final double weight;

    public enum ContainerType {
        //Represents the different container types.
        DRY_STORAGE,
        OPEN_TOP,
        OPEN_SIDE,
        REFRIGERATED,
        LIQUID
    }

    public Container(String containerID, ContainerType type, String contents, double weight) {
        this.containerID = containerID;
        this.type = type;
        this.contents = contents;
        this.weight = weight;
    }

    public void load(ContainerType type) {
        //Implement loading logic based on container type.
    }

    public void unload() {
    }

    public double calculateFuelConsumption(Vehicle vehicle, double distanceInKm) {
        // Define fuel consumption rate
        double fuelRate = switch (type) {
            // 'switch' allows to test the value of 'type' against different cases

            case DRY_STORAGE -> (vehicle instanceof Ship) ? 3.5 : 4.6;
            // If the vehicle is a ship, '3.5' is assigned to fuelRate.
            // If the vehicle is not a ship, 4.6 is assigned to fuelRate.

            case OPEN_TOP -> (vehicle instanceof Ship) ? 2.8 : 3.2;
            case OPEN_SIDE -> (vehicle instanceof Ship) ? 2.7 : 3.2;
            case REFRIGERATED -> (vehicle instanceof Ship) ? 4.5 : 5.4;
            case LIQUID -> (vehicle instanceof Ship) ? 4.8 : 5.3;
        }; // Default value

        return fuelRate * weight * distanceInKm;
    }
}


