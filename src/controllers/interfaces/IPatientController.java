package controllers.interfaces;

import models.Patient;

import java.util.List;

public interface IPatientController {
    boolean addPatient(Patient patient);

    String getPatientById(int id);

    List<Patient> getAllPatients();
}
