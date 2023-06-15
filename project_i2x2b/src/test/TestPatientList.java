
import exceptions.TooManyPatients;
import list.PatientList;
import hospital.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TestPatientList {
    private PatientList newPatientList;

    @BeforeEach
    void runBefore() {
        newPatientList = new PatientList();

    }

    @Test
    void testGet() throws TooManyPatients {
        newPatientList.add(new Patient("0000000000"));
        assertEquals("0000000000",newPatientList.get(0).getCareCard());
    }

    @Test
    void testElementCount() throws IOException, TooManyPatients {
        assertEquals(newPatientList.getElementCount(),0);
        Patient person = new Patient("0000000000");
        newPatientList.add(person);
        assertEquals(newPatientList.getElementCount(),1);
    }

    @Test
    void testInit() {
        assertEquals(newPatientList.getElementCount(),0);
    }

    @Test
    void testAddNotTen() throws IOException, TooManyPatients {
        Patient personT = new Patient("123456789");
        assertFalse(newPatientList.add(personT));
        Patient newPerson = new Patient("123456789012");
        assertFalse(newPatientList.add(newPerson));
        assertEquals(newPatientList.getElementCount(),0);
    }

    @Test
    void testAddTen() throws IOException, TooManyPatients {
        Patient person = new Patient("1234567890");
        assertTrue(newPatientList.add(person));
        assertEquals(newPatientList.getElementCount(),1);
    }


    @Test
    void testContain() throws IOException, TooManyPatients {
        Patient person = new Patient("1234567890");
        Patient fakePerson = new Patient("0000000000");
        newPatientList.add(person);
        assertTrue(newPatientList.contains(person.getCareCard()));
        assertFalse(newPatientList.contains(fakePerson.getCareCard()));
    }

    @Test
    void testExpectTooMany() throws IOException {
        newPatientList.setElementCount(10);
        Patient person = new Patient("0000000000");
        try {
            newPatientList.add(person);
            fail("should be thrown");
        } catch (TooManyPatients tooManyPatients) {
            //tooManyPatients.printStackTrace();
        }
    }

    @Test
    void testNotExpectingTooMany() throws IOException {
        try {
            newPatientList.add(new Patient("0000000000"));
        } catch (TooManyPatients tooManyPatients) {
            fail("Not expecting throws");
        }
    }

//    @Test
//    void testExpectingLength() throws IOException {
//        try {
//            newPatientList.contains("1");
//            fail("Should been thrown");
//        } catch (CareCardLength careCardLength) {
//            careCardLength.printStackTrace();
//        }
//    }
//
//    @Test
//    void testNotExpectingLength() throws IOException {
//        try {
//            newPatientList.contains("0000000000");
//        } catch (CareCardLength careCardLength) {
//            fail("Not expecting throws");
//        }
//    }

}
