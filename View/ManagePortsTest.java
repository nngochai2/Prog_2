package View;

import Controller.ManagePorts;
import Model.Port;

import java.util.ArrayList;

public class ManagePortsTest {
    public static void main(String[] args) {
        // Create an instance of ManagePorts
        ManagePorts managePorts = ManagePorts.getInstance();

        // Example: Adding ports
        managePorts.addPorts("Port 1", 42.123, -71.456, 1000, true);
        managePorts.addPorts("Port 2", 41.789, -70.123, 800, false);

        // Example: Listing all ports
        ArrayList<Port> portsList = managePorts.getPortsList();
        for (Port port : portsList) {
            System.out.println("Port ID: " + port.getPortID());
            System.out.println("Name: " + port.getName());
            System.out.println("Latitude: " + port.getLatitude());
            System.out.println("Longitude: " + port.getLongitude());
            System.out.println("Storing Capacity: " + port.getStoringCapacity());
            System.out.println("Landing Ability: " + port.hasLandingAbility());
            System.out.println("--------------------");
        }

        // Example: Removing a port
        String portIDToRemove = "p-2"; // Replace with the actual ID you want to remove
        boolean removed = managePorts.removePort(portIDToRemove);
        if (removed) {
            System.out.println("Port with ID " + portIDToRemove + " has been removed.");
        } else {
            System.out.println("Port with ID " + portIDToRemove + " not found.");
        }

        // You can continue using managePorts for other operations as needed

        // ... Rest of your code ...
    }
}

