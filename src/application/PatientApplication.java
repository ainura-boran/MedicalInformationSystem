package application;

import controllers.PatientController;
import models.Patient;

import java.time.LocalDate;
import java.util.Scanner;

public class PatientApplication {
    private final PatientController controller;
    private final Scanner scanner = new Scanner(System.in);

    public PatientApplication(PatientController controller) {
        this.controller = controller;
    }

    public void start() {
        while (true) {
            System.out.println("\n--- Patient Management System ---");
            System.out.println("1. Add Patient");
            System.out.println("2. Get Patient by ID");
            System.out.println("3. List All Patients");
            System.out.println("4. Appointment");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> addPatient();
                case 2 -> getPatientById();
                case 3 -> listAllPatients();
                //case 4 -> makeAppointment();
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
        int iin = scanner.nextInt();

        System.out.print("Enter Full Name: ");
        String fullName = scanner.next();

        System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
        String dateOfBirth = scanner.next();

        System.out.print("Enter Gender (Male/Female): ");
        String gender = scanner.next();

        System.out.print("Enter Nationality: ");
        String nationality = scanner.next();

        System.out.print("Enter Citizenship: ");
        String citizenship = scanner.next();

        System.out.print("Enter Address: ");
        String address = scanner.next();

        System.out.print("Enter Blood Group (e.g., A+, O-): ");
        String bloodGroup = scanner.next();

        System.out.print("Enter Rhesus Factor (+ or -): ");
        String rhesusFactor = scanner.next();

        Patient patient = new Patient(1, iin, fullName, dateOfBirth, gender, nationality, citizenship, address, bloodGroup, rhesusFactor);

        boolean success = controller.addPatient(patient);
        System.out.println(success ? "Patient added successfully!" : "Failed to add patient.");
    }

    private void getPatientById() {
        System.out.println("\n--- Get Patient by ID ---");
        System.out.print("Enter Patient ID: ");
        int ids = scanner.nextInt();
        scanner.nextLine();

        Patient response = controller.getPatientById(ids);
        if(response != null){
            System.out.println("Patient with this id:");
            System.out.println("ID: " + response.getId());
            System.out.println("IIN: " + response.getIin());
            System.out.println("Full Name: " + response.getFullName());
            System.out.println("Date of Birth: " + response.getDateOfBirth());
            System.out.println("Gender: " + response.getGender());
            System.out.println("Nationality: " + response.getNationality());
            System.out.println("Citizenship: " + response.getCitizenship());
            System.out.println("Address: " + response.getAddress());
            System.out.println("Blood Group: " + response.getBloodGroup());
            System.out.println("Rhesus Factor: " + response.getRhesusFactor());
        } else{
            System.out.println("Patient with id " + ids + " is not founded");
        }

    }

    private void listAllPatients() {
        System.out.println("\n--- List of Patients ---");
        controller.getAllPatients().forEach(System.out::println);
    }
}