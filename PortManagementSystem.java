import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PortManagementSystem {
    private static List<Port> ports = new ArrayList<>();
    private static List<Vehicle> vehicles = new ArrayList<>();
    private static List<Container> containers = new ArrayList<>();
    private Scanner scanner;

    public static void main(String[] args) {
        PortManagementSystem portManagement = new PortManagementSystem();
        portManagement.displayWelcomeScreen();
        portManagement.scanner = new Scanner(System.in);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Add a new Port");
            System.out.println("2. Remove an existing Port");
            System.out.println("3. Add a new Vehicle");
            System.out.println("4. Remove an existing Vehicle");
            System.out.println("5. Add a new Container");
            System.out.println("6. Remove an existing Container");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:

            }
        }

    }

    // Add new
    // Display group information
    private void displayWelcomeScreen() {
        // Display welcome information
        System.out.println("""
                COSC2081 GROUP ASSIGNMENT
                CONTAINER PORT MANAGEMENT SYSTEM
                Instructor: Mr. Minh Vu & Dr. Phong Ngo
                Group: Programming 2
                s3978281, Nguyen Ngoc Hai
                s3979331, Hoang Quoc Dat
                s3979170, Nguyen Chi Nghia
                s3978546, Bui Cong Duy
                """
        );
    }

}
