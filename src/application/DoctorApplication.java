package application;

import controllers.DoctorController;
import models.Doctor;

import java.util.Scanner;

public class DoctorApplication {
    private final DoctorController doctorController;
    private final Scanner scanner = new Scanner(System.in);

    public DoctorApplication(DoctorController doctorController) {
        this.doctorController = doctorController;
    }


    public void start() {
        while (true) {
            System.out.println("\n--- Doctor Management System ---");
            System.out.println("1. Add Doctor");
            System.out.println("2. Get Doctor by ID");
            System.out.println("3. List All Doctors");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> addDoctor();
                case 2 -> getDoctorById();
                case 3 -> listAllDoctors();
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void addDoctor() {
        System.out.println("\n--- Add Doctor ---");
        System.out.print("Enter Full Name: ");
        String fullName = scanner.nextLine();

        System.out.print("Enter Specialization: ");
        String specialization = scanner.nextLine();

        System.out.print("Enter Working Hours: ");
        String workingHours = scanner.nextLine();

        System.out.print("Enter Office: ");
        String office = scanner.nextLine();

        System.out.print("Enter Years of Experience: ");
        int experienceYears = scanner.nextInt();
        scanner.nextLine();

        Doctor doctor = new Doctor(1, fullName, specialization, workingHours, office, experienceYears);

        boolean success = doctorController.createDoctor(doctor);
        System.out.println(success ? "Doctor added successfully!" : "Failed to add doctor.");
    }

    private void getDoctorById() {
        System.out.println("\n--- Get Doctor by ID ---");
        System.out.print("Enter Doctor ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Doctor doctor = doctorController.getDoctorById(id);
        if (doctor != null) {
            System.out.println("Doctor Found:");
            System.out.println("ID: " + doctor.getId());
            System.out.println("Full Name: " + doctor.getFullName());
            System.out.println("Specialization: " + doctor.getSpecialization());
            System.out.println("Working Hours: " + doctor.getWorkingHours());
            System.out.println("Office: " + doctor.getOffice());
            System.out.println("Experience: " + doctor.getExperienceYears() + " years");
        } else {
            System.out.println("Doctor with ID " + id + " not found.");
        }
    }

    private void listAllDoctors() {
        System.out.println("\n--- List All Doctors ---");
        doctorController.getAllDoctors().forEach(System.out::println);
    }
}