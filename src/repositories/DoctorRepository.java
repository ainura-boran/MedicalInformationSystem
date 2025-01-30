package repositories;

import data.interfaces.IDB;
import models.Doctor;
import repositories.interfaces.IDoctorRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DoctorRepository implements IDoctorRepository {
    private final IDB db;

    public DoctorRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createDoctor(Doctor doctor) {
        String query = "INSERT INTO doctors (full_name, specialization, working_hours, office, experience_years) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, doctor.getFullName());
            stmt.setString(2, doctor.getSpecialization());
            stmt.setString(3, doctor.getWorkingHours());
            stmt.setString(4, doctor.getOffice());
            stmt.setInt(5, doctor.getExperienceYears());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error creating doctor: " + e.getMessage());
            return false;
        }
    }
    @Override
    public Doctor getDoctorById(int id) {
        String query = "SELECT * FROM doctors WHERE id = ?";
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
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
        } catch (Exception e) {
            System.out.println("Error retrieving doctor by ID: " + e.getMessage());
        }
        return null;
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        try {
            String query = "SELECT * FROM doctors";
            Connection connection = db.getConnection();
            Statement stmt = connection.createStatement();
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
        } catch (Exception e) {
            System.out.println("Error retrieving doctors: " + e.getMessage());
        }
        return doctors;
    }
}