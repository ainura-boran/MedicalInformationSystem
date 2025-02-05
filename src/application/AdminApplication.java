package application;

import controllers.AppointmentController;
import controllers.PatientController;
import models.Admin;
import models.Doctor;
import models.Patient;
import repositories.AdminRepository;
import repositories.DoctorRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AdminApplication {
    private final DoctorApplication doctorApplication;
    private final PatientApplication patientApplication;
    private final AppointmentController appointmentController;
    private final AdminRepository adminRepository;

    public AdminApplication(DoctorApplication doctorApplication, PatientApplication patientApplication,
                            AppointmentController appointmentController, AdminRepository adminRepository) {
        this.doctorApplication = doctorApplication;
        this.patientApplication = patientApplication;
        this.appointmentController = appointmentController;
        this.adminRepository = adminRepository;
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
                    appointmentController.viewAllAppointments();
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

    private static void registerAdmin(Scanner scanner, AdminRepository adminRepository) {
        System.out.print("Enter new admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter new admin password: ");
        String password = scanner.nextLine();

        if (adminRepository.registerAdmin(username, password)) {
            System.out.println("Admin registered successfully.");
        } else {
            System.out.println("Error registering admin.");
        }
    }

    private static void listAllPatients(PatientController patientController) {
        System.out.println("\n--- List of Patients ---");
        patientController.getAllPatients().forEach(System.out::println);
    }

    private static void getPatientById(int id, PatientController patientController) {
        Patient response = patientController.getPatientById(id);
        if (response != null) {
            System.out.println("Patient Details:");
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
        } else {
            System.out.println("Patient with ID " + id + " is not found.");
        }
    }

    public void handleAdminActions(Scanner scanner, AppointmentController appointmentController,
                                   DoctorRepository doctorRepository, PatientController patientController) {
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

            try {
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        System.out.print("Enter Date to Check Schedules (YYYY-MM-DD): ");
                        String dateInput = scanner.nextLine().trim();
                        LocalDate date = LocalDate.parse(dateInput);
                        List<Doctor> doctors = doctorRepository.getAllDoctors();
                        for (Doctor doctor : doctors) {
                            int count = appointmentController.getAppointmentsCountForDoctorOnDate(doctor.getId(), date.atStartOfDay());
                            System.out.println("Doctor: " + doctor.getFullName() + " - Appointments: " + count);
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
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine();
            }
        }
    }


    private void loginAdmin(Scanner scanner, AdminRepository adminRepository,
                                   AppointmentController appointmentController,
                                   DoctorRepository doctorRepository,
                                   PatientController patientController) {
        System.out.print("Enter admin username: ");
        String adminUsername = scanner.nextLine();
        System.out.print("Enter password: ");
        String adminPassword = scanner.nextLine();

        Admin admin = adminRepository.authenticateAdmin(adminUsername, adminPassword);
        if (admin != null) {
            System.out.println("Welcome, Admin.");
            AdminApplication adminApp = new AdminApplication(doctorApplication, patientApplication,
                     appointmentController, adminRepository);
            adminApp.handleAdminActions(scanner, appointmentController, doctorRepository, patientController);
        } else {
            System.out.println("Invalid username or password.");
        }
    }
}
