package JavaPackages.UserManagement;

public class User {
    private String name;
    private String username;
    private String password;
    private String status;

    // Constructor
    public User(String name, String username, String password, String status) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return username + "," + password + "," + status + "," + name;
    }
}