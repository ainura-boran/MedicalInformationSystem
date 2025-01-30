package repositories.interfaces;

import models.Appointment;

import java.util.List;
import java.util.Optional;

public interface IAppointmentRepository {
    void addAppointment(Appointment appointment);
    Optional<Appointment> getAppointmentById(int id);
    List<Appointment> getAllAppointments();
    void updateAppointment(Appointment appointment);
    void deleteAppointment(int id);
}