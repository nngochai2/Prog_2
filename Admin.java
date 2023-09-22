import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Admin extends User implements IAdmin{

    private ArrayList<Port> ports;
    private ArrayList<Vehicle> vehicles;
    private ArrayList<Container> containers;
    private ArrayList<Manager> managers;
    private ArrayList<Trip> trips;

    public Admin(String userID, String username, String password, UserRole role, ArrayList<Port> ports,
            ArrayList<Vehicle> vehicles, ArrayList<Container> containers, ArrayList<Manager> managers,
            ArrayList<Trip> trips) {
        super(userID, username, password, role);
        this.ports = ports;
        this.vehicles = vehicles;
        this.containers = containers;
        this.managers = managers;
        this.trips = trips;
    }

    // GETTER AND SETTER METHODS
    public ArrayList<Port> getPorts() {
        return this.ports;
    }

    public void setPorts(ArrayList<Port> ports) {
        this.ports = ports;
    }

    public ArrayList<Vehicle> getVehicles() {
        return this.vehicles;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public ArrayList<Container> getContainers() {
        return this.containers;
    }

    public void setContainers(ArrayList<Container> containers) {
        this.containers = containers;
    }

    public ArrayList<Manager> getManagers() {
        return this.managers;
    }

    public void setManagers(ArrayList<Manager> managers) {
        this.managers = managers;
    }

    public ArrayList<Trip> getTrips() {
        return trips;
    }

    public void setTrips(ArrayList<Trip> trips) {
        this.trips = trips;
    }

    public void displayMenu() {
        System.out.println("Welcome Admin " + getUsername());
        System.out.println("Administrator's menu: ");
        System.out.println("1. Add Port");
        System.out.println("2. Remove Port");
        System.out.println("3. Add Vehicle");
        System.out.println("4. Remove Vehicle");
        System.out.println("5. Add Container");
        System.out.println("6. Remove Container");
        System.out.println("7. Add Manager");
        System.out.println("8. Remove Manager");
        System.out.println("9. List Ships in Port");
        System.out.println("10. List Trips on a Date");
        System.out.println("11. List Trips from Date to Date");
        System.out.println("12. Calculate Daily Fuel Usage");
        System.out.println("13. Calculate Container Weights");
    }

    @Override
    public void viewPortDetails() {

        // Offer the choices for the admin
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose an option:");
        System.out.println("1. View all port details");
        System.out.println("2. View details of a specific port");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (choice == 1) {
            ArrayList<Port> ports = this.ports;
            System.out.println("List of Ports:");

            // View all ports' details
            for (Port port : ports) {
                System.out.println("Port ID: " + port.getPortID());
                System.out.println("Name: " + port.getName());
                System.out.println("Latitude: " + port.getLatitude());
                System.out.println("Longitude: " + port.getLongitude());
                System.out.println("Storing Capacity: " + port.getStoringCapacity());
                System.out.println("Landing Ability: " + port.isLandingAbility());
                System.out.println("---------------------");
            }
        } else if (choice == 2) {
            System.out.print("Enter the Port ID to view details: ");
            String portIDToView = scanner.nextLine();

            boolean found = false;

            // View details of a specific port
            for (Port port : this.ports) {
                if (port.getPortID().equals(portIDToView)) {
                    found = true;
                    System.out.println("Port ID: " + port.getPortID());
                    System.out.println("Name: " + port.getName());
                    System.out.println("Latitude: " + port.getLatitude());
                    System.out.println("Longitude: " + port.getLongitude());
                    System.out.println("Storing Capacity: " + port.getStoringCapacity());
                    System.out.println("Landing Ability: " + port.isLandingAbility());
                    System.out.println("---------------------");
                    break;
                }
            }

            if (!found) {
                System.out.println("Port with ID " + portIDToView + " not found.");
            }
        } else {
            System.out.println("Invalid choice.");
        }
    }


    @Override
    public void login(String username, String password) {

    }

    @Override
    public void calculateDailyFuelUsage(Date date, ArrayList<Trip> trips) {
        // This method is responsible for calculating daily fuel usage
        double dailyFuelUsage = 0;
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
        System.out.println("Total fuel used on " + date + " is: " + dailyFuelUsage + " gallons");
    }

    @Override
    public void calculateContainerWeights() {
        Scanner scanner = new Scanner(System.in);

        // Offer choices for the admin
        System.out.println("Choose an option:");
        System.out.println("1. Calculate total container weights for all ports");
        System.out.println("2. Calculate total container weight for a specific port by entering its ID");

        int choice = scanner.nextInt();

        // Handle the options
        switch (choice) {
            case 1 -> {
                System.out.println("Container Weights for All Ports:");

                // Calculate and print container weights for all ports
                for (Port port : this.ports) {
                    double totalWeight = port.calculateTotalWeight();
                    System.out.println("Port ID: " + port.getPortID() + " - Total Container Weight: " + totalWeight);
                }
            }
            case 2 -> {
                System.out.println("Enter the ID of the port:");
                String portID = scanner.next();

                // Find the port with the specified ID
                Port specificPort = null;
                for (Port port : this.ports) {
                    if (port.getPortID().equals(portID)) {
                        specificPort = port;
                        break;
                    }
                }

                // If the ID is correct, proceed to calculate the weight of targeted port
                if (specificPort != null) {
                    double totalWeight = specificPort.calculateTotalWeight();
                    System.out.println("Total Container Weight for Port ID " + portID + ": " + totalWeight);
                } else {
                    System.out.println("Port with ID " + portID + " not found.");
                }
            }
            default -> System.out.println("Invalid choice.");
        }

        scanner.close();
    }

    @Override
    public void listShipsInPort() {
        Scanner scanner = new Scanner(System.in);

        // Require user input
        System.out.println("Enter the ID of the port to list ships:");
        String portID = scanner.next();

        // Find the port with the specified ID
        Port specificPort = null;
        for (Port port : this.ports) {
            if (port.getPortID().equals(portID)) {
                specificPort = port;
                break;
            }
        }


        if (specificPort != null) {
            ArrayList<Ship> shipsInPort = new ArrayList<>();

            // Iterate through all vehicles in the port to find ships
            for (Vehicle vehicle : specificPort.getVehicles()) {
                if (vehicle instanceof Ship) {
                    Ship ship = (Ship) vehicle;
                    shipsInPort.add(ship);
                }
            }

            if (!shipsInPort.isEmpty()) {
                System.out.println("Ships in Port ID " + portID + ":");
                for (Ship ship : shipsInPort) {
                    System.out.println("Ship ID: " + ship.getVehicleID() + " - Name: " + ship.getName());
                }
            } else {
                System.out.println("No ships found in Port ID " + portID + ".");
            }
        } else {
            System.out.println("Port with ID " + portID + " not found.");
        }

        scanner.close();
    }


    @Override
    public void listTripsOnDate() {

    }

    @Override
    public void listTripsFromDateToDate() {

    }

    @Override
    public void viewVehicleDetails() {

    }

    @Override
    public void viewTripDetails() {

    }

    // Add a new port to the system
    public void addPort() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the Port ID: ");
        String portID = scanner.nextLine();

        // Check if there is already an existing port with the same ID
        for (Port existingPort : ports) {
            if (existingPort.getPortID().equals(portID)) {
                System.out.println("A port with the same ID already exists!");
                return;
            }
        }

        System.out.println("Enter the Port Name: ");
        String name = scanner.nextLine();

        for (Port existingPort : ports) {
            if (existingPort.getName().equals(name)) {
                System.out.println("A port with the same name already exists!");
                return;
            }
        }

        System.out.println("Enter the Latitude: ");
        double latitude = scanner.nextDouble();

        System.out.println("Enter the Longitude: ");
        double longitude = scanner.nextDouble();

        for (Port existingPort : ports) {
            if (existingPort.getLatitude() == latitude && existingPort.getLongitude() == longitude) {
                System.out.println("A port with the same location already exists!");
                return;
            }
        }

        // Check storing capacity
        System.out.println("Enter the Storing Capacity: ");
        int storingCapacity = scanner.nextInt();

        // Might need to be changed
        System.out.println("Does it have landing ability? (true/false): ");
        boolean landingAbility = scanner.nextBoolean();

        // Create a new Port object with the collected information
        Port newPort = new Port(portID, name, latitude, longitude, storingCapacity, landingAbility, 0, 0,
                new ArrayList<>(), new ArrayList<>());

        // Add the new port to the list of ports
        ports.add(newPort);
        System.out.println("New port with ID " + newPort.getPortID() + " has been added successfully.");
        scanner.close();
    }

    @Override
    public void editPortDetails() {

    }

    // Remove an existing port
    @Override
    public void deletePort() {
        Scanner scanner = new Scanner(System.in);
        // Collect the port ID that the admin wants to delete
        System.out.println("Enter the Port ID to delete:");
        String portIDToDelete = scanner.nextLine();

        // Find the port with the given ID
        Port portToDelete = null;
        for (Port port : ports) {
            if (port.getPortID().equals(portIDToDelete)) {
                portToDelete = port;
                break; // Break from the for loop once the port ID is found
            }
        }

        if (portToDelete != null) {
            // Remove the port from the list of ports
            ports.remove(portToDelete);
            System.out.println("Port with ID " + portIDToDelete + " has been deleted.");
        } else {
            System.out.println("Port with ID " + portIDToDelete + " not found. Deletion failed.");
        }
    }

    // Add a vehicle
    public void addVehicle() {
        Scanner scanner = new Scanner(System.in);

        // Require user input
        System.out.println("Enter the vehicle ID: ");
        String vehicleID = scanner.nextLine();

        
        for (Vehicle vehicle : vehicles) {
            // Check if there is already a vehicle with the same ID
            if (vehicleID.matches("^sh\\d+$") && vehicleID.matches("^tr\\d+$")) {
                if (vehicle.getVehicleID().equals(vehicleID)) {
                    System.out.println("Error. There is already a vehicle with the same ID");
                    return;
                }
            }
        }

        System.out.println("Enter the vehicle type (SHIP, BASIC_TRUCK, REEFER_TRUCK, TANKER_TRUCK): ");
        String vehicleTypeStr = scanner.nextLine();
        Vehicle.VehicleType vehicleType = Vehicle.VehicleType.valueOf(vehicleTypeStr);

        // Enter vehicle name
        System.out.println("Enter the vehicle name: ");
        String name = scanner.nextLine();

        // Enter vehicle name
        System.out.println("Enter the current fuel: ");
        double currentFuel = scanner.nextDouble();

        // Enter vehicle capacity
        System.out.println("Enter the vehicle capacity: ");
        double capacity = Double.parseDouble(scanner.nextLine());

        // Enter vehicle fuel capacity
        System.out.println("Enter the vehicle capacity: ");
        double fuelCapacity = Double.parseDouble(scanner.nextLine());

        // Create the new vehicle based on user input
        Vehicle newVehicle;
        switch (vehicleType) {
            case SHIP -> newVehicle = new Ship(vehicleID, capacity);
            case BASIC_TRUCK -> newVehicle = new BasicTruck(vehicleID, capacity);
            case REEFER_TRUCK -> newVehicle = new ReeferTruck(vehicleID, capacity);
            case TANKER_TRUCK -> newVehicle = new TankerTruck(vehicleID, capacity);
            default -> {
                System.out.println("Invalid vehicle type.");
                return;
            }
        }

        // Add the new vehicle to the list of vehicles
        vehicles.add(newVehicle);
        System.out.println("New vehicle with ID " + vehicleID + " has been added successfully.");
        scanner.close();
    }

    // Remove a vehicle from the system
    public void removeVehicle(String vehicleID) {
        for (Vehicle vehicle : vehicles) {
            // Check if the removing vehicle exists
            if (vehicle.getVehicleID().equals(vehicleID)) {
                // Remove the vehicle from the list of vehicles
                vehicles.remove(vehicle);
                System.out.println("Vehicle with the ID " + vehicleID + " has been removed.");
                return;
            }
        }
    }



    @Override
    public void editVehicleDetails() {

    }

    @Override
    public void removeVehicle() {

    }

    //    public void addContainer(Container container) {
//        // Check if there is already an existing container with the same ID
//        for (Container exisitngContainer : containers) {
//            if(exisitngContainer.getContainerID().equals(container.getContainerID())) {
//                System.out.println("A container with the same ID has already exists!");
//                return;
//            }
//        }
//        containers.add(container);
//        System.out.println("New container with ID " + container.getContainerID() + " has been added successfully.");
//    }
    // Add a container to the system
    public void addContainer() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the container ID: ");
        String containerID = scanner.nextLine();

        System.out.println("Enter the container type (e.g., DRY_STORAGE, OPEN_TOP, OPEN_SIDE, REFRIGERATED, LIQUID): ");
        String containerTypeStr = scanner.nextLine();
        Container.ContainerType containerType = Container.ContainerType.valueOf(containerTypeStr);

        System.out.println("Enter the weight of the container: ");
        double weight = scanner.nextDouble();

        // Create a new Container object based on the collected information.
        Container newContainer = new Container(containerID, containerType, weight);

        // Add the new container to your ArrayList or data structure.
        containers.add(newContainer);

        System.out.println("Container " + containerID + " has been added successfully.");
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

    // Remove a container from the system
    public void removeContainer(String containerID) {
        // Check if the removing container exists based on its unique ID.
        Container containerToRemove = null;
        for (Container container : containers) {
            if (container.getContainerID().equals(containerID)) {
                containerToRemove = container;
                break; // Stop searching once the container is found.
            }
        }

        if (containerToRemove != null) {
            // Remove the found container from the list.
            containers.remove(containerToRemove);
            System.out.println("Container with ID " + containerID + " has been removed successfully.");
        } else {
            System.out.println("Container with ID " + containerID + " does not exist. Removal failed.");
        }
    }

    // Add a new manager to the system

    public void addManager() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the new manager's ID: ");
        String userID = scanner.nextLine();

        // Check if a user with the same ID exists
        for (Manager existingManager : managers) {
            if (existingManager.getUserID().equals(userID)) {
                System.out.println("A manager with the same ID already exists!");
                return;
            }
        }

        System.out.println("Enter the new manager's user: ");
        String username = scanner.nextLine();

        System.out.println("Enter the manager's password: ");
        String password = scanner.nextLine();

        scanner.close();
    }

    @Override
    public void editManagerDetails() {

    }

    @Override
    public void deleteManager() {

    }

    @Override
    public void generateTrafficReport() {

    }

    @Override
    public void generateContainerStatisticsReport() {

    }

    // Remove a manager from the system
    public void removeManager(String username) {
        // Check if the manager exists based on the username.
        Manager managerToRemove = null;
        for (Manager manager : managers) {
            if (manager.getUsername().equals(username)) {
                managerToRemove = manager;
                break; // Stop searching once the manager is found.
            }
        }

        if (managerToRemove != null) {
            // Remove the found manager from the list.
            managers.remove(managerToRemove);
            System.out.println("Manager with username " + username + " has been removed successfully.");
        } else {
            System.out.println("Manager with username " + username + " does not exist. Removal failed.");
        }
    }
}
