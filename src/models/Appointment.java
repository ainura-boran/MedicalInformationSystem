
package models;

import java.time.LocalDateTime;

public class Appointment {
    private int id;
    private int doctorId;
    private int patientId;
    private LocalDateTime dateTime;
    private String status;

    public Appointment(int id, int doctorId, int patientId, LocalDateTime dateTime, String status) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.dateTime = dateTime;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getDate() {
        return dateTime;
    }

}
