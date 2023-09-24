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

    public void addPorts (String name, double latitude, double longitude, double storingCapacity, boolean landingAbility) {
        String portID = generateUniquePortID();
        Port port = new Port(generateUniquePortID(),name, latitude, longitude, storingCapacity, landingAbility);
        portsList.add(port); // Add to containerList

        serializePortsToFile("data/containers.dat");
    }



    public boolean removePort(String portID) {
        for (Port port : portsList) {
            if (port.getPortID().equals(portID)) {
                portsList.remove(port);
                serializePortsToFile("data/ports.dat");
                return true;
            }
        }
        return false;
    }

    public void serializePortsToFile(String filePath) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs(); // Create parent directories if they don't exist
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(portsList);

            System.out.println("Containers have been saved to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error: Unable to save containers to " + filePath);
        }
    }


    public void deserializePortsFromFile() {
        try (FileInputStream fileInputStream = new FileInputStream("data/ports.dat");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            Object importedObject = objectInputStream.readObject();

            if (importedObject instanceof ArrayList) {
                ArrayList<?> importedData = (ArrayList<?>) importedObject;

                if (!importedData.isEmpty() && importedData.get(0) instanceof Port) {
                    portsList = (ArrayList<Port>) importedData;

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
    private synchronized String generateUniquePortID() {
        LastUsedID++;
        return "p-" + LastUsedID;
    }
}
