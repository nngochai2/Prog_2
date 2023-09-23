package Controller;

import Model.Trip;
import Model.Port;

import java.util.*;
import java.util.stream.*;

public class ManageTrips {
    private static ManageTrips instance;

    private ArrayList<Trip> trips = new ArrayList<Trip>();

    public static ManageTrips getInstance() {
        if (instance == null) {
            instance = new ManageTrips();
        }
        return instance;
    }

    public void addTrip(Trip trip) {
        reloadHistory();
        this.trips.add(trip);
    }

    public ArrayList<Trip> listTrips() {
        reloadHistory();
        return this.trips;
    }

    // Both Admin and Manager
    public ArrayList<Trip> listTripsOfPort(Port port) {
        reloadHistory();
        return trips.stream()
                .filter(trip -> (trip.getArrivalPort().equals(port) || trip.getDeparturePort().equals(port)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // Both Admin and Manager
    public ArrayList<Trip> listTripsOnDate(Port port, Date date) {
        reloadHistory();
        return trips.stream()
                .filter(trip -> (trip.getDeparturePort().equals(port)
                        || trip.getArrivalPort().equals(port) && trip.getArrivalDate().equals(date)
                        || trip.getDepartureDate().equals(date)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // Admin
    public ArrayList<Trip> listTripsOnDate(Date date) {
        reloadHistory();
        return trips.stream()
                .filter(trip -> (trip.getArrivalDate().equals(date) || trip.getDepartureDate().equals(date)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // Both Admin and Manager
    public ArrayList<Trip> listTripsToPort(Port port) {
        reloadHistory();
        return trips.stream().filter(trip -> (trip.getArrivalPort().equals(port)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // Both Admin and Manager
    public ArrayList<Trip> listTripsFromPort(Port port) {
        reloadHistory();
        return trips.stream().filter(trip -> (trip.getDeparturePort().equals(port)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // Both Admin and Manager
    public ArrayList<Trip> listTripsBetweenDates(Port port, Date date_A, Date date_B) {
        reloadHistory();
        return trips.stream()
                .filter(trip -> (trip.getDeparturePort().equals(port) || trip.getArrivalPort().equals(port))
                        && (trip.getDepartureDate().after(date_A) || trip.getDepartureDate().equals(date_A)) &&
                        trip.getArrivalDate().before(date_B) || trip.getArrivalDate().equals(date_B))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // Admin
    public ArrayList<Trip> listTripsBetweenDates(Date date_A, Date date_B) {
        reloadHistory();
        return trips.stream()
                .filter(trip -> (trip.getDepartureDate().after(date_A) || trip.getDepartureDate().equals(date_A)) &&
                        trip.getArrivalDate().before(date_B) || trip.getArrivalDate().equals(date_B))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // Ensure that all trips are within 7 days
    public void reloadHistory() {
        Date now = new Date();
        long sevenDays = 1000 * 60 * 60 * 24 * 7;
        Date seveDaysAgo = new Date(now.getTime() - sevenDays);
        this.trips = trips.stream().filter(trip -> (trip.getArrivalDate().after(seveDaysAgo)))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
