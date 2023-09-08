import java.util.Map;

public class Container {
    private String containerID;
    private ContainerType type;
    private String contents;

    private double weight;

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

    public double calculateFuelConsumption() {
        // Define fuel consumption rates

    }
}


