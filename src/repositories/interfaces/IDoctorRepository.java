package repositories.interfaces;

import models.Doctor;

import java.util.List;

public interface IDoctorRepository {
    Doctor getDoctorById(int id);
    List<Doctor> getAllDoctors();
    boolean registerDoctor(String fullName, String specialization, String workingHours, String office, int experienceYears, String username, String password);
    Doctor getDoctorByUsername(String username);
}