import hospital.Doctor;
import hospital.PatientObserver;
import hospital.Subject;
import list.PatientList;
import hospital.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestSubject {
    private Patient patient;
    private Doctor doctor;

    @BeforeEach
    void runBefore() {
        patient = new Patient("0000000000");
        doctor = new Doctor("Amy");
    }

    @Test
    void testAddObserver() {
        patient.addObserver(doctor);
        assertTrue(patient.getPatientObservers().contains(doctor));
        patient.addObserver(doctor);
        assertEquals(1,patient.getPatientObservers().size());
    }

    @Test
    void testRemoveObserver() {
        patient.addObserver(doctor);
        patient.removeObserver(doctor);
        assertEquals(0,patient.getPatientObservers().size());
    }

    @Test
    void testNotify() {
        patient.setHealingStatus(true);
        patient.notify(patient);
    }
}
