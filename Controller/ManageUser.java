package Controller;

import Model.Port;
import Model.User;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Optional;

public class ManageUser {

    private int lastUsedID;
    private static ManageUser instance;

    public Optional<User> authenticateUser(String username, String providedPassword) {
        Optional<User> user = this.userList.stream()
                .filter(user1 -> user1.getUsername().equals(username))
                .findFirst();

        // Check if the user was found and verify the provided password
        if (user.isPresent() && user.get().verifyPassword(providedPassword)) {
            return user;
        } else {
            return Optional.empty(); // Either user not found or password doesn't match
        }
    }


    public ArrayList<User> userList = new ArrayList<>();


    public void serializeUsersToFile(String filePath) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(userList);
            System.out.println("Users have been serialized and saved to " + filePath);

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error: Unable to serialize users to " + filePath);
        }
    }

    public void deserializeUsersFromFile(String filePath) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            ArrayList<User> importedUsers = (ArrayList<User>) objectInputStream.readObject();
            userList = importedUsers;
            System.out.println("Users have been deserialized and imported from " + filePath);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Error: Unable to deserialize users from " + filePath);
        }
    }
    private synchronized int generateUserID() {
        lastUsedID++;
        return lastUsedID;
    }
}
