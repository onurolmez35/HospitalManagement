// Subclass - Nurse (Singleton)
class Nurse extends HospitalStaff {
    private static Nurse instance;

    private Nurse(String name) {
        super(name);
    }

    public static Nurse getInstance(String name) {
        if (instance == null) {
            instance = new Nurse(name);
        }
        return instance;
    }

    @Override
    public void performDuties(Patient patient) {
        System.out.println("Nurse applying procedures for patient: " + patient.getName());
        // Apply procedures based on patient's problem type
        if (patient instanceof CardiologyPatient) {
            applyCardiologyProcedures((CardiologyPatient) patient);
        } else if (patient instanceof GastroenterologyPatient) {
            applyGastroenterologyProcedures((GastroenterologyPatient) patient);
        }
    }

    private void applyCardiologyProcedures(CardiologyPatient patient) {
        System.out.println("Virtual angiography applied for patient: " + patient.getName());
    }

    private void applyGastroenterologyProcedures(GastroenterologyPatient patient) {
        System.out.println("MRI applied for patient: " + patient.getName());
    }

    public void notifyHighBloodPressure(Patient patient) {
        System.out.println("Nurse received high blood pressure warning for patient: " + patient.getName());
        measureBloodPressure(patient);
    }

    private void measureBloodPressure(Patient patient) {
        // Manual blood pressure measurement
        int bloodPressure = 140; // Simulated value
        patient.setBloodPressure(bloodPressure);
    }
}
