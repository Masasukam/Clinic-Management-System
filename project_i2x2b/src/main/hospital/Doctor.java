package hospital;

import exceptions.TooManyPatients;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Doctor implements PatientObserver, Save, Load {
    private String name;
    private List<Patient> patients = new ArrayList<>();
    private int numberHealed;
    private String fileLocation = "./data/inputFile.txt";
    private static Doctor doctor;

    public Doctor(String name) {
        this.name = name;
        numberHealed = 0;
        loadList();
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFile(String newLocation) {
        this.fileLocation = newLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //EFFECTS: if patients contains the patient, return true, otherwise return false
    public boolean containPatient(Patient patient) {
        return patients.contains(patient);
    }

    public List<Patient> getPatients() {
        return patients;
    }

    //REQUIRES: Patient's care card number must be 10-digit
    //MODIFIES: this
    //EFFECTS: If patient list is full, throw TooMany Patients exception. If the list
    //         does not have the patient, add the patient to the list and assign to the doctor
    public void addPatient(Patient patient) throws TooManyPatients {
        if (patients.size() > 10) {
            throw new TooManyPatients();
        }
        if (!patients.contains(patient)) {
            patients.add(patient);
            //saveList(patient.getCareCard());
            patient.addDoctor(this);
        }
    }

    //MODIFIES: this
    //EFFECTS: remove the patient from the list if he is already in the list
    public void removePatient(Patient patient) {
        if (patients.contains(patient)) {
            patients.remove(patient);
            patient.removeDoctor();
        }
    }

    //REQUIRES: The care card number must be 10-digit
    //EFFECTS: return the patient if he is in the list, otherwise return null
    public Patient searchPatient(String careCard) {
        for (Patient person : patients) {
            if (person.getCareCard().equals(careCard)) {
                return person;
            }
        }
        return null;
    }

    //MODIFIES: this
    //EFFECTS: get updated if the healing status of the patient is changed, increment numberHealed
    //         by one if the healing status changes to true. Returns the healing status of the patient
    @Override
    public boolean update(Patient patient) {
        if (patient.getHealingStatus()) {
            System.out.println("Patient " + patient.getCareCard() + " is healed!!");
            numberHealed++;
        } else {
            System.out.println("Patient " + patient.getCareCard() + " still need further treatment");
        }

        return patient.getHealingStatus();
    }

    public int getNumberHealed() {
        //System.out.println("I have healed " + numberHealed + " patients");
        return numberHealed;
    }

    //EFFECTS: Loads the data from the input file to the patient list
    @Override
    public void loadList() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(
                    fileLocation));
            String line = reader.readLine();
            while (line != null) {
                String[] splitedLine = line.split(";");
                setName(splitedLine[0]);
                Patient person = new Patient(splitedLine[1]);
                person.setHealingStatus(splitedLine[2].equals("true"));
                addPatient(person);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException | TooManyPatients e) {
            //
        }
    }

    //EFFECTS: save the patient list and store the information into the data file
    @Override
    public void saveList() {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(fileLocation,false));
            for (Patient p: this.patients) {
                writer.write(this.name + ";" + p.getCareCard() + ";" + (p.getHealingStatus() ? "true" : "false"));
                writer.write("\n");
            }
            writer.close();

        } catch (IOException e) {
            //
        }
    }
}
