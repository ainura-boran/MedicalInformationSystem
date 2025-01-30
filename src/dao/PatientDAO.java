package dao;

import models.Patient;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {
    private final Connection connection;

    public PatientDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean addPatient(Patient patient) {
        String query = "INSERT INTO patients (iin, full_name, date_of_birth, gender, nationality, citizenship, address, blood_group, rhesus_factor) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, patient.getIin());
            stmt.setString(2, patient.getFullName());
            stmt.setString(3, patient.getDateOfBirth());
            stmt.setString(4, patient.getGender());
            stmt.setString(5, patient.getNationality());
            stmt.setString(6, patient.getCitizenship());
            stmt.setString(7, patient.getAddress());
            stmt.setString(8, patient.getBloodGroup());
            stmt.setString(9, patient.getRhesusFactor());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error adding patient: " + e.getMessage());
            return false;
        }
    }

    public Patient getPatientById(int id) {
        String query = "SELECT * FROM patients WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return new Patient(
                        rs.getInt("id"),
                        rs.getString("iin"),
                        rs.getString("full_name"),
                        rs.getString("date_of_birth"),
                        rs.getString("gender"),
                        rs.getString("nationality"),
                        rs.getString("citizenship"),
                        rs.getString("address"),
                        rs.getString("blood_group"),
                        rs.getString("rhesus_factor")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving patient: " + e.getMessage());
        }
        return null;
    }

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM patients";
        try (Statement stmt = connection.createStatement();) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                patients.add(new Patient(
                        rs.getInt("id"),
                        rs.getString("iin"),
                        rs.getString("full_name"),
                        rs.getString("date_of_birth"),
                        rs.getString("gender"),
                        rs.getString("nationality"),
                        rs.getString("citizenship"),
                        rs.getString("address"),
                        rs.getString("blood_group"),
                        rs.getString("rhesus_factor")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving patients: " + e.getMessage());
        }
        return patients;
    }
}