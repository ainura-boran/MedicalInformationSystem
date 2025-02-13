package application;

import controllers.AppointmentController;
import data.PostgresDB;
import data.interfaces.IDB;
import models.Appointment;
import models.Doctor;
import models.Patient;
import repositories.AppointmentRepository;
import repositories.DoctorRepository;
import repositories.PatientRepository;
import repositories.interfaces.IAppointmentRepository;
import repositories.interfaces.IDoctorRepository;
import repositories.interfaces.IPatientRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AppointmentApplication {
    public static void main(String[] args) {
        IDB database = new PostgresDB("jdbc:postgresql://localhost:5432", "postgresdb", "12345", "hospital");

        IAppointmentRepository repository = new AppointmentRepository(database);
        IDoctorRepository doctorRepository = new DoctorRepository(database);
        IPatientRepository patientRepository = new PatientRepository(database);
        List<Doctor> doctors = doctorRepository.getAllDoctors();
        List<Patient> patients = patientRepository.getAllPatients();

        AppointmentController controller = new AppointmentController(repository, doctors, patients);

        Scanner scanner = new Scanner(System.in);

        int doctorId = getValidatedId(scanner, "Enter Doctor ID: ");
        int patientId = getValidatedId(scanner, "Enter Patient ID: ");

        System.out.print("Enter Appointment Date and Time (YYYY-MM-DDTHH:MM): ");
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(scanner.nextLine().trim());

            if (dateTime.isBefore(LocalDateTime.now())) {
                System.out.println("The date you entered is in the past. Please enter a future date.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter the date in the format YYYY-MM-DDTHH:MM.");
            return;
        }

        if (controller.scheduleAppointment(doctorId, patientId, dateTime)) {
            System.out.println("Appointment scheduled successfully!");
        } else {
            System.out.println("Failed to schedule appointment. Doctor might not be available.");
        }

        int appointmentId = getValidatedId(scanner, "Enter Appointment ID to view details: ");
        Optional<Appointment> appointment = controller.getAppointmentById(appointmentId);
        appointment.ifPresentOrElse(
                a -> {
                    Doctor doctor = doctorRepository.getDoctorById(a.getDoctorId());
                    Patient patient = patientRepository.getPatientById(a.getPatientId());
                    System.out.println("Appointment Details:");
                    System.out.println("Doctor: " + (doctor != null ? doctor.getFullName() : "Unknown"));
                    System.out.println("Patient: " + (patient != null ? patient.getFullName() : "Unknown"));
                    System.out.println("Date & Time: " + a.getDateTime());
                },
                () -> System.out.println("Appointment not found."));
    }

    private static int getValidatedId(Scanner scanner, String prompt) {
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
}