class Physician extends HospitalStaff {
    private static Physician instance;

    private Physician(String name) {
        super(name);
    }

    public static Physician getInstance(String name) {
        if (instance == null) {
            instance = new Physician(name);
        }
        return instance;
    }

    @Override
    public void performDuties(Patient patient) {
        System.out.println("Physician consulting patient: " + patient.getName());
    }
}
