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

        boolean success = controller.addPatient(patient);
        System.out.println(success ? "Patient added successfully!" : "Failed to add patient.");
    }

    private void bookAppointment() {
        System.out.println("\n--- Book an Appointment ---");

        int patientId = getValidatedId("Enter Patient ID: ");

        List<String> specializations = doctorRepository.getAllSpecializations();
        if (specializations.isEmpty()) {
            System.out.println("No specializations available.");
            return;
        }

        System.out.println("\n--- Select Specialization ---");
        for (int i = 0; i < specializations.size(); i++) {
            System.out.println((i + 1) + ". " + specializations.get(i));
        }

        int specializationChoice = getValidatedChoice("Enter specialization number: ", 1, specializations.size());
        String selectedSpecialization = specializations.get(specializationChoice - 1);

        List<Doctor> availableDoctors = doctorRepository.getDoctorsBySpecialization(selectedSpecialization);
        if (availableDoctors.isEmpty()) {
            System.out.println("No doctors available for this specialization.");
            return;
        }

        System.out.println("\n--- Available Doctors ---");
        for (int i = 0; i < availableDoctors.size(); i++) {
            Doctor doctor = availableDoctors.get(i);
            System.out.println((i + 1) + ". " + doctor.getFullName() +
                    " | Experience: " + doctor.getExperienceYears() + " years" +
                    " | Working Hours: " + doctor.getWorkingHours());
        }

        int doctorChoice = getValidatedChoice("Enter doctor number: ", 1, availableDoctors.size());
        Doctor selectedDoctor = availableDoctors.get(doctorChoice - 1);

        LocalDate date;
        while (true) {
            try {
                System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
                date = LocalDate.parse(scanner.nextLine().trim());

                if (date.isBefore(LocalDate.now())) {
                    System.out.println("The date you entered is in the past. Please enter a future date.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter the date in the format YYYY-MM-DD.");
            }
        }

        System.out.println("Doctor's available working hours: " + selectedDoctor.getWorkingHours());

        LocalTime time;
        while (true) {
            try {
                System.out.print("Enter Appointment Time (HH:MM, 24-hour format): ");
                time = LocalTime.parse(scanner.nextLine().trim());
                break;
            } catch (Exception e) {
                System.out.println("Invalid time format. Please enter the time in the format HH:MM.");
            }
        }

        LocalDateTime appointmentDateTime = LocalDateTime.of(date, time);
        boolean success = appointmentController.scheduleAppointment(selectedDoctor.getId(), patientId, appointmentDateTime);

        System.out.println(success ? "Appointment scheduled successfully!" : "Failed to schedule appointment. Doctor might not be available.");
    }

    public void getPatientById(int id) {
        Patient patient = controller.getPatientById(id);
        if (patient != null) {
            System.out.println("Patient ID: " + patient.getId());
            System.out.println("Full Name: " + patient.getFullName());
            System.out.println("IIN: " + patient.getIin());
            System.out.println("Date of Birth: " + patient.getDateOfBirth());
            System.out.println("Gender: " + patient.getGender());
        } else {
            System.out.println("Patient not found.");
        }
    }

    public void listAllPatients() {
        List<Patient> patients = controller.getAllPatients();
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
        } else {
            System.out.println("----- Patients -----");
            for (Patient patient : patients) {
                System.out.println("ID: " + patient.getId() + " | Name: " + patient.getFullName());
            }
        }
    }

    private int getValidatedId(String prompt) {
        int id;
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                id = Integer.parseInt(input);
                if (id <= 0) {
                    System.out.println("ID must be a positive number. Please try again.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric ID.");
            }
        }
        return id;
    }

    private int getValidatedChoice(String prompt, int min, int max) {
        int choice;
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                choice = Integer.parseInt(input);
                if (choice < min || choice > max) {
                    System.out.println("Invalid choice. Please select a number between " + min + " and " + max + ".");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }
        return choice;
    }

}