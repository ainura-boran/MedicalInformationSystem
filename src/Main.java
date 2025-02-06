import application.AdminApplication;
import application.DoctorApplication;
import application.PatientApplication;
import controllers.AppointmentController;
import controllers.DoctorController;
import controllers.PatientController;
import data.PostgresDB;
import repositories.AdminRepository;
import repositories.AppointmentRepository;
import repositories.DoctorRepository;
import repositories.PatientRepository;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("The Medical System manages patient records, doctor profiles, and appointment scheduling.");
        System.out.println("It enables:");
        System.out.println("Patients to register and book appointments.");
        System.out.println("Doctors to manage their schedules and patient lists.");
        System.out.println("Admins to view all data.");
        System.out.println();

        PostgresDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "12345", "hospital");


        Scanner scanner = new Scanner(System.in);
        HospitalApplication app = new HospitalApplication();
        app.start();
        db.close();

    }
}