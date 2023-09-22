public class Manager extends User {
    private Port managedPort;

    public Manager(String userID, String username, String password, Port managedPort) {
        super(userID, username, password, UserRole.MANAGER);
        this.managedPort = managedPort;
    }

    // public void addContainer(Container container) {
    // this.managedPort.addContainer(container);
    // }

    // =================================GETTER AND SETTER METHODS=======================================================
    public Port getManagedPort() {
        return managedPort;
    }

    public void setManagedPort(Port managedPort) {
        this.managedPort = managedPort;
    }

    @Override
    public void displayMenu() {
        System.out.println("Manager Menu for port: " + managedPort.getName());
    }


}
