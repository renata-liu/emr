package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Office o = new Office("Office 1");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyOffice() {
        try {
            Office o = new Office("Office 1");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyOffice.json");
            writer.open();
            writer.write(o);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyOffice.json");
            o = reader.read();
            assertEquals("Office 1", o.getName());
            assertEquals(0, o.getNumPatients());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterOffice() {
        try {
            Office o = new Office("Office 1");

            Patient p1 = new Patient("Renata", "Liu", 20050308, 123456789);
            Patient p2 = new Patient("Vivian", "Chow", 19900812, 234567890);

            o.addPatient(p1);
            o.addPatient(p2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralOffice.json");
            writer.open();
            writer.write(o);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralOffice.json");
            o = reader.read();
            assertEquals("Office 1", o.getName());
            ArrayList<Patient> patients = o.getAllPatients();

            assertEquals(2, patients.size());
            checkPatient("Renata", "Liu", 20050308, 123456789, new ArrayList<>(), patients.get(0));
            checkPatient("Vivian", "Chow", 19900812, 234567890, new ArrayList<>(), patients.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}