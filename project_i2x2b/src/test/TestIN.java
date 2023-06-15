import exceptions.TooManyPatients;
import list.PatientList;
import hospital.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestIN {

    private PatientList newPatientList;

    @BeforeEach
    void runBefore() {
        newPatientList = new PatientList();

    }

//    @Test
//    void testSave() throws TooManyPatients, IOException {
//        Patient personA = new Patient("0000000000");
//        Patient personB = new Patient("1111111111");
//        Patient personC = new Patient("2222222222");
//        newPatientList.add(personA);
//        newPatientList.add(personB);
//
//        newPatientList.add(personC);
//        BufferedReader reader;
//        reader = new BufferedReader(new FileReader(
//                "./data/inputFile.txt"));
//        String line = reader.readLine();
//        assertEquals(line,personA.getCareCard());
//        line = reader.readLine();
//        assertEquals(line,personB.getCareCard());
//        line = reader.readLine();
//        assertEquals(line,personC.getCareCard());
//
//    }
//
//    @Test
//    void testLoadNotExpecting() {
//        newPatientList.load();
//
//
//        Patient personA = new Patient("0000000000");
//        Patient personB = new Patient("1111111111");
//        Patient personC = new Patient("2222222222");
//
//        assertEquals(newPatientList.get(0).getCareCard(),personA.getCareCard());
//        assertEquals(newPatientList.get(1).getCareCard(),personB.getCareCard());
//        assertEquals(newPatientList.get(2).getCareCard(),personC.getCareCard());
//
//    }

    @Test
    void testFileLocation() {
        assertEquals("E:\\UBC\\CPSC210\\project_i2x2b\\data/inputFile.txt",newPatientList.getFileLocation());
        newPatientList.setFile("DiskC");
        assertEquals("DiskC",newPatientList.getFileLocation());
    }
}
