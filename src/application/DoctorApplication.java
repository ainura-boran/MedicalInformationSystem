package application;

import controllers.AppointmentController;
import controllers.DoctorController;
import models.Appointment;
import models.Doctor;
import models.Patient;
import repositories.AppointmentRepository;
import repositories.DoctorRepository;
import repositories.PatientRepository;

import java.util.List;
import java.util.Scanner;

public class DoctorApplication {
    private final DoctorController doctorController;
    private final Scanner scanner = new Scanner(System.in);;
    private final AppointmentController appointmentController;
    private final PatientRepository patientRepository;


    public DoctorApplication(DoctorController doctorController, AppointmentController appointmentController, PatientRepository patientRepository) {
        this.doctorController = doctorController;
        this.appointmentController = appointmentController;
        this.patientRepository = patientRepository;
    }

    public void start(Scanner scanner) {
        System.out.print("Enter doctor username: ");
        String doctorUsername = scanner.nextLine();
        System.out.print("Enter password: ");
        String doctorPassword = scanner.nextLine();

        Doctor doctor = doctorController.authenticateDoctor(doctorUsername, doctorPassword);
        if (doctor != null) {
            System.out.println("Welcome, Dr. " + doctor.getFullName());
            doctorMenu(scanner, doctor.getId());
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private void doctorMenu(Scanner scanner, int doctorId) {
        while (true) {
            System.out.println("-----Doctor Menu-----");
            System.out.println("1. View Appointments");
            System.out.println("2. View Patient List");
            System.out.println("0. Logout");
            System.out.print("Enter your choice: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    appointmentController.viewAppointmentsForDoctor(doctorId);
                    break;
                case 2:
                    patientRepository.getPatientsForDoctor(doctorId).forEach(System.out::println);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    public void registerDoctor(Scanner scanner) {
        System.out.print("Enter Doctor's Full Name: ");
        String fullName = scanner.nextLine();

        System.out.print("Enter Specialization: ");
        String specialization = scanner.nextLine();

        System.out.print("Enter Working Hours: ");
        String workingHours = scanner.nextLine();

        System.out.print("Enter Office: ");
        String office = scanner.nextLine();

        System.out.print("Enter Experience Years: ");
        int experienceYears = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        boolean success = doctorController.registerDoctor(fullName, specialization, workingHours, office, experienceYears, username, password);

        if (success) {
            System.out.println("Doctor registered successfully!");
        } else {
            System.out.println("Failed to register doctor.");
        }
    }

    public void getDoctorById(int id) {
        Doctor doctor = doctorController.getDoctorById(id);
        if (doctor != null) {
            System.out.println("Doctor ID: " + doctor.getId());
            System.out.println("Full Name: " + doctor.getFullName());
            System.out.println("Specialization: " + doctor.getSpecialization());
        } else {
            System.out.println("Doctor not found.");
        }
    }

    public void listAllDoctors() {
        List<Doctor> doctors = doctorController.getAllDoctors();
        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
        } else {
            System.out.println("\n--- List of Doctors ---");
            for (Doctor doctor : doctors) {
                System.out.println("ID: " + doctor.getId() +
                        " | Name: " + doctor.getFullName() +
                        " | Specialization: " + doctor.getSpecialization() +
                        " | Experience: " + doctor.getExperienceYears() + " years" +
                        " | Working Hours: " + doctor.getWorkingHours() +
                        " | Office: " + doctor.getOffice());
            }
        }
    }
}