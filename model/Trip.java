package model;

import java.util.Date;
import java.util.ArrayList;

public class Trip {
    private String id;
    private Vehicle vehicle;
    private ArrayList<Container> containersOnTrip; // List to store containers on the trip
    private Date departureDate;
    private Date arrivalDate;
    private Port departurePort;
    private Port arrivalPort;
    private String status;

    // Constructor
    public Trip(String id, Vehicle vehicle, Date departureDate, Date arrivalDate, Port departurePort, Port arrivalPort,
            String status) {
        this.id = id;
        this.vehicle = vehicle;
        this.containersOnTrip = new ArrayList<>(vehicle.getContainers());
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departurePort = departurePort;
        this.arrivalPort = arrivalPort;
        this.status = status;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
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

    public String getStatus() {
        return status;
    }
}
