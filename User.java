import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public abstract class User {
    private final String username;
    private final String password;
    private final UserRole role;

    public User(String username, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public enum UserRole {
        ADMIN,
        MANAGER,
    }

    public UserRole getRole() {
        return this.role;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean login(String username, String password) {
        // This method is responsible for verifying the username and password
        return (this.username.equals(username) && this.password.equals(password));
    }

    public void calculateDailyFuelUsage(Date date, ArrayList<Trip> trips) {
        // This method is responsible for calculating daily fuel usage
        double totalFuelUsage = 0;
        for (Trip trip : trips) {
            if ((date.after(trip.getDepartureDate()) && date.before(trip.getArrivalDate()))
                    || (date.equals(trip.getDepartureDate())) || (date.equals(trip.getArrivalDate()))) {
                Vehicle vehicle = trip.getVehicle();
                ArrayList<Container> containers = trip.getContainersOnTrip();
                // Get the distance traveled during the trip
                double distance = trip.getDeparturePort().calculateDistance(trip.getArrivalPort());

                // Calculate the fuel consumption for the trip
                for (Container container : containers) {
                    double fuelRate = container.calculateFuelConsumption(vehicle);
                    totalFuelUsage += fuelRate;
                }
                totalFuelUsage *= distance;
            }
        }
        System.out.println("Total fuel used on " + date + " is: " + totalFuelUsage + " gallons");
    }

    public void calculateContainerWeights(ArrayList<Container> containers) {
        // This method is responsible for calculating total container weights
        double totalWeight = 0;
        for (Container container : containers) {
            totalWeight += container.getWeight();
        }
        System.out.println("Total weight of all containers: " + totalWeight + " units");
    }

    // Chua lam dc
    public void listShipsInPort(Port port) {
        // This method is responsible for listing all the ships in the given port
        ArrayList<Ship> ships;
    }

    public void listTripsOnDate(Date date, List<Trip> trips) {
        // This method is responsible for listing all trips on the given
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Trip trip : trips) {
            if ((date.after(trip.getDepartureDate()) && date.before(trip.getArrivalDate()))
                    || (date.equals(trip.getDepartureDate())) || (date.equals(trip.getArrivalDate()))) {
                System.out.println("Trip ID: " + trip.getId());
                System.out.println("Departure Date: " + sdf.format(trip.getDepartureDate()));
                System.out.println("Arrival Date: " + sdf.format(trip.getArrivalDate()));
                System.out.println("Vehicle ID: " + trip.getVehicle().getVehicleID());
                System.out.println("Departure Port: " + trip.getDeparturePort());
                System.out.println("Arrival Port: " + trip.getArrivalPort());
                System.out.println("Status: " + trip.getStatus());
                System.out.println("-----------------------------");
            }

        }
    }

    public void listTripsFromDateToDate(Date startDate, Date endDate, List<Trip> trips) {
        // This method is responsible for listin trips from one date to another
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Trip trip : trips) {
            if ((startDate.equals(trip.getDepartureDate()) || startDate.after(trip.getDepartureDate()))
                    && (endDate.before(trip.getArrivalDate())) || endDate.equals(trip.getArrivalDate())) {
                System.out.println("Trip ID: " + trip.getId());
                System.out.println("Departure Date: " + sdf.format(trip.getDepartureDate()));
                System.out.println("Arrival Date: " + sdf.format(trip.getArrivalDate()));
                System.out.println("Vehicle ID: " + trip.getVehicle().getVehicleID());
                System.out.println("Departure Port: " + trip.getDeparturePort());
                System.out.println("Arrival Port: " + trip.getArrivalPort());
                System.out.println("Status: " + trip.getStatus());
                System.out.println("-----------------------------");
            }
        }
    }

    public abstract void displayMenu();
    // This method is responsible for displaying menu based on the role of a us;
}