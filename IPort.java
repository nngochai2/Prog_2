public interface IPort {
    String getPortID(); // Get the port's unique ID
    String getName(); // Get the port's name
    double getLatitude(); // Get the latitude coordinate
    double getLongitude(); // Get the longitude coordinate
    int getStoringCapacity(); // Get the storing capacity of the port
    boolean hasLandingAbility(); // Check the landing ability
    double calculateDistance(Port otherPort); // Calculate distance between ports
    void addContainers(int amount); // Add containers to the port
    void decreaseContainer(int amount); // Decrease containers at the port
}