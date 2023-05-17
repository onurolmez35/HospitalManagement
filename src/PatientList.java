import java.util.List;

// Iterator Pattern - PatientList class
class PatientList implements PatientIterator {
    private List<Patient> patients;
    private int position;

    public PatientList(List<Patient> patients) {
        this.patients = patients;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < patients.size();
    }

    @Override
    public Patient next() {
        if (hasNext()) {
            Patient patient = patients.get(position);
            position++;
            return patient;
        }
        return null;
    }
}
