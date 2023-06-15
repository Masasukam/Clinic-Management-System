package hospital;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private List<PatientObserver> patientObservers;

    public Subject() {
        patientObservers = new ArrayList<>();
    }

    public List<PatientObserver> getPatientObservers() {
        return patientObservers;
    }

    //MODIFIES: this
    //EFFECTS: add the observer into the registered list if the observer is not present in the list
    public void addObserver(PatientObserver patientObserver) {
        if (!patientObservers.contains(patientObserver)) {
            patientObservers.add(patientObserver);
        }
    }

    //MODIFIES: this
    //EFFECTS: remove the observer from the observer list
    public void removeObserver(PatientObserver patientObserver) {
        patientObservers.remove(patientObserver);
    }

    //EFFECTS: Notifies the doctor if the patient's healing status is changed
    public void notify(Patient patient) {
        for (PatientObserver observer : patientObservers) {
            observer.update(patient);
        }
    }
}
