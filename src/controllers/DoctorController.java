package controllers;

import controllers.interfaces.IDoctorController;
import models.Doctor;
import repositories.interfaces.IDoctorRepository;

public class DoctorController implements IDoctorController {
    private final IDoctorRepository repository;

    public DoctorController(IDoctorRepository repository) {
        this.repository = repository;
    }

    public boolean createDoctor(Doctor doctor) {
        return repository.createDoctor(doctor);
    }

    public Doctor getDoctorById(int id) {
        return repository.getDoctorById(id);
    }

    public java.util.List<Doctor> getAllDoctors() {
        return repository.getAllDoctors();
    }
}