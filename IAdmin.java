
import java.util.ArrayList;

public interface IAdmin extends IManager {
    void addPort(); // Exclusive method for admin to add a new port

    void editPortDetails(); // Exclusive method for admin to edit port details

    void deletePort(); // Exclusive method for admin to delete a port

    void addVehicle(); // Exclusive method for admin to add a new vehicle

    void editVehicleDetails(); // Exclusive method for admin to edit vehicle details

    void removeVehicle(); // Exclusive method for admin to delete a vehicle

    void addManager(); // Exclusive method for admin to add a new manager

    void editManagerDetails(); // Exclusive method for admin to edit manager details

    void deleteManager(); // Exclusive method for admin to delete a manager

    void generateTrafficReport(); // Exclusive method for admin to generate traffic report

    void generateContainerStatisticsReport(); // Exclusive method for admin to generate container statistics report
}
