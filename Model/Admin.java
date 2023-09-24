package Model;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Model.Container.ContainerType;
import Model.User.UserRole;
import Model.Vehicle.VehicleType;


public class Admin extends User implements IAdmin {

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

    // =================================GETTER AND SETTER=======================================================
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
    public boolean verifyUser (String username, String password) {
        String encryptPassword = this.encrypt(password); // Hash the input password
        // If the username and password after hashing are correct

        return username.equals("admin");
    }

    public void getUserInfo() throws IOException {
        List<String[]> users = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("./dataFile/users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length >= 9) { // Check if there are at least 9 fields in the line
                    String ID = userData[0].trim();
                    String name = userData[1].trim();
                    String username = userData[2].trim();
                    String password = userData[6].trim();
                    String role = userData[8].trim();
                    String[] data = {ID, name, username, password, role};
                    users.add(data);
                }
            }
        }
    }

    // Display the menu for the admin
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
    public Admin login(String username, String password) {
        return (Admin) super.login(username, password);
    }

    // Calculate the total amount of fuel used in one day ( dung duoc )
//    public void calculateDailyFuelUsage(Date date) {
//        // This method is responsible for calculating daily fuel usage
//        double dailyFuelUsage = 0;
//
//        // Calculate the number of milliseconds in a day
//        long millisecondsInADay = 24 * 60 * 60 * 1000;
//
//        for (Trip trip : trips) {
//            Date tripDepartureDate = trip.getDepartureDate();
//            Date tripArrivalDate = trip.getArrivalDate();
//
//            // Check if the trip overlaps with the specified date
//            if ((date.equals(tripDepartureDate) || date.equals(tripArrivalDate)) ||
//                    (date.after(tripDepartureDate) && date.before(tripArrivalDate))) {
//
//                Vehicle vehicle = trip.getVehicle();
//                List<Container> containers = trip.getContainersOnTrip();
//
//                // Calculate the distance traveled during the trip
//                double distance = trip.getDeparturePort().calculateDistance(trip.getArrivalPort());
//
//                // Calculate the fuel consumption for the trip
//                for (Container container : containers) {
//                    double fuelRate = container.calculateFuelConsumption(vehicle);
//                    dailyFuelUsage += fuelRate;
//                }
//
//                // Calculate the duration of the trip in days
//                long tripDurationInDays = (tripArrivalDate.getTime() - tripDepartureDate.getTime()) / millisecondsInADay;
//
//                // Calculate daily fuel consumption for this trip
//                dailyFuelUsage += (dailyFuelUsage / distance) * tripDurationInDays;
//            }
//        }
//
//        System.out.println("Total fuel used on " + date + " is: " + dailyFuelUsage + " gallons");
//    }

    // =========================================TRIP FUNCTIONS==========================================================

    @Override
    public void listTripsFromDateToDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Scanner scanner = new Scanner(System.in);

        // Prompt the admin to select a port
        System.out.println("Select a port to list trips:");
        for (Port port : ports) {
            System.out.println("Port ID: " + port.getPortID());
            System.out.println("Name: " + port.getName());
        }

        // Read the selected port ID from the admin
        System.out.print("Enter the ID of the port: ");
        String portID = scanner.nextLine();

        // Find the selected port
        Port selectedPort = null;
        for (Port port : this.ports) {
            if (port.getPortID().equals(portID)) {
                selectedPort = port;
                break;
            }
        }

        if (selectedPort != null) {
            List<Trip> trips = selectedPort.getCurrentTrips();

            // Read the start and end dates from the admin
            System.out.print("Enter the start date (yyyy-MM-dd): ");
            Date startDate = parseDate(scanner.nextLine(), sdf);

            System.out.print("Enter the end date (yyyy-MM-dd): ");
            Date endDate = parseDate(scanner.nextLine(), sdf);

            System.out.println("Trips from " + sdf.format(startDate) + " to " + sdf.format(endDate) + " in Port "
                    + selectedPort.getName() + ":");

            for (Trip trip : trips) {
                Date tripStartDate = trip.getDepartureDate();
                Date tripEndDate = trip.getArrivalDate();

                // Check if trip falls within the specified date range
                if ((tripStartDate.equals(startDate) || tripStartDate.after(startDate))
                        && (tripEndDate.equals(endDate) || tripEndDate.before(endDate)
                        || (tripStartDate.equals(startDate) && tripEndDate.equals(endDate)))) {
                    // Print trip details
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
        } else {
            System.out.println("Port with ID " + portID + " not found.");
        }
    }

    // Check date format
    private Date parseDate(String dateStr, SimpleDateFormat sdf) {
        try {
            return sdf.parse(dateStr);
        } catch (Exception e) {
            System.err.println("Invalid date format. Please use yyyy-MM-dd format.");
            return null;
        }
    }

    @Override
    public void viewTripDetails() {
        // Might be unnecessary
    }

    // =========================================PORT FUNCTIONS==========================================================

    // Calculate the total weight of containers (in one port/in all ports)
//    @Override (dung duoc)
//    public void calculateContainerWeights() {
//        Scanner scanner = new Scanner(System.in);
//
//        // Offer choices for the admin
//        System.out.println("Choose an option:");
//        System.out.println("1. Calculate total container weights for all ports");
//        System.out.println("2. Calculate total container weight for a specific port by entering its ID");
//
//        int choice = scanner.nextInt();
//
//        // Handle the options
//        switch (choice) {
//            case 1 -> {
//                System.out.println("Container Weights for All Ports:");
//
//                // Calculate and print container weights for all ports
//                for (Port port : this.ports) {
//                    double totalWeight = port.calculateTotalWeight();
//                    System.out.println("Port ID: " + port.getPortID() + " - Total Container Weight: " + totalWeight);
//                }
//            }
//            case 2 -> {
//                System.out.println("Enter the ID of the port:");
//                String portID = scanner.next();
//
//                // Find the port with the specified ID
//                Port specificPort = null;
//                for (Port port : this.ports) {
//                    if (port.getPortID().equals(portID)) {
//                        specificPort = port;
//                        break;
//                    }
//                }
//
//                // If the ID is correct, proceed to calculate the weight of targeted port
//                if (specificPort != null) {
//                    double totalWeight = specificPort.calculateTotalWeight();
//                    System.out.println("Total Container Weight for Port ID " + portID + ": " + totalWeight);
//                } else {
//                    System.out.println("Port with ID " + portID + " not found.");
//                }
//            }
//            default -> System.out.println("Invalid choice.");
//        }
//
//        scanner.close();
//    }

    // View details of all ports/a specific port
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
    public void calculateDailyFuelUsage(Date date, Vehicle vehicle) {

    }

    @Override
    public void calculateContainerWeights() {

    }

    // List all the ships in a specific port
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
                if (vehicle instanceof Ship ship) {
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

    // Edit the details of a targeted port
    @Override
    public void editPortDetails() {
        Scanner scanner = new Scanner(System.in);

        // Collect the port ID to edit
        System.out.println("Enter the port ID to edit:");
        String portID = scanner.nextLine();

        // Find the port with the specified ID
        Port portToEdit = null;
        for (Port port : ports) {
            if (port.getPortID().equals(portID)) {
                portToEdit = port;
                break;
            }
        }

        // Check if the port with the specified ID was found
        if (portToEdit != null) {
            System.out.println("Port found:");
            System.out.println("Port ID: " + portToEdit.getPortID());
            System.out.println("Name: " + portToEdit.getName());
            System.out.println("Latitude: " + portToEdit.getLatitude());
            System.out.println("Longitude: " + portToEdit.getLongitude());
            System.out.println("Storing Capacity: " + portToEdit.getStoringCapacity());
            System.out.println("Landing Ability: " + portToEdit.isLandingAbility());

            // Ask for confirmation
            while (true) {
                System.out.println("Do you want to edit this port? (yes/no): ");
                String confirmation = scanner.nextLine().toLowerCase();

                if (confirmation.equals("yes")) {
                    System.out.println("Edit Port Details:");
                    System.out.println("1. Edit Name");
                    System.out.println("2. Edit Latitude");
                    System.out.println("3. Edit Longitude");
                    System.out.println("4. Edit Storing Capacity");
                    System.out.println("5. Edit Landing Ability");
                    System.out.println("6. Save and Exit");

                    System.out.println("Enter your choice: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1 -> {
                            System.out.println("Enter the new name: ");
                            String newName = scanner.nextLine();
                            portToEdit.setName(newName);
                            System.out.println("Port name updated.");
                        }
                        case 2 -> {
                            System.out.println("Enter the new latitude: ");
                            double newLatitude = scanner.nextDouble();
                            portToEdit.setLatitude(newLatitude);
                            System.out.println("Port latitude updated.");
                        }
                        case 3 -> {
                            System.out.println("Enter the new longitude: ");
                            double newLongitude = scanner.nextDouble();
                            portToEdit.setLongitude(newLongitude);
                            System.out.println("Port longitude updated.");
                        }
                        case 4 -> {
                            System.out.println("Enter the new storing capacity: ");
                            double newStoringCapacity = scanner.nextDouble();
                            portToEdit.setStoringCapacity(newStoringCapacity);
                            System.out.println("Storing capacity updated.");
                        }
                        case 5 -> {
                            System.out.println("Enter the new landing ability (true/false): ");
                            boolean newLandingAbility = scanner.nextBoolean();
                            portToEdit.setLandingAbility(newLandingAbility);
                            System.out.println("Landing ability updated.");
                        }
                        case 6 -> {
                            System.out.println("Port details updated and saved.");
                            return;
                        }
                    }
                } else if (confirmation.equals("no")) {
                    System.out.println("Editing canceled. The port was not edited.");
                    return;
                } else {
                    System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                }
            }
        } else {
            System.out.println("Port with ID " + portID + " was not found. Editing failed.");
        }
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

    // ======================================VEHICLE
    // FUNCTIONS==========================================================

    @Override
    public void viewVehicleDetails() {

    }

    // Add a vehicle
    @Override
    public void addVehicle() {

//        Scanner scanner = new Scanner(System.in);
//
//        // Require user input
//        System.out.println("Enter the vehicle ID: ");
//        String vehicleID = scanner.nextLine();
//
//        // Check if there is already a vehicle with the same ID
//        for (Vehicle vehicle : vehicles) {
//            if (vehicle.getVehicleID().equals(vehicleID)) {
//                System.out.println("Error. There is already a vehicle with the same ID");
//                return;
//            }
//        }
//
//        // Require vehicle type
//        System.out.println("Enter the vehicle type (SHIP, BASIC_TRUCK, REEFER_TRUCK, TANKER_TRUCK): ");
//        String vehicleTypeStr = scanner.nextLine();
//        Vehicle.VehicleType vehicleType;
//        try {
//            vehicleType = Vehicle.VehicleType.valueOf(vehicleTypeStr);
//        } catch (IllegalArgumentException e) {
//            System.out.println("Invalid vehicle type.");
//            return;
//        }
//
//        // Enter vehicle name
//        System.out.println("Enter the vehicle name: ");
//        String name = scanner.nextLine();
//
//        // Enter current fuel
//        System.out.println("Enter the current fuel: ");
//        double currentFuel;
//        try {
//            currentFuel = Double.parseDouble(scanner.nextLine());
//        } catch (NumberFormatException e) {
//            System.out.println("Invalid current fuel value. Please enter a valid number.");
//            return;
//        }
//
//        // Enter carrying capacity
//        System.out.println("Enter the carrying capacity: ");
//        double carryingCapacity;
//        try {
//            carryingCapacity = Double.parseDouble(scanner.nextLine());
//        } catch (NumberFormatException e) {
//            System.out.println("Invalid carrying capacity value. Please enter a valid number.");
//            return;
//        }
//
//        // Enter fuel capacity
//        System.out.println("Enter the fuel capacity: ");
//        double fuelCapacity;
//        try {
//            fuelCapacity = Double.parseDouble(scanner.nextLine());
//        } catch (NumberFormatException e) {
//            System.out.println("Invalid fuel capacity value. Please enter a valid number.");
//            return;
//        }
//
//        // Create the new vehicle based on user input
//        Vehicle newVehicle;
//        switch (vehicleType) {
//            case SHIP:

//                newVehicle = new Ship(vehicleID, name, currentFuel, carryingCapacity, fuelCapacity, null, 0,
//                        new ArrayList<>(), new HashMap<>());
//                break;
//            case BASIC_TRUCK:
//                newVehicle = new BasicTruck(vehicleID, name, currentFuel, carryingCapacity, fuelCapacity, null, 0,
//                        new ArrayList<>(), new HashMap<>());
//                break;
//            case REEFER_TRUCK:
//                newVehicle = new ReeferTruck(vehicleID, name, currentFuel, carryingCapacity
//                );
//                break;
//            case TANKER_TRUCK:
//                newVehicle = new TankerTruck(vehicleID, name, currentFuel, carryingCapacity
//                );

//                break;
//            default:
//                System.out.println("Invalid vehicle type.");
//                return;
//        }
//
//        // Add the new vehicle to the list of vehicles
//        vehicles.add(newVehicle);
//        System.out.println("New vehicle with ID " + vehicleID + " has been added successfully.");

//    }

//
//        // Enter carrying capacity
//        System.out.println("Enter the carrying capacity: ");
//        double carryingCapacity;
//        try {
//            carryingCapacity = Double.parseDouble(scanner.nextLine());
//        } catch (NumberFormatException e) {
//            System.out.println("Invalid carrying capacity value. Please enter a valid number.");
//            return;
//        }
//
//
//        // Enter fuel capacity
//        System.out.println("Enter the fuel capacity: ");
//        double fuelCapacity;
//        try {
//            fuelCapacity = Double.parseDouble(scanner.nextLine());
//        } catch (NumberFormatException e) {
//            System.out.println("Invalid fuel capacity value. Please enter a valid number.");
//            return;
//        }
//
//        // Create the new vehicle based on user input
//        Vehicle newVehicle;
//        switch (vehicleType) {
//            case SHIP:
//                newVehicle = new Ship(vehicleID, name, currentFuel, carryingCapacity, fuelCapacity, null, 0,
//                        new ArrayList<>(), new HashMap<>());
//                break;
//            case BASIC_TRUCK:
//                newVehicle = new BasicTruck(vehicleID, name, currentFuel, carryingCapacity, fuelCapacity, null, 0,
//                        new ArrayList<>(), new HashMap<>());
//                break;
//            case REEFER_TRUCK:
//                newVehicle = new ReeferTruck(vehicleID, name, currentFuel, carryingCapacity
//                );
//                break;
//            case TANKER_TRUCK:
//                newVehicle = new TankerTruck(vehicleID, name, currentFuel, carryingCapacity
//                );
//                break;
//            default:
//                System.out.println("Invalid vehicle type.");
//                return;
//        }
//
//        // Add the new vehicle to the list of vehicles
//        vehicles.add(newVehicle);
//        System.out.println("New vehicle with ID " + vehicleID + " has been added successfully.");

    }


    // Remove a vehicle from the system
    @Override
    public void removeVehicle() {
        Scanner scanner = new Scanner(System.in);

        // Require user input
        System.out.println("Enter the ID of the vehicle you want to remove: ");
        String vehicleIDToDelete = scanner.nextLine();

        // Find the vehicle with given ID
        Vehicle vehicleToDelete = null;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleID().equals(vehicleIDToDelete)) {
                vehicleToDelete = vehicle;
                break;
            }
        }
        if (vehicleToDelete != null) {
            // Remove the vehicle from the list of ports
            vehicles.remove(vehicleToDelete);
            System.out.println("Vehicle with ID " + vehicleIDToDelete + " has been deleted.");
        } else {
            System.out.println("Vehicle with ID " + vehicleIDToDelete + " not found. Deletion failed.");
        }
        scanner.close();
    }

    // Edit the vehicle details
    @Override
    public void editVehicleDetails() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Collect the vehicle ID to edit
            System.out.println("Enter the vehicle ID to edit:");
            String vehicleID = scanner.nextLine();

            // Find the vehicle with the specified ID
            Vehicle oldVehicle = null;
            Vehicle vehicleToEdit = null;
            for (Vehicle vehicle : vehicles) {
                if (vehicle.getVehicleID().equals(vehicleID)) {
                    vehicleToEdit = vehicle;
                    break;
                }
            }

            // Check if the vehicle with the specified ID was found
            if (vehicleToEdit != null) {
                System.out.println("Vehicle found:");
                System.out.println("Vehicle ID: " + vehicleToEdit.getVehicleID());
                System.out.println("Vehicle Type: " + vehicleToEdit.getVehicleType());
                System.out.println("Vehicle Name: " + vehicleToEdit.getName());
                System.out.println("Current Fuel: " + vehicleToEdit.getCurrentFuel());
                System.out.println("Carrying Capacity: " + vehicleToEdit.getCarryingCapacity());
                System.out.println("Fuel Capacity: " + vehicleToEdit.getFuelCapacity());

                // Ask for confirmation
                while (true) {
                    System.out.println("Do you want to edit this vehicle? (yes/no): ");
                    String confirmation = scanner.nextLine().toLowerCase();

                    if (confirmation.equals("yes")) {
                        System.out.println("Edit Vehicle Details:");
                        System.out.println("1. Edit Vehicle Name");
                        System.out.println("2. Edit Current Fuel");
                        System.out.println("3. Edit Carrying Capacity");
                        System.out.println("4. Edit Fuel Capacity");
                        System.out.println("5. Save and Exit");

                        System.out.println("Enter your choice: ");
                        int choice = scanner.nextInt();
                        scanner.nextLine();

                        switch (choice) {
                            case 1 -> {
                                System.out.println("Enter the new vehicle name: ");
                                String newName = scanner.nextLine();
                                vehicleToEdit.setName(newName);
                                System.out.println("Vehicle name updated.");
                            }
                            case 2 -> {
                                System.out.println("Enter the new current fuel: ");
                                int newCurrentFuel = scanner.nextInt();
                                vehicleToEdit.setCurrentFuel(newCurrentFuel);
                                System.out.println("Current fuel updated.");
                            }
                            case 3 -> {
                                System.out.println("Enter the new carrying capacity: ");
                                int newCarryingCapacity = scanner.nextInt();
                                vehicleToEdit.setCarryingCapacity(newCarryingCapacity);
                                System.out.println("Carrying capacity updated.");
                            }
                            case 4 -> {
                                System.out.println("Enter the new fuel capacity: ");
                                int newFuelCapacity = scanner.nextInt();
                                vehicleToEdit.setFuelCapacity(newFuelCapacity);
                                System.out.println("Fuel capacity updated.");
                            }
                            case 5 -> {
                                System.out.println("Vehicle details updated and saved.");
                                return;
                            }
                        }
                    } else if (confirmation.equals("no")) {
                        System.out.println("Editing canceled. The vehicle was not edited.");
                    } else {
                        System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                    }
                }
            } else {
                System.out.println("Vehicle with ID " + vehicleID + " not found. Editing failed.");
                System.out.println("Do you want to try editing another vehicle? (yes/no): ");
                String tryAgain = scanner.nextLine().toLowerCase();

                if (!tryAgain.equals("yes")) {
                    return; // Exit the function if the admin doesn't want to try again
                }
            }
        }
    }

    // ====================================CONTAINER
    // FUNCTIONS==========================================================

    // public void addContainer(Container container) {
    // // Check if there is already an existing container with the same ID
    // for (Container exisitngContainer : containers) {
    // if(exisitngContainer.getContainerID().equals(container.getContainerID())) {
    // System.out.println("A container with the same ID has already exists!");
    // return;
    // }
    // }
    // containers.add(container);
    // System.out.println("New container with ID " + container.getContainerID() + "
    // has been added successfully.");
    // }

    // Add a container to the system
    @Override
    public void addContainer() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the container ID: ");
        String containerID = scanner.nextLine();

        System.out.println("Enter the container type (e.g., DRY_STORAGE, OPEN_TOP, OPEN_SIDE, REFRIGERATED, LIQUID): ");
        String containerTypeStr = scanner.nextLine();
        Container.ContainerType containerType = Container.ContainerType.valueOf(containerTypeStr);

        System.out.println("Enter the weight of the container (in kilograms): ");
        double weight = scanner.nextDouble();

        // Require the container location (a port ID is required since container can not
        // be set directly on a vehicle
        System.out.println("Enter the port ID as the location of the container: ");
        String portID = scanner.next();

        // Check if the location (the port ID) is valid
        boolean validPort = false;
        for (Port port : this.ports) {
            if (port.getPortID().equals(portID)) {
                validPort = true;
                break;
            }
        }

        if (!validPort) {
            System.out.println("Invalid port ID. Container location cannot be set"); // Output if the location is not
            // valid
        }

        // Create a new Container object based on the collected information (necessary
        // for further modification of the created container)

        if (validPort) {
            Container newContainer = new Container(containerID, containerType, weight, portID);
            containers.add(newContainer); // Add the new container to your ArrayList or data structure.
            System.out.println("Container " + containerID + " has been added successfully.");
        }

        System.out.println("Container " + containerID + " has been added successfully.");
    }

    @Override
    public void editContainerDetails() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Collect the container ID to edit
            System.out.println("Enter the container ID to edit:");
            String containerID = scanner.nextLine();

            // Find the container with the specified ID
            Container containerToEdit = null;
            for (Container container : containers) {
                if (container.getContainerID().equals(containerID)) {
                    containerToEdit = container;
                    break;
                }
            }

            // Check if the container with the specified ID was found
            if (containerToEdit != null) {
                System.out.println("Container found:");
                System.out.println("Container ID: " + containerToEdit.getContainerID());
                System.out.println("Container Type: " + containerToEdit.getType());
                System.out.println("Container Weight: " + containerToEdit.getWeight());

                // Ask for confirmation
                while (true) {
                    System.out.println("Do you want to edit this container? (yes/no): ");
                    String confirmation = scanner.nextLine().toLowerCase();

                    if (confirmation.equals("yes")) {
                        // Provide options for the admin
                        System.out.println("Edit Container Details:");
                        System.out.println("1. Edit Container Type");
                        System.out.println("2. Edit Container Weight");
                        System.out.println("3. Save and Exit");

                        System.out.println("Enter your choice: ");
                        int choice = scanner.nextInt();
                        scanner.nextLine();

                        // Handling choices
                        switch (choice) {
                            case 1 -> {
                                System.out.println(
                                        "Enter the new container type (e.g., DRY_STORAGE, OPEN_TOP, OPEN_SIDE, REFRIGERATED, LIQUID): ");
                                String newContainerTypeStr = scanner.nextLine();
                                try {
                                    Container.ContainerType newContainerType = Container.ContainerType
                                            .valueOf(newContainerTypeStr);
                                    containerToEdit.setContainerType(newContainerType);
                                    System.out.println(
                                            "Container " + containerID + " has been updated with the new type.");
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Invalid container type.");
                                }
                            }
                            case 2 -> {
                                System.out.println("Enter the new container weight: ");
                                double newWeight = scanner.nextDouble();
                                containerToEdit.setWeight(newWeight);
                                System.out.println("Container weight updated.");
                            }
                            case 3 -> {
                                System.out.println("Container details updated and saved.");
                                return;
                            }
                        }
                    } else if (confirmation.equals("no")) {
                        System.out.println("Editing canceled. The container was not edited.");
                    } else {
                        System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                    }
                }
            } else {
                System.out.println("Container with ID " + containerID + " not found. Editing failed.");
                System.out.println("Do you want to try editing another container? (yes/no): ");
                String tryAgain = scanner.nextLine().toLowerCase();

                if (!tryAgain.equals("yes")) {
                    return; // Exit the function if the admin doesn't want to try again
                }
            }
        }
    }

    @Override
    public void deleteContainer() {
        Scanner scanner = new Scanner(System.in);

        // Require a container ID to find the container
        System.out.println("Enter the container ID to delete: ");
        String containerIDToDelete = scanner.nextLine();

        // Find and display the container information
        Container containerToDelete = null;
        for (Container container : containers) {
            if (container.getContainerID().equals(containerIDToDelete)) {
                containerToDelete = container;
                break;
            }
        }

        // If the ID is correct, proceed to find the container information
        if (containerToDelete != null) {
            System.out.println("Container found:");
            System.out.println("Container ID: " + containerToDelete.getContainerID());
            System.out.println("Container Type: " + containerToDelete.getType());
            System.out.println("Container Weight: " + containerToDelete.getWeight());

            // Ask for the user confirmation before deletion
            System.out.println("Are you sure you want to delete this container? (yes/no): ");
            String confirmation = scanner.nextLine().toLowerCase();

            if (confirmation.equals("yes")) {
                containers.remove(containerToDelete);
                System.out.println("Container with ID " + containerIDToDelete + " has been deleted successfully.");
            } else if (confirmation.equals("no")) {
                System.out.println("Deletion canceled. Container was not deleted");
            } else
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
        } else {
            System.out.println("Container with ID " + containerIDToDelete + "  not found");
            System.out.println("Do you want to try deleting another container? (yes/no): ");
            String tryAgain = scanner.nextLine().toLowerCase();

            if (!tryAgain.equals("yes")) {
                return; // Exit the function if the admin doesn't want to try again
            }
        }
    }

    // Load a container on a vehicle
    @Override
    public void loadContainerOntoVehicle() {
        Scanner scanner = new Scanner(System.in);

        // Display available vehicles (ships and trucks)
        System.out.println("Available Vehicles (Ships and Trucks):");
        for (Port port : this.ports) {
            for (Vehicle vehicle : port.getVehicles()) {
                System.out.println("Vehicle ID: " + vehicle.getVehicleID() + " - Name: " + vehicle.getName());
            }
        }

        // Ask the admin to select a vehicle to load containers onto
        System.out.println("Enter the Vehicle ID to load container(s) onto: ");
        String selectedVehicleID = scanner.next();

        // Find the vehicle with matched ID
        Vehicle selectedVehicle = null;
        for (Port port : this.ports) {
            for (Vehicle vehicle : port.getVehicles()) {
                if (vehicle.getVehicleID().equals(selectedVehicleID)) {
                    selectedVehicle = vehicle;
                    break;
                }
            }
            if (selectedVehicle != null) {
                break;
            }
        }

        if (selectedVehicle != null) {
            // Display available containers for the admin to choose
            System.out.println("Available Containers: ");
            for (Container container : containers) {
                System.out.println("Container ID: " + container.getContainerID() + " - Type: " + container.getType());
            }

            // Ask the admin to select containers to load onto the vehicle
            System.out.println("Enter the Container IDs to load onto the vehicle (comma-separated):");
            String selectedContainerIDs = scanner.next();
            String[] containerIDs = selectedContainerIDs.split(",");

            // Load the selected containers onto the vehicle
            for (String containerID : containerIDs) {
                Container containerToAdd = null;
                for (Container container : containers) {
                    if (container.getContainerID().equals(containerID.trim())) {
                        containerToAdd = container;
                        break;
                    }
                }
                if (containerToAdd != null) {
                    // Check if the vehicle can carry the container type
                    if (selectedVehicle.canLoadContainerType(containerToAdd.getType())) {
                        selectedVehicle.loadContainer(containerToAdd.getType());
                        System.out.println("Container " + containerID + " loaded onto Vehicle " + selectedVehicleID);
                    } else {
                        System.out
                                .println("Error: This vehicle cannot load " + containerToAdd.getType() + " container.");
                    }
                } else {
                    System.out.println("Container " + containerID + " not found.");
                }
            }
        } else {
            System.out.println("Vehicle with ID " + selectedVehicleID + " not found.");
        }

        scanner.close();
    }

    @Override
    public void unloadContainerFromVehicle() {
        Scanner scanner = new Scanner(System.in);

        // Display available vehicles (ships and trucks)
        System.out.println("Available Vehicles (Ships and Trucks):");
        for (Port port : this.ports) {
            for (Vehicle vehicle : port.getVehicles()) {
                System.out.println("Vehicle ID: " + vehicle.getVehicleID() + " - Name: " + vehicle.getName());
            }
        }

        // Ask the admin to select a vehicle to unload containers from
        System.out.println("Enter the Vehicle ID to unload containers from:");
        String selectedVehicleID = scanner.next();

        // Find the selected vehicle
        Vehicle selectedVehicle = null;
        for (Port port : this.ports) {
            for (Vehicle vehicle : port.getVehicles()) {
                if (vehicle.getVehicleID().equals(selectedVehicleID)) {
                    selectedVehicle = vehicle;
                    break;
                }
            }
            if (selectedVehicle != null) {
                break; // Vehicle found, exit the loop
            }
        }

        if (selectedVehicle != null) {
            // Check if the vehicle has any containers to unload
            if (selectedVehicle.getTotalContainers() > 0) {
                // Display containers loaded on the selected vehicle
                System.out.println("Containers loaded on Vehicle " + selectedVehicleID + ":");
                for (Container type : selectedVehicle.getContainerCounts().keySet()) {
                    int count = selectedVehicle.getContainerCounts().get(type);
                    System.out.println(type + " Containers: " + count);
                }

                // Ask the admin to select a container type to unload
                System.out.println("Enter the Container Type to unload (e.g., DRY_STORAGE):");
                String selectedContainerTypeStr = scanner.next();
                Container.ContainerType selectedContainerType = Container.ContainerType
                        .valueOf(selectedContainerTypeStr);

                // Check if the selected container type is loaded on the vehicle
                if (selectedVehicle.getContainerCount(selectedContainerType) > 0) {
                    // Decrement the count of the specified container type
                    selectedVehicle.updateContainerCount(selectedContainerType,
                            selectedVehicle.getContainerCount(selectedContainerType) - 1);
                    System.out.println("Container of type " + selectedContainerType + " unloaded from vehicle "
                            + selectedVehicle.getVehicleID());
                } else {
                    System.err.println("Error: No " + selectedContainerType + " container to unload from vehicle "
                            + selectedVehicle.getVehicleID());
                }
            } else {
                System.out.println("Vehicle " + selectedVehicleID + " does not have any containers loaded.");
            }
        } else {
            System.out.println("Vehicle with ID " + selectedVehicleID + " not found.");
        }

        scanner.close();
    }

    // Add a new manager to the system
    @Override
    public void addManager() {
        Scanner scanner = new Scanner(System.in);

        // Collect information about the new manager
        System.out.println("Enter the manager ID:");
        String managerID = scanner.nextLine();

        // Check if a user with the same ID exists
        for (Manager existingManager : managers) {
            if (existingManager.getUserID().equals(managerID)) {
                System.out.println("A manager with the same ID already exists!");
                return;
            }
        }

        System.out.println("Enter the manager username:");
        String username = scanner.nextLine();

        System.out.println("Enter the manager password:");
        String password = scanner.nextLine();

        // Display available ports for the admin to choose from
        System.out.println("Available Ports:");
        for (Port port : this.ports) {
            System.out.println("Port ID: " + port.getPortID() + " - Name: " + port.getName());
        }

        System.out.println("Enter the port ID to assign to the manager:");
        String portID = scanner.next();

        // Find the port with the specified ID
        Port managedPort = null;
        for (Port port : this.ports) {
            if (port.getPortID().equals(portID)) {
                managedPort = port;
                break;
            }
        }

        if (managedPort != null) {
            // Create the new manager and associate them with the selected port
            Manager newManager = new Manager(managerID, username, password, managedPort);
            managers.add(newManager);
            System.out.println("Manager with ID " + managerID + " has been added and assigned to port "
                    + managedPort.getName() + " successfully.");
        } else {
            System.out.println("Port with ID " + portID + " not found. Manager assignment failed.");
        }

        scanner.close();
    }

    @Override
    public void editManagerDetails() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Collect the manager's user ID to edit
            System.out.println("Enter the manager's user ID to edit:");
            String managerUserID = scanner.nextLine();

            // Find the manager with the specified user ID
            Manager managerToEdit = null;
            for (Manager manager : managers) {
                if (manager.getUserID().equals(managerUserID)) {
                    managerToEdit = manager;
                    break;
                }
            }

            // Check if the manager with the specified user ID was found
            if (managerToEdit != null) {
                System.out.println("Manager found:");
                System.out.println("User ID: " + managerToEdit.getUserID());
                System.out.println("Username: " + managerToEdit.getUsername());

                // Ask for confirmation
                while (true) {
                    System.out.println("Do you want to edit this manager? (yes/no): ");
                    String confirmation = scanner.nextLine().toLowerCase();

                    if (confirmation.equals("yes")) {
                        System.out.println("Edit Manager Details:");
                        System.out.println("1. Edit Username");
                        System.out.println("2. Edit Password");
                        System.out.println("3. Save and Exit");

                        System.out.println("Enter your choice: ");
                        int choice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        switch (choice) {
                            case 1 -> {
                                System.out.println("Enter the new username: ");
                                String newUsername = scanner.nextLine();
                                managerToEdit.setUsername(newUsername);
                                System.out.println("Username updated.");
                            }
                            case 2 -> {
                                System.out.println("Enter the new password: ");
                                String newPassword = scanner.nextLine();
                                managerToEdit.setPassword(newPassword);
                                System.out.println("Password updated.");
                            }
                            case 3 -> {
                                System.out.println("Manager details updated and saved.");
                                return;
                            }
                            default -> System.out.println("Invalid choice. Please enter a valid option.");
                        }
                    } else if (confirmation.equals("no")) {
                        System.out.println("Editing canceled. The manager was not edited.");
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                    }
                }
            } else {
                System.out.println("Manager with user ID " + managerUserID + " not found. Editing failed.");
                System.out.println("Do you want to try editing another manager? (yes/no): ");
                String tryAgain = scanner.nextLine().toLowerCase();

                if (!tryAgain.equals("yes")) {
                    return; // Exit the function if the admin doesn't want to try again
                }
            }
        }
    }

    @Override
    public void deleteManager() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Collect the manager's user ID to delete
            System.out.println("Enter the manager's user ID to delete:");
            String managerUserID = scanner.nextLine();

            // Find the manager with the specified user ID
            Manager managerToDelete = null;
            for (Manager manager : managers) {
                if (manager.getUserID().equals(managerUserID)) {
                    managerToDelete = manager;
                    break;
                }
            }

            // Check if the manager with the specified user ID was found
            if (managerToDelete != null) {
                System.out.println("Manager found:");
                System.out.println("User ID: " + managerToDelete.getUserID());
                System.out.println("Username: " + managerToDelete.getUsername());

                // Ask for confirmation
                while (true) {
                    System.out.println("Are you sure you want to delete this manager? (yes/no): ");
                    String confirmation = scanner.nextLine().toLowerCase();

                    if (confirmation.equals("yes")) {
                        // Delete the manager from the list
                        managers.remove(managerToDelete);
                        System.out.println("Manager deleted successfully.");
                        return; // Exit the function after deletion
                    } else if (confirmation.equals("no")) {
                        System.out.println("Deletion canceled. The manager was not deleted.");
                        break; // Exit the confirmation loop
                    } else {
                        System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                    }
                }
            } else {
                System.out.println("Manager with user ID " + managerUserID + " not found. Deletion failed.");
                System.out.println("Do you want to try deleting another manager? (yes/no): ");
                String tryAgain = scanner.nextLine().toLowerCase();

                if (!tryAgain.equals("yes")) {
                    return; // Exit the function if the admin doesn't want to try again
                }
            }
        }
    }

    @Override
    public void generateTrafficReport() {

    }

    @Override
    public void generateContainerStatisticsReport() {

    }

}