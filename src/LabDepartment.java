// Factory Pattern - LabDepartment class
class LabDepartment implements DepartmentFactory {
    private static LabDepartment instance;

    private LabDepartment() {
    }

    public static LabDepartment getInstance() {
        if (instance == null) {
            instance = new LabDepartment();
        }
        return instance;
    }

    @Override
    public void applyProcedures(Patient patient) {
        System.out.println("Hema blood test and gastro blood test applied for patient: " + patient.getName());
    }
}
