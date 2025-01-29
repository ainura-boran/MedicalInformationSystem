package dao;

import models.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    private final Connection connection;

    public DoctorDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean createDoctor(Doctor doctor) {
        String query = "INSERT INTO doctors (full_name, specialization, working_hours, office, experience_years) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, doctor.getFullName());
            stmt.setString(2, doctor.getSpecialization());
            stmt.setString(3, doctor.getWorkingHours());
            stmt.setString(4, doctor.getOffice());
            stmt.setInt(5, doctor.getExperienceYears());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error creating doctor: " + e.getMessage());
            return false;
        }
    }

    public Doctor getDoctorById(int id) {
        String query = "SELECT * FROM doctors WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Doctor(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("specialization"),
                        rs.getString("working_hours"),
                        rs.getString("office"),
                        rs.getInt("experience_years")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving doctor: " + e.getMessage());
        }
        return null;
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctors";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                doctors.add(new Doctor(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("specialization"),
                        rs.getString("working_hours"),
                        rs.getString("office"),
                        rs.getInt("experience_years")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving doctors: " + e.getMessage());
        }
        return doctors;
    }
}