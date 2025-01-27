package controllers.interfaces;

import models.Doctor;

import java.util.List;

public interface IDoctorController {
    String createDoctor(Doctor doctor);
    String getDoctorById(int id);
    List<Doctor> getAllDoctors();
}