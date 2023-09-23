package Model;

import java.util.List;

public interface IPort {
    String getPortID(); // Get the port's unique ID

    String getName(); // Get the port's name

    double getLatitude(); // Get the latitude coordinate

    double getLongitude(); // Get the longitude coordinate

    double getStoringCapacity(); // Get the storing capacity of the port

    boolean hasLandingAbility(); // Check the landing ability

    double calculateDistance(Port otherPort); // Calculate distance between ports

    int addContainers(List<Container> containersToAdd); // Add containers to the port

    void removeContainer(Container container); // Decrease containers at the port

}