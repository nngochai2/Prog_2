package Model;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.*;

import Model.Container.ContainerType;
import Model.User.UserRole;

public class Manager extends User implements IManager {
    private Port managedPort;

    public Manager(String userID, String username, String password, Port managedPort) {
        super(userID, username, password, UserRole.MANAGER);
        this.managedPort = managedPort;
    }

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

    public Manager login(String username, String password) {
        return (Manager) super.login(username, password);
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

    // @Override
    // public void listTripsFromDateToDate(Date startDate, Date endDate) {
    // List<Trip> trips = this.managedPort.getCurrentTrips();
    // // This method is responsible for listing trips from one date to another
    // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    // for (Trip trip : trips) {
    // if ((startDate.equals(trip.getDepartureDate()) ||
    // startDate.after(trip.getDepartureDate()))
    // && (endDate.before(trip.getArrivalDate())) ||
    // endDate.equals(trip.getArrivalDate())) {
    // System.out.println("Trip ID: " + trip.getId());
    // System.out.println("Departure Date: " + sdf.format(trip.getDepartureDate()));
    // System.out.println("Arrival Date: " + sdf.format(trip.getArrivalDate()));
    // System.out.println("Vehicle ID: " + trip.getVehicle().getVehicleID());
    // System.out.println("Departure Port: " + trip.getDeparturePort());
    // System.out.println("Arrival Port: " + trip.getArrivalPort());
    // System.out.println("Status: " + trip.getStatus());
    // System.out.println("-----------------------------");
    // }
    // }
    // }


    public void listTripsOnDate() {

    }

    public void listTripsFromDateToDate() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("Enter the start date (yyyy-MM-dd): ");
        Date startDate = parseDate(scanner.nextLine());

        System.out.println("Enter the end date (yyyy-MM-dd): ");
        Date endDate = parseDate(scanner.nextLine());

        List<Trip> trips = managedPort.getCurrentTrips();


        System.out.println("Trips in " + managedPort.getName() + " from " + sdf.format(startDate) + " to " + sdf.format(endDate) + ":");

        for (Trip trip : trips) {
            Date tripStartDate = trip.getDepartureDate();
            Date tripEndDate = trip.getArrivalDate();

            // Check if trip falls within the specified date range
            if ((tripStartDate.equals(startDate) || tripStartDate.after(startDate))
                    && (tripEndDate.equals(endDate) || tripEndDate.before(endDate))) {
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
    }

    // Check date format
    private Date parseDate(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return null;
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
        System.out.print("Please select vehicle ID: ");
        int choice = scanner.nextInt();
        Vehicle selectedVehicle = vehicles.get(choice);
        System.out.println();
    }

    @Override
    public void viewTripDetails() {

    }

    @Override
    public void addContainer() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Container> containers = new ArrayList<>();
        // Check if the manager has an assigned port
        if (managedPort == null) {
            System.out.println("Manager has no assigned port. Cannot add containers.");
            return;
        }

        while (true) {
            System.out.println("Enter the details of the container:");

            // Collect container information from the Manager
            System.out.print("Container ID: ");
            String containerID = scanner.nextLine();

            System.out.print("Container Type (e.g., DRY_STORAGE, OPEN_TOP, OPEN_SIDE, REFRIGERATED, LIQUID): ");
            String containerTypeStr = scanner.nextLine();
            try {
                Container.ContainerType containerType = Container.ContainerType.valueOf(containerTypeStr);

                System.out.print("Container Weight (in kilograms): ");
                double containerWeight = scanner.nextDouble();
                scanner.nextLine(); // Consume the newline character

                // Create the new container

                Container newContainer = new Container(containerID, containerType, containerWeight, managedPort.getPortID());


                // Display container information for confirmation
                System.out.println("Container Details:");
                System.out.println("Container ID: " + newContainer.getContainerID());
                System.out.println("Container Type: " + newContainer.getType());
                System.out.println("Container Weight: " + newContainer.getWeight());
                System.out.println("Destination Port: " + managedPort);

                // Ask for confirmation
                System.out.print("Confirm adding this container? (yes/no): ");
                String confirmation = scanner.nextLine().toLowerCase();

                if (confirmation.equals("yes")) {
                    // Add the container to the port
                    newContainer = new Container(containerID, containerType, containerWeight, managedPort.getPortID());

                    containers.add(newContainer);     // Add the new container to your ArrayList or data structure.

                    System.out.println("Container " + containerID + " has been added successfully.");
                    break; // Exit the loop
                } else if (confirmation.equals("no")) {
                    System.out.println("Container not added.");
                    break; // Exit the loop
                } else {
                    System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid container type.");
            }
        }
    }

    @Override
    public void editContainerDetails() {
        Scanner scanner = new Scanner(System.in);

        // Check if the manager has an assigned port
        if (managedPort == null) {
            System.out.println("Manager has no assigned port. Cannot edit containers.");
            return;
        }

        while (true) {
            System.out.println("Enter the ID of the container to edit:");
            String containerID = scanner.nextLine();

            // Find the container with the specified ID in the manager's assigned port
            Container containerToEdit = null;
            for (Container container : managedPort.getContainers()) {
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
                        System.out.println("Edit Container Details:");
                        System.out.println("1. Edit Container Type");
                        System.out.println("2. Edit Container Weight");
                        System.out.println("3. Save and Exit");

                        System.out.println("Enter your choice: ");
                        int choice = scanner.nextInt();
                        scanner.nextLine();

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
                            default -> System.out.println("Invalid choice. Please select a valid option.");
                        }
                    } else if (confirmation.equals("no")) {
                        System.out.println("Editing canceled. The container was not edited.");
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                    }
                }
            } else {
                System.out.println("Container with ID " + containerID + " not found in your assigned port.");
                System.out.println("Do you want to try editing another container? (yes/no): ");
                String tryAgain = scanner.nextLine().toLowerCase();

                if (!tryAgain.equals("yes")) {
                    return; // Exit the function if the manager doesn't want to try again
                }
            }
        }
    }


    @Override
    public void deleteContainer() {
        Scanner scanner = new Scanner(System.in);

        // Check if the manager has an assigned port
        if (managedPort == null) {
            System.out.println("Manager has no assigned port. Cannot delete containers.");
            return;
        }

        while (true) {
            System.out.println("Enter the ID of the container to delete:");
            String containerID = scanner.nextLine();

            // Find the container with the specified ID in the manager's assigned port
            Container containerToDelete = null;
            for (Container container : managedPort.getContainers()) {
                if (container.getContainerID().equals(containerID)) {
                    containerToDelete = container;
                    break;
                }
            }

            // Check if the container with the specified ID was found
            if (containerToDelete != null) {
                System.out.println("Container found:");
                System.out.println("Container ID: " + containerToDelete.getContainerID());
                System.out.println("Container Type: " + containerToDelete.getType());
                System.out.println("Container Weight: " + containerToDelete.getWeight());

                // Ask for confirmation
                while (true) {
                    System.out.println("Are you sure you want to delete this container? (yes/no): ");
                    String confirmation = scanner.nextLine().toLowerCase();

                    if (confirmation.equals("yes")) {
                        // Remove the container from the port
                        managedPort.removeContainer(containerToDelete);
                        System.out.println("Container " + containerID + " has been deleted.");
                        return;
                    } else if (confirmation.equals("no")) {
                        System.out.println("Deletion canceled. The container was not deleted.");
                        return;
                    } else {
                        System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                    }
                }
            } else {
                System.out.println("Container with ID " + containerID + " not found in your assigned port.");
                System.out.println("Do you want to try deleting another container? (yes/no): ");
                String tryAgain = scanner.nextLine().toLowerCase();

                if (!tryAgain.equals("yes")) {
                    return; // Exit the function if the manager doesn't want to try again
                }
            }
        }
    }


    @Override
    public void loadContainerOntoVehicle() {
        Scanner scanner = new Scanner(System.in);

        // Display available vehicles (ships and trucks) in the managed port
        if (managedPort == null) {
            System.out.println("Manager has no assigned port. Cannot load containers.");
            return;
        }

        System.out.println("Available Vehicles (Ships and Trucks) in " + managedPort.getName() + ":");
        for (Vehicle vehicle : managedPort.getVehicles()) {
            System.out.println("Vehicle ID: " + vehicle.getVehicleID() + " - Name: " + vehicle.getName());
        }

        // Ask the manager to select a vehicle to load containers onto
        System.out.println("Enter the Vehicle ID to load container(s) onto: ");
        String selectedVehicleID = scanner.next();

        // Find the vehicle with matched ID
        Vehicle selectedVehicle = null;
        for (Vehicle vehicle : managedPort.getVehicles()) {
            if (vehicle.getVehicleID().equals(selectedVehicleID)) {
                selectedVehicle = vehicle;
                break;
            }
        }

        if (selectedVehicle != null) {
            // Display available containers in the managed port for the manager to choose
            System.out.println("Available Containers in " + managedPort.getName() + ": ");
            for (Container container : managedPort.getContainers()) {
                System.out.println("Container ID: " + container.getContainerID() + " - Type: " + container.getType());
            }

            // Ask the manager to select containers to load onto the vehicle
            System.out.println("Enter the Container IDs to load onto the vehicle (comma-separated):");
            String selectedContainerIDs = scanner.next();
            String[] containerIDs = selectedContainerIDs.split(",");

            // Load the selected containers onto the vehicle
            for (String containerID : containerIDs) {
                Container containerToAdd = null;
                for (Container container : managedPort.getContainers()) {
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

                        System.out.println("Error: This vehicle cannot load " + containerToAdd.getType() + " container.");

                    }
                } else {
                    System.out.println("Container " + containerID + " not found.");
                }
            }
        } else {
            System.out.println("Vehicle with ID " + selectedVehicleID + " not found in " + managedPort.getName() + ".");
        }

        scanner.close();
    }


    @Override
    public void unloadContainerFromVehicle() {
        Scanner scanner = new Scanner(System.in);

        // Display available vehicles (ships and trucks) in the managed port
        if (managedPort == null) {
            System.out.println("Manager has no assigned port. Cannot unload containers.");
            return;
        }

        System.out.println("Available Vehicles (Ships and Trucks) in " + managedPort.getName() + ":");
        for (Vehicle vehicle : managedPort.getVehicles()) {
            System.out.println("Vehicle ID: " + vehicle.getVehicleID() + " - Name: " + vehicle.getName());
        }

        // Ask the manager to select a vehicle to unload containers from
        System.out.println("Enter the Vehicle ID to unload containers from:");
        String selectedVehicleID = scanner.next();

        // Find the selected vehicle in the managed port
        Vehicle selectedVehicle = null;
        for (Vehicle vehicle : managedPort.getVehicles()) {
            if (vehicle.getVehicleID().equals(selectedVehicleID)) {
                selectedVehicle = vehicle;
                break;
            }
        }

        if (selectedVehicle != null) {
            // Check if the vehicle has any containers to unload
            if (selectedVehicle.getTotalContainers() > 0) {
                // Display containers loaded on the selected vehicle
                System.out.println("Containers loaded on Vehicle " + selectedVehicleID + ":");
                for (Container containerType : selectedVehicle.getContainerCounts().keySet()) {
                    int count = selectedVehicle.getContainerCount(containerType.getType());
                    System.out.println(containerType + " Containers: " + count);
                }

                // Ask the manager to select a container type to unload
                System.out.println("Enter the Container Type to unload (e.g., DRY_STORAGE):");
                String selectedContainerTypeStr = scanner.next();

                Container.ContainerType selectedContainerType = Container.ContainerType.valueOf(selectedContainerTypeStr);


                // Check if the selected container type is loaded on the vehicle
                if (selectedVehicle.getContainerCount(selectedContainerType) > 0) {
                    // Decrement the count of the specified container type

                    selectedVehicle.updateContainerCount(selectedContainerType, selectedVehicle.getContainerCount(selectedContainerType) - 1);
                    System.out.println("Container of type " + selectedContainerType + " unloaded from vehicle " + selectedVehicle.getVehicleID());
                } else {
                    System.err.println("Error: No " + selectedContainerType + " container to unload from vehicle " + selectedVehicle.getVehicleID());

                }
            } else {
                System.out.println("Vehicle " + selectedVehicleID + " does not have any containers loaded.");
            }
        } else {
            System.out.println("Vehicle with ID " + selectedVehicleID + " not found in " + managedPort.getName() + ".");
        }

        scanner.close();
    }

}