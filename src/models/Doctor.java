package models;

public class Doctor {
    private int id;
    private String fullName;
    private String specialization;
    private String workHours;
    private String officeNumber;
    private int experienceYears;
    private String contactInfo;
    //Getters and Setters
    public Doctor(){

    }

    public Doctor(int id, String fullName, String specialization, String workHours, String officeNumber, int experienceYears, String contactInfo) {
        setId(id);
        setFullName(fullName);
        setSpecialization(specialization);
        setWorkHours(workHours);
        setOfficeNumber(officeNumber);
        setExperienceYears(experienceYears);
        setContactInfo(contactInfo);
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
    public String getWorkHours() {
        return workHours;
    }
    public void setWorkHours(String workHours) {
        this.workHours = workHours;
    }
    public String getOfficeNumber() {
        return officeNumber;
    }
    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }
    public int getExperienceYears() {
        return experienceYears;
    }
    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
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
                ", name: '" + fullName + '\'' +
                 " specialization: '" + specialization+ '\'' +
                ", workhours: " + workHours + '\''+
                ", officeNumber: " + officeNumber + '\'' +
                ", experienceYears: " + experienceYears + '\'' +
                ", contactInfo: " + contactInfo + '\'' +
                '}';
    }
}