package View;
import java.util.Scanner;

public class ContainersView {

    public void containers(){
        // Menu - Containers
        Scanner scanner = new Scanner(System.in);
        System.out.println("__________________________Containers__________________________");
        System.out.println("There are currently [number] containers");

        String input;
        do {
            System.out.println("""
                   [0] GO BACK
                   [1] List All Containers
                   [2] Find Container by ID
                   [3] Add Container
                   [4] Remove Container
                    """);
            System.out.println("ENTER THE NUMBER TO CHOOSE THE OPTION:");
            input = scanner.nextLine().trim();
            switch (input) {
                case "0":
//                    menu();
                case "1":
                    this.containersList();
                    break;
                case "2":
                    this.containerByID();
                    break;
                case "3":
                    this.addContainer();
                    break;
                case "4":
                    this.removeContainer();
                    break;
                default:
                    System.out.println("Invalid input");
            }
        } while(!input.equals("0"));
        // Go back
    }

    public void containersList() {
        // Menu - Containers - All Containers
        System.out.println("__________________________MENU - CONTAINERS - All Containers__________________________");
        System.out.println("There are currently [number] containers \n");
        // Go back
        this.containers();
    }

    public void containerByID() {
        // Menu - Containers - All Containers
        Scanner scanner = new Scanner(System.in);
        System.out.println("__________________________MENU - CONTAINERS - Find Container by ID__________________________");

        String input;
        do {
            System.out.println("(Enter ! to cancel)");
            System.out.println("Enter Container ID: ");
            input = scanner.nextLine().trim();
        } while(!input.equals("!"));
        // Go back
        this.containers();
    }

    public void addContainer() {
        // Menu - Containers - Add Container
        Scanner scanner = new Scanner(System.in);
        System.out.println("__________________________MENU - CONTAINERS - Add Container__________________________");

        String input;
        do {
            System.out.println("There are currently [number] containers");
            System.out.println("(Enter ! to cancel)");
            System.out.println("Separate values by \" , \" ");
            System.out.println("Enter Container's ID, type, weight, location: ");
            input = scanner.nextLine().trim();

            if (input.equals("!")) {
                break;
            }

            try {
                String[] values = input.split(",");
                String ID = values[0];
                String type = values[1];
                double weight = Double.parseDouble(values[2]);
                String location = values[3];

                // Add container
                System.out.println("Container added successfully!");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Not enough values entered!");
            }
        } while (true);
        // Go back
        this.containers();
    }

    public void removeContainer() {
        // Menu - Containers - Remove Containers
        Scanner scanner = new Scanner(System.in);
        System.out.println("__________________________MENU - CONTAINERS - Remove Containers__________________________");

        String input;
        do {
            System.out.println("(Enter ! to cancel)");
            System.out.println("Enter Container ID: ");
            input = scanner.nextLine().trim();

//            try {
//                // validate
//                // deleteContainer(input)
//            } catch (IllegalArgumentException e) {
//                System.out.println(e.getMessage());
//            }
        } while(!input.equals("!"));
        // Go back
        this.containers();
    }
}