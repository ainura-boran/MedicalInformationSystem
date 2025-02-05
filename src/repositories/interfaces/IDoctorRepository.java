package repositories.interfaces;

import models.Doctor;

import java.util.List;

public interface IDoctorRepository {
    boolean createDoctor(Doctor doctor);

    Doctor getDoctorById(int id);

    List<Doctor> getAllDoctors();

    Doctor authenticateDoctor(String username, String password);

    boolean registerDoctor(String fullName, String specialization, String workingHours, String office, int experienceYears, String username, String password);

    Doctor getDoctorByUsername(String username);
}