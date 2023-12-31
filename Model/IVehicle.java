
package Model;

import java.util.Map;

public interface IVehicle {
    String getVehicleID(); // Get vehicle ID
    String getName(); // Get vehicle name
    double getCurrentFuel(); // Get current fuel
    double getFuelCapacity(); // Get vehicle fuel capacity
    double getCarryCapacity(); // Get vehicle carry capacity
    int getTotalContainers(); // Get total containers
    Port getCurretPort(); // Get the current port
    Map<Container, Integer> getContainerCounts();

    void loadContainer(Container.ContainerType type);
    void unloadContainer(Container.ContainerType type);
    boolean canLoadContainerType(Container.ContainerType type);
    void move(Port destinationPort);
}
