package Controller;
import Model.Container;
import Model.Port;

import java.util.UUID;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ManageContainers {
    private static ManageContainers instance;
    private ArrayList<Container> containerList;
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

    public boolean validateContainerId(String containerId) {
        String regex = "c-[0-9a-zA-Z]+";
        return containerId.matches(regex);
    }

    public Optional<Container> getContainerByID(String containerID) {
        return this.containerList.stream()
                .filter(port -> port.getContainerID().equals(containerID))
                .findFirst();
    }


    public boolean contains(String containerID) {
        return this.containerList.stream().anyMatch(container -> container.getContainerID().equals(containerID));
    }

    public boolean addContainer(double weight, Container.ContainerType type) {
        String containerID = generateUniqueContainerID();
        Container container = new Container(containerID, type, weight, ""); // Initialize Container
        containerList.add(container); // Add to containerList
        serializeContainersToFile("data/containers.dat");
        return true;
    }


    public boolean removeContainer(String containerID) {
        ArrayList<Container> newList = new ArrayList<>();
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
            serializeContainersToFile("data/containers.dat");

            return true;
        }

        return false;
    }

    public void updateContainer(String id, Container container) {
        for (int i = 0; i < containerList.size(); i++) {
            if (containerList.get(i).getContainerID().equals(id)) {
                containerList.set(i, container);
                serializeContainersToFile("data/containers.dat");
                break;
            }
        }
    }


    public void serializeContainersToFile(String filePath) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs(); // Create parent directories if they don't exist
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(containerList);

            System.out.println("Containers have been saved to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error: Unable to save containers to " + filePath);
        }
    }



    public void deserializeContainersFromFile() {
        try (FileInputStream fileInputStream = new FileInputStream("data/containers.dat");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            Object importedObject = objectInputStream.readObject();

            if (importedObject instanceof ArrayList) {
                ArrayList<?> importedData = (ArrayList<?>) importedObject;

                if (!importedData.isEmpty() && importedData.get(0) instanceof Container) {
                    containerList = (ArrayList<Container>) importedData;

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



    private synchronized String generateUniqueContainerID() {
        LastUsedID++;
        return "c-" + LastUsedID;
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Container List:\n");

        if (containerList.isEmpty()) {
            result.append("No containers available.\n");
        } else {
            for (Container container : containerList) {
                result.append("Container ID: ").append(container.getContainerID()).append("\n")
                        .append("Type: ").append(container.getType()).append("\n")
                        .append("Weight: ").append(container.getWeight()).append(" kg\n")
                        .append("--------------------\n");
            }
        }

        return result.toString();
    }
}

