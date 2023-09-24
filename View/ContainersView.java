package View;
import Controller.ManageContainers;
import Model.Container;
import Model.Port;

import java.util.Optional;
import java.util.Scanner;

public class ContainersView {
    ManageContainers manageContainers = ManageContainers.getInstance();

    public void containers(){
        // Menu - Containers
        Scanner scanner = new Scanner(System.in);
        System.out.println("__________________________Containers__________________________");
        System.out.println("There are currently " + manageContainers.getAllContainers().size() + " containers \n");

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
                    // Go back
                    break;
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
    }

    public void containersList() {
        // Menu - Containers - All Containers
        System.out.println("__________________________MENU - CONTAINERS - All Containers__________________________");
        System.out.println("There are currently " + manageContainers.getAllContainers().size() + " containers \n");
        System.out.println(manageContainers.toString());
        
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

            if (manageContainers.validateContainerId(input)) {
                Optional<Container> container = manageContainers.getContainerByID(input);
                if (container.isPresent()) {
                    System.out.println("Container ID - type - weight - location");
                    System.out.println(container.get().getContainerID() + " - " + container.get().getType() + " - " +
                            container.get().getWeight() + " - " + container.get().getLocation());
                } else {
                    System.out.println("No container found\n");
                }
            } else {
                System.out.println("Invalid input!\n");
            }
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
            System.out.println("There are currently " + manageContainers.getAllContainers().size() + " containers \n");
            System.out.println("(Enter ! to cancel)");
            System.out.println("Separate values by \" , \" ");
            System.out.println("Enter Container's type (DRY_STORAGE, OPEN_TOP, OPEN_SIDE, REFRIGERATED, LIQUID), weight: ");
            input = scanner.nextLine().trim();

            if (input.equals("!")) {
                break;
            }

            try {
                String[] values = input.split(",");
                String type = values[0].toUpperCase();
                double weight = Double.parseDouble(values[1]);
                if (manageContainers.addContainer(weight, Container.ContainerType.valueOf(type))) {
                    System.out.println("Container added successfully!\n");
                } else {
                    System.out.println("Failed to add container\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!\n");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Not enough values entered!\n");
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
            System.out.println("Enter Port ID: ");
            input = scanner.nextLine().trim();

            if (manageContainers.validateContainerId(input)) {
                Optional<Container> container = manageContainers.getContainerByID(input);
                if (manageContainers.removeContainer(input)) {
                    System.out.println("Removed container successfully\n");
                } else {
                    System.out.println("No port was found.\n");
                }
            } else {
                System.out.println("Invalid input!\n");
            }
        } while(!input.equals("!"));
        // Go back
        this.containers();
    }

    public static void main(String[] args) {
        ContainersView containersView = new ContainersView();
        containersView.containers();
    }
}
