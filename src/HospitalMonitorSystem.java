import java.util.ArrayList;
import java.util.List;

// Facade Pattern - HospitalMonitorSystem class
class HospitalMonitorSystem {
    private HospitalStaff physician;
    private HospitalStaff nurse;
    private List<Patient> patients;

    public HospitalMonitorSystem() {
        physician = Physician.getInstance("Dr. Smith");
        nurse = Nurse.getInstance("Nurse Johnson");
        patients = new ArrayList<>();
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void monitorPatients() {
        for (Patient patient : patients) {
            physician.performDuties(patient);
            nurse.performDuties(patient);
        }
    }
}