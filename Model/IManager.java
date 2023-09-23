package Model;

import java.util.*;

public interface IManager {

    User login(String username, String password); // Common method for login

    void displayMenu(); // Common method to view dashboard

    void viewPortDetails(); // Common method to view port details

    void calculateDailyFuelUsage(Date date); // Common method to generate daily fuel usage report

    void calculateContainerWeights(); // Admin can calculate the weight of their targeted port(s) or the total weight
                                      // of every ports

    void listShipsInPort(); // Common method to list all ships in a port

    void listTripsFromDateToDate(); // Common method to list all trips in range(date, anotherDate)

    void viewVehicleDetails(); // Common method to view vehicle details

    void viewTripDetails(); // Common method to view trip details

    void addContainer(); // Exclusive method for manager to add a new container

    void editContainerDetails(); // Exclusive method for manager to edit container details

    void deleteContainer(); // Exclusive method for manager to delete a container

    void loadContainerOntoVehicle();

    void unloadContainerFromVehicle();
}
