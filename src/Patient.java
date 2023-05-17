// Observer Pattern - Patient class
class Patient implements Observer {
    private String name;
    private int bloodPressure;

    public Patient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBloodPressure(int bloodPressure) {
        this.bloodPressure = bloodPressure;
        checkBloodPressure();
    }

    private void checkBloodPressure() {
        if (bloodPressure > 140) {
            Nurse.getInstance("Nurse Johnson").notifyHighBloodPressure(this);
        }
    }

    @Override
    public void update(int bloodPressure) {
        setBloodPressure(bloodPressure);
    }
}
