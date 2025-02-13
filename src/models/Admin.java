package models;

public class Admin extends User {

    public Admin(int id, String fullName, String username, String passwordHash) {
        super(id, fullName, username, passwordHash);
    }

    @Override
    public String getRole() {
        return "Admin";
    }
}
