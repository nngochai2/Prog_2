import java.awt.*;
import java.util.List;

public class Port implements IPort{
    private String portID;
    private String name;
    private double latitude;
    private double longitude;
    private int storingCapacity;
    private boolean landingAbility;
    private int containersCount;
    private int vehiclesCount;
    private List<Trip> pastTrips;
    private List<Trip> currentTrips;

    public Port(String portID, String name, double latitude, double longitude, int storingCapacity, boolean landingAbility, int containersCount, int vehiclesCount, List<Trip> pastTrips, List<Trip> currentTrips) {
        if (!portID.matches("^p\\d+$")) {
            System.out.println("Invalid port ID. It must be p-number.");
        }
        this.portID = portID;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.storingCapacity = storingCapacity;
        this.landingAbility = landingAbility;
        this.containersCount = containersCount;
        this.vehiclesCount = vehiclesCount;
        this.pastTrips = pastTrips;
        this.currentTrips = currentTrips;
    }

    public String getPortID() {
        return portID;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getStoringCapacity() {
        return storingCapacity;
    }

    @Override
    public boolean hasLandingAbility() {
        return false;
    }

    @Override
    public double calculateDistance(Port otherPort) {
        return 0;
    }

    public boolean isLandingAbility() {
        return landingAbility;
    }

    public int getContainersCount() {
        return containersCount;
    }

    public int getVehiclesCount() {
        return vehiclesCount;
    }

    public List<Trip> getPastTrips() {
        return pastTrips;
    }

    public List<Trip> getCurrentTrips() {
        return currentTrips;
    }

    public boolean canLandTrucks() {
        return landingAbility;
    }

    public void addNewTrip(Trip trip) {
        currentTrips.add(trip);
    }

    public void completeTrip(Trip trip) {
        currentTrips.remove(trip);
        pastTrips.add(trip);
    }

    public void addContainers(int amount) {
        containersCount += amount;
        vehiclesCount++;
    }

    @Override
    public void decreaseContainer(int amount) {
        containersCount -= amount;
        vehiclesCount--;
    }

//    public double distanceCalculator(Port otherPort) {
//        // Calculating the distance between the current port to another.
//        double dis
//    }


}
