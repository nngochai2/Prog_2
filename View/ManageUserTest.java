package View;
import Controller.ManageUser;
import Model.Admin;
import Model.Manager;
import Model.Port;
import Model.User;
import Controller.ManagePorts;

import java.util.Optional;


public class ManageUserTest {

    public Port port;
    public static void main(String[] args) {
        ManageUser userManager = new ManageUser();

        // Create some user objects and add them to the user list
        User adminUser = new Admin(1, "admin", "adminPassword", User.UserRole.ADMIN);


        userManager.userList.add(adminUser);


        // Serialize users to a file
        userManager.serializeUsersToFile("dataFile/users.dat");

        // Deserialize users from the file
        userManager.deserializeUsersFromFile("dataFile/users.dat");

        // Test authentication
        Optional<User> authenticatedUser = userManager.authenticateUser("admin", "adminPassword");

        if (authenticatedUser.isPresent()) {
            System.out.println("Authentication successful. User role: " + authenticatedUser.get().getRole());
        } else {
            System.out.println("Authentication failed.");
        }
    }
}

