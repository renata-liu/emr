package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatientTest {

    private Patient p1;
    private Appointment a1;
    private Appointment a2;

    @BeforeEach
    void setUp() {
        p1 = new Patient("Griffin", "Heyes", 19900618, 123456789);
        a1 = new Appointment("March", 1, 2024, "13:30", "Dr. Hung", false);
        a2 = new Appointment("March", 14, 2024, "10:20", "Dr. Hung", true);
    }

    @Test
    void constructorTest() {
        assertEquals(p1.getFirst(), "Griffin");
        assertEquals(p1.getLast(), "Heyes");
        assertEquals(p1.getBirthday(), 19900618);
        assertEquals(p1.getPhn(), 123456789);
    }

    @Test
    void setPhoneTest() {
        p1.setPhone("604-564-0099");
        assertEquals(p1.getPhone(), "604-564-0099");
    }

    @Test
    void setEmailTest() {
        p1.setEmail("gheyes@gmail.com");
        assertEquals(p1.getEmail(), "gheyes@gmail.com");
    }

//    @Test
//    void getAgeTest() {
//        assertEquals(p1.getAge(), 33);
//    }

    @Test
    void getFirstTest() {
        assertEquals(p1.getFirst(), "Griffin");
    }

    @Test
    void getLastTest() {
        assertEquals(p1.getLast(), "Heyes");
    }

    @Test
    void getBirthdayTest() {
        assertEquals(p1.getBirthday(), 19900618);
    }

    @Test
    void getPhnTest() {
        assertEquals(p1.getPhn(), 123456789);
    }

    @Test
    void getPhoneTest() {
        assertEquals(p1.getPhone(), "no phone number on file");
    }

    @Test
    void getEmailTest() {
        assertEquals(p1.getEmail(), "no email on file");
    }

    @Test
    void getAppointmentsTest() {
        ArrayList<Appointment> test = new ArrayList<>();
        p1.bookAppointment(a1);
        test.add(a1);
        assertEquals(p1.getAppointments(), test);
        p1.bookAppointment(a2);
        test.add(a2);
        assertEquals(p1.getAppointments(), test);
    }
}