package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                    return new Admin.Builder()
                            .setId(rs.getInt("id"))
                            .setUsername(rs.getString("username"))
                            .setPasswordHash(storedHash)
                            .build();
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error during admin authentication: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }
}
