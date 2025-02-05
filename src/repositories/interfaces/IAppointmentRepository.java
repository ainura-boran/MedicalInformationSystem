package repositories.interfaces;

import models.Appointment;

import java.util.List;
import java.util.Optional;

public interface IAppointmentRepository {
    boolean createAppointment(Appointment appointment);

    void addAppointment(Appointment appointment);
    Optional<Appointment> getAppointmentById(int id);
    List<Appointment> getAllAppointments();
    List<Appointment> getAppointmentsForDoctor(int doctorId);
}