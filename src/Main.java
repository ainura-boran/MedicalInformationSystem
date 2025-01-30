import application.DoctorApplication;
import application.PatientApplication;
import controllers.AppointmentController;
import controllers.DoctorController;
import controllers.PatientController;
import data.PostgresDB;
import models.Appointment;
import models.Doctor;
import models.Patient;
import repositories.AppointmentRepository;
import repositories.DoctorRepository;
import repositories.PatientRepository;
import repositories.interfaces.IAppointmentRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("The Medical System manages patient records, doctor profiles, and appointment scheduling.");
        System.out.println("It enables:");
        System.out.println("Patients to register and book appointments.");
        System.out.println("Doctors to manage their schedules and patient lists.");
        System.out.println("Admins to view all data.");
        System.out.println();

        PostgresDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "0000", "medicalInformationSystem");

        DoctorRepository doctorRepository = new DoctorRepository(db);
        DoctorController doctorController = new DoctorController(doctorRepository);
        DoctorApplication app = new DoctorApplication(doctorController);

        PatientRepository patientRepository = new PatientRepository(db);
        PatientController patientController = new PatientController(patientRepository);
        PatientApplication patientApplication = new PatientApplication(patientController);

        AppointmentRepository appointmentRepository = new AppointmentRepository(db);
        AppointmentController appointmentController = new AppointmentController(appointmentRepository, doctorRepository, patientRepository);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("-----Who are you?-----");
            System.out.println("1. Doctor");
            System.out.println("2. Patient");
            System.out.println("3. Schedule Appointment");
            System.out.println("4. View Appointment by ID");
            System.out.println("0. Exit");
            try {
                int option = scanner.nextInt();
                switch (option) {
                    case 1: app.start(); break;
                    case 2: patientApplication.start(); break;
                    case 3:
                        System.out.print("Enter Doctor ID: ");
                        int doctorId = scanner.nextInt();
                        System.out.print("Enter Patient ID: ");
                        int patientId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Appointment Date and Time (YYYY-MM-DDTHH:MM): ");
                        LocalDateTime dateTime = LocalDateTime.parse(scanner.nextLine());

                        if (appointmentController.scheduleAppointment(doctorId, patientId, dateTime)) {
                            System.out.println("Appointment scheduled successfully!");
                        } else {
                            System.out.println("Failed to schedule appointment. Doctor might not be available.");
                        }
                        break;
                    case 4:
                        System.out.print("Enter Appointment ID to view details: ");
                        int appointmentId = scanner.nextInt();
                        Optional<Appointment> appointment = appointmentController.getAppointmentById(appointmentId);
                        appointment.ifPresentOrElse(
                                a -> {
                                    Doctor doctor = doctorRepository.getDoctorById(a.getDoctorId());
                                    Patient patient = patientRepository.getPatientById(a.getPatientId());
                                    System.out.println("Appointment Details:");
                                    System.out.println("Doctor: " + (doctor != null ? doctor.getFullName() : "Unknown"));
                                    System.out.println("Patient: " + (patient != null ? patient.getFullName() : "Unknown"));
                                    System.out.println("Date & Time: " + a.getDateTime());
                                },
                                () -> System.out.println("Appointment not found.")
                        );
                        break;
                    case 0: System.exit(0);
                    default: return;
                }
            }catch(Exception e){
                System.out.println("Invalid option. Try again.");
            }
        }
    }

}