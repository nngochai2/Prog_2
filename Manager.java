import java.text.SimpleDateFormat;
import java.util.*;

public class Manager extends User implements IManager {
    private Port managedPort;

    public Manager(String userID, String username, String password, Port managedPort) {
        super(userID, username, password, UserRole.MANAGER);
        this.managedPort = managedPort;
    }

    // public void addContainer(Container container) {
    // this.managedPort.addContainer(container);
    // }


    public Port getManagedPort() {
        return managedPort;
    }

    public void setManagedPort(Port managedPort) {
        this.managedPort = managedPort;
    }

    @Override
    public void displayMenu() {
        System.out.println("Welcome Manager, " + getUsername() + "!");
        System.out.println("Manager's menu for port: " + managedPort.getName());
        System.out.println("1. View port details");
        System.out.println("2. Calculate daily fuel usage in the port");
        System.out.println("3. Calculate container weights in the port");
        System.out.println("4. List ships in the port");
        System.out.println("5. List trips on the given date related to port");
        System.out.println("6. List trips between the dates related to port");
        System.out.println("7. View vehicle detais in the port");
        System.out.println("8. View trips details associated with the port");
        System.out.println("9. Add container to the port");
        System.out.println("10. Edit container");
        System.out.println("11. Remove container from the port");
    }

    @Override
    public void viewPortDetails() {
        System.out.println("Port ID: " + this.managedPort.getPortID());
        System.out.println("Name: " + this.managedPort.getName());
        System.out.println("Latitude: " + this.managedPort.getLatitude());
        System.out.println("Longitude: " + this.managedPort.getLongitude());
        System.out.println("Storing Capacity: " + this.managedPort.getStoringCapacity());
        System.out.println("Landing Ability: " + this.managedPort.isLandingAbility());
        System.out.println("---------------------");
    }

    @Override
    public void login(String username, String password) {

    }

    @Override
    public void calculateDailyFuelUsage(Date date) {
        double dailyFuelUsage = 0;
        List<Trip> trips = this.managedPort.getCurrentTrips();
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
                    dailyFuelUsage += fuelRate;
                }
                // Calculate the number of days the trip lasts
                long millisecondsInADay = 24 * 60 * 60 * 1000;
                long tripDurationInDays = (trip.getDepartureDate().getTime() - trip.getArrivalDate().getTime())
                        / millisecondsInADay;
                // Calculate daily fuel consumption for this trip
                dailyFuelUsage *= distance / tripDurationInDays;
            }
        }
        System.out.println("Total fuel used on " + date + " is: " + dailyFuelUsage + " gallons in the port: "
                + managedPort.getName());
    }

    @Override
    public void calculateContainerWeights() {
        double totalWeight = 0;
        ArrayList<Container> containers = this.managedPort.getContainers();
        for (Container container : containers) {
            totalWeight += container.getWeight();
        }
        System.out.println(
                "Total weight of all containers: " + totalWeight + " units in the port: " + this.managedPort.getName());
    }

    @Override
    public void listShipsInPort() {
        for (Vehicle vehicle : this.managedPort.getVehicles()) {
            if (vehicle instanceof Ship) {
                // shipsAtPort.add((Ship) vehicle);
                System.out.println(vehicle);
            }
        }
    }

    @Override
    public void listTripsOnDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Trip> trips = this.managedPort.getCurrentTrips();
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

    @Override
    public void listTripsFromDateToDate(Date startDate, Date endDate) {
        List<Trip> trips = this.managedPort.getCurrentTrips();
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

    @Override
    public void viewVehicleDetails() {
        Scanner scanner = new Scanner(System.in);
        List<Vehicle> vehicles = this.managedPort.getVehicles();
        for (int i = 0; i < vehicles.size(); i++) {
            System.out.println(
                    i + 1 + ". Vehicle ID: " + vehicles.get(i).getVehicleID() + " Name: " + vehicles.get(i).getName());
        }
        System.out.println("-----------------------------");
        System.out.print("Pleasee select vehicle ID: ");
        int choice = scanner.nextInt();
        Vehicle selectedVehicle = vehicles.get(choice);
        System.out.println();
    }

    @Override
    public void viewTripDetails() {

    }

    @Override
    public void addContainer() {

    }

    @Override
    public void editContainerDetails() {

    }

    @Override
    public void deleteContainer() {

    }

    @Override
    public void loadContainerOntoVehicle() {

    }

    @Override
    public void unloadContainerFromVehicle() {

    }
}