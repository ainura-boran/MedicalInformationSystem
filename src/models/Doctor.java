package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Doctor extends User {
    private String specialization;
    private String workingHours;
    private String office;
    private int experienceYears;
    private List<Appointment> appointments = new ArrayList<>();

    public Doctor(int id, String fullName, String username, String passwordHash,
                  String specialization, String workingHours, String office, int experienceYears) {
        super(id, fullName, username, passwordHash);
        this.specialization = specialization;
        this.workingHours = workingHours;
        this.office = office;
        this.experienceYears = experienceYears;
    }

    @Override
    public String getRole() {
        return "Doctor";
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public String getOffice() {
        return office;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public boolean isAvailable(LocalDateTime dateTime) {
        return appointments.stream().noneMatch(a -> a.getDateTime().equals(dateTime));
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    @Override
    public String toString() {
        return super.toString() + " { Specialization: " + specialization + ", Office: " + office + " }";
    }
}
