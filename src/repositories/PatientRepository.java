package repositories;

import data.interfaces.IDB;
import models.Patient;
import repositories.interfaces.IPatientRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientRepository implements IPatientRepository {
    private final IDB db;

    public PatientRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean addPatient(Patient patient) {
        String sql = "INSERT INTO patients (iin, full_name, date_Of_Birth, nationality, gender, citizenship, address, blood_Group, rhesus_Factor ) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, patient.getIin());
            stmt.setString(2, patient.getFullName());
            stmt.setString(3, patient.getDateOfBirth());
            stmt.setString(4, patient.getNationality());
            stmt.setString(5, patient.getGender());
            stmt.setString(6, patient.getCitizenship());
            stmt.setString(7, patient.getAddress());
            stmt.setString(8, patient.getBloodGroup());
            stmt.setString(9, patient.getRhesusFactor());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error creating patient: " + e.getMessage());
            System.exit(1);
            return false;
        }
    }

    @Override
    public Patient getPatientById(int id) {
        String query = "SELECT * FROM patients WHERE id = ?";
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Patient(
                        rs.getInt("id"),
                        rs.getString("iin"),
                        rs.getString("full_name"),
                        rs.getString("date_of_birth"),
                        rs.getString("nationality"),
                        rs.getString("gender"),
                        rs.getString("citizenship"),
                        rs.getString("adress"),
                        rs.getString("blood_group"),
                        rs.getString("rhesus_factor")
                );
            }
        } catch (Exception e) {
            System.out.println("Error retrieving patient by ID: " + e.getMessage());
        }
        return null;
    }

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        try {
            String sql = "SELECT id, full_name, date_of_birth, gender, nationality, citizenship, adress, blood_group, rhesus_factor FROM patients WHERE doctor_id = ?";
            Connection connection = db.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                patients.add(new Patient(
                        rs.getInt("id"),
                        rs.getString("iin"),
                        rs.getString("full_name"),
                        rs.getString("date_of_birth"),
                        rs.getString("nationality"),
                        rs.getString("gender"),
                        rs.getString("citizenship"),
                        rs.getString("adress"),
                        rs.getString("blood_group"),
                        rs.getString("rhesus_factor")

                ));
            }
        } catch (Exception e) {
            System.out.println("Error retrieving patients: " + e.getMessage());
        }
        return patients;
    }
    public List<Patient> getPatientsForDoctor(int doctorId) {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT id, iin, full_name, date_of_birth, gender, nationality, citizenship, adress, blood_group, rhesus_factor FROM patients WHERE id = ?";

        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, doctorId);
            ResultSet rs = stmt.executeQuery();

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
        } catch (Exception e) {
            System.out.println("Error retrieving patients: " + e.getMessage());
        }
        return patients;
    }

    public Map<Integer, String> getPatientNames() {
        Map<Integer, String> patientNames = new HashMap<>();
        String query = "SELECT id, full_name FROM patients";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                patientNames.put(rs.getInt("id"), rs.getString("full_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patientNames;
    }

}