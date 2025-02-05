package repositories;

import data.interfaces.IDB;
import models.Appointment;
import repositories.interfaces.IAppointmentRepository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AppointmentRepository implements IAppointmentRepository {
    private final IDB db;

    public AppointmentRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createAppointment(Appointment appointment) {
        String query = "INSERT INTO appointments (id, doctor_id, patient_id, date_time, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, appointment.getDoctorId());
            stmt.setInt(2, appointment.getPatientId());
            stmt.setObject(3, appointment.getDateTime());
            stmt.setString(4, appointment.getStatus());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error creating appointment: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void addAppointment(Appointment appointment) {
        String query = "INSERT INTO appointments (id, doctor_id, patient_id, date_time, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, appointment.getId());
            stmt.setInt(2, appointment.getDoctorId());
            stmt.setInt(3, appointment.getPatientId());
            stmt.setObject(4, appointment.getDateTime());
            stmt.setString(5, appointment.getStatus());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Appointment successfully added to the database.");
            } else {
                System.out.println("Failed to add appointment.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding appointment: " + e.getMessage());
        }
    }

    @Override
    public Optional<Appointment> getAppointmentById(int id) {
        String query = "SELECT * FROM appointments WHERE id = ?";
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Appointment(
                        rs.getInt("id"),
                        rs.getInt("doctor_id"),
                        rs.getInt("patient_id"),
                        rs.getObject("date_time", LocalDateTime.class),
                        rs.getString("status")
                ));
            }
        } catch (Exception e) {
            System.out.println("Error retrieving appointment by ID: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM appointments";
        try (Connection connection = db.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                appointments.add(new Appointment(
                        rs.getInt("id"),
                        rs.getInt("doctor_id"),
                        rs.getInt("patient_id"),
                        rs.getObject("date_time", LocalDateTime.class),
                        rs.getString("status")
                ));
            }
        } catch (Exception e) {
            System.out.println("Error retrieving appointments: " + e.getMessage());
        }
        return appointments;
    }

    public List<Appointment> getAppointmentsForDoctor(int doctorId) {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM appointments WHERE doctor_id = ? ORDER BY date_time ASC";

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
}
