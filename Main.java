
import java.util.ArrayList;
import java.util.List;


/*
 Observer pattern: The Device class is an observable object that notifies its observers (in this case, the Nurse and Physician classes) when the blood pressure of a patient becomes dangerously high.
Factory pattern: The Patient class is a factory for creating patients with different types (CARDIOLOGICAL and GASTROENTEROLOGICAL).
DEGISECEK--Strategy pattern: The different procedures (VirtualAngiography, MRI, HemaBloodTest, and GastroBloodTest) are implemented as strategies, which allows the Physician to choose the appropriate procedure depending on the patient's type.
Singleton pattern: The RadiologyDepartment and LabDepartment classes are singletons, meaning that there can only be one instance of each class.
Command pattern: The different procedures (VirtualAngiography, MRI, HemaBloodTest, and GastroBloodTest) could be implemented as command objects, which would allow the Physician to easily keep track of the procedures that have been ordered for each patient. However, this pattern is not implemented in the code provided.

*/


// Abstract class for hospital staff
abstract class HospitalStaff {
    protected String name;

    public HospitalStaff(String name) {
        this.name = name;
    }

    public abstract void performDailyTasks();

    public abstract void notifyDangerousBP(Patient patient);
}

// Physician class
class Physician extends HospitalStaff {
    private List<Patient> patients;

    public Physician(String name) {
        super(name);
        this.patients = new ArrayList<>();
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    @Override
    public void performDailyTasks() {
        for (Patient patient : patients) {
            if (patient.getType() == PatientType.CARDIOLOGICAL) {
                RadiologyDepartment radiology = RadiologyDepartment.getInstance();
                radiology.performVirtualAngiography(patient);
                LabDepartment lab = LabDepartment.getInstance();
                lab.performHemaBloodTest(patient);
            } else if (patient.getType() == PatientType.GASTROENTEROLOGICAL) {
                RadiologyDepartment radiology = RadiologyDepartment.getInstance();
                radiology.performMRI(patient);
                LabDepartment lab = LabDepartment.getInstance();
                lab.performGastroBloodTest(patient);
            }
        }
    }

    @Override
    public void notifyDangerousBP(Patient patient) {

    }
}

// Nurse class
class Nurse extends HospitalStaff {
    private Physician physician;

    public Nurse(String name, Physician physician) {
        super(name);
        this.physician = physician;
    }

    public void notifyDangerousBP(Patient patient) {
        System.out.println("Dangerous blood pressure level detected for patient " + patient.getName());
        measureBloodPressure(patient);
    }

    public void measureBloodPressure(Patient patient) {
        // Code to measure blood pressure
        if (isDangerousBloodPressure(patient)) {
            physician.notifyDangerousBP(patient);
        }
    }

    private boolean isDangerousBloodPressure(Patient patient) {
        // Code to check if blood pressure is dangerous
        return true;
    }

    @Override
    public void performDailyTasks() {
        // Nurse doesn't have any daily tasks
    }
}

// Patient class
class Patient {
    private String name;
    private PatientType type;

    public Patient(String name, PatientType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public PatientType getType() {
        return type;
    }
}

enum PatientType {
    CARDIOLOGICAL,
    GASTROENTEROLOGICAL
}

// Radiology department singleton class
class RadiologyDepartment {
    private static RadiologyDepartment instance = null;

    private RadiologyDepartment() {
    }

    public static RadiologyDepartment getInstance() {
        if (instance == null) {
            instance = new RadiologyDepartment();
        }
        return instance;
    }

    public void performVirtualAngiography(Patient patient) {
        // Code to perform virtual angiography
        System.out.println("Virtual angiography performed for patient " + patient.getName());
    }

    public void performMRI(Patient patient) {
        // Code to perform MRI
        System.out.println("MRI performed for patient " + patient.getName());
    }
}

// Lab department singleton class
class LabDepartment {
    private static LabDepartment instance = null;

    private LabDepartment() {
    }

    public static LabDepartment getInstance() {
        if (instance == null) {
            instance = new LabDepartment();
        }
        return instance;
    }

    public void performHemaBloodTest(Patient patient) {
        // Code to perform hematology blood test
        System.out.println("Hema blood test performed for patient " + patient.getName());
    }

    public void performGastroBloodTest(Patient patient) {
        // Code to perform gastroenterology blood test
        System.out.println("Gastro blood test performed for patient " + patient.getName());
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        // Create physician and patients
        Physician physician = new Physician("Dr. Smith");
        Patient patient1 = new Patient("John Doe", PatientType.CARDIOLOGICAL);
        Patient patient2 = new Patient("Jane Smith", PatientType.GASTROENTEROLOGICAL);
        physician.addPatient(patient1);
        physician.addPatient(patient2);

        // Create nurse and assign to physician
        Nurse nurse = new Nurse("Nurse Johnson", physician);
        physician.performDailyTasks();
    }
}

