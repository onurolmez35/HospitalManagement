public class Main {
    public static void main(String[] args) {
        // Creating patients
        Patient patient1 = new CardiologyPatient("John Doe");
        Patient patient2 = new GastroenterologyPatient("Jane Smith");

        // Creating hospital monitor system
        HospitalMonitorSystem monitorSystem = new HospitalMonitorSystem();
        monitorSystem.addPatient(patient1);
        monitorSystem.addPatient(patient2);

        // Monitoring patients
        monitorSystem.monitorPatients();
    }
}
