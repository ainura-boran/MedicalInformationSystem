package repositories;

import data.interfaces.IDB;
import models.Appointment;
import models.Doctor;
import org.mindrot.jbcrypt.BCrypt;
import repositories.interfaces.IDoctorRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DoctorRepository implements IDoctorRepository {
    private final IDB db;

    public DoctorRepository(IDB db) {
        this.db = db;
    }

    public List<Appointment> getAppointmentsForDoctor(int doctorId) {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM appointments WHERE doctor_id = ?";

        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, doctorId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                appointments.add(new Appointment(
                        rs.getInt("id"),
                        rs.getInt("doctor_id"),
                        rs.getInt("patient_id"),
                        rs.getTimestamp("date_time").toLocalDateTime(),
                        rs.getString("status")
                ));
            }
        } catch (Exception e) {
            System.out.println("Error retrieving appointments: " + e.getMessage());
        }
        return appointments;
    }

    public boolean registerDoctor(String fullName, String specialization, String workingHours,
                                  String office, int experienceYears, String username, String password) {
        String query = "INSERT INTO doctors (full_name, specialization, working_hours, office, experience_years, username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, fullName);
            stmt.setString(2, specialization);
            stmt.setString(3, workingHours);
            stmt.setString(4, office);
            stmt.setInt(5, experienceYears);
            stmt.setString(6, username);
            stmt.setString(7, password);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Error registering doctor: " + e.getMessage());
            return false;
        }
    }

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
                        rs.getInt("experience_years"),
                        rs.getString("username"),
                        rs.getString("password")
                );
            }
        } catch (Exception e) {
            System.out.println("Error retrieving doctor by ID: " + e.getMessage());
        }
        return null;
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctors";
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                doctors.add(new Doctor(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("specialization"),
                        rs.getString("working_hours"),
                        rs.getString("office"),
                        rs.getInt("experience_years"),
                        rs.getString("username"),
                        rs.getString("password")
                ));
            }
        } catch (Exception e) {
            System.out.println("Error retrieving doctors: " + e.getMessage());
        }
        return doctors;
    }

    public Doctor authenticateDoctor(String username, String password) {
        String query = "SELECT * FROM doctors WHERE username = ? AND password = ?";
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Doctor(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("specialization"),
                        rs.getString("working_hours"),
                        rs.getString("office"),
                        rs.getInt("experience_years"),
                        rs.getString("username"),
                        rs.getString("password")
                );
            }
        } catch (Exception e) {
            System.out.println("Error authenticating doctor: " + e.getMessage());
        }
        return null;
    }

    public List<String> getAllSpecializations() {
        List<String> specializations = new ArrayList<>();
        String query = "SELECT DISTINCT specialization FROM doctors";

        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                specializations.add(rs.getString("specialization"));
            }
        } catch (Exception e) {
            System.out.println("Error retrieving specializations: " + e.getMessage());
        }
        return specializations;
    }

    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctors WHERE specialization = ? ORDER BY experience_years DESC";

        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, specialization);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                doctors.add(new Doctor(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("specialization"),
                        rs.getString("working_hours"),
                        rs.getString("office"),
                        rs.getInt("experience_years"),
                        rs.getString("username"),
                        rs.getString("password")
                ));
            }
        } catch (Exception e) {
            System.out.println("Error retrieving doctors: " + e.getMessage());
        }
        return doctors;
    }

    public Doctor getDoctorByUsername(String username) {
        String query = "SELECT id, full_name, specialization, working_hours, office, experience_years, username, password FROM doctors WHERE username = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Doctor(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("specialization"),
                        rs.getString("working_hours"),
                        rs.getString("office"),
                        rs.getInt("experience_years"),
                        rs.getString("username"),
                        rs.getString("password")
                );

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<Integer, String> getDoctorNames() {
        Map<Integer, String> doctorNames = new HashMap<>();
        String query = "SELECT id, full_name FROM doctors";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                doctorNames.put(rs.getInt("id"), rs.getString("full_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctorNames;
    }

}