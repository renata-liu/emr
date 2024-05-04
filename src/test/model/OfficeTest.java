package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class OfficeTest {

    private Office o1;
    private Patient p1;
    private Patient p2;
    private Patient p3;
    private Patient p4;
    private Patient p5;

    @BeforeEach
    void setUp() {
        o1 = new Office("UBC Health Clinic");
        p1 = new Patient("Griffin", "Heyes", 19900618, 123456789);
        p2 = new Patient("Rachel", "Gray", 20001215, 472847294);
        p3 = new Patient("Jia Yi", "Lo", 19820517, 938471627);
        p4 = new Patient("Phillip", "Gallagher", 19980725, 738945698);
        p5 = new Patient("Natasha", "Fei", 19720912, 972645367);
    }

    @Test
    void constructorTest() {
        assertEquals(o1.getName(), "UBC Health Clinic");
        assertEquals(o1.getAllPatients().size(), 0);
    }

    @Test
    void addPatientTest() {
        o1.addPatient(p1);
        assertEquals(o1.getAllPatients().size(), 1);
        o1.addPatient(p2);
        o1.addPatient(p3);
        assertEquals(o1.getAllPatients().size(), 3);
    }

    @Test
    void findPatientTest() {
        o1.addPatient(p1);
        o1.addPatient(p2);
        assertTrue(o1.findPatient(123456789));
        assertTrue(o1.findPatient(472847294));
        assertFalse(o1.findPatient(938471627));
    }

    @Test
    void getPatientTest() {
        o1.addPatient(p1);
        o1.addPatient(p2);
        o1.addPatient(p5);
        assertEquals(o1.getPatient(123456789), p1);
        assertEquals(o1.getPatient(472847294), p2);
        assertEquals(o1.getPatient(972645367), p5);
        assertEquals(o1.getPatient(972645360), null);
    }

    @Test
    void getNumPatientsTest() {
        o1.addPatient(p1);
        o1.addPatient(p2);
        o1.addPatient(p5);
        assertEquals(o1.getNumPatients(), 3);
    }

    @Test
    void alphabetizeByLastTest() {
        ArrayList<Patient> temp = new ArrayList<>();

        o1.addPatient(p1);
        o1.addPatient(p2);
        o1.addPatient(p4);
        o1.addPatient(p5);

        temp.add(p5);
        temp.add(p4);
        temp.add(p2);
        temp.add(p1);

        assertEquals(o1.alphabetizeByLast(), temp);
    }

    @Test
    void removePatientTest() {
        o1.addPatient(p1);
        o1.addPatient(p2);
        o1.addPatient(p4);
        o1.addPatient(p5);
        o1.removePatient(123456789);
        assertEquals(o1.getAllPatients().size(), 3);
    }

}
