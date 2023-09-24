package View;
import Controller.ManagePorts;
import Model.Port;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminView {
    ManagePorts managePorts = ManagePorts.getInstance();

    public void menu() {
        // Admin Menu
        Scanner scanner = new Scanner(System.in);

        String input;
        do {
            System.out.println("__________________________WELCOME ADMIN__________________________");
            System.out.println("[0] LOG OUT\n[1] Manage Ports\n[2] Manage Vehicles\n[3] Manage Containers\n[4] Manage Managers\n");
            System.out.println("ENTER THE NUMBER TO CHOOSE THE OPTION:");
            input = scanner.nextLine().trim();
            switch (input) {
                case "0":
                    break;
                case "1":
                    this.ports();
                    break;
                case "2":
                    VehiclesView vehiclesView = new VehiclesView();
                    vehiclesView.vehicles();
                case "3":
                    ContainersView containersView = new ContainersView();
                    containersView.containers();
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
            System.out.println("There are currently " + managePorts.getPortsList().size() + " ports");
            System.out.println("[0] GO BACK\n[1] List All Ports\n[2] Find Port by ID\n[3] Add Port\n[4] Remove Port\n");
            System.out.println("ENTER THE NUMBER TO CHOOSE THE OPTION:");
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
                    System.out.println("Invalid input\n");
            }
        } while(!input.equals("0"));

    }

    public void portsList() {
        // Admin Menu - Ports - All Ports
        System.out.println("__________________________ADMIN - PORTS - All Ports__________________________");
        System.out.println("There are currently " + managePorts.getPortsList().size() + " ports");
        ArrayList<Port> portsList = managePorts.getPortsList();
        for (Port port : portsList) {
            System.out.println("Port ID - Name - Latitude - Longitude - Storing Capacity - Landing Ability ");
            System.out.println(port.getPortID() + " - " + port.getName() + " - " + port.getLatitude() + " - " +
                    port.getLongitude() + " - " + port.getStoringCapacity() + " - " + port.hasLandingAbility());

        }
        System.out.println("--------------------\n");
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

            if (managePorts.validatePortId(input)) {
                Port port = managePorts.getPortByID(input);
                if (port != null) {
                    System.out.println("Port ID - Name - Latitude - Longitude - Storing Capacity - Landing Ability ");
                    System.out.println(port.getPortID() + port.getName() + port.getLatitude() + port.getLongitude() + port.getStoringCapacity() + port.hasLandingAbility());
                } else {
                    System.out.println("No port was found.\n");
                }
            } else {
                System.out.println("Invalid input!\n");
            }
        } while (!input.equals("!")) ;
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
            System.out.println("Enter Port's name, latitude, longitude, storing capacity, landing ability: ");
            input = scanner.nextLine().trim();

            try {
                String[] values = input.split(",");
                String name = values[0];
                double latitude = Double.parseDouble(values[1]);
                double longitude = Double.parseDouble(values[2]);
                double storingCapacity = Double.parseDouble(values[3]);
                boolean landingAbility = Boolean.parseBoolean(values[4]);

                if (managePorts.addPorts(name, latitude, longitude, storingCapacity, landingAbility)) {
                    System.out.println("Port added successfully!\n");
                } else {
                    System.out.println("Failed to add port\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number for the latitude, longitude, storing capacity, and landing ability fields.\n");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Not enough values entered!\n");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred. Please try again.\n");
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

           if (managePorts.validatePortId(input)) {
                Port port = managePorts.getPortByID(input);
                if (managePorts.removePort(input)) {
                    System.out.println("Removed port successfully");
                } else {
                    System.out.println("No port was found.\n");
                }
            } else {
                System.out.println("Invalid input!\n");
            }
        } while(!input.equals("!"));
        // Go back
        this.ports();
    }

    public static void main(String[] args) {
        AdminView av = new AdminView();
        av.menu();
    }
}
