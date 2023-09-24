package View;
import java.util.Scanner;

public class ManagerView {
    public void menu() {
        // Manager Menu
        Scanner scanner = new Scanner(System.in);

        String input;
        do {
            System.out.println("__________________________WELCOME MANAGER__________________________");
            System.out.println("ENTER THE NUMBER TO CHOOSE THE OPTION:");
            System.out.println("[0] LOG OUT\n[1] Manage Vehicles\n[2] Manage Containers\n");
            input = scanner.nextLine().trim();
            switch (input) {
                case "0":
                    // main()
                    break;
                case "1":
                    VehiclesView vehiclesView = new VehiclesView();
                    vehiclesView.vehicles();
                    break;
                case "2":
                    ContainersView containersView = new ContainersView();
                    containersView.containers();
                default:
                    System.out.println("Invalid input");
            }
        } while(!input.equals("0"));
    }
}
