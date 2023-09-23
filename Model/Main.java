package Model;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin =  new Admin();

    }
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

}
