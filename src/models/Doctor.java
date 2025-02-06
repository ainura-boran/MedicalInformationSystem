package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Doctor {
    private int id;
    private String fullName;
    private String specialization;
    private String workingHours;
    private String office;
    private int experienceYears;
    private String username;
    private String password;
    private List<Appointment> appointments = new ArrayList<>();

    public Doctor() {}

    public Doctor(int id, String fullName, String specialization, String workingHours, String office, int experienceYears, String username, String password) {
        this.id = id;
        this.fullName = fullName;
        this.specialization = specialization;
        this.workingHours = workingHours;
        this.office = office;
        this.experienceYears = experienceYears;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
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

    @Override
    public String toString() {
        return "Doctor {" +
                "ID: " + id +
                ", Full Name: '" + fullName + '\'' +
                ", Specialization: '" + specialization + '\'' +
                ", Working Hours: '" + workingHours + '\'' +
                ", Office: '" + office + '\'' +
                ", Experience: " + experienceYears + " years" +
                '}';
    }

    public boolean isAvailable(LocalDateTime dateTime) {
        return appointments.stream().noneMatch(a -> a.getDateTime().equals(dateTime));
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }
}