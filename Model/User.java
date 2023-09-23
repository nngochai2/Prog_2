package Model;

import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public abstract class User {
    private String userID;
    private String username;
    private String password;
    private UserRole role;

    public User(String userID, String username, String password, UserRole role) {
        this.userID = userID;
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

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    // Check if the provided username and password match the store value
    public User login(String username, String password) {
        if (this.username.equals(username) && this.password.equals(password)) {
            return this;
        }
        return null;
    }

    //    public boolean login(String username, String password) {
//        // This method is responsible for verifying the username and password
//        return (this.username.equals(username) && this.password.equals(password));
//    }


//    public void calculateDailyFuelUsage(Date date, ArrayList<Trip> trips) {
//        // This method is responsible for calculating daily fuel usage
//        double dailyFuelUsage = 0;
//        for (Trip trip : trips) {
//            if ((date.after(trip.getDepartureDate()) && date.before(trip.getArrivalDate()))
//                    || (date.equals(trip.getDepartureDate())) || (date.equals(trip.getArrivalDate()))) {
//                Vehicle vehicle = trip.getVehicle();
//                ArrayList<Container> containers = trip.getContainersOnTrip();

//                // Get the distance traveled during the trip
//                double distance = trip.getDeparturePort().calculateDistance(trip.getArrivalPort());
//
//                // Calculate the fuel consumption for the trip for (Container container : containers) {
//                    double fuelRate = container.calculateFuelConsumption(vehicle);
//                    dailyFuelUsage += fuelRate;
//                }
//                // Calculate the number of days the trip lasts
//                long millisecondsInADay = 24 * 60 * 60 * 1000;
//                long tripDurationInDays = (trip.getDepartureDate().getTime() - trip.getArrivalDate().getTime())
//                        / millisecondsInADay;
//                // Calculate daily fuel consumption for this trip
//                dailyFuelUsage *= distance / tripDurationInDays;
//            }
//        }
//        System.out.println("Total fuel used on " + date + " is: " + dailyFuelUsage + " gallons");
//    }

    public void calculateContainerWeights(ArrayList<Container> containers) {
        // This method is responsible for calculating total container weights
        double totalWeight = 0;
        for (Container container : containers) {
            totalWeight += container.getWeight();
        }
        System.out.println("Total weight of all containers: " + totalWeight + " units");
    }

    public void listShipsInPort(Port port) {
        // This method is responsible for listing all the ships in the given port
        // ArrayList<model.Ship> shipsAtPort = new ArrayList<>();
        for (Vehicle vehicle : port.getVehicles()) {
            if (vehicle instanceof Ship) {
                // shipsAtPort.add((model.Ship) vehicle);
                System.out.println(vehicle);
            }
        }
    }

    public void listTripsOnDate(Date date, List<Trip> trips) {
        // This method is responsible for listing all trips on the given
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Trip trip : trips) {
            if ((date.after(trip.getDepartureDate()) && date.before(trip.getArrivalDate()))
                    || (date.equals(trip.getDepartureDate())) || (date.equals(trip.getArrivalDate()))) {
                System.out.println("model.Trip ID: " + trip.getId());
                System.out.println("Departure Date: " + sdf.format(trip.getDepartureDate()));
                System.out.println("Arrival Date: " + sdf.format(trip.getArrivalDate()));
                System.out.println("model.Vehicle ID: " + trip.getVehicle().getVehicleID());
                System.out.println("Departure model.Port: " + trip.getDeparturePort());
                System.out.println("Arrival model.Port: " + trip.getArrivalPort());
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
                System.out.println("model.Trip ID: " + trip.getId());
                System.out.println("Departure Date: " + sdf.format(trip.getDepartureDate()));
                System.out.println("Arrival Date: " + sdf.format(trip.getArrivalDate()));
                System.out.println("model.Vehicle ID: " + trip.getVehicle().getVehicleID());
                System.out.println("Departure model.Port: " + trip.getDeparturePort());
                System.out.println("Arrival model.Port: " + trip.getArrivalPort());
                System.out.println("Status: " + trip.getStatus());
                System.out.println("-----------------------------");
            }
        }
    }

    public abstract void displayMenu();
    // This method is responsible for displaying menu based on the role of a us;
}