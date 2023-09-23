package Controller;

import Model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManageContainers {
    private static ManageContainers instance;
    private List<Container> containerList;
    private int LastUsedID = 0;

    private ManageContainers() {
        containerList = new ArrayList<>();
    }

    public static ManageContainers getInstance() {
        if (instance == null) {
            instance = new ManageContainers();
        }
        return instance;
    }

    public List<Container> getAllContainers() {
        return containerList;
    }

    public Optional<Container> getContainerByID(String containerID) {
        return this.containerList.stream()
                .filter(port -> port.getContainerID().equals(containerID))
                .findFirst();
    }

    public boolean contains(String containerID) {
        return this.containerList.stream().anyMatch(container -> container.getContainerID().equals(containerID));
    }

    public Container addContainer(double weight, Container.ContainerType type) {
        Container container = new Container(this.generateUniqueContainerID(), weight, type);
        containerList.add(container);
        this.serializeContainersToFile();
        return container;
    }

    public boolean removeContainers(String containerID) {
        if (containerList.removeIf(container -> container.getContainerID().equals(containerID))) {
            this.serializeContainersToFile();
            return true;
        }
        return false;
    }

    public void serializeContainersToFile() {
        try (FileOutputStream fileOutputStream = new FileOutputStream("data/containers.dat");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(containerList);

            System.out.println("Containers have been serialized and saved to data/containers.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deserializeContainersFromFile() {
        try (FileInputStream fileInputStream = new FileInputStream("data/containers.dat");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            ArrayList<Container> importedContainers = (ArrayList<Container>) objectInputStream.readObject();

            containerList = importedContainers;

            System.out.println("Containers have been deserialized and imported from data/containers.dat");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private synchronized String generateUniqueContainerID() {
        LastUsedID++;
        return "C-" + LastUsedID;
    }

    // public static void main(String[] args) {
    // ManageContainers manageContainers = ManageContainers.getInstance();
    // manageContainers.addContainer(78.9, Container.ContainerType.LIQUID);
    // }
}
