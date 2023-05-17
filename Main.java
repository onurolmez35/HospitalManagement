
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


/*
Nurse, Physician                    --Observer pattern: The Device class is an observable object that notifies its observers (in this case, the Nurse and Physician classes) when the blood pressure of a patient becomes dangerously high.
Patient                             --Factory pattern: The Patient class is a factory for creating patients with different types (CARDIOLOGICAL and GASTROENTEROLOGICAL).
Physician                           --Template pattern: The different procedures (VirtualAngiography, MRI, HemaBloodTest, and GastroBloodTest) are implemented as strategies, which allows the Physician to choose the appropriate procedure depending on the patient's type.
RadiologyDepartment, LabDepartment  --Singleton pattern: The RadiologyDepartment and LabDepartment classes are singletons, meaning that there can only be one instance of each class.
Command pattern: The different procedures (VirtualAngiography, MRI, HemaBloodTest, and GastroBloodTest) could be implemented as command objects, which would allow the Physician to easily keep track of the procedures that have been ordered for each patient. However, this pattern is not implemented in the code provided.
*/


// Abstract class for hospital staff
import java.util.ArrayList;
import java.util.List;

abstract class HospitalStaff {
    private String name;

    public HospitalStaff(String name) {
        this.name = name;
    }

    public void performDailyTasks() {
        measureBloodPressure();
        performMedicalTests();
    }

    public abstract void measureBloodPressure();

    public abstract void performMedicalTests();

    public abstract void notifyDangerousBP(Patient patient);
}
// Physician class
// Physician class
class Physician extends HospitalStaff implements Iterable<Patient> {
    private List<Patient> patients;

    public Physician(String name) {
        super(name);
        this.patients = new ArrayList<>();
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public List<Patient> getPatients() {
        return patients;
    }

    @Override
    public Iterator<Patient> iterator() {
        return new PhysicianIterator(patients);
    }

    @Override
    public void measureBloodPressure() {
        // Physician doesn't measure blood pressure
    }

    @Override
    public void performMedicalTests() {
        /*

        for (Patient patient : this) {
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
     */
    }

    @Override
    public void notifyDangerousBP(Patient patient) {
        System.out.println("Dangerous blood pressure level detected for patient " + patient.getName());
    }

    private static class PhysicianIterator implements Iterator<Patient> {
        private List<Patient> patients;
        private int index;

        public PhysicianIterator(List<Patient> patients) {
            this.patients = patients;
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < patients.size();
        }

        @Override
        public Patient next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return patients.get(index++);
        }
    }
}

// Nurse class
class Nurse extends HospitalStaff {
    private Physician physician;

    public Nurse(String name, Physician physician) {
        super(name);
        this.physician = physician;
    }

    public void measureBloodPressure() {
        for (Patient patient : physician.getPatients()) {
            // Code to measure blood pressure
            if (isDangerousBloodPressure(patient)) {
                physician.notifyDangerousBP(patient);
            }
        }
    }

    private boolean isDangerousBloodPressure(Patient patient) {
        // Code to check if blood pressure is dangerous
        return true;
    }

    @Override
    public void performMedicalTests() {
        // Nurse doesn't perform medical tests
    }

    @Override
    public void notifyDangerousBP(Patient patient) {
        // Nurse doesn't notify dangerous blood pressure
    }
}
class Patient {
    private String name;
    private PatientType type;

    public Patient(String name, PatientType type) {
        this.name = name;
        this.type = type;
    }

    public String toString() {
        return "Patient: " + name + ", Type: " + type;
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

// Product interface

interface MedicalProcedure {
    void perform();
}

// ConcreteProduct classes
class VirtualAngiography implements MedicalProcedure {
    public void perform() {
        System.out.println("Performing virtual angiography");
    }
}

class HemaBloodTest implements MedicalProcedure {
    public void perform() {
        System.out.println("Performing hematology blood test");
    }
}

class MRI implements MedicalProcedure {
    public void perform() {
        System.out.println("Performing MRI");
    }
}

class GastroBloodTest implements MedicalProcedure {
    public void perform() {
        System.out.println("Performing gastroenterology blood test");
    }
}

// Creator interface
interface MedicalProcedureFactory {
    MedicalProcedure createMedicalProcedure();
}

// ConcreteCreator classes
class CardiologyProcedureFactory implements MedicalProcedureFactory {
    public MedicalProcedure createMedicalProcedure() {
        return new VirtualAngiography();
    }
}

class GastroenterologyProcedureFactory implements MedicalProcedureFactory {
    public MedicalProcedure createMedicalProcedure() {
        return new MRI();
    }
}

// Abstract Factory interface
interface DepartmentFactory {
    MedicalProcedureFactory createProcedureFactory();
}

// Concrete Factory classes
class LabDepartmentFactory implements DepartmentFactory {
    public MedicalProcedureFactory createProcedureFactory() {
        return new HemaBloodTestFactory();
    }
}

class RadiologyDepartmentFactory implements DepartmentFactory {
    public MedicalProcedureFactory createProcedureFactory() {
        return new VirtualAngiographyFactory();
    }
}

// Concrete Product Factory classes
class HemaBloodTestFactory implements MedicalProcedureFactory {
    public MedicalProcedure createMedicalProcedure() {
        return new HemaBloodTest();
    }
}

class VirtualAngiographyFactory implements MedicalProcedureFactory {
    public MedicalProcedure createMedicalProcedure() {
        return new VirtualAngiography();
    }
}

// Client class
public class Main {
    private DepartmentFactory cardiologyDepartmentFactory;
    private DepartmentFactory gastroenterologyDepartmentFactory;

    public Main() {
        this.cardiologyDepartmentFactory = new RadiologyDepartmentFactory();
        this.gastroenterologyDepartmentFactory = new LabDepartmentFactory();
    }

    public void performConsultation() {
        // Cardiology patients
        Patient cardiologyPatient = new Patient("John Doe",PatientType.CARDIOLOGICAL);
        MedicalProcedure virtualAngiography = cardiologyDepartmentFactory.createProcedureFactory().createMedicalProcedure();
        MedicalProcedure hemaBloodTest = new LabDepartmentFactory().createProcedureFactory().createMedicalProcedure();
        System.out.println("Consultation for cardiology patients:");
        System.out.println(cardiologyPatient);
        virtualAngiography.perform();
        hemaBloodTest.perform();

        // Gastroenterology patients
        Patient gastroenterologyPatient = new Patient("Jane Doe",PatientType.GASTROENTEROLOGICAL);
        MedicalProcedure mri = gastroenterologyDepartmentFactory.createProcedureFactory().createMedicalProcedure();
        MedicalProcedure gastroBloodTest = new GastroenterologyProcedureFactory().createMedicalProcedure();
        System.out.println("Consultation for gastroenterology patients:");
        System.out.println(gastroenterologyPatient);
        mri.perform();
        gastroBloodTest.perform();
    }

    public static void main(String[] args) {
        Main hospital = new Main();
        hospital.performConsultation();
    }
}
