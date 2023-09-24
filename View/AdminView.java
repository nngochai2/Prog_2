package View;
import java.util.Scanner;

public class AdminView {

    public void menu() {
        // Admin Menu
        Scanner scanner = new Scanner(System.in);

        String input;
        do {
            System.out.println("__________________________WELCOME ADMIN__________________________");
            System.out.println("ENTER THE NUMBER TO CHOOSE THE OPTION:");
            System.out.println("[0] LOG OUT\n[1] Manage Ports\n[2] Manage Vehicles\n[3] Manage Containers\n[4] Manage Managers\n");
            input = scanner.nextLine().trim();
            switch (input) {
                case "0":
                    // main()
                    break;
                case "1":
                    this.ports();
                    break;
                case "2":
                    VehiclesView vehiclesView = new VehiclesView();
                    vehiclesView.vehicles();
                case "3":
//                    ContainersView containersView = new ContainersView();
//                    containersView.containers();
                case "4":
                    break;
                default:
                    System.out.println("Invalid input");
            }
        } while(!input.equals("0"));
    }

    public void ports() {
        // Admin Menu - Ports
        Scanner scanner = new Scanner(System.in);

        String input;
        do {
            System.out.println("__________________________ADMIN - PORTS__________________________");
            System.out.println("There are currently [number] ports");
            System.out.println("ENTER THE NUMBER TO CHOOSE THE OPTION:");
            System.out.println("[0] GO BACK\n[1] List All Ports\n[2] Find Port by ID\n[3] Add Port\n[4] Remove Port\n");
            input = scanner.nextLine().trim();
            switch (input) {
                case "0":
                    this.menu();
                case "1":
                    this.portsList();
                    break;
                case "2":
                    this.portByID();
                    break;
                case "3":
                    this.addPort();
                    break;
                case "4":
                    this.removePort();
                    break;
                default:
                    System.out.println("Invalid input");
            }
        } while(!input.equals("0"));

    }

    public void portsList() {
        // Admin Menu - Ports - All Ports
        System.out.println("__________________________ADMIN - PORTS - All Ports__________________________");
        System.out.println("There are currently [number] ports \n");
        // Go back
        this.ports();
    }

    public void portByID() {
        // Admin Menu - Ports - Find Port by ID
        Scanner scanner = new Scanner(System.in);
        System.out.println("__________________________ADMIN - PORTS - Find Port by ID__________________________");

        String input;
        do {
            System.out.println("(Enter ! to cancel)");
            System.out.println("Enter Port ID: ");
            input = scanner.nextLine().trim();


            // try {
            // Validate input

            // getPortByID()
            // }

        } while(!input.equals("!"));
        // Go back
        this.ports();
    }

    public void addPort() {
        // Admin Menu - Ports - Add Port
        Scanner scanner = new Scanner(System.in);
        System.out.println("__________________________ADMIN - PORTS - Add Port__________________________");

        String input;
        do {
            System.out.println("(Enter ! to cancel)");
            System.out.println("Seperate values by \" , \" ");
            System.out.println("Enter Port's ID, name, latitude, longitude, storing capacity, landing ability: ");
            input = scanner.nextLine().trim();

            try {
                String[] values = input.split(",");
                String ID = values[0];
                String name = values[1];
                double latitude = Double.parseDouble(values[2]);
                double longitude = Double.parseDouble(values[3]);
                double storingCapacity = Double.parseDouble(values[4]);
                boolean landingAbility = Boolean.parseBoolean(values[5]);

                // Add port
                System.out.println("Port added successfully!");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number for the latitude, longitude, storing capacity, and landing ability fields.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Not enough values entered!");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred. Please try again.");
            }
        } while (!input.equals("!"));

        // Go back
        this.ports();
    }

    public void removePort() {
        // Admin Menu - Ports - Remove Port
        Scanner scanner = new Scanner(System.in);
        System.out.println("__________________________ADMIN - PORTS - Remove Port__________________________");

        String input;
        do {
            System.out.println("(Enter ! to cancel)");
            System.out.println("Enter Port ID: ");
            input = scanner.nextLine().trim();

//            try {
//                // validate
//                // deletePort(input)
//            } catch (IllegalArgumentException e) {
//                System.out.println(e.getMessage());
//            }
        } while(!input.equals("!"));
        // Go back
        this.ports();
    }

    public static void main(String[] args) {
        AdminView av = new AdminView();
        av.menu();
    }
}
