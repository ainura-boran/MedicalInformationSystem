package repositories;

import models.Appointment;
import repositories.interfaces.IAppointmentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class AppointmentRepository implements IAppointmentRepository {
    private List<Appointment> appointments = new ArrayList<>();

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public Optional<Appointment> getAppointmentById(int id) {
        return appointments.stream().filter(a -> a.getId() == id).findFirst();
    }

    public List<Appointment> getAllAppointments() {
        return new ArrayList<>(appointments);
    }

    public void updateAppointment(Appointment appointment) {
        deleteAppointment(appointment.getId());
        appointments.add(appointment);
    }

    public void deleteAppointment(int id) {
        appointments.removeIf(a -> a.getId() == id);
    }
}