import java.util.Scanner;

public class PortManagementSystem {
    public static void main(String[] args) {
        // Display group information
        System.out.println("""
                COSC2081 GROUP ASSIGNMENT
                CONTAINER PORT MANAGEMENT SYSTEM
                Instructor: Mr. Minh Vu & Dr. Phong Ngo
                Group: Programming 2
                s3978281, Nguyen Ngoc Hai
                """
        );

        Scanner scanner = new Scanner(System.in);

        // Display welcome message
        System.out.println("Welcome to the Port Management System!");

        // Main menu loop
        boolean exit = false;

        while (!exit) {
            // Display options
            System.out.println("\nMenu: ");
            System.out.println("1. View Port Information");
            System.out.println("2. Manage Vehicles");
            System.out.println("3. Manage Containers");
            System.out.println("4. Exit");

            // Require user input
            System.out.println("Enter your option (Please enter the number: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            // Perform actions based on the user choice

          }
    }
}
