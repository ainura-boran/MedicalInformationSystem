package controllers;

import models.Appointment;
import models.Doctor;
import models.Patient;
import repositories.interfaces.IAppointmentRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

class AppointmentController {
    private IAppointmentRepository repository;
    private List<Doctor> doctors;
    private List<Patient> patients;

    public AppointmentController(IAppointmentRepository repository, List<Doctor> doctors, List<Patient> patients) {
        this.repository = repository;
        this.doctors = doctors;
        this.patients = patients;
    }

    public boolean scheduleAppointment(int doctorId, int patientId, LocalDateTime dateTime) {
        Doctor doctor = doctors.stream().filter(d -> d.getId() == doctorId).findFirst().orElse(null);
        Patient patient = patients.stream().filter(p -> p.getId() == patientId).findFirst().orElse(null);

        if (doctor == null || patient == null) return false;
        if (!doctor.isAvailable(dateTime)) return false;

        Appointment appointment = new Appointment(repository.getAllAppointments().size() + 1, doctorId, patientId, dateTime, "Scheduled");
        doctor.addAppointment(appointment);
        repository.addAppointment(appointment);
        return true;
    }

    public Optional<Appointment> getAppointmentById(int id) {
        return repository.getAppointmentById(id);
    }
}
