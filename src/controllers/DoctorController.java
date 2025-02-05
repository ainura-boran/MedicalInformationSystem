package controllers;

import models.Doctor;
import org.mindrot.jbcrypt.BCrypt;
import repositories.DoctorRepository;
import repositories.interfaces.IDoctorRepository;
import java.util.List;


public class DoctorController {
    private final IDoctorRepository doctorRepository;

    public DoctorController(IDoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor authenticateDoctor(String username, String password) {
        Doctor doctor = doctorRepository.getDoctorByUsername(username);
        if (doctor != null && BCrypt.checkpw(password, doctor.getPassword())) {
            return doctor;
        }
        return null;
    }

    public boolean registerDoctor(String fullName, String specialization, String workingHours,
                                  String office, int experienceYears, String username, String password) {
        return doctorRepository.registerDoctor(fullName, specialization, workingHours, office, experienceYears, username, password);
    }

    public Doctor getDoctorById(int id) {
        return doctorRepository.getDoctorById(id);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.getAllDoctors();
    }

}
