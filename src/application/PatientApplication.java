package application;

import controllers.AppointmentController;
import controllers.PatientController;
import models.Doctor;
import models.Patient;
import repositories.DoctorRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class PatientApplication {
    private final PatientController controller;
    private final AppointmentController appointmentController;
    private final DoctorRepository doctorRepository;
    private final Scanner scanner = new Scanner(System.in);

    public PatientApplication(PatientController controller, AppointmentController appointmentController, DoctorRepository doctorRepository) {
        this.controller = controller;
        this.appointmentController = appointmentController;
        this.doctorRepository = doctorRepository;
    }

    public void start() {
        while (true) {
            System.out.println("\n--- Patient Management System ---");
            System.out.println("1. Add Patient");
            System.out.println("2. Book an Appointment");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> addPatient();
                case 2 -> bookAppointment();
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addPatient() {
        System.out.println("\n--- Add Patient ---");

        System.out.print("Enter IIN: ");
        String iin = scanner.nextLine();

        System.out.print("Enter Full Name: ");
        String fullName = scanner.nextLine();

        System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
        String dateOfBirth = scanner.nextLine();

        System.out.print("Enter Gender (Male/Female): ");
        String gender = scanner.nextLine();

        System.out.print("Enter Nationality: ");
        String nationality = scanner.nextLine();

        System.out.print("Enter Citizenship: ");
        String citizenship = scanner.nextLine();

        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        System.out.print("Enter Blood Group (e.g., A+, O-): ");
        String bloodGroup = scanner.nextLine();

        System.out.print("Enter Rhesus Factor (+ or -): ");
        String rhesusFactor = scanner.nextLine();

        Patient patient = new Patient(1, iin, fullName, dateOfBirth, gender, nationality, citizenship, address, bloodGroup, rhesusFactor);

        System.out.println(controller.addPatient(patient) ? "Patient added successfully!" : "Failed to add patient.");
    }

    private void bookAppointment() {
        System.out.println("\n--- Book an Appointment ---");
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        scanner.nextLine();

        List<String> specializations = doctorRepository.getAllSpecializations();
        if (specializations.isEmpty()) {
            System.out.println("No specializations available.");
            return;
        }

        System.out.println("\n--- Select Specialization ---");
        specializations.forEach(spec -> System.out.println((specializations.indexOf(spec) + 1) + ". " + spec));

        System.out.print("Enter specialization number: ");
        int specializationChoice = scanner.nextInt();
        scanner.nextLine();

        if (specializationChoice < 1 || specializationChoice > specializations.size()) {
            System.out.println("Invalid choice. Please try again.");
            return;
        }
        String selectedSpecialization = specializations.get(specializationChoice - 1);

        List<Doctor> availableDoctors = doctorRepository.getDoctorsBySpecialization(selectedSpecialization);
        if (availableDoctors.isEmpty()) {
            System.out.println("No doctors available for this specialization.");
            return;
        }

        System.out.println("\n--- Available Doctors ---");
        availableDoctors.forEach(doctor -> System.out.println((availableDoctors.indexOf(doctor) + 1) + ". " +
                doctor.getFullName() + " | Experience: " + doctor.getExperienceYears() + " years" +
                " | Working Hours: " + doctor.getWorkingHours()));

        System.out.print("Enter doctor number: ");
        int doctorChoice = scanner.nextInt();
        scanner.nextLine();

        if (doctorChoice < 1 || doctorChoice > availableDoctors.size()) {
            System.out.println("Invalid choice. Please try again.");
            return;
        }
        Doctor selectedDoctor = availableDoctors.get(doctorChoice - 1);

        System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(scanner.nextLine().trim());

        System.out.println("Doctor's available working hours: " + selectedDoctor.getWorkingHours());
        System.out.print("Enter Appointment Time (HH:MM, 24-hour format): ");
        LocalTime time = LocalTime.parse(scanner.nextLine().trim());

        LocalDateTime appointmentDateTime = LocalDateTime.of(date, time);
        System.out.println(appointmentController.scheduleAppointment(selectedDoctor.getId(), patientId, appointmentDateTime) ?
                "Appointment scheduled successfully!" : "Failed to schedule appointment. Doctor might not be available.");
    }

    public void getPatientById(int id) {
        Patient patient = controller.getPatientById(id);
        System.out.println((patient != null) ? ("Patient ID: " + patient.getId() + "\nFull Name: " + patient.getFullName() +
                "\nIIN: " + patient.getIin() + "\nDate of Birth: " + patient.getDateOfBirth() + "\nGender: " + patient.getGender())
                : "Patient not found.");
    }

    public void listAllPatients() {
        List<Patient> patients = controller.getAllPatients();
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
        } else {
            System.out.println("----- Patients -----");
            patients.forEach(patient -> System.out.println("ID: " + patient.getId() + " | Name: " + patient.getFullName()));
        }
    }
}
