package models;

public class Appointment {
    public int idDoctor;
    public int idPatient;
    public String data;
    public String time;

    public Appointment(int idDoctor, int idPatient, String data, String time) {
        setIdDoctor(idDoctor);
        setIdPatient(idPatient);
        setData(data);
        setTime(time);
    }

    public int getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    @Override
    public String toString() {
        return "Appointment: " + "\n" +
                "Doctor" + idDoctor + "\n" +
                "Patien: " + idPatient;
    }
}
