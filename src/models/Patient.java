package models;

public class Patient {

    private int id;
    private String iin;
    private String name;
    private String dateOfBirth;
    private String gender;
    private String nationality;
    private String citizenship;
    private String address;
    private String bloodGroup;
    private String rhesusFactor;
    private String contactInfo;
    //Getters and Setters

    public Patient(){

    }
    public Patient(int id, String iin, String name, String dateOfBirth, String gender, String nationality, String citizenship, String address, String bloodGroup, String rhesusFactor, String contactInfo) {
        setId(id);
        setIin(iin);
        setName(name);
        setDateOfBirth(dateOfBirth);
        setGender(gender);
        setNationality(nationality);
        setCitizenship(citizenship);
        setAddress(address);
        setBloodGroup(bloodGroup);
        setRhesusFactor(rhesusFactor);
        setContactInfo(contactInfo);
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
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
    public String getContactInfo() {
        return contactInfo;
    }
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id: " + id +
                ", iin: '" + iin + '\'' +
                " name: '" + name+ '\'' +
                ", dateOfBirth: " + dateOfBirth + '\''+
                ", gender: " + gender + '\'' +
                ", nationality: " + nationality + '\'' +
                ", citizensjip: " + citizenship + '\'' +
                ", bloodGroup: " + bloodGroup + '\'' +
                ", rhesusFactor: " + rhesusFactor + '\'' +
                ", contactInfo: " + contactInfo + '\'' +
                '}';
    }


}