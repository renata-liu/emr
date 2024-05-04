package persistence;

import model.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkPatient(String first, String last, int birthday, int phn, ArrayList<Appointment> appointments, Patient p) {
        assertEquals(first, p.getFirst());
        assertEquals(last, p.getLast());
        assertEquals(birthday, p.getBirthday());
        assertEquals(phn, p.getPhn());
        assertEquals(appointments, p.getAppointments());
    }

}
