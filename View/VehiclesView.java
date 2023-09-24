package View;
import java.util.Scanner;

public class VehiclesView {
    // shared by admins and managers
    public void vehicles(){
        // Menu - Vehicles
        Scanner scanner = new Scanner(System.in);
        System.out.println("__________________________Vehicles__________________________");
        System.out.println("There are currently [number] vehicles");

        String input;
        do {
            System.out.println("""
                   [0] GO BACK
                   [1] List All Vehicles
                   [2] Find Vehicle by ID
                   [3] Add Vehicle
                   [4] Remove Vehicle
                    """);
            System.out.println("ENTER THE NUMBER TO CHOOSE THE OPTION:");
            input = scanner.nextLine().trim();
            switch (input) {
                case "0":
//                    menu();
                case "1":
                    this.vehiclesList();
                    break;
                case "2":
                    this.vehicleByID();
                    break;
                case "3":
                    this.addVehicle();
                    break;
                case "4":
                    this.removeVehicle();
                    break;
                default:
                    System.out.println("Invalid input");
            }
        } while(!input.equals("0"));
        // Go back
    }

    public void vehiclesList() {
        // Menu - Vehicles - All Vehicles
        System.out.println("__________________________MENU - VEHICLES - All Vehicles__________________________");
        System.out.println("There are currently [number] vehicles \n");
        // Go back
        this.vehicles();
    }

    public void vehicleByID() {
        // Menu - Vehicles - All Vehicles
        Scanner scanner = new Scanner(System.in);
        System.out.println("__________________________MENU - VEHICLES - Find Vehicle by ID__________________________");

        String input;
        do {
            System.out.println("(Enter ! to cancel)");
            System.out.println("Enter Vehicle ID: ");
            input = scanner.nextLine().trim();
        } while(!input.equals("!"));
        // Go back
        this.vehicles();
    }

    public void addVehicle() {
        // Menu - Vehicles - Add Vehicles
        Scanner scanner = new Scanner(System.in);
        System.out.println("__________________________MENU - VEHICLES - Add Vehicles__________________________");

        String input;
        do {
            System.out.println("There are currently [number] vehicles");
            System.out.println("""
                   [0] GO BACK
                   [1] Add Tanker Truck
                   [2] Add Reefer Truck
                   [3] Add Ship
                    """);
            System.out.println("ENTER THE NUMBER TO CHOOSE THE OPTION:");
            input = scanner.nextLine().trim();
            switch (input) {
                case "0":
                    this.vehicles();
                case "1":
                    this.addTankerTruck();
                    break;
                case "2":
                    this.addReeferTruck();
                    break;
                case "3":
                    this.addShip();
                    break;
                default:
                    System.out.println("Invalid input");
            }
        } while(!input.equals("0"));
        // Go back
        this.vehicles();
    }

    public void addTankerTruck() {
        // Menu - Vehicles - Add Vehicles - Add Tanker Truck
        Scanner scanner = new Scanner(System.in);
        System.out.println("__________________________MENU - VEHICLES - Add Vehicles - Add Tanker Truck__________________________");

        String input;
        do {
            System.out.println("There are currently [number] tanker trucks");
            System.out.println("(Enter ! to cancel)");
            System.out.println("Separate values by \" , \" ");
            System.out.println("Enter Vehicle's ID, name, carrying capacity, fuel capacity, current port: ");
            input = scanner.nextLine().trim();

            if (input.equals("!")) {
                break;
            }

            try {
                String[] values = input.split(",");
                String ID = values[0];
                String name = values[1];
                double carryingCapacity = Double.parseDouble(values[2]);
                double fuelCapacity = Double.parseDouble(values[3]);
                String currentPort = values[4];

                // Add tanker truck
                System.out.println("The following tanker trucks have been added:");

                System.out.println("Tanker truck added successfully!");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Not enough values entered!");
            }
        } while (true);
        // Go back
        this.addVehicle();
    }

    public void addReeferTruck() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("__________________________MENU - VEHICLES - Add Vehicles - Add Reefer Truck__________________________");

        String input;
        do {
            System.out.println("There are currently [number] reefer trucks");
            System.out.println("(Enter ! to cancel)");
            System.out.println("Separate values by \" , \" ");
            System.out.println("Enter Vehicle's ID, name, carrying capacity, fuel capacity, current port: ");
            input = scanner.nextLine().trim();

            if (input.equals("!")) {
                break;
            }

            try {
                String[] values = input.split(",");
                String ID = values[0];
                String name = values[1];
                double carryingCapacity = Double.parseDouble(values[2]);
                double fuelCapacity = Double.parseDouble(values[3]);
                String currentPort = values[4];

                // Add reefer truck
                System.out.println("Reefer truck added successfully!");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Not enough values entered!");
            }
        } while (true);
        // Go back
        this.addVehicle();
    }

    public void addShip() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("__________________________MENU - VEHICLES - Add Vehicles - Add Ship__________________________");

        String input;
        do {
            System.out.println("There are currently [number] ships");
            System.out.println("(Enter ! to cancel)");
            System.out.println("Separate values by \" , \" ");
            System.out.println("Enter Vehicle's ID, name, carrying capacity, fuel capacity, current port: ");
            input = scanner.nextLine().trim();

            if (input.equals("!")) {
                break;
            }

            try {
                String[] values = input.split(",");
                String ID = values[0];
                String name = values[1];
                double carryingCapacity = Double.parseDouble(values[2]);
                double fuelCapacity = Double.parseDouble(values[3]);
                String currentPort = values[4];

                // Add ship
                System.out.println("Ship added successfully!");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Not enough values entered!");
            }
        } while (true);
        // Go back
        this.addVehicle();
    }

    public void removeVehicle() {
        // Menu - Vehicles - Remove Vehicles
        Scanner scanner = new Scanner(System.in);
        System.out.println("__________________________MENU - VEHICLES - Remove Vehicles__________________________");

        String input;
        do {
            System.out.println("(Enter ! to cancel)");
            System.out.println("Enter Port ID: ");
            input = scanner.nextLine().trim();

//            try {
//                // validate
//                // deleteVehicle(input)
//            } catch (IllegalArgumentException e) {
//                System.out.println(e.getMessage());
//            }
        } while(!input.equals("!"));
        // Go back
        this.vehicles();
    }

    public static void main(String[] args) {
        VehiclesView vv = new VehiclesView();
        vv.addTankerTruck();
    }
}
