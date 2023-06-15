import exceptions.TooManyPatients;
import hospital.Doctor;
import list.PatientList;
import hospital.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestLoad {
    private Doctor doctor;

    @BeforeEach
    void runBefore() {
        doctor = new Doctor("Mike");
    }

    @Test
    void testGetFile() {
        assertEquals("./data/inputFile.txt",doctor.getFileLocation());
        doctor.setFile("DiskC");
        assertEquals("DiskC",doctor.getFileLocation());
    }

    @Test
    void testSave() throws TooManyPatients, IOException {
        Patient personA = new Patient("0000000000");
        Patient personB = new Patient("1111111111");
        Patient personC = new Patient("2222222222");
        doctor.addPatient(personA);
        doctor.addPatient(personB);
        doctor.addPatient(personC);
        doctor.saveList();
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(
                "./data/inputFile.txt"));
        String line = reader.readLine();
        String[] splitedLine = line.split(";");
        assertEquals(splitedLine[1],personA.getCareCard());
        line = reader.readLine();
        splitedLine = line.split(";");
        assertEquals(splitedLine[1],personB.getCareCard());
        line = reader.readLine();
        splitedLine = line.split(";");
        assertEquals(splitedLine[1],personC.getCareCard());
    }

    @Test
    void testLoad() {
        doctor.loadList();

        Patient personA = new Patient("0000000000");
        Patient personB = new Patient("1111111111");
        Patient personC = new Patient("2222222222");

        assertEquals(doctor.getPatients().get(0).getCareCard(),personA.getCareCard());
        assertEquals(doctor.getPatients().get(1).getCareCard(),personB.getCareCard());
        assertEquals(doctor.getPatients().get(2).getCareCard(),personC.getCareCard());
    }
}
