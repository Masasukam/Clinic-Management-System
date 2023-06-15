package ui;

import exceptions.TooManyPatients;
import hospital.Doctor;
import hospital.Patient;
import network.Network;
import ui.tools.MainView;

import javax.print.Doc;
import java.io.IOException;
import java.util.Scanner;


public class Client {
    private Scanner scanner = new Scanner(System.in);
    // private PatientList system = new PatientList();
    private Doctor surgeon = new Doctor("Mike");

    private Client() throws IOException {
        Network network = new Network();
        network.readWeb();
        welcome();
        choice();
    }

    private void choice() throws IOException {
        while (true) {
            menu();
            String choice = scanner.nextLine();

            if (choice.equals("e")) {
                addToList();
            } else if (choice.equals("t")) {
                removeFromList();
            } else if (choice.equals("s")) {
                searchForPatient();
            } else if (choice.equals("m")) {
                modifyPatient();
            } else if (choice.equals("x")) {
                System.out.println("Exiting");
                break;
            }
        }
    }

    private void addToList() throws IOException {
        System.out.println("Please enter a ten-digit care card number:");
        String careCard = scanner.nextLine();
        try {
            if (surgeon.searchPatient(careCard) == null) {
                surgeon.addPatient(new Patient(careCard));
                //surgeon.saveList(careCard);
                System.out.println("Patient added successfully");
            } else {
                System.out.println("Patient already exists in the list");
            }
        } catch (TooManyPatients tooManyPatients) {
            System.out.println("The patient list is currently full, please visit other clinics!");
        }
    }

    private void removeFromList() {
        System.out.println("Please enter the Care Card number you like to remove:");
        String careCard = scanner.nextLine();
        Patient removePatient = new Patient(careCard);
        surgeon.removePatient(removePatient);
        System.out.println("Patient removed from the list successfully");
    }

    private void searchForPatient() {
        System.out.println("Please enter the Care Card number you like to search:");
        String careCard = scanner.nextLine();
        Patient search = new Patient(careCard);
        Patient result = surgeon.searchPatient(careCard);
        if (result == null) {
            System.out.println("The patient you searched is not in our client.");
        } else if (result.getHealingStatus()) {
            System.out.println("The patient is cured!");
        } else {
            System.out.println("The patient is still having treatment");
        }
    }

    private void modifyPatient() {
        System.out.println("Please enter the Care Card number of the patient you like to modify:");
        String careCard = scanner.nextLine();
        System.out.println("Enter his/her status: ");
        System.out.println("Enter 1 for cured, 2 for not cured");
        String status = scanner.nextLine();
        Patient patient = surgeon.searchPatient(careCard);
        if (status.equals("1")) {
            patient.setHealingStatus(true);
            System.out.println("Patient's status modified.");
        } else {
            patient.setHealingStatus(false);
            System.out.println("Patient's status modified.");
        }
    }

    private void welcome() {
        System.out.println("\n---Welcome to the walk-in clinic patient system!---\n");
    }


    private void menu() {
        System.out.println("To enter a new patient into the system:\t  enter: e");
        System.out.println("To remove a patient from the system:\t  enter: t");
        System.out.println("To modify a patient's record:\t\t\t  enter: m");
        System.out.println("To search for an existing patient:\t\t  enter: s");
        System.out.println("To exit the system:\t\t\t\t\t\t  enter: x");
        System.out.println("\nPick your choice:");
    }

    public static void main(String[] args) throws IOException {
//        welcome();
//        menu();

        Doctor doctor = new Doctor("Amy");
        doctor.loadList();
        new MainView(doctor);
    }
}
