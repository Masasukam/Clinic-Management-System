package list;

import java.io.*;
import java.util.LinkedList;

import exceptions.TooManyPatients;
import hospital.Patient;

public class PatientList {
    private int elementCount;
    //public static final int MAX_SIZE = 10;
    private LinkedList<Patient> patients;
    private String fileLocation;

    //EFFECTS: Create a new list of patients, the initial size is zero
    public PatientList() {
        elementCount = 0;
        patients = new LinkedList<>();
        this.fileLocation = "E:\\UBC\\CPSC210\\project_i2x2b\\data/inputFile.txt";
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFile(String newLocation) {
        this.fileLocation = newLocation;
    }


    //EFFECTS: Returns the size of the list
    public int getElementCount() {
        return elementCount;
    }

    public int setElementCount(int count) {
        elementCount = count;
        return elementCount;
    }

    //REQUIRES: Patient must not already in the list
    //EFFECTS: new patient is added to the list, the size of the list is incremented by 1
    public boolean add(Patient pp) throws TooManyPatients {
        if (elementCount >= 10) {
            throw new TooManyPatients();
        }
//        if (contains(pp.getCareCard())) {
//            System.out.println("");
//        }
        if (pp.getCareCard().length() != 10) {
            System.out.println("Your care card should be ten digit");
            return false;
        }
        patients.add(pp);
        elementCount++;
        return true;
    }

    //EFFECTS: Check the patient's existence in the list using his care card number
    public boolean contains(String card) {
        for (Patient patient : patients) {
            if (patient.getCareCard().equals(card)) {
                return true;
            }
        }
        return false;
    }

    public Patient get(int i) {
        return patients.get(i);
    }

    //EFFECTS: Print the care card number of each patient in the list
//    public void printPatient() {
//        for (Patient each: patients) {
//            System.out.println(each.getCareCard());
//        }
//    }
}
