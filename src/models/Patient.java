package models;

import java.time.LocalDate;

public class Patient {
    private int id;
    private String iin; // Individual Identification Number
    private String fullName;
    private LocalDate dateOfBirth;
    private String gender;
    private String nationality;
    private String citizenship;
    private String address;
    private String bloodGroup;
    private String rhesusFactor;

    public Patient() {}

    public Patient(int id, String iin, String fullName, LocalDate dateOfBirth, String gender,
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIin() {
        return iin;
    }

    public void setIin(String iin) {
        this.iin = iin;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getRhesusFactor() {
        return rhesusFactor;
    }

    public void setRhesusFactor(String rhesusFactor) {
        this.rhesusFactor = rhesusFactor;
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