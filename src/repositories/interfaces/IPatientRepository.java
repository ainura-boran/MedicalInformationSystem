package repositories.interfaces;

import models.Patient;

import java.util.List;
import controllers.PatientController;
import application.PatientApplication;
public interface IPatientRepository {

    boolean addPatient(Patient patient);
    Patient getPatientById(int id);
    List<Patient> getAllPatients();
}