package models;

public class Patient extends User {
    private String iin;
    private String dateOfBirth;
    private String gender;
    private String nationality;
    private String citizenship;
    private String address;
    private String bloodGroup;
    private String rhesusFactor;

    public Patient(int id, String fullName, String username, String passwordHash,
                   String iin, String dateOfBirth, String gender, String nationality,
                   String citizenship, String address, String bloodGroup, String rhesusFactor) {
        super(id, fullName, username, passwordHash);
        this.iin = iin;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.nationality = nationality;
        this.citizenship = citizenship;
        this.address = address;
        this.bloodGroup = bloodGroup;
        this.rhesusFactor = rhesusFactor;
    }

    @Override
    public String getRole() {
        return "Patient";
    }

    public String getIin() {
        return iin;
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
        return super.toString() + " { IIN: " + iin + ", Date of Birth: " + dateOfBirth + " }";
    }
}
