
import exceptions.TooManyPatients;
import hospital.Patient;
import hospital.Doctor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TestDoctor {
    private static final String name = "Amy";
    private Doctor doctor;

    @BeforeEach
    void runBefore() {
        doctor = new Doctor(name);
    }


    @Test
    void testInit() {
        assertEquals(doctor.getName().length(),3);
        assertEquals(0,doctor.getNumberHealed());
    }

    @Test
    void testSetName() {
        doctor.setName("Matt");
        assertEquals("Matt",doctor.getName());
        doctor.setName("Amy");
    }

    @Test
    void testGetName() {
        assertEquals("Amy", doctor.getName());
    }

//    @Test
//    void testNext() throws IOException {
//        doctor.nextPatient();
//        assertEquals(doctor.nextPatient().getCareCard(),"0000000000");
//        assertTrue(doctor.duringTreatment());
//    }

    @Test
    void testAddPatient() {
        Patient patient = new Patient("5649874123");
        try {
            doctor.addPatient(patient);
        } catch (TooManyPatients tooManyPatients) {
            fail("Not expecting this exception");
        }
        assertTrue(doctor.containPatient(patient));
        assertEquals(doctor,patient.getDoctor());
    }

    @Test
    void testAddWhenFull() {
        for (int i=0;i<7;i++) {
            String flag = "name" + i;
            try {
                doctor.addPatient(new Patient(flag));
            } catch (TooManyPatients tooManyPatients) {
                fail();
            }
        }
        try {
            doctor.addPatient(new Patient("shouldFail"));
            fail();
        } catch (TooManyPatients tooManyPatients) {
            System.out.println("Success");
        }

    }


    @Test
    void testRemovePatient() throws TooManyPatients {
        Patient patient = new Patient("0000000000");
//        doctor.addPatient(patient);
        doctor.removePatient(patient);
        assertFalse(doctor.containPatient(patient));
        assertNull(patient.getDoctor());
    }

    @Test
    void testSearchPatient() throws TooManyPatients {
        Patient patient = new Patient("0000000000");
        assertNull(doctor.searchPatient("9845919881"));
        //doctor.addPatient(patient);
        assertEquals(patient,doctor.searchPatient("0000000000"));
    }

    @Test
    void testUpdate() {
        assertEquals(0,doctor.getNumberHealed());
        Patient patient = new Patient("5649812633");
        patient.setHealingStatus(true);
        assertTrue(doctor.update(patient));
        assertEquals(1,doctor.getNumberHealed());
        patient.setHealingStatus(false);
        assertFalse(doctor.update(patient));
    }


}
