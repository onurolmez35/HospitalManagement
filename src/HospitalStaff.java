// Base class - HospitalStaff
abstract class HospitalStaff {
    private String name;

    public HospitalStaff(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void performDuties(Patient patient);
}
