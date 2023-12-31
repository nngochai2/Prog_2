
package Model;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;

public class Trip implements Serializable {
    private String tripid;
    private Vehicle vehicle;
    private ArrayList<Container> containersOnTrip; // List to store containers on the trip
    private Date departureDate;
    private Date arrivalDate;
    private Port departurePort;
    private Port arrivalPort;
    private tripStatus status;

    // Constructor
    public Trip(String tripid, Vehicle vehicle, Date departureDate, Date arrivalDate, Port departurePort, Port arrivalPort,
            tripStatus status) {
        this.tripid = tripid;
        this.vehicle = vehicle;
        this.containersOnTrip = new ArrayList<>(vehicle.getContainers());
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departurePort = departurePort;
        this.arrivalPort = arrivalPort;
        this.status = status;
    }

    public enum tripStatus {
        IN_PROGRESS,
        COMPLETED
    }

    public void setId(String id) {
        this.tripid = id;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setDeparturePort(Port departurePort) {
        this.departurePort = departurePort;
    }

    public void setArrivalPort(Port arrivalPort) {
        this.arrivalPort = arrivalPort;
    }

    public void setStatus(tripStatus status) {
        this.status = status;
    }

    public String getId() {
        return tripid;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setContainersOnTrip(ArrayList<Container> containersOnTrip) {
        this.containersOnTrip = containersOnTrip;
    }

    // Get an unmodifiable view of the containers associated with this trip
    public ArrayList<Container> getContainersOnTrip() {
        return containersOnTrip;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public Port getDeparturePort() {
        return departurePort;
    }

    public Port getArrivalPort() {
        return arrivalPort;
    }

    public tripStatus getStatus() {
        return status;
    }
}
