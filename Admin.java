import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
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

    // =================================GETTER AND SETTER METHODS=======================================================
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
    public void login(String username, String password) {

    }

    // Calculate the total amount of fuel used in one day
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


    // =========================================TRIP FUNCTIONS==========================================================
    @Override
    public void listTripsOnDate() {

    }

    @Override
    public void listTripsFromDateToDate() {

    }


    @Override
    public void viewTripDetails() {

    }

    // =========================================PORT FUNCTIONS==========================================================

    // Calculate the total weight of containers (in one port/in all ports)
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

    // ======================================VEHICLE FUNCTIONS==========================================================


    @Override
    public void viewVehicleDetails() {

    }
    // Add a vehicle
    @Override
    public void addVehicle() {
        Scanner scanner = new Scanner(System.in);

        // Require user input
        System.out.println("Enter the vehicle ID: ");
        String vehicleID = scanner.nextLine();

        
        for (Vehicle vehicle : vehicles) {
            // Check if there is already a vehicle with the same ID
            if (vehicleID.matches("^sh\\d+$") && vehicleID.matches("^tr\\d+$")) {  // Firstly, check whether the ID is correct or not
                if (vehicle.getVehicleID().equals(vehicleID)) {
                    System.out.println("Error. There is already a vehicle with the same ID");
                    return;
                }
            }
        }

        // Require vehicle type
        System.out.println("Enter the vehicle type (SHIP, BASIC_TRUCK, REEFER_TRUCK, TANKER_TRUCK): ");
        String vehicleTypeStr = scanner.nextLine();
        Vehicle.VehicleType vehicleType = Vehicle.VehicleType.valueOf(vehicleTypeStr);

        // Enter vehicle name
        System.out.println("Enter the vehicle name: ");
        String name = scanner.nextLine();

        // Enter vehicle name
        System.out.println("Enter the current fuel: ");
        double currentFuel = Double.parseDouble(scanner.nextLine());

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
                        System.out.println("1. Edit Vehicle Type");
                        System.out.println("2. Edit Vehicle Name");
                        System.out.println("3. Edit Current Fuel");
                        System.out.println("4. Edit Carrying Capacity");
                        System.out.println("5. Edit Fuel Capacity");
                        System.out.println("6. Save and Exit");

                        System.out.println("Enter your choice: ");
                        int choice = scanner.nextInt();
                        scanner.nextLine();

                        switch (choice) {
                            case 1 -> {
                                System.out.println("Enter the new vehicle type (SHIP, BASIC_TRUCK, REEFER_TRUCK, TANKER_TRUCK): ");
                                String newVehicleTypeStr = scanner.nextLine();
                                Vehicle.VehicleType newVehicleType = Vehicle.VehicleType.valueOf(newVehicleTypeStr);

                                Vehicle newVehicle = null;

                                // Copy common attributes from the old vehicle to the new one
                                switch (newVehicleType) {
                                    case SHIP -> newVehicle = new Ship(oldVehicle.getVehicleID(), oldVehicle.getCarryingCapacity());
                                    case BASIC_TRUCK -> newVehicle = new BasicTruck(oldVehicle.getVehicleID(), oldVehicle.getCarryingCapacity());
                                    case REEFER_TRUCK -> newVehicle = new ReeferTruck(oldVehicle.getVehicleID(), oldVehicle.getCarryingCapacity());
                                    case TANKER_TRUCK -> newVehicle = new TankerTruck(oldVehicle.getVehicleID(), oldVehicle.getCarryingCapacity());
                                }

                                if (newVehicle != null) {
                                    // Replace the old vehicle with the new one in the list
                                    int index = vehicles.indexOf(oldVehicle);
                                    if (index != -1) {
                                        vehicles.set(index, newVehicle);
                                        System.out.println("Vehicle " + vehicleID + " has been updated with the new type.");
                                    } else {
                                        System.out.println("Failed to update vehicle type. Vehicle not found.");
                                    }
                                } else {
                                    System.out.println("Invalid vehicle type.");
                                }
                            }

                            case 2 -> {
                                System.out.println("Enter the new vehicle name: ");
                                String newName = scanner.nextLine();
                                vehicleToEdit.setName(newName);
                                System.out.println("Vehicle name updated.");
                            }
                            case 3 -> {
                                System.out.println("Enter the new current fuel: ");
                                int newCurrentFuel = scanner.nextInt();
                                vehicleToEdit.setCurrentFuel(newCurrentFuel);
                                System.out.println("Current fuel updated.");
                            }
                            case 4 -> {
                                System.out.println("Enter the new carrying capacity: ");
                                int newCarryingCapacity = scanner.nextInt();
                                vehicleToEdit.setCarryingCapacity(newCarryingCapacity);
                                System.out.println("Carrying capacity updated.");
                            }
                            case 5 -> {
                                System.out.println("Enter the new fuel capacity: ");
                                int newFuelCapacity = scanner.nextInt();
                                vehicleToEdit.setFuelCapacity(newFuelCapacity);
                                System.out.println("Fuel capacity updated.");
                            }
                            case 6 -> {
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


    // ====================================CONTAINER FUNCTIONS==========================================================
    // Add a container to the system
    @Override
    public void addContainer() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the container ID: ");
        String containerID = scanner.nextLine();

        System.out.println("Enter the container type (e.g., DRY_STORAGE, OPEN_TOP, OPEN_SIDE, REFRIGERATED, LIQUID): ");
        String containerTypeStr = scanner.nextLine();
        Container.ContainerType containerType = Container.ContainerType.valueOf(containerTypeStr);

        System.out.println("Enter the weight of the container: ");
        double weight = scanner.nextDouble();

        // Require the container location (a port ID is required since container can not be set directly on a vehicle
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
            System.out.println("Invalid port ID. Container location cannot be set"); // Output if the location is not valid
        }

        // Create a new Container object based on the collected information (necessary for further modification of the created container)

        if (validPort) {
            Container newContainer = new Container(containerID, containerType, weight, portID);
            containers.add(newContainer);     // Add the new container to your ArrayList or data structure.
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
                                System.out.println("Enter the new container type (e.g., DRY_STORAGE, OPEN_TOP, OPEN_SIDE, REFRIGERATED, LIQUID): ");
                                String newContainerTypeStr = scanner.nextLine();
                                try {
                                    Container.ContainerType newContainerType = Container.ContainerType.valueOf(newContainerTypeStr);
                                    containerToEdit.setContainerType(newContainerType);
                                    System.out.println("Container " + containerID + " has been updated with the new type.");
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
        Scanner scanner =  new Scanner(System.in);

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

    }

    @Override
    public void unloadContainerFromVehicle() {

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
            System.out.println("Manager with ID " + managerID + " has been added and assigned to port " + managedPort.getName() + " successfully.");
        } else {
            System.out.println("Port with ID " + portID + " not found. Manager assignment failed.");
        }

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

}
