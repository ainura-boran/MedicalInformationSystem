package controllers;

import controllers.interfaces.IPatientController;
import models.Patient;
import repositories.interfaces.IPatientRepository;

import java.util.List;

public class PatientController implements IPatientController {
    private final IPatientRepository repository;

    public PatientController(IPatientRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean addPatient(Patient patient) {
        return repository.addPatient(patient);
    }

    @Override
    public String getPatientById(int id) {
        Patient patient = repository.getPatientById(id);
        return (patient != null) ? patient.toString() : "Patient not found.";
    }

    @Override
    public List<Patient> getAllPatients() {
        return repository.getAllPatients();
    }
}
