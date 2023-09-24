package Controller;

import Model.*;

import java.util.ArrayList;
import java.io.*;

public class ManagePorts {
    // Attributes
    private static ManagePorts instance;
    private ArrayList<Port> portsList = new ArrayList<>();

    private int LastUsedID = 0;

    public static ManagePorts getInstance() {
        if (instance == null) {
            instance = new ManagePorts();
        }
        return instance;
    }

    // Methods
    public ArrayList<Port> getPortsList() {
        return portsList;
    }

    public int getPortsCount() {
        return portsList.size();
    }

    public Port getPortByID(String portID) {
        Port port = null;
        for (Port p : portsList) {
            if (p.getPortID() == portID) {
                port = p;
            }
        }
        return port;
    }

    public void addPort(String name, double latitude, double longitude, int storingCapacity, boolean landingAbility){
        Port port = new Port("p-"+generateUniquePortID(),name, latitude, longitude, storingCapacity, landingAbility);
        this.portsList.add(port);
        serializePortsToFile();
    }

    public boolean removePort(String portID) {
        for (Port port : portsList) {
            if (port.getPortID().equals(portID)) {
                portsList.remove(port);
                serializePortsToFile();
                return true;
            }
        }
        return false;
    }

    public void serializePortsToFile() {
        try (FileOutputStream fileOutputStream = new FileOutputStream("data/ports.dat");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(portsList);

            System.out.println("Ports have been serialized and saved to data/ports.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deserializePortsFromFile() {
        try (FileInputStream fileInputStream = new FileInputStream("data/ports.dat");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            ArrayList<Port> importedPorts = (ArrayList<Port>) objectInputStream.readObject();

            portsList = importedPorts;

            System.out.println("Ports have been deserialized and imported from data/ports.dat");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private synchronized String generateUniquePortID() {
        LastUsedID++;
        return "P-" + LastUsedID;
    }
}
