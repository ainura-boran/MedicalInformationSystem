package repositories;

import application.PatientApplication;
import dao.PatientDAO;
import data.interfaces.IDB;
import models.Doctor;
import models.Patient;
import repositories.interfaces.IPatientRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
            stmt.setInt(1, patient.getIin());
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


    public Patient getPatientById(int id) {
        String sql = "SELECT * FROM patients WHERE id = ?";
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Patient(
                    rs.getInt("id"),
                    rs.getInt("iin"),
                    rs.getString("full_name"),
                    rs.getString("date_of_birth"),
                    rs.getString("nationality"),
                    rs.getString("gender"),
                    rs.getString("citizenship"),
                    rs.getString("address"),
                    rs.getString("blood_group"),
                    rs.getString("rhesus_factor"));
            }
        } catch (Exception e) {
            System.out.println("Error retrieving patient by ID: " + e.getMessage());
        }
        return null;
    }

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        try {
            String sql = "SELECT * FROM patients";
            Connection connection = db.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                patients.add(new Patient(
                        rs.getInt("id"),
                        rs.getInt("iin"),
                        rs.getString("full_name"),
                        rs.getString("date_of_birth"),
                        rs.getString("nationality"),
                        rs.getString("gender"),
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
}