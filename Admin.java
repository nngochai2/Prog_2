import java.util.ArrayList;

public class Admin extends User {

    private ArrayList<Port> ports;
    private ArrayList<Vehicle> vehicles;
    private ArrayList<Container> containers;
    private ArrayList<Manager> managers;

    public Admin(String username, String password, ArrayList<Port> ports, ArrayList<Vehicle> vehicles,
            ArrayList<Container> containers, ArrayList<Manager> managers) {
        super(username, password, UserRole.ADMIN);
        this.ports = ports;
        this.vehicles = vehicles;
        this.containers = containers;
        this.managers = managers;
    }

    public ArrayList<Port> getPorts() {
        return this.ports;
    }

    public void setPorts(ArrayList<Port> ports) {
        this.ports = ports;
    }

    public ArrayList<Vehicle> getVehicles() {
        return this.vehicles;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public ArrayList<Container> getContainers() {
        return this.containers;
    }

    public void setContainers(ArrayList<Container> containers) {
        this.containers = containers;
    }

    public ArrayList<Manager> getManagers() {
        return this.managers;
    }

    public void setManagers(ArrayList<Manager> managers) {
        this.managers = managers;
    }

    public void displayMenu() {
        System.out.println("ADMIN's menu");
    }
}
