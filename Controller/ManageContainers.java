package Controller;
import Model.Container;
import java.util.UUID;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManageContainers {
    private static ManageContainers instance;
    private List<Container> containerList;
    private int LastUsedID = 0;

    public ManageContainers() {
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

    public Container addContainer(Container.ContainerType type, double weight) {
        Container container = new Container(this.generateUniqueContainerID(), weight, type);
        containerList.add(container);

        this.saveContainers();
        return container;
    }

    public boolean removeContainer(String containerID) {
        List<Container> newList = new ArrayList<>();
        boolean found = false;

        for (Container container : containerList) {
            if (!container.getContainerID().equals(containerID)) {
                newList.add(container);
            } else {
                found = true;
            }
        }

        if (found) {
            containerList = newList; // Update the original list without the removed element
            saveContainers();

            return true;
        }

        return false;
    }

    public void updateContainer(String id, Container container) {
        for (int i = 0; i < containerList.size(); i++) {
            if (containerList.get(i).getContainerID().equals(id)) {
                containerList.set(i, container);
                saveContainers();
                break;
            }
        }
    }


    public void saveContainers() {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream("dataFile/containers.ser");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(containerList);

            System.out.println("Containers have been saved to dataFile/containers.ser");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
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
