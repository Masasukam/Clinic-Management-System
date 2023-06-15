package hospital;

import exceptions.TooManyPatients;

import java.util.*;

public class Patient extends Subject {
    private String careCard;
    private Doctor doctor;
    public Map<Patient, String> treatmentNeeded = new HashMap<>();
    private boolean healingStatus;


    //EFFECTS: Instantiates a new patient object with the patient's care card number
    public Patient(String careCard) {
        this.careCard = careCard;
    }


    //EFFECTS: Returns the care card number of the patient
    public String getCareCard() {
        return careCard;
    }

    public void setHealingStatus(Boolean healingStatus) {
        this.healingStatus = healingStatus;
    }

    public boolean getHealingStatus() {
        return healingStatus;
    }


    public Doctor getDoctor() {
        return doctor;
    }

    //MODIFIES: this
    //EFFECTS: Add needed treatment to the related patient
    public void addTreatment(Patient patient, String treatment) {
        treatmentNeeded.put(patient, treatment);
    }

    //MODIFIES: this
    //EFFECTS: Assign this patient to the doctor if not assigned previously
    public void addDoctor(Doctor doctor) throws TooManyPatients {
        if (this.doctor == null) {
            this.doctor = doctor;
            doctor.addPatient(this);
        }
    }

    //EFFECTS: return all the information of the patient
    @Override
    public String toString() {
        return "Patient:\n"
                + "\b\b\b\bcareCard:" + careCard + '\n'
                + "\b\b\b\bdoctor:" + doctor.getName() + '\n'
                + "\b\b\b\bhealingStatus:" + healingStatus;
    }

    //MODIFIES: this
    //EFFECTS: remove the doctor if the patient is assigned to a doctor
    public void removeDoctor() {
        if (this.doctor != null) {
            Doctor flag = this.doctor;
            this.doctor = null;
            flag.removePatient(this);
        }
    }

    //EFFECTS: Checks the care card number of two patient objects, returns true if they are equal,
    //         otherwise returns false
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Patient patient = (Patient) o;
        return careCard.equals(patient.careCard);
    }

    //EFFECTS: Hashcode the care card number
    @Override
    public int hashCode() {
        //return Objects.hash(careCard);
        return careCard.hashCode();
    }

    //EFFECTS: Compare if two care card numbers are equal
    //         Returns true if equal, else returns false
    public boolean numEqual(String acareCard) {
        return this.careCard.equals(acareCard);
    }

}
