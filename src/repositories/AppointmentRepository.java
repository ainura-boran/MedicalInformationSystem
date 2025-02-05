package repositories;

import data.interfaces.IDB;
import models.Appointment;
import repositories.interfaces.IAppointmentRepository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AppointmentRepository implements IAppointmentRepository {
    private final IDB db;

    public AppointmentRepository(IDB db) {
        this.db = db;
    }

    public List<String> getDoctorSchedules() {
        List<String> schedules = new ArrayList<>();
        String query = "SELECT id, doctor_id, patient_id, date_time, status FROM appointments";

        DoctorRepository doctorRepository = new DoctorRepository(db);
        PatientRepository patientRepository = new PatientRepository(db);

        Map<Integer, String> doctorNames = doctorRepository.getDoctorNames();
        Map<Integer, String> patientNames = patientRepository.getPatientNames();

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int doctorId = rs.getInt("doctor_id");
                int patientId = rs.getInt("patient_id");
                String doctorName = doctorNames.getOrDefault(doctorId, "Unknown Doctor");
                String patientName = patientNames.getOrDefault(patientId, "Unknown Patient");

                String schedule = "Appointment ID: " + rs.getInt("id") +
                        " | Doctor: " + doctorName +
                        " | Patient: " + patientName +
                        " | Date: " + rs.getTimestamp("date_time").toLocalDateTime() +
                        " | Status: " + rs.getString("status");

                schedules.add(schedule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
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
