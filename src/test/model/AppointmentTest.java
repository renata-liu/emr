package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppointmentTest {

    private Appointment a1;
    private Appointment a2;

    @BeforeEach
    void setUp() {
        a1 = new Appointment("March", 22, 2024, "09:00", "Dr. Yue", true);
        a2 = new Appointment("June", 1, 2024, "14:20", "Dr. Low", false);
    }

    @Test
    void constructorTest() {
        assertEquals(a1.getMonth(), "March");
        assertEquals(a1.getDay(), 22);
        assertEquals(a1.getYear(), 2024);
        assertEquals(a1.getTime(), "09:00");
        assertEquals(a1.getDoctor(), "Dr. Yue");
        assertTrue(a1.isInPerson());
    }

    @Test
    void getAppointmentDateTest() {
        assertEquals(a1.getAppointmentDate(), "March 22, 2024");
    }

    @Test
    void setReasonTest() {
        a1.setReason("sore throat");
        assertEquals(a1.getReason(), "sore throat");
    }

    @Test
    void getAppointmentDetailsTest() {
        a2.setReason("cough");
        String a1Details = "In-person appointment with Dr. Yue on March 22, 2024";
        String a2Details = "Virtual appointment with Dr. Low on June 1, 2024 for cough";
        assertEquals(a1Details, a1.getAppointmentDetails());
        assertEquals(a2Details, a2.getAppointmentDetails());
    }

    @Test
    void getModeTest() {
        assertEquals("In-person", a1.getMode());
        assertEquals("Virtual", a2.getMode());
    }
}
