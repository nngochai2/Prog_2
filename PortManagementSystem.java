import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PortManagementSystem {
    private static List<Port> ports = new ArrayList<>();
    private static List<Vehicle> vehicles = new ArrayList<>();
    private static List<Container> containers = new ArrayList<>();
    private Scanner scanner;
    public PortManagementSystem() {
        users = new ArrayList<>();
        // Load users from an external source
    }

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
                """);
    }

    private void login() {
        Scanner scanner = new Scanner(System.in);
        // Ask for login credentials
        System.out.println("Please log in.");

        System.out.println("Username: ");
        String username = scanner.nextLine();

        System.out.println("Password: ");
        String password = scanner.nextLine();

        // Find user with the provided credentials
        User user = authenticate(username, password);

        if (user != null) {
            loggedInUsers = user;
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid username or password");
        }
    }

    private User authenticate(String username, String password) {
        // Authenticate user information
        for (User user : users)
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        return null;
    }

    private void displayMainMenu() {
        // When a user login in successfully, this menu will display first
        if (loggedInUsers.getRole() == User.UserRole.ADMIN) {
            // Display options for system admin
            System.out.println("Welcome system admin!");
            System.out.println(
                    """
                            Main Menu
                            1. Fuel used today
                            2. View containers' weight
                            3. View ships in ports
                            4. View trips today
                            5. View trips
                            """);
            System.out.println("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                // Handling user choice
                case 1:
                    break;
                case 2:
                    break;

            }

        } else if (loggedInUsers.getRole() == User.UserRole.MANAGER) {
            // Display options for port manager
            System.out.println("Welcome Port Manager!");
            System.out.println(
                    """
                            Main menu"
                            1.
                            2.
                            3.
                            """);
        }
    }
}
