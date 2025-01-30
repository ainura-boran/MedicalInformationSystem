package models;

import java.time.LocalDateTime;

public class Doctor {
    private int id;
    private String fullName;
    private String specialization;
    private String workingHours;
    private String office;
    private int experienceYears;
    private List<Appointment> appointments = new ArrayList<>();


    public Doctor() {}

    public Doctor(int id, String fullName, String specialization, String workingHours, String office, int experienceYears) {
        this.id = id;
        this.fullName = fullName;
        this.specialization = specialization;
        this.workingHours = workingHours;
        this.office = office;
        this.experienceYears = experienceYears;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
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