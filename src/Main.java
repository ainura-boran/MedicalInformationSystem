import data.PostgresDB;

public class Main {
    public static void main(String[] args) {
        System.out.println("The Medical System manages patient records, doctor profiles, and appointment scheduling.");
        System.out.println("It enables:");
        System.out.println("Patients to register and book appointments.");
        System.out.println("Doctors to manage their schedules and patient lists.");
        System.out.println("Admins to view all data.");
        System.out.println();
        PostgresDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "12345", "hospital");
        HospitalApplication app = new HospitalApplication();
        app.start();
        db.close();

//
//        DoctorRepository doctorRepository = new DoctorRepository(db);
//        DoctorController doctorController = new DoctorController(doctorRepository);
//        DoctorApplication app = new DoctorApplication(doctorController);
//
//        PatientRepository patientRepository = new PatientRepository(db);
//        PatientController patientController = new PatientController(patientRepository);
//        PatientApplication patientApplication = new PatientApplication(patientController);
//
//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            System.out.println("-----Who are you?-----");
//            System.out.println("1. Doctor");
//            System.out.println("2. Patient");
//            System.out.println("0. Exit");
//            try {
//                int option = scanner.nextInt();
//                switch (option) {
//                    case 1: app.start(); break;
//                    case 2: patientApplication.start(); break;
//                    case 0: System.exit(0);
//                    default: return;
//                }
//            }catch(Exception e){
//                System.out.println("Invalid option. Try again.");
//            }
//        }


    }

}