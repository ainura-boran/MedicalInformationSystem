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

public class HospitalApplication {
    PostgresDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "0000", "medicalInformationSystem");


    DoctorRepository doctorRepository = new DoctorRepository(db);
    PatientRepository patientRepository = new PatientRepository(db);
    AppointmentRepository appointmentRepository = new AppointmentRepository(db);
    AdminRepository adminRepository = new AdminRepository(db);

    DoctorController doctorController = new DoctorController(doctorRepository);
    PatientController patientController = new PatientController(patientRepository);
    AppointmentController appointmentController = new AppointmentController(appointmentRepository, doctorRepository.getAllDoctors(), patientRepository.getAllPatients());

    DoctorApplication doctorApplication = new DoctorApplication(doctorController, appointmentController, patientRepository);
    PatientApplication patientApplication = new PatientApplication(patientController, appointmentController, doctorRepository);
    AdminApplication adminApplication = new AdminApplication(doctorApplication, patientApplication, appointmentController, adminRepository, appointmentRepository);
    Scanner scanner = new Scanner(System.in);


    public void start() {
        while (true) {
            mainmenu();
            try {
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        doctorApplication.start(scanner);
                        break;
                    case 2:
                        patientApplication.start();
                        break;
                    case 3:
                        adminApplication.start(scanner);
                        break;
                    case 0:
                        System.out.println("Exiting...");
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


        private void mainmenu () {
            System.out.println("-----Who are you?-----");
            System.out.println("1. Doctor");
            System.out.println("2. Patient");
            System.out.println("3. Administrator");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
        }
    }





