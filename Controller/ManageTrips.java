package Controller;

import Model.*;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class ManageTrips {
    private static ManageTrips instance;

    private int lastUsedID;

    private ArrayList<Trip> listTrip = new ArrayList<Trip>();

    public static ManageTrips getInstance() {
        if (instance == null) {
            instance = new ManageTrips();
        }
        return instance;
    }


    public void addTrip(Trip trip){
        listTrip.add(trip);
        this.reloadHistory();
        serializeTripsToFile("data/trips.dat");
    }
    public ArrayList<Trip> listTrips() {
        reloadHistory();
        return this.listTrip;
    }

    // Both Admin and Manager
    public ArrayList<Trip> listTripsOfPort(Port port) {
        reloadHistory();
        return listTrip.stream()
                .filter(trip -> (trip.getArrivalPort().equals(port) || trip.getDeparturePort().equals(port)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // Both Admin and Manager
    public ArrayList<Trip> listTripsOnDate(Port port, Date date) {
        reloadHistory();
        return listTrip.stream()
                .filter(trip -> (trip.getDeparturePort().equals(port)
                        || trip.getArrivalPort().equals(port) && trip.getArrivalDate().equals(date)
                        || trip.getDepartureDate().equals(date)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // Admin
    public ArrayList<Trip> listTripsOnDate(Date date) {
        reloadHistory();
        return listTrip.stream()
                .filter(trip -> (trip.getArrivalDate().equals(date) || trip.getDepartureDate().equals(date)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // Both Admin and Manager
    public ArrayList<Trip> listTripsToPort(Port port) {
        reloadHistory();
        return listTrip.stream().filter(trip -> (trip.getArrivalPort().equals(port)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // Both Admin and Manager
    public ArrayList<Trip> listTripsFromPort(Port port) {
        reloadHistory();
        return listTrip.stream().filter(trip -> (trip.getDeparturePort().equals(port)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // Both Admin and Manager
    public ArrayList<Trip> listTripsBetweenDates(Port port, Date date_A, Date date_B) {
        reloadHistory();
        return listTrip.stream()
                .filter(trip -> (trip.getDeparturePort().equals(port) || trip.getArrivalPort().equals(port))
                        && (trip.getDepartureDate().after(date_A) || trip.getDepartureDate().equals(date_A)) &&
                        trip.getArrivalDate().before(date_B) || trip.getArrivalDate().equals(date_B))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // Admin
    public ArrayList<Trip> listTripsBetweenDates(Date date_A, Date date_B) {
        reloadHistory();
        return listTrip.stream()
                .filter(trip -> (trip.getDepartureDate().after(date_A) || trip.getDepartureDate().equals(date_A)) &&
                        trip.getArrivalDate().before(date_B) || trip.getArrivalDate().equals(date_B))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // Ensure that all trips are within 7 days
    public void reloadHistory() {
        Date now = new Date();
        long sevenDays = 1000 * 60 * 60 * 24 * 7;
        Date seveDaysAgo = new Date(now.getTime() - sevenDays);
        this.listTrip = listTrip.stream().filter(trip -> (trip.getArrivalDate().after(seveDaysAgo)))
                .collect(Collectors.toCollection(ArrayList::new));
    }


    public void serializeTripsToFile(String filePath) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs(); // Create parent directories if they don't exist
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(listTrip);

            System.out.println("Containers have been saved to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error: Unable to save containers to " + filePath);
        }
    }

    public void deserializeTripsFromFile() {
        try (FileInputStream fileInputStream = new FileInputStream("data/trips.dat");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            Object importedObject = objectInputStream.readObject();

            if (importedObject instanceof ArrayList) {
                ArrayList<?> importedData = (ArrayList<?>) importedObject;

                if (!importedData.isEmpty() && importedData.get(0) instanceof Port) {
                    listTrip = (ArrayList<Trip>) importedData;

                    System.out.println("Containers have been deserialized and imported from data/containers.dat");
                } else {
                    System.out.println("Error: Unexpected data format in the file.");
                }
            } else {
                System.out.println("Error: Unexpected data format in the file.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private synchronized int generateTripID() {
        lastUsedID++;
        return lastUsedID;
    }
}
