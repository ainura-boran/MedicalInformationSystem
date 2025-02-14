package controllers.interfaces;
import models.Appointment;
import java.util.List;

public interface IAppointmentController {
    void scheduleAppointment(Appointment appointment);
    Appointment getAppointment(int id);
    List<Appointment> listAppointments();
    void modifyAppointment(Appointment appointment);
    void cancelAppointment(int id);
}