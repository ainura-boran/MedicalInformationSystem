package controllers.interfaces;

import models.Patient;

import java.util.List;

public interface IPatientController {
    boolean addPatient(Patient patient);

    Patient getPatientById(int id);

    List<Patient> getAllPatients();
}