import application.DoctorApplication;
import controllers.DoctorController;
import data.PostgresDB;
import repositories.DoctorRepository;

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

        app.start();
    }
}