package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Office o = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyOffice() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyOffice.json");
        try {
            Office o = reader.read();
            assertEquals("Office 1", o.getName());
            assertEquals(0, o.getNumPatients());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralOffice() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralOffice.json");
        try {
            Office o = reader.read();
            assertEquals("Office 1", o.getName());
            List<Patient> patients = o.getAllPatients();
            assertEquals(2, patients.size());

            checkPatient("Renata", "Liu", 20050308, 123456789, new ArrayList<>(), patients.get(0));
            checkPatient("Vivian", "Chow", 19900812, 234567890, new ArrayList<>(), patients.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}