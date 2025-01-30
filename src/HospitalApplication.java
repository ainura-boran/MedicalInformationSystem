import application.DoctorApplication;
import application.PatientApplication;
import controllers.DoctorController;
import controllers.PatientController;
import data.PostgresDB;
import repositories.DoctorRepository;
import repositories.PatientRepository;

import java.util.Scanner;

public class HospitalApplication {
        PostgresDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "12345", "hospital");

        DoctorRepository doctorRepository = new DoctorRepository(db);
        DoctorController doctorController = new DoctorController(doctorRepository);
        DoctorApplication app = new DoctorApplication(doctorController);

        PatientRepository patientRepository = new PatientRepository(db);
        PatientController patientController = new PatientController(patientRepository);
        PatientApplication patientApplication = new PatientApplication(patientController);


        Scanner scanner = new Scanner(System.in);
        public void start() {
            while (true) {
                System.out.println("-----Who are you?-----");
                System.out.println("1. Doctor");
                System.out.println("2. Patient");
                System.out.println("0. Exit");
                try {
                    int option = scanner.nextInt();
                    switch (option) {
                        case 1:
                            app.start();
                            break;
                        case 2:
                            patientApplication.start();
                            break;
                        case 0:
                            System.exit(0);
                        default:
                            return;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid option. Try again.");
                }
            }
        }


}