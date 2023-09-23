package Controller;

import Model.Port;

import java.util.ArrayList;

public class ManagePorts {
    // Attributes
    private static ManagePorts instance;
    private ArrayList<Port> portsList = new ArrayList<>();

    public static ManagePorts getInstance(){
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
}
