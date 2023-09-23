package View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AdminView {

    public void menu() {
        // Admin Page
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            System.out.println("__________________________WELCOME ADMIN" +
                    "__________________________");
            System.out.println("ENTER THE NUMBER TO CHOOSE THE OPTION:");
            System.out.println("""
                [0] LOG OUT
                [1] Manage Ports
                [2] Manage Vehicles
                [3] Manage Containers
                [4] Manage Managers
                """);
            input = scanner.nextLine().trim();
            switch (input) {
                case "1":
                    ports();
                    break;
                case "2":
                    // vehicles()
                    break;
                case "3":
                    // containers()
                    break;
                case "4":
                    // managers()
                    break;
                default:
                    System.out.println("Invalid input");
            }
        } while (!input.equals("0"));
    }

    public void ports() {
        // Admin Page -> Ports
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            System.out.println("__________________________ADMIN - PORTS" +
                    "__________________________");
            System.out.println("There are currently [number] ports");
            System.out.println("ENTER THE NUMBER TO CHOOSE THE OPTION:");
            System.out.println("""
                [0] GO BACK
                [1] List All Ports
                [2] Find Port by ID
                [3] Add Port
                [4] Remove Port
                """);

            input = scanner.nextLine().trim();
            switch (input) {
                case "1":
                    ports();
                    break;
                case "2":
                    portsList();
                    break;
                case "3":
                    addPort();
                    break;
                case "4":
                    // managers()
                    break;
                default:
                    System.out.println("Invalid input");
            }
        } while (!input.equals("0"));
    }

    public void portsList() {
        // Admin Page -> Ports -> List All ports
        System.out.println("__________________________All Ports" +
                "__________________________");
        System.out.println("There are currently [number] ports \n");
        // getPortsList()
        ports();
        // Auto go back
    }

    public void portsByID() {
        // Admin Page -> Ports -> Find Port by ID
        Scanner scanner = new Scanner(System.in);
        System.out.println("__________________________Find Port by ID" +
                "__________________________");
        String input;
        do {
            System.out.println("(Enter ! to cancel)");
            System.out.println("Enter Port ID: ");
            input = scanner.nextLine().trim();
            // getPortByID(input)
        } while (!input.equals("!"));
        // Auto go back
    }

    public void addPort() {
        // Admin Page -> Ports -> Add Port
        Scanner scanner = new Scanner(System.in);
        System.out.println("__________________________Add Port" +
                "__________________________");
        String input;
        scanner.useDelimiter(",");
        do {
            System.out.println("(Enter ! to cancel)");
            System.out.println("Seperate values by \" , \" ");
            System.out.println("Enter Port's ID, name, latitude, longitude, storing capacity, landing ability: ");
            input = scanner.nextLine().trim();

            // Pick value from input
            List<String> values = Arrays.asList(input.split(","));
            String ID = values.get(0);
            String name = values.get(1);
            double latitude = Double.valueOf(values.get(2));
            double longitude = Double.valueOf(values.get(3));
            double storingCapacity = Double.valueOf(values.get(4));
            boolean landingAbility = Boolean.valueOf(values.get(5));

            // Port.addPort(ID, name, latitude, longitude, storingCapacity, landingAbility)


        } while (!input.equals("!"));
    }

    public static void main(String[] args) {
        AdminView av = new AdminView();
        av.menu();

        // Admin Page -> Ports -> Remove Port
    }
}
