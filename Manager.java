public class Manager extends User {
    private Port managedPort;

    public Manager(String username, String password, Port managedPort) {
        super(userID, username, password, UserRole.MANAGER);
        this.managedPort = managedPort;
    }

    // public void addContainer(Container container) {
    // this.managedPort.addContainer(container);
    // }

    @Override
    public void displayMenu() {
        System.out.println("Manager Menu for port: " + managedPort.getName());
    }
}
