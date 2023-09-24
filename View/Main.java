package View;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Welcome message
        System.out.println("""
                COSC2081 GROUP ASSIGNMENT
                CONTAINER PORT MANAGEMENT SYSTEM
                Instructor: Mr. Minh Vu & Dr. Phong Ngo
                Group: Programming 2
                s3978281, Nguyen Ngoc Hai
                s3979331, Hoang Quoc Dat
                s3979170, Nguyen Chi Nghia
                s3978546, Bui Cong Duy""");
        System.out.println("__________________________WELCOME TO MY ASS" +
                           "__________________________");
        // Login
        System.out.println("LOGIN");
        System.out.println("ENTER THE NUMBER TO CHOOSE THE OPTION:");
        System.out.println("""
                [1] Login as Admin
                [2] Login as Port Manager
                """);
        String option = scanner.nextLine();

        // Display menu
        if (option.equals("1")){
            AdminView adminView = new AdminView();
            adminView.menu();
        } else if (option.equals("2")) {
            ManagerView managerView = new ManagerView();
            managerView.menu();
        } else {
            System.out.println("well fuck off then");
        }
    }
}
