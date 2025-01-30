import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import models.Appointment;

public class AppointmentDAO {
    private final Connection connection;

    public AppointmentDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean createAppointment(Appointment appointment) {
        String query = "INSERT INTO appointments (doctor_id, patient_id, date_time, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, appointment.getDoctorId());
            stmt.setInt(2, appointment.getPatientId());
            stmt.setObject(3, appointment.getDateTime());
            stmt.setString(4, appointment.getStatus());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Appointment saved in database!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error saving appointment: " + e.getMessage());
        }
        return false;
    }

    public Appointment getAppointmentById(int id) {
        String query = "SELECT * FROM appointments WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Appointment(
                        rs.getInt("id"),
                        rs.getInt("doctor_id"),
                        rs.getInt("patient_id"),
                        rs.getObject("date_time", LocalDateTime.class),
                        rs.getString("status")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving appointment: " + e.getMessage());
        }
        return null;
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM appointments";
        try (Statement stmt = connection.createStatement();
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
        } catch (SQLException e) {
            System.out.println("Error retrieving appointments: " + e.getMessage());
        }
        return appointments;
    }
}
