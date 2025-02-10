package models;

public class Patient {
    private int id;
    private String iin; // Individual Identification Number
    private String fullName;
    private String dateOfBirth;
    private String gender;
    private String nationality;
    private String citizenship;
    private String address;
    private String bloodGroup;
    private String rhesusFactor;

    public Patient() {}

    public Patient(int id, String iin, String fullName, String dateOfBirth, String gender,
                   String nationality, String citizenship, String address, String bloodGroup, String rhesusFactor) {
        this.id = id;
        this.iin = iin;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.nationality = nationality;
        this.citizenship = citizenship;
        this.address = address;
        this.bloodGroup = bloodGroup;
        this.rhesusFactor = rhesusFactor;
    }

    public Patient(int id) {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIin() {
        return iin;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getNationality() {
        return nationality;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public String getAddress() {
        return address;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getRhesusFactor() {
        return rhesusFactor;
    }

    @Override
    public String toString() {
        return "Patient {" +
                "ID=" + id +
                ", IIN='" + iin + '\'' +
                ", Full Name='" + fullName + '\'' +
                ", Date of Birth=" + dateOfBirth +
                ", Gender='" + gender + '\'' +
                ", Nationality='" + nationality + '\'' +
                ", Citizenship='" + citizenship + '\'' +
                ", Address='" + address + '\'' +
                ", Blood Group='" + bloodGroup + '\'' +
                ", Rhesus Factor='" + rhesusFactor + '\'' +
                '}';
    }
}