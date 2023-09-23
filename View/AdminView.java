package View;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AdminView {
    public AdminView() {
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);

        String input;
        do {
            System.out.println("__________________________WELCOME ADMIN__________________________");
            System.out.println("ENTER THE NUMBER TO CHOOSE THE OPTION:");
            System.out.println("[0] LOG OUT\n[1] Manage Ports\n[2] Manage Vehicles\n[3] Manage Containers\n[4] Manage Managers\n");
            input = scanner.nextLine().trim();
            switch (input) {
                case "1":
                    this.ports();
                case "2":
                case "3":
                case "4":
                    break;
                default:
                    System.out.println("Invalid input");
            }
        } while(!input.equals("0"));

    }

    public void ports() {
        Scanner scanner = new Scanner(System.in);

        String input;
        do {
            System.out.println("__________________________ADMIN - PORTS__________________________");
            System.out.println("There are currently [number] ports");
            System.out.println("ENTER THE NUMBER TO CHOOSE THE OPTION:");
            System.out.println("[0] GO BACK\n[1] List All Ports\n[2] Find Port by ID\n[3] Add Port\n[4] Remove Port\n");
            input = scanner.nextLine().trim();
            switch (input) {
                case "1":
                    this.ports();
                    break;
                case "2":
                    this.portsList();
                    break;
                case "3":
                    this.addPort();
                case "4":
                    break;
                default:
                    System.out.println("Invalid input");
            }
        } while(!input.equals("0"));

    }

    public void portsList() {
        System.out.println("__________________________All Ports__________________________");
        System.out.println("There are currently [number] ports \n");
        this.ports();
    }

    public void portsByID() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("__________________________Find Port by ID__________________________");

        String input;
        do {
            System.out.println("(Enter ! to cancel)");
            System.out.println("Enter Port ID: ");
            input = scanner.nextLine().trim();
        } while(!input.equals("!"));

    }

    public void addPort() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("__________________________Add Port__________________________");
        scanner.useDelimiter(",");

        String input;
        do {
            System.out.println("(Enter ! to cancel)");
            System.out.println("Seperate values by \" , \" ");
            System.out.println("Enter Port's ID, name, latitude, longitude, storing capacity, landing ability: ");
            input = scanner.nextLine().trim();
            List<String> values = Arrays.asList(input.split(","));
            String ID = (String)values.get(0);
            String name = (String)values.get(1);
            double latitude = Double.valueOf((String)values.get(2));
            double longitude = Double.valueOf((String)values.get(3));
            double storingCapacity = Double.valueOf((String)values.get(4));
            boolean var12 = Boolean.valueOf((String)values.get(5));
        } while(!input.equals("!"));

    }

    public static void main(String[] args) {
        AdminView av = new AdminView();
        av.menu();
    }
}
