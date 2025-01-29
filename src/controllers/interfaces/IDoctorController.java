package controllers.interfaces;

import models.Doctor;

import java.util.List;

public interface IDoctorController {
    boolean createDoctor(Doctor doctor);
    Doctor getDoctorById(int id);
    List<Doctor> getAllDoctors();
}