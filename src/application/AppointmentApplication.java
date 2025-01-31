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
        IDB database = new PostgresDB("jdbc:postgresql://localhost:5432", "postgresdb", "0000", "medicalInformationSystem");

        IAppointmentRepository repository = new AppointmentRepository(database);
        IDoctorRepository doctorRepository = new DoctorRepository(database);
        IPatientRepository patientRepository = new PatientRepository(database);
        List<Doctor> doctors = doctorRepository.getAllDoctors();
        List<Patient> patients = patientRepository.getAllPatients();

        AppointmentController controller = new AppointmentController(repository, doctors, patients);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Doctor ID: ");
        int doctorId = scanner.nextInt();
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Appointment Date and Time (YYYY-MM-DDTHH:MM): ");
        LocalDateTime dateTime = LocalDateTime.parse(scanner.nextLine());

        if (controller.scheduleAppointment(doctorId, patientId, dateTime)) {
            System.out.println("Appointment scheduled successfully!");
        } else {
            System.out.println("Failed to schedule appointment. Doctor might not be available.");
        }


        System.out.print("Enter Appointment ID to view details: ");
        int appointmentId = scanner.nextInt();
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
}