package application;

import controllers.AppointmentController;
import repositories.AdminRepository;
import repositories.AppointmentRepository;

import java.util.List;
import java.util.Scanner;

public class AdminApplication {
    private final DoctorApplication doctorApplication;
    private final PatientApplication patientApplication;
    private final AppointmentController appointmentController;
    private final AdminRepository adminRepository;
    private final AppointmentRepository appointmentRepository;

    public AdminApplication(DoctorApplication doctorApplication, PatientApplication patientApplication,
                            AppointmentController appointmentController, AdminRepository adminRepository, AppointmentRepository appointmentRepository) {
        this.doctorApplication = doctorApplication;
        this.patientApplication = patientApplication;
        this.appointmentController = appointmentController;
        this.adminRepository = adminRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public void start(Scanner scanner) {
        System.out.print("Enter admin username: ");
        String adminUsername = scanner.nextLine();
        System.out.print("Enter password: ");
        String adminPassword = scanner.nextLine();

        if (adminRepository.authenticateAdmin(adminUsername, adminPassword) != null) {
            System.out.println("Welcome, Admin.");
            adminMenu(scanner);
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private void adminMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n----- Administrator Menu -----");
            System.out.println("1. View Doctor Schedules");
            System.out.println("2. Register New Doctor");
            System.out.println("3. Get Patient by ID");
            System.out.println("4. List All Patients");
            System.out.println("5. Get Doctor By ID");
            System.out.println("6. List All Doctors");
            System.out.println("0. Go Back");
            System.out.print("Enter your choice: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    List<String> schedules = appointmentRepository.getDoctorSchedules();
                    if (schedules.isEmpty()) {
                        System.out.println("No doctor schedules available.");
                    } else {
                        schedules.forEach(System.out::println);
                    }
                    break;

                case 2:
                    doctorApplication.registerDoctor(scanner);
                    break;
                case 3:
                    System.out.print("Enter Patient ID: ");
                    int patientId = scanner.nextInt();
                    scanner.nextLine();
                    patientApplication.getPatientById(patientId);
                    break;
                case 4:
                    patientApplication.listAllPatients();
                    break;
                case 5:
                    System.out.print("Enter Doctor ID: ");
                    int doctorId = scanner.nextInt();
                    scanner.nextLine();
                    doctorApplication.getDoctorById(doctorId);
                    break;
                case 6:
                    doctorApplication.listAllDoctors();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}