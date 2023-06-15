import exceptions.TooManyPatients;
import hospital.Doctor;
import hospital.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.print.Doc;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TestPatient {

    private static final String number = "0000000000";
    private Patient person;

    @BeforeEach
    void runBefore() {
        person = new Patient(number);
    }

    @Test
    void testGetCareCard() {
        Patient person = new Patient("0000000000");
        assertEquals(person.getCareCard().length(),10);
    }

    @Test
    void testInit() {
        assertEquals(person.getCareCard().length(),10);
    }

    @Test
    void testEqualCard() {
        Patient otherPerson = new Patient("0000000000");
        assertTrue(person.numEqual(otherPerson.getCareCard()));
    }

    @Test
    void testUnequalCard() {
        Patient otherPerson = new Patient("1234567890");
        assertFalse(person.numEqual(otherPerson.getCareCard()));
    }

    @Test
    void testHealingStatus() {
        person.setHealingStatus(true);
        assertTrue(person.getHealingStatus());
        person.setHealingStatus(false);
        assertFalse(person.getHealingStatus());
    }

    @Test
    void testAddTreatment() {
        assertEquals(0, person.treatmentNeeded.size());
        person.addTreatment(person, "brain surgery");
        String treatment = person.treatmentNeeded.get(person);
        System.out.println(treatment);
        assertEquals(1, person.treatmentNeeded.size());
    }

    @Test
    void testEquals() {
        assertTrue(person.equals(person));
        Patient personTwo = null;
        assertFalse(person.equals(personTwo));
        Patient personThree = new Patient("0000000000");
        assertTrue(person.equals(personThree));
    }

    @Test
    void testAddDoctor() {
        Doctor doctor = new Doctor("Mike");
        try {
            person.addDoctor(doctor);
        } catch (TooManyPatients tooManyPatients) {
            fail();
        }
        assertEquals(doctor,person.getDoctor());
        assertTrue(doctor.containPatient(person));
    }

    @Test
    void testRemoveDoctor() throws TooManyPatients{
        Doctor doctor = new Doctor("Mike");
        person.addDoctor(doctor);
        person.removeDoctor();
        assertNull(person.getDoctor());
        assertFalse(doctor.containPatient(person));
    }

    @Test
    void testToString() throws TooManyPatients {
        person.setHealingStatus(true);
        person.addDoctor(new Doctor("Amy"));
        assertEquals("Patient:\n"
                + "\b\b\b\bcareCard:" + "0000000000" + '\n'
                + "\b\b\b\bdoctor:" + "Amy" + '\n'
                + "\b\b\b\bhealingStatus:" + "true",person.toString());
    }


}
