package controller;

import model.Container;

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
        Container container = new Container(this.generateUniqueContainerID(),weight, type) ;
        containerList.add(container);
        this.saveContainersToFile();
        return container;
    }

    public boolean removeContainers(String containerID) {
        if (containerList.removeIf(container -> container.getContainerID().equals(containerID))) {
            this.saveContainersToFile();
            return true;
        }
        return false;
    }

    public void saveContainersToFile() {
        try (FileOutputStream fileOutputStream = new FileOutputStream("dataFile/containers.ser");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(containerList);

            System.out.println("Containers have been saved to " + "dataFile/containers.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getContainerInfo() {
        try (FileInputStream fileInputStream = new FileInputStream("dataFile/containers.ser");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            ArrayList<Container> loadedContainers = (ArrayList<Container>) objectInputStream.readObject();

            containerList = loadedContainers;

            System.out.println("Containers have been loaded from " + "dataFile/containers.ser");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private synchronized String generateUniqueContainerID() {
        LastUsedID++;
        return "c-" + LastUsedID;
    }

    public static void main(String[] args) {
        ManageContainers manageContainers = ManageContainers.getInstance();
        manageContainers.addContainer(78.9, Container.ContainerType.LIQUID);
    }
}



