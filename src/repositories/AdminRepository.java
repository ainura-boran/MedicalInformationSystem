package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import data.interfaces.IDB;
import models.Admin;
import org.mindrot.jbcrypt.BCrypt;

public class AdminRepository {
    private final IDB db;

    public AdminRepository(IDB db) {
        this.db = db;
    }

    public Admin authenticateAdmin(String username, String password) {
        String query = "SELECT * FROM admins WHERE username = ?";
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedHash = rs.getString("password_hash");
                if (BCrypt.checkpw(password, storedHash)) {
                    return new Admin(rs.getInt("id"), rs.getString("username"), storedHash);
                }
            }
        } catch (Exception e) {
            System.out.println("Admin login error: " + e.getMessage());
        }
        return null;
    }
    public boolean registerAdmin(String username, String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        String query = "INSERT INTO admins (username, password_hash) VALUES (?, ?)";
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error registering admin: " + e.getMessage());
            return false;
        }
    }
}

