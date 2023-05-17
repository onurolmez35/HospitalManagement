// Factory Pattern - RadiologyDepartment class
class RadiologyDepartment implements DepartmentFactory {
    private static RadiologyDepartment instance;

    private RadiologyDepartment() {
    }

    public static RadiologyDepartment getInstance() {
        if (instance == null) {
            instance = new RadiologyDepartment();
        }
        return instance;
    }

    @Override
    public void applyProcedures(Patient patient) {
        System.out.println("Virtual angiography and MRI applied for patient: " + patient.getName());
    }

}