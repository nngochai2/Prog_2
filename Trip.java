import java.util.Date;

public class Trip {
    private Vehicle vehicle;
    private Date departureDate;
    private Date arrivalDate;
    private Port departurePort;
    private Port arrivalPort;
    private String status;

//    Constructor
    public Trip(Vehicle vehicle, Date departureDate, Date arrivalDate, Port departurePort, Port arrivalPort, String status) {
        this.vehicle = vehicle;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departurePort = departurePort;
        this.arrivalPort = arrivalPort;
        this.status = status;
    }

    public Vehicle getVehicle() {
        return vehicle;
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
